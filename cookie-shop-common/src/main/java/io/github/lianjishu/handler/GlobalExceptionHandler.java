package io.github.lianjishu.handler;

import io.github.lianjishu.common.Result;
import io.github.lianjishu.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理。
 *
 * <p>两条路径，靠异常类型区分（不靠 URL、不靠请求头猜）：</p>
 * <ol>
 *     <li>{@link BusinessException} 只由「数据接口」主动抛出，因此固定序列化为 {@link Result}；</li>
 *     <li>其它异常一律记日志并渲染 {@code error} 视图，页面接口保持原有的视图语义。</li>
 * </ol>
 *
 * <p><b>历史问题</b>：本类此前位于 {@code cookie-shop-common} 却未被任何
 * {@code @ComponentScan} 覆盖（根容器扫 service/interceptor/properties，子容器只扫 controller），
 * 因此从未注册；且它返回的 {@code error} 视图当时没有对应的 error.jsp。
 * 现阶段已在 {@code WebMvcConfig} 中补扫 {@code io.github.lianjishu.handler} 并补齐 error.jsp。</p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 业务异常：可预期失败，不打堆栈（避免日志污染），错误码与文案来自 {@code ResultCode}
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result<Object> handleBusinessException(BusinessException e) {
        log.debug("业务异常 code={} message={}", e.getResultCode().getCode(), e.getMessage());
        return Result.fail(e.getResultCode(), e.getData());
    }

    /**
     * 其余异常：属于 bug 或环境问题，打印完整堆栈供排查，页面只看到友好提示
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest req) {
        log.error("未处理异常 uri={}", req.getRequestURI(), e);
        return "error";
    }
}