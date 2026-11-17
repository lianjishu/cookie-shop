package io.github.lianjishu.controller;

import io.github.lianjishu.entity.Order;
import io.github.lianjishu.entity.OrderItem;
import io.github.lianjishu.entity.Review;
import io.github.lianjishu.entity.User;
import io.github.lianjishu.service.OrderService;
import io.github.lianjishu.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 替代旧 ReviewServlet (/review)：订单评价
 */
@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/review")
    public String reviewShow(@RequestParam(value = "orderId", required = false) Integer orderId,
                             HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/user_login";
        }
        if (orderId == null) {
            return "redirect:/order_list";
        }
        if (reviewService.hasReview(orderId)) {
            return "redirect:/order_list?msg=already_reviewed";
        }
        Order order = orderService.getOrderDetail(orderId);
        if (order == null || order.getUser_id() != user.getId()) {
            return "redirect:/order_list";
        }
        List<OrderItem> items = orderService.getOrderItemsWithGoodsName(orderId);
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "review";
    }

    @PostMapping("/review")
    public String reviewAdd(@RequestParam(value = "orderId", required = false) Integer orderId,
                            @RequestParam(value = "rating", required = false) Integer rating,
                            @RequestParam(value = "content", required = false) String content,
                            HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/user_login";
        }
        if (orderId == null || rating == null || content == null || content.trim().isEmpty()) {
            return "redirect:/order_list";
        }
        if (reviewService.hasReview(orderId)) {
            return "redirect:/order_list?msg=already_reviewed";
        }
        List<OrderItem> items = orderService.getOrderItems(orderId);
        if (items == null || items.isEmpty()) {
            return "redirect:/order_list?msg=review_fail";
        }

        Review review = new Review();
        review.setContent(content.trim());
        review.setRating(rating);
        review.setOrder_id(orderId);
        review.setGoods_id(items.get(0).getGoods_id());
        review.setUser_id(user.getId());

        boolean success = reviewService.addReview(review);
        return "redirect:/order_list?msg=" + (success ? "review_success" : "review_fail");
    }
}
