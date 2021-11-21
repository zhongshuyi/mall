package com.mall.common.core.domain;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Data;

/**
 * 通用返回对象.
 *
 * @param <T> 需要返回的类型
 * @author 钟舒艺
 **/
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@SuppressWarnings("unused")
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = -2339115200498941889L;
    /**
     * 状态码.
     */
    private int code;

    /**
     * 提示信息.
     */
    private String message;

    /**
     * 数据封装.
     */
    private T result;

    /**
     * 类型.
     */
    private String type;

    protected CommonResult() {
    }

    protected CommonResult(final int code, final String message, final T data, final String type) {
        this.code = code;
        this.message = message;
        this.result = data;
        this.type = type;
    }

    /**
     * 成功返回结果.
     *
     * @return 通用返回类
     */
    public static CommonResult<Void> success() {
        return new CommonResult<>(HttpStatus.HTTP_OK, "操作成功", null, "success");
    }


    /**
     * 成功返回结果.
     *
     * @param data 获取的数据
     * @param <T>  要返回的类型
     * @return 通用返回
     */
    public static <T> CommonResult<T> success(final T data) {
        return new CommonResult<>(HttpStatus.HTTP_OK, "操作成功", data, "success");
    }

    /**
     * 成功返回结果.
     *
     * @param data    获取的数据
     * @param message 提示信息
     * @param <T>     要返回的类型
     * @return 通用返回
     */
    public static <T> CommonResult<T> success(final T data, final String message) {
        return new CommonResult<>(HttpStatus.HTTP_OK, message, data, "success");
    }

    /**
     * 成功返回结果.
     *
     * @param message 提示信息
     * @param <T>     要返回的类型
     * @return 通用返回
     */
    public static <T> CommonResult<T> success(final String message) {
        return new CommonResult<>(HttpStatus.HTTP_OK, message, null, "success");
    }

    /**
     * 失败返回类型.
     *
     * @return 通用返回类
     */
    public static CommonResult<Void> failed() {
        return new CommonResult<>(HttpStatus.HTTP_INTERNAL_ERROR, "操作失败", null, "error");
    }

    /**
     * 失败返回结果.
     *
     * @param msg 信息
     * @return 通用返回结果
     */
    public static CommonResult<Void> failed(final String msg) {
        return new CommonResult<>(HttpStatus.HTTP_INTERNAL_ERROR, msg, null, "error");
    }


    /**
     * 失败返回结果.
     *
     * @param code 状态码
     * @param msg  提示信息
     * @return 通用返回类型
     */
    public static CommonResult<Void> failed(final Integer code, final String msg) {
        return new CommonResult<>(code, msg, null, "error");
    }

    /**
     * 失败返回结果.
     *
     * @param code 状态码
     * @param msg  信息
     * @param data 数据
     * @param <T>  数据类型
     * @return 通用返回类
     */
    public static <T> CommonResult<T> failed(final Integer code, final String msg, final T data) {
        return new CommonResult<>(code, msg, data, "error");
    }

    /**
     * 参数验证失败返回结果.
     *
     * @return 通用返回结果
     */
    public static CommonResult<Void> validateFailed() {
        return failed(HttpStatus.HTTP_NOT_FOUND, "参数校验失败");
    }

    /**
     * 参数验证失败返回结果.
     *
     * @param message 提示信息
     * @return 通用返回结果
     */
    public static CommonResult<Void> validateFailed(final String message) {
        return new CommonResult<>(HttpStatus.HTTP_NOT_FOUND, message, null, "error");
    }

    /**
     * 未登录返回结果.
     *
     * @return 通用返回结果
     */
    public static CommonResult<Void> unauthorized() {
        return new CommonResult<>(HttpStatus.HTTP_UNAUTHORIZED, "未登录或已过期", null, "error");
    }

    /**
     * 未登录返回结果.
     *
     * @param msg 信息
     * @return 通用返回结果
     */
    public static CommonResult<Void> unauthorized(final String msg) {
        return new CommonResult<>(HttpStatus.HTTP_UNAUTHORIZED, "未登录或已过期", null, "error");
    }

    /**
     * 未授权返回结果.
     *
     * @param msg 信息
     * @return 通用返回类
     */
    public static CommonResult<Void> forbidden(final String msg) {
        return new CommonResult<>(HttpStatus.HTTP_FORBIDDEN, msg, null, "error");
    }
}
