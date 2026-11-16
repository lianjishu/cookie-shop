package io.github.lianjishu.controller.admin;

import io.github.lianjishu.domain.Goods;
import io.github.lianjishu.domain.Page;
import io.github.lianjishu.service.GoodsService;
import io.github.lianjishu.service.OrderService;
import io.github.lianjishu.service.RecommendService;
import io.github.lianjishu.service.TypeService;
import io.github.lianjishu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 替代旧 AdminIndexServlet(/admin/index) 和商品管理 6 个 Servlet：
 * goods_list / goods_add / goods_delete / goods_edit / goods_editshow / goods_recommend
 */
@Controller
public class AdminGoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private RecommendService recommendService;

    // ---------- 后台首页 ----------

    @RequestMapping("/admin/index")
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        boolean justLoggedIn = false;
        if (session != null && session.getAttribute("justLoggedIn") != null) {
            justLoggedIn = (Boolean) session.getAttribute("justLoggedIn");
            session.removeAttribute("justLoggedIn");
        }
        model.addAttribute("justLoggedIn", justLoggedIn);
        if ("true".equals(request.getParameter("fromCookie"))) {
            model.addAttribute("justLoggedIn", true);
        }

        model.addAttribute("goodsCount", goodsService.selectCount());
        model.addAttribute("orderCount", orderService.getOrderCount(0));
        model.addAttribute("userCount", userService.selectCount());
        return "admin/index";
    }

    // ---------- 商品列表 ----------

    @RequestMapping("/admin/goods_list")
    public String goodsList(@RequestParam(value = "type", defaultValue = "0") int type,
                            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                            Model model) {
        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        Page p = goodsService.getGoodsRecommendPage(type, pageNumber);
        if (p.getTotalPage() == 0) {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        model.addAttribute("p", p);
        model.addAttribute("type", type);
        return "admin/goods_list";
    }

    // ---------- 添加商品（含图片上传，替代旧 Part 循环逻辑） ----------

    @GetMapping("/admin/goods_add")
    public String goodsAddShow(Model model) {
        model.addAttribute("typeList", typeService.GetAllType());
        return "admin/goods_add";
    }

    @PostMapping("/admin/goods_add")
    public String goodsAdd(@RequestParam("name") String name,
                           @RequestParam("price") float price,
                           @RequestParam("intro") String intro,
                           @RequestParam("stock") int stock,
                           @RequestParam("typeid") int typeId,
                           MultipartHttpServletRequest request) throws IOException {
        Goods g = new Goods();
        g.setName(name);
        g.setPrice(price);
        g.setIntro(intro);
        g.setStock(stock);
        g.setType_id(typeId);
        saveUploadedImages(request, g);
        goodsService.insert(g);
        return "forward:/admin/goods_list";
    }

    // ---------- 删除商品 ----------

    @RequestMapping("/admin/goods_delete")
    public String goodsDelete(@RequestParam("id") int id) {
        goodsService.delete(id);
        return "forward:/admin/goods_list";
    }

    // ---------- 编辑商品 ----------

    @RequestMapping("/admin/goods_editshow")
    public String goodsEditShow(@RequestParam("id") int id, Model model) {
        model.addAttribute("g", goodsService.getGoodsById(id));
        return "admin/goods_edit";
    }

    @PostMapping("/admin/goods_edit")
    public String goodsEdit(@RequestParam("id") int id,
                            @RequestParam("name") String name,
                            @RequestParam("price") float price,
                            @RequestParam("intro") String intro,
                            @RequestParam("cover") String cover,
                            @RequestParam("image1") String image1,
                            @RequestParam("image2") String image2,
                            @RequestParam("stock") int stock,
                            @RequestParam("typeid") int typeId,
                            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                            @RequestParam(value = "type", defaultValue = "0") int type,
                            MultipartHttpServletRequest request) throws IOException {
        Goods g = new Goods();
        g.setId(id);
        g.setName(name);
        g.setPrice(price);
        g.setIntro(intro);
        g.setCover(cover);
        g.setImage1(image1);
        g.setImage2(image2);
        g.setStock(stock);
        g.setType_id(typeId);
        // 新上传的图片覆盖隐藏域中的旧图片路径
        saveUploadedImages(request, g);
        goodsService.update(g);
        return "forward:/admin/goods_list?pageNumber=" + pageNumber + "&type=" + type;
    }

    // ---------- 推荐位管理 ----------

    @RequestMapping("/admin/goods_recommend")
    public void goodsRecommend(@RequestParam(value = "method", required = false) String method,
                               @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "typeTarget", required = false) String typeTarget,
                               HttpServletRequest request,
                               jakarta.servlet.http.HttpServletResponse response) throws IOException {
        if (method != null && !method.isEmpty() && typeTarget != null && !typeTarget.isEmpty()
                && id != null && !id.isEmpty()) {
            if ("add".equals(method)) {
                recommendService.addRecommend(Integer.parseInt(typeTarget), Integer.parseInt(id));
            } else if ("remove".equals(method)) {
                recommendService.deleteRecommend(Integer.parseInt(typeTarget), Integer.parseInt(id));
            }
        }
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer != null ? referer : request.getContextPath() + "/admin/goods_list");
    }

    /**
     * 处理 cover / image1 / image2 三个文件域，保存到 picture 目录并回填路径
     */
    private void saveUploadedImages(MultipartHttpServletRequest request, Goods g) throws IOException {
        for (Map.Entry<String, List<MultipartFile>> entry : request.getMultiFileMap().entrySet()) {
            for (MultipartFile file : entry.getValue()) {
                if (file == null || file.isEmpty() || file.getOriginalFilename() == null) {
                    continue;
                }
                String fileName = file.getOriginalFilename();
                fileName = fileName.substring(fileName.lastIndexOf("."));
                fileName = "/" + new Date().getTime() + fileName;
                String path = request.getServletContext().getRealPath("/picture") + fileName;
                file.transferTo(new java.io.File(path));
                switch (entry.getKey()) {
                    case "cover":
                        g.setCover("/picture" + fileName);
                        break;
                    case "image1":
                        g.setImage1("/picture" + fileName);
                        break;
                    case "image2":
                        g.setImage2("/picture" + fileName);
                        break;
                }
            }
        }
    }
}
