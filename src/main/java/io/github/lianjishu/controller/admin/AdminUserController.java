package io.github.lianjishu.controller.admin;

import io.github.lianjishu.domain.Page;
import io.github.lianjishu.domain.User;
import io.github.lianjishu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 替代旧用户管理 7 个 Servlet：
 * /admin/user_list、/admin/user_add、/admin/user_delete、/admin/user_edit、
 * /admin/user_editshow、/admin/user_reset、/admin/user_logout
 */
@Controller
public class AdminUserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/admin/user_list")
    public String userList(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber, Model model) {
        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        Page p = userService.getUserPage(pageNumber);
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        model.addAttribute("p", p);
        return "admin/user_list";
    }

    @PostMapping("/admin/user_add")
    public String userAdd(@ModelAttribute User user, Model model) {
        if (userService.register(user)) {
            model.addAttribute("msg", "客户添加成功！");
            return "forward:/admin/user_list";
        }
        model.addAttribute("failMsg", "用户名或邮箱重复，请重新填写！");
        model.addAttribute("u", user);
        return "admin/user_add";
    }

    @RequestMapping("/admin/user_delete")
    public String userDelete(@RequestParam("id") int id, Model model) {
        boolean isSuccess = userService.delete(id);
        if (isSuccess) {
            model.addAttribute("msg", "客户删除成功");
        } else {
            model.addAttribute("failMsg", "客户有下的订单，请先删除该客户下的订单，再来删除客户！");
        }
        return "forward:/admin/user_list";
    }

    @RequestMapping("/admin/user_editshow")
    public String userEditShow(@RequestParam("id") int id, Model model) {
        model.addAttribute("u", userService.selectById(id));
        return "admin/user_edit";
    }

    @PostMapping("/admin/user_edit")
    public String userEdit(@ModelAttribute User user) {
        userService.updateUserAddress(user);
        return "forward:/admin/user_list";
    }

    @PostMapping("/admin/user_reset")
    public String userReset(@ModelAttribute User user) {
        userService.updatePwd(user);
        return "forward:/admin/user_list";
    }

    @RequestMapping("/admin/user_logout")
    public String userLogout() {
        return "redirect:/user_logout";
    }
}
