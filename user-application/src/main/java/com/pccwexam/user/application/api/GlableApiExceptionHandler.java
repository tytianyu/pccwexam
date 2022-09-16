package com.pccwexam.user.application.api;

import com.pccwexam.user.api.dto.ResultBean;
import com.pccwexam.user.application.utils.ResultBeanUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@ControllerAdvice
public class GlableApiExceptionHandler {

    /**
     * 校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultBean<?> exceptionHandler(MethodArgumentNotValidException e) {
        StringBuilder errorMsg = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> errorMsg.append(fieldError.getDefaultMessage()).append("!"));
        log.error(errorMsg.toString(), e);
        return ResultBeanUtil.fail(errorMsg.toString());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBean<?> exceptionHandler(Exception e) {
        log.error("handler exception", e);
        return ResultBeanUtil.fail("Internal Server exception");
    }

}
