package io.github.lianjishu.exception;

import io.github.lianjishu.common.ResultCode;

/**
 * 业务异常（可预期的失败，不是程序 bug）。
 *
 * <p>继承 {@link RuntimeException}，避免业务代码被迫写 try-catch；
 * 由 {@code GlobalExceptionHandler} 统一转成 {@code Result} 返回给前端。</p>
 *
 * <p>只应由「数据接口」抛出：页面接口仍用返回视图名/重定向的方式表达失败，
 * 这样全局处理器才能保证「业务异常 ⇒ JSON，其它异常 ⇒ 错误页」这个不变量。</p>
 */
public class BusinessException extends RuntimeException {

    private final ResultCode resultCode;

    /** 可选：随响应一起返回的业务上下文数据 */
    private final Object data;

    public BusinessException(ResultCode resultCode) {
        this(resultCode, null);
    }

    public BusinessException(ResultCode resultCode, Object data) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
        this.data = data;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public Object getData() {
        return data;
    }
}