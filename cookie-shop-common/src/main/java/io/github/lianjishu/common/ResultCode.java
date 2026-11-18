package io.github.lianjishu.common;

/**
 * 统一响应里的错误码枚举。
 *
 * <p>编号沿用 placeorder 的分段习惯：200 成功、4xxx 调用方问题、5xxx 服务端/业务问题。
 * 这些数字只出现在响应体的 {@code code} 字段，不改变 HTTP 状态码。</p>
 */
public enum ResultCode {

    /** 成功 */
    SUCCESS(200, "success"),

    /** 参数缺失或不合法 */
    PARAM_ERROR(4001, "参数错误"),

    /** 会话里没有登录用户 */
    NOT_LOGIN(4002, "请先登录"),

    /** 目标记录不存在，或存在但不属于当前用户（越权操作统一此码，避免泄露存在性） */
    CART_ITEM_NOT_FOUND(4003, "购物车项不存在或无权操作"),

    /** 未预期的服务端异常（详情只进日志，不回前端） */
    SYSTEM_ERROR(5000, "系统异常"),

    /** 业务操作明确失败（当前实现不会返回 false，保留以备用） */
    CART_OPERATION_FAIL(5001, "购物车操作失败");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}