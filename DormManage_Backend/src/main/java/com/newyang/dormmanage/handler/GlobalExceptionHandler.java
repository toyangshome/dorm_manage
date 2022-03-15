package com.newyang.dormmanage.handler;

import com.newyang.dormmanage.commons.ResStatus;
import com.newyang.dormmanage.commons.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.List;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 0:32
 */

@RestControllerAdvice
@Slf4j
@ResponseStatus(code = HttpStatus.OK)
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public Response<Void> handler (RuntimeException e) {
        log.error("RuntimeException ======> {}", e.getCause().getMessage());
        return Response.failure(ResStatus.FAILURE.code(), "服务器错误!");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response<Void> handler (HttpMessageNotReadableException e) {
        log.error("Message Parse Error: ======> {}", e.getMessage());
        return Response.failure(ResStatus.JSON_PARSE_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public Response<Void> handler (NullPointerException e) {
        log.error("Null Pointer Error: ======> {}", e.getMessage());
        return Response.failure(ResStatus.FAILURE.code(),"服务器错误!");
    }

    @ExceptionHandler(ValidationException.class)
    public Response<Void> handler (ValidationException e) {
        log.error("Param Validate Error ======> {}", e.getMessage());
        return Response.failure(ResStatus.PARAM_TYPE_BIND_ERROR.code(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<Void> handler (MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        String message = "";
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            if (errors.size() > 0) {
                FieldError fieldError = (FieldError) errors.get(0);
                message = fieldError.getDefaultMessage();
            }
        }
        log.error("MethodArgumentNotValidException ======> {}", message);
        return Response.failure(ResStatus.PARAM_TYPE_BIND_ERROR.code(), "".equals(message) ? "参数非法" : message);
    }
}
