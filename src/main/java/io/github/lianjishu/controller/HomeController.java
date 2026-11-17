package io.github.lianjishu.controller;

import io.github.lianjishu.entity.Goods;
import io.github.lianjishu.entity.Type;
import io.github.lianjishu.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 替代旧 IndexServlet (/index)：首页推荐与分类
 */
@Controller
public class HomeController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/index")
    public String index(org.springframework.ui.Model model) {
        List<Type> types = goodsService.getAllTypes();
        model.addAttribute("typeList", types);

        List<Goods> allGoods = goodsService.getAllGoods();
        model.addAttribute("goodsList", allGoods);

        return "index";
    }
}
