package io.github.lianjishu.interceptor;

import io.github.lianjishu.entity.User;
import io.github.lianjishu.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 替代旧项目的 CartCountFilter：把当前用户购物车数量放入 request 属性 cartCount
 */
@Component
public class CartCountInterceptor implements HandlerInterceptor {

    private final CartService cartService;

    @Autowired
    public CartCountInterceptor(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                request.setAttribute("cartCount", cartService.getCartCount(user.getId()));
            }
        }
        return true;
    }
}
