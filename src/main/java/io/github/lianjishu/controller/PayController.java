package io.github.lianjishu.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import io.github.lianjishu.entity.Order;
import io.github.lianjishu.service.OrderService;
import io.github.lianjishu.utils.AlipayConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * 替代旧 GoPayServlet (/goPay)：跳转支付宝沙箱支付
 */
@Controller
public class PayController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/goPay")
    public void goPay(@RequestParam(value = "orderId", required = false) Integer orderId,
                      HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (orderId == null) {
            response.sendRedirect(request.getContextPath() + "/order_list");
            return;
        }
        Order order = orderService.getOrderDetail(orderId);
        if (order == null) {
            response.sendRedirect(request.getContextPath() + "/order_list");
            return;
        }

        // 1. 获得初始化的 AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.GATEWAY_URL,
                AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY,
                "json",
                AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.SIGN_TYPE
        );
        // 2. 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        // 3. 商户订单号（格式: ORDER_{orderId}）
        String outTradeNo = "ORDER_" + order.getId();
        // 4. 订单总金额
        String totalAmount = String.valueOf(order.getTotal());
        // 5. 订单名称
        String subject = "订单结算";
        // 封装参数（JSON 格式）
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"total_amount\":\"" + totalAmount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        // 6. 请求
        String result = "";
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        // 7. 直接将完整的表单 html 输出到页面
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        response.getWriter().println(result);
    }
}
