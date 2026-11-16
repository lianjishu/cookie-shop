package io.github.lianjishu.controller;

import io.github.lianjishu.domain.Goods;
import io.github.lianjishu.domain.Page;
import io.github.lianjishu.domain.Review;
import io.github.lianjishu.domain.Type;
import io.github.lianjishu.service.GoodsService;
import io.github.lianjishu.service.RecommendService;
import io.github.lianjishu.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 替代旧 GoodsListServlet(/goods_list)、GoodsDetailServlet(/goods_detail)、
 * GoodsSearchServlet(/goods_search)、GoodsRecommendServlet(/goods_recommend)
 */
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private RecommendService recommendService;

    @RequestMapping("/goods_list")
    public String goodsList(@RequestParam(value = "type_id", required = false) Integer typeId,
                            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                            Model model) {
        int pageSize = 12;

        List<Type> types = goodsService.getAllTypes();
        model.addAttribute("typeList", types);

        Page page;
        if (typeId != null) {
            model.addAttribute("typeId", typeId);
            page = goodsService.getGoodsByTypeIdByPage(typeId, pageNum, pageSize);
            model.addAttribute("currentType", goodsService.getTypeById(typeId));
        } else {
            page = goodsService.getGoodsByPage(pageNum, pageSize);
        }
        model.addAttribute("page", page);
        return "goods_list";
    }

    @RequestMapping("/goods_detail")
    public String goodsDetail(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id == null) {
            return "redirect:/goods_list";
        }
        Goods goods = goodsService.getGoodsDetail(id);
        if (goods == null) {
            return "redirect:/goods_list";
        }
        model.addAttribute("goods", goods);
        model.addAttribute("type", goodsService.getTypeById(goods.getType_id()));
        model.addAttribute("typeList", goodsService.getAllTypes());

        List<Review> reviews = reviewService.getReviewsByGoodsId(id);
        double avgRating = reviewService.getAverageRating(id);
        model.addAttribute("reviews", reviews);
        model.addAttribute("avgRating", Math.round(avgRating * 10) / 10.0);
        model.addAttribute("reviewCount", reviewService.getReviewCount(id));
        return "goods_detail";
    }

    @RequestMapping("/goods_search")
    public String goodsSearch(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return "redirect:/goods_list";
        }
        keyword = keyword.trim();
        model.addAttribute("keyword", keyword);
        model.addAttribute("goodsList", goodsService.searchGoods(keyword));
        model.addAttribute("typeList", goodsService.getAllTypes());
        return "goods_search";
    }

    @RequestMapping("/goods_recommend")
    public String goodsRecommend(@RequestParam(value = "type", defaultValue = "1") int type, Model model) {
        model.addAttribute("recommendList", recommendService.getRecommendByType(type));
        return "goodsrecommend_list";
    }
}
