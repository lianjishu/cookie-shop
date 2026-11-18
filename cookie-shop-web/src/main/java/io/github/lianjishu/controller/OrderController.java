package io.github.lianjishu.controller;

import io.github.lianjishu.entity.Cart;
import io.github.lianjishu.entity.Order;
import io.github.lianjishu.entity.User;
import io.github.lianjishu.service.CartService;
import io.github.lianjishu.service.OrderService;
import io.github.lianjishu.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 替代旧 OrderSubmitServlet(/order_submit)、OrderListServlet(/order_list)、
 * OrderDetailServlet(/order_detail)、OrderCancelServlet(/order_cancel)
 */
@Controller
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping("/order_submit")
    public String orderSubmit(@RequestParam("name") String name,
                              @RequestParam("phone") String phone,
                              @RequestParam("address") String address,
                              @RequestParam(value = "paytype", defaultValue = "1") int paytype,
                              HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/user_login";
        }

        List<Cart> cartList = cartService.getCartByUserId(user.getId());
        if (cartList == null || cartList.isEmpty()) {
            model.addAttribute("msg", "购物车为空");
            return "goods_cart";
        }

        float total = 0;
        for (Cart cart : cartList) {
            total += cart.getGoods().getPrice() * cart.getAmount();
        }

        Order order = new Order();
        order.setTotal(total);
        order.setAmount(cartList.size());
        order.setStatus(1);
        order.setPaytype(paytype);
        order.setName(name);
        order.setPhone(phone);
        order.setAddress(address);
        order.setUser_id(user.getId());

        int orderId = orderService.createOrder(order, cartList);

        if (orderId > 0) {
            cartService.clearCart(user.getId());
            if (paytype == 2) {
                return "redirect:/goPay?orderId=" + orderId;
            }
            model.addAttribute("orderId", orderId);
            model.addAttribute("paytype", paytype);
            return "order_success";
        }
        model.addAttribute("msg", "订单创建失败");
        return "goods_cart";
    }

    @RequestMapping("/order_list")
    public String orderList(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/user_login";
        }

        List<Order> orderList = orderService.getOrdersByUserId(user.getId());
        Map<Integer, Boolean> reviewedMap = new HashMap<>();
        if (orderList != null) {
            for (Order order : orderList) {
                reviewedMap.put(order.getId(), reviewService.hasReview(order.getId()));
            }
        }
        model.addAttribute("orderList", orderList);
        model.addAttribute("reviewedMap", reviewedMap);
        return "order_list";
    }

    /**
     * 订单详情入口。
     *
     * <p>工程里没有 order_detail.jsp，直接返回视图名会 forward 到不存在的资源并 404；
     * 这里统一回订单列表（列表页已包含订单全部字段）。将来补上详情页时，
     * 在本方法里恢复「校验归属 → 查明细 → 返回视图」的逻辑即可。</p>
     */
    @RequestMapping("/order_detail")
    public String orderDetail() {
        return "redirect:/order_list";
    }

    @GetMapping("/order_cancel")
    public String orderCancel(@RequestParam(value = "orderId", required = false) Integer orderId,
                              HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/user_login";
        }
        if (orderId == null) {
            return "redirect:/order_list";
        }
        Order order = orderService.getOrderDetail(orderId);
        if (order != null && order.getUser_id() == user.getId() && order.getStatus() == 1) {
            orderService.updateOrderStatus(orderId, 0);
        }
        return "redirect:/order_list";
    }
}
