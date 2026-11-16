package io.github.lianjishu.interceptor;

import io.github.lianjishu.domain.User;
import io.github.lianjishu.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 替代旧项目的 AdminAuthFilter：拦截 /admin/**，
 * 未登录管理员时尝试用 username Cookie 自动登录，失败则跳转登录页
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession(false);
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("user");
        }

        if (user == null || !user.isIsadmin()) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("username".equals(cookie.getName())) {
                        user = userService.loginByUsername(cookie.getValue());
                        if (user != null && user.isIsadmin()) {
                            HttpSession newSession = request.getSession(true);
                            newSession.setAttribute("user", user);
                            newSession.setAttribute("justLoggedIn", true);
                            return true;
                        }
                    }
                }
            }
            response.sendRedirect(request.getContextPath() + "/user_login.jsp");
            return false;
        }
        return true;
    }
}
