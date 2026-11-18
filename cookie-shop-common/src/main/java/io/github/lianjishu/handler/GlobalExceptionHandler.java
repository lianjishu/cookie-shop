package io.github.lianjishu.handler;

import io.github.lianjishu.common.Result;
import io.github.lianjishu.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.ErrorResponse;
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
     * 参数类型转换失败（如 {@code ?orderId=abc}）：Spring 6.1 的
     * {@code MethodArgumentTypeMismatchException} 并未实现 {@link ErrorResponse}，
     * 不单独拦就会落到下面的通用分支被当成 500，这里显式回 400。
     */
    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatch(TypeMismatchException e, HttpServletRequest req, HttpServletResponse resp) {
        log.warn("参数类型错误 uri={} message={}", req.getRequestURI(), e.getMessage());
        return errorView(req, resp, 400);
    }

    /**
     * 其余异常：分两类，状态码如实回传，页面都渲染 {@code error} 视图。
     *
     * <ul>
     *     <li>实现了 {@link ErrorResponse} 的异常属于「客户端问题」（400 缺参数、404 路径不存在、
     *         405 方法不支持、415 媒体类型不对…），用它们自带的状态码 + WARN 日志，不冒充 500；</li>
     *     <li>其它异常属于程序 bug 或环境问题，固定 500 + ERROR 堆栈。</li>
     * </ul>
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest req, HttpServletResponse resp) {
        if (e instanceof ErrorResponse errorResponse) {
            log.warn("请求错误 uri={} status={} message={}",
                    req.getRequestURI(), errorResponse.getStatusCode().value(), e.getMessage());
            return errorView(req, resp, errorResponse.getStatusCode().value());
        }
        log.error("未处理异常 uri={}", req.getRequestURI(), e);
        return errorView(req, resp, 500);
    }

    /**
     * 统一渲染错误视图，并把状态码同时写进响应头与页面（error.jsp 读取 errorStatus）
     */
    private String errorView(HttpServletRequest req, HttpServletResponse resp, int status) {
        resp.setStatus(status);
        req.setAttribute("errorStatus", status);
        return "error";
    }
}