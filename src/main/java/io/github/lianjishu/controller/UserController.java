package io.github.lianjishu.controller;

import io.github.lianjishu.domain.User;
import io.github.lianjishu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 替代旧 UserLoginServlet(/user_login)、UserLogoutServlet(/user_logout)、
 * UserRegisterServlet(/user_register)、UserChangePwdServlet(/user_changepwd)、
 * UserChangeAddressServlet(/user_changeaddress)
 */
@Controller
public class UserController {

    private static final int COOKIE_MAX_AGE = 7 * 24 * 60 * 60; // 7 天记住登录

    @Autowired
    private UserService userService;

    @PostMapping("/user_login")
    public String login(@RequestParam("ue") String ue,
                        @RequestParam("password") String password,
                        @RequestParam(value = "remember", required = false) String remember,
                        HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = userService.login(ue, password);
        if (user == null) {
            model.addAttribute("failMsg", "用户名、邮箱或者密码错误，请重新登录！");
            return "user_login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("justLoggedIn", true);
        if ("true".equals(remember)) {
            jakarta.servlet.http.Cookie userCookie = new jakarta.servlet.http.Cookie(
                    "username", URLEncoder.encode(user.getUsername(), StandardCharsets.UTF_8));
            userCookie.setMaxAge(COOKIE_MAX_AGE);
            userCookie.setPath(request.getContextPath());
            response.addCookie(userCookie);
        }
        return "user_center";
    }

    @GetMapping("/user_login")
    public String loginByCookie(HttpServletRequest request) {
        jakarta.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (jakarta.servlet.http.Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    User user = userService.loginByUsername(cookie.getValue());
                    if (user != null && user.isIsadmin()) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("user", user);
                        session.setAttribute("justLoggedIn", true);
                        return "forward:/admin/index";
                    }
                }
            }
        }
        return null;
    }

    @RequestMapping("/user_logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "redirect:/index";
    }

    @PostMapping("/user_register")
    public String register(@ModelAttribute User user, Model model) {
        if (userService.register(user)) {
            model.addAttribute("msg", "注册成功，请登录！");
            return "user_login";
        }
        model.addAttribute("msg", "用户名或邮箱重复，请重新填写！");
        return "user_register";
    }

    @PostMapping("/user_changepwd")
    public String changePwd(@RequestParam("password") String password,
                            @RequestParam("newPassword") String newPassword,
                            HttpServletRequest request, Model model) {
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null) {
            return "redirect:/user_login.jsp";
        }
        if (!loginUser.getPassword().equals(password)) {
            model.addAttribute("failMsg", "原密码错误！");
            return "user_center";
        }
        loginUser.setPassword(newPassword);
        userService.updatePwd(loginUser);
        request.getSession().setAttribute("user", loginUser);
        model.addAttribute("msg", "密码修改成功！");
        return "user_center";
    }

    @PostMapping("/user_changeaddress")
    public String changeAddress(@RequestParam("name") String name,
                                @RequestParam("phone") String phone,
                                @RequestParam("address") String address,
                                HttpServletRequest request, Model model) {
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null) {
            return "redirect:/user_login.jsp";
        }
        loginUser.setName(name);
        loginUser.setPhone(phone);
        loginUser.setAddress(address);
        userService.updateUserAddress(loginUser);
        request.getSession().setAttribute("user", loginUser);
        model.addAttribute("msg", "收货信息修改成功！");
        return "user_center";
    }
}
