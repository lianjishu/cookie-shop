package io.github.lianjishu.controller;

import io.github.lianjishu.common.Result;
import io.github.lianjishu.common.ResultCode;
import io.github.lianjishu.entity.Cart;
import io.github.lianjishu.entity.User;
import io.github.lianjishu.exception.BusinessException;
import io.github.lianjishu.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 替代旧 CartAddServlet(/cart_add)、CartListServlet(/cart_list)。
 *
 * <p>本控制器同时存在两类接口，刻意不做「一刀切」：</p>
 * <ul>
 *     <li><b>数据接口</b>：{@code /cart_add}、{@code POST /cart_list}，返回统一的
 *         {@link Result}（{code, message, data}），由 cart.js 消费；</li>
 *     <li><b>视图接口</b>：{@code GET /cart_list}，仍返回 JSP 视图名。</li>
 * </ul>
 */
@Controller
public class CartController {

    private static final Logger log = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    /**
     * 加入购物车（数据接口）
     *
     * <p>成功：{@code {"code":200,"message":"success","data":{"cartCount":N}}}；
     * 未登录：{@code code=4002}；缺参数：{@code code=4001}；未预期异常：{@code code=5000}。</p>
     */
    @RequestMapping("/cart_add")
    @ResponseBody
    public Result<Map<String, Object>> cartAdd(
            @RequestParam(value = "goods_id", required = false) Integer goodsId,
            @RequestParam(value = "amount", required = false, defaultValue = "1") Integer amount,
            HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new BusinessException(ResultCode.NOT_LOGIN);
        }
        if (goodsId == null) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }

        try {
            if (!cartService.addToCart(user.getId(), goodsId, amount)) {
                throw new BusinessException(ResultCode.CART_OPERATION_FAIL);
            }
            Map<String, Object> data = new HashMap<>();
            data.put("cartCount", cartService.getCartCount(user.getId()));
            return Result.success(data);
        } catch (BusinessException e) {
            throw e; // 交给全局处理器，保持错误码语义
        } catch (RuntimeException e) {
            log.error("cart_add 失败 userId={}, goodsId={}", user.getId(), goodsId, e);
            return Result.fail(ResultCode.SYSTEM_ERROR);
        }
    }

    /**
     * 购物车页面（视图接口）
     */
    @GetMapping("/cart_list")
    public String cartList(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/user_login";
        }
        List<Cart> cartList = cartService.getCartByUserId(user.getId());
        model.addAttribute("cartList", cartList);
        return "goods_cart";
    }

    /**
     * 修改数量 / 删除（数据接口）
     */
    @PostMapping("/cart_list")
    @ResponseBody
    public Result<Void> cartUpdate(@RequestParam(value = "action", required = false) String action,
                                   @RequestParam(value = "cart_id", required = false) Integer cartId,
                                   @RequestParam(value = "amount", required = false) Integer amount,
                                   HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new BusinessException(ResultCode.NOT_LOGIN);
        }
        if (cartId == null) {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }

        try {
            boolean ok;
            if ("update".equals(action) && amount != null) {
                ok = cartService.updateCartAmount(user.getId(), cartId, amount);
            } else if ("delete".equals(action)) {
                ok = cartService.removeFromCart(user.getId(), cartId);
            } else {
                throw new BusinessException(ResultCode.PARAM_ERROR);
            }
            if (!ok) {
                // 0 行 = 这条购物车记录不存在，或不属于当前用户（越权），一码兜住不泄露存在性
                throw new BusinessException(ResultCode.CART_ITEM_NOT_FOUND);
            }
            return Result.success();
        } catch (BusinessException e) {
            throw e;
        } catch (RuntimeException e) {
            log.error("cart_list 操作失败 userId={}, action={}, cartId={}, amount={}",
                    user.getId(), action, cartId, amount, e);
            return Result.fail(ResultCode.SYSTEM_ERROR);
        }
    }
}