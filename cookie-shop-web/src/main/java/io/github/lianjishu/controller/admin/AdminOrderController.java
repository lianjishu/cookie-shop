package io.github.lianjishu.controller.admin;

import io.github.lianjishu.entity.Page;
import io.github.lianjishu.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * 替代旧 AdminOrderList/Status/Delete 3 个 Servlet：
 * /admin/order_list、/admin/order_status、/admin/order_delete
 */
@Controller
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/admin/order_list")
    public String orderList(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                            @RequestParam(value = "status", defaultValue = "0") int status,
                            Model model) {
        pageNumber = Math.max(pageNumber, 1);
        status = Math.max(status, 0);

        Page page = orderService.selectPageByStatus(status, pageNumber);
        model.addAttribute("p", page);
        model.addAttribute("status", status);
        return "admin/order_list";
    }

    @RequestMapping("/admin/order_status")
    public void orderStatus(@RequestParam(value = "status", required = false) Integer status,
                            @RequestParam(value = "id", required = false) Integer orderId,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        if (status != null && orderId != null) {
            orderService.updateStatus(orderId, status);
        }
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer != null ? referer : request.getContextPath() + "/admin/order_list");
    }

    @RequestMapping("/admin/order_delete")
    public void orderDelete(@RequestParam(value = "id", required = false) String id,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        if (id != null && !id.isEmpty()) {
            orderService.deleteOrder(Integer.parseInt(id));
        }
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer != null ? referer : request.getContextPath() + "/admin/order_list");
    }
}
