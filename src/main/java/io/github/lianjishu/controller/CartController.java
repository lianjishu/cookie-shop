package io.github.lianjishu.controller;

import io.github.lianjishu.domain.Cart;
import io.github.lianjishu.domain.User;
import io.github.lianjishu.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 替代旧 CartAddServlet(/cart_add)、CartListServlet(/cart_list)。
 * cart_add/cart_list 的 POST 返回 JSON（与旧版格式一致，cart.js 直接解析）
 */
@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/cart_add")
    public void cartAdd(@RequestParam(value = "goods_id", required = false) Integer goodsId,
                        @RequestParam(value = "amount", required = false, defaultValue = "1") Integer amount,
                        HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            out.print("{\"success\":false,\"message\":\"请先登录\"}");
            return;
        }

        if (goodsId == null) {
            out.print("{\"success\":false,\"message\":\"参数错误\"}");
            return;
        }

        try {
            boolean success = cartService.addToCart(user.getId(), goodsId, amount);
            if (success) {
                int cartCount = cartService.getCartCount(user.getId());
                out.print("{\"success\":true,\"message\":\"添加成功\",\"cartCount\":" + cartCount + "}");
            } else {
                out.print("{\"success\":false,\"message\":\"添加失败\"}");
            }
        } catch (RuntimeException e) {
            String msg = e.getMessage() != null ? e.getMessage().replace("\"", "'") : "未知错误";
            out.print("{\"success\":false,\"message\":\"" + msg + "\"}");
        }
    }

    @GetMapping("/cart_list")
    public String cartList(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/user_login.jsp";
        }
        List<Cart> cartList = cartService.getCartByUserId(user.getId());
        model.addAttribute("cartList", cartList);
        return "goods_cart";
    }

    @PostMapping("/cart_list")
    public void cartUpdate(@RequestParam(value = "action", required = false) String action,
                           @RequestParam(value = "cart_id", required = false) Integer cartId,
                           @RequestParam(value = "amount", required = false) Integer amount,
                           HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if ("update".equals(action) && cartId != null && amount != null) {
            cartService.updateCartAmount(cartId, amount);
            out.print("{\"success\":true}");
        } else if ("delete".equals(action) && cartId != null) {
            cartService.removeFromCart(cartId);
            out.print("{\"success\":true}");
        } else {
            out.print("{\"success\":false,\"message\":\"参数错误\"}");
        }
    }
}
