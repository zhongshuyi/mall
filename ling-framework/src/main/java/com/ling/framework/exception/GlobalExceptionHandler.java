package com.ling.framework.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.ling.common.core.domain.CommonResult;
import com.ling.common.exception.BusinessErrorException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理程序.
 *
 * @author 钟舒艺
 */
@Slf4j
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 未登录.
     *
     * @param ex NotLoginException
     * @return 通用返回类
     */
    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public CommonResult<Void> notLoginException(
            final NotLoginException ex) {
        log.info("未登录");
        return CommonResult.unauthorized();
    }


    /**
     * 拦截：缺少权限异常.
     *
     * @param e 异常信息.
     * @return 通用返回
     */
    @ExceptionHandler(NotPermissionException.class)
    public CommonResult<Void> handlerException(NotPermissionException e) {
        return CommonResult.failed(HttpStatus.FORBIDDEN.value(), "缺少权限");
    }

    /**
     * 缺少请求参数异常.
     *
     * @param ex HttpMessageNotReadableException
     * @return 通用返回类
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CommonResult<Void> handleHttpMessageNotReadableException(
            final MissingServletRequestParameterException ex) {
        log.error(ex.getMessage(), ex);
        log.error("缺少请求参数，{}", ex.getMessage());
        return CommonResult.failed(cn.hutool.http.HttpStatus.HTTP_BAD_REQUEST, "缺少必要的请求参数");
    }

    /**
     * 参数验证异常.
     *
     * @param ex MethodArgumentNotValidException
     * @return 通用返回类
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CommonResult<Void> parameterValidationException(
            final MethodArgumentNotValidException ex
    ) {
        log.error(ex.getMessage(), ex);
        final BindingResult result = ex.getBindingResult();
        final StringBuilder errorMsg = new StringBuilder();
        if (result.hasErrors()) {
            final List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(error -> errorMsg.append(error.getDefaultMessage()).append("! ;"));
        }
        log.error("参数验证异常,{}", ex.getMessage());
        return CommonResult.failed(cn.hutool.http.HttpStatus.HTTP_BAD_REQUEST, errorMsg.toString());
    }

    /**
     * 空指针异常.
     *
     * @param ex NullPointerException
     * @return 通用返回类
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult<Void> handleTypeMismatchException(final NullPointerException ex) {
        log.error(ex.getMessage(), ex);
        log.error("空指针异常，{}", ex.getMessage());
        return CommonResult.failed(cn.hutool.http.HttpStatus.HTTP_INTERNAL_ERROR, "空指针异常了");
    }

    /**
     * 拦截业务异常,返回业务异常信息.
     *
     * @param ex 异常
     * @return 通用返回
     */
    @ExceptionHandler(BusinessErrorException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public CommonResult<Void> handleBusinessError(final BusinessErrorException ex) {
        log.error(ex.getMessage(), ex.getException());
        return CommonResult.failed(ex.getCode(), ex.getMessage());
    }
}
