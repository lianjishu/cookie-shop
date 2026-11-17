package io.github.lianjishu.common;

/**
 * 统一响应封装。
 *
 * <p>约定：任何「数据接口」（返回 JSON 的接口）响应体永远是 {@code {code, message, data}}，
 * 与 placeorder 工程保持同一契约：
 * <ul>
 *     <li>{@code code = 200}：成功；</li>
 *     <li>{@code code = 4xxx}：调用方问题（参数错误、未登录）；</li>
 *     <li>{@code code = 5xxx}：服务端问题（系统异常、业务操作失败）。</li>
 * </ul>
 *
 * <p><b>注意</b>：业务失败时 HTTP 状态码仍是 200，语义只体现在 {@code code} 字段里
 * （Spring 不会因为返回对象的字段值去改 HTTP 状态码）。前端请断言响应体，不要断言状态码。</p>
 *
 * <p>返回「视图」的页面接口不使用本类，保持 ModelAndView / 视图名返回。</p>
 *
 * @param <T> 业务数据类型
 */
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功并携带业务数据
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功且没有业务数据（data 为 null）
     */
    public static Result<Void> success() {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    /**
     * 失败且没有业务数据
     */
    public static <T> Result<T> fail(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    /**
     * 失败并携带业务上下文数据（例如冲突的库存、失败的商品清单等）
     */
    public static <T> Result<T> fail(ResultCode resultCode, T data) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}