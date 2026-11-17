package io.github.lianjishu.controller.admin;

import io.github.lianjishu.entity.Type;
import io.github.lianjishu.service.TypeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 替代旧 TypeList/Add/Edit/Delete 4 个 Servlet：
 * /admin/type_list、/admin/type_add、/admin/type_edit、/admin/type_delete
 */
@Controller
public class AdminTypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("/admin/type_list")
    public String typeList(HttpServletRequest request, Model model) {
        List<Type> list = typeService.GetAllType();
        model.addAttribute("list", list);
        // 旧逻辑：同步到 ServletContext，供其他页面读取
        request.getServletContext().setAttribute("typeList", list);
        return "admin/type_list";
    }

    @RequestMapping("/admin/type_add")
    public String typeAdd(@RequestParam("name") String name) {
        typeService.insert(new Type(name));
        return "forward:/admin/type_list";
    }

    @RequestMapping("/admin/type_edit")
    public String typeEdit(@RequestParam("id") int id, @RequestParam("name") String name) {
        Type t = new Type(id, name);
        typeService.update(t);
        return "forward:/admin/type_list";
    }

    @RequestMapping("/admin/type_delete")
    public String typeDelete(@RequestParam("id") int id, Model model) {
        boolean isSuccess = typeService.delete(id);
        if (isSuccess) {
            model.addAttribute("msg", "删除成功");
        } else {
            model.addAttribute("failMsg", "类目下包含商品，无法直接删除类目！");
        }
        return "forward:/admin/type_list";
    }
}
