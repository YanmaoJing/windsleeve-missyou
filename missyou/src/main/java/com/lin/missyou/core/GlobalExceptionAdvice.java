package com.lin.missyou.core;

import com.lin.missyou.core.configuration.ExceptionCodeConfiguration;
import com.lin.missyou.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @Autowired
    private ExceptionCodeConfiguration codeConfiguration;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(code= HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest req, Exception e){
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        UnifyResponse message = new UnifyResponse(9999, "服务器异常", method + " " + requestUrl);
        return message;
    }

    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest req, HttpException e){
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        UnifyResponse message = new UnifyResponse(e.getCode(), codeConfiguration.getMessage(e.getCode()), method + " " + requestUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());
        ResponseEntity<UnifyResponse> responseEntity = new ResponseEntity<>(message, headers, httpStatus);
        return responseEntity;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    public UnifyResponse handleBeanValidationException(HttpServletRequest req, MethodArgumentNotValidException e){
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message = formatAllErrorMessages(errors);
        return new UnifyResponse(10001, message, method + " " + requestUrl);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    public UnifyResponse handleConstraintException(HttpServletRequest req, ConstraintViolationException e){
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        String message = e.getMessage();
        for (ConstraintViolation error : e.getConstraintViolations()) {

        }
        return new UnifyResponse(10001, message, method + " " + requestUrl);
    }

    private String formatAllErrorMessages(List<ObjectError> errors){
        StringBuilder errorMsg = new StringBuilder();
        errors.forEach(error ->
                errorMsg.append(error.getDefaultMessage()).append(";")
        );
        return errorMsg.toString();
    }


}
