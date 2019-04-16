package com.xz.comtroller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;

import com.xz.exception.AuthorizationException;
import com.xz.exception.SystemException;
import com.xz.vo.entity.Result;
import com.xz.vo.entity.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.validation.UnexpectedTypeException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器全局异常 Created by wubei on 2017/5/4.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ModelAndView handleValidException(BindException e) {
        Map error = new HashMap();
        List<FieldError> fieldErrors = e.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            if (error.containsKey(fieldError.getField()))
                continue;
            error.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        FastJsonJsonView view = new FastJsonJsonView();
        Result result = new Result(ResultCode.PARAM, error);
        logger.error("ERROR:400 - result: {}", result.toString(), e);
        view.setAttributesMap((Map<String, ?>)JSONObject.toJSON(result));
        return new ModelAndView(view);
    }

    /**
     * POST requestBody提交异常
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map error = new HashMap();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            if (error.containsKey(fieldError.getField()))
                continue;
            error.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        FastJsonJsonView view = new FastJsonJsonView();
        Result result = new Result(ResultCode.PARAM, error);
        logger.error("ERROR:400 - result: {}", result.toString(), e);
        view.setAttributesMap((Map<String, ?>)JSONObject.toJSON(result));
        return new ModelAndView(view);
    }

    /**
     * JSON参数异常
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(JSONException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerJSONException(JSONException e) {
        FastJsonJsonView view = new FastJsonJsonView();
        Result result = new Result(ResultCode.PARAM.getCode(), "数据结构错误", null);
        logger.warn("ERROR:400 - result: {},{}", result.toString(), e.getMessage());
        view.setAttributesMap((Map<String, ?>)JSONObject.toJSON(result));
        return new ModelAndView(view);
    }

    /**
     * 数据类型异常
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerUnexpectedTypeException(UnexpectedTypeException e) {
        FastJsonJsonView view = new FastJsonJsonView();
        Result result = new Result(ResultCode.PARAM.getCode(), "数据类型不匹配", null);
        logger.warn("ERROR:400 - result: {},{}", result.toString(), e.getMessage());
        view.setAttributesMap((Map<String, ?>)JSONObject.toJSON(result));
        return new ModelAndView(view);
    }

    /**
     * token 失效
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ModelAndView handleTokenException(AuthorizationException e) {
        FastJsonJsonView view = new FastJsonJsonView();
        Result result = new Result(ResultCode.AUTH);
        logger.warn("ERROR:401 - result: {}", result.toString());
        view.setAttributesMap((Map<String, ?>)JSONObject.toJSON(result));
        return new ModelAndView(view);
    }

//    @ExceptionHandler(TradeException.class)
//    // 因前端接收问题改为200异常
//    // @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public ModelAndView handleTradeException(TradeException e) {
//        FastJsonJsonView view = new FastJsonJsonView();
//        Result result = new Result(ResultCode.ERROR.getCode(), e.getMessage(), null);
//        logger.warn("ERROR:503 - result: {}", result.toString());
//        view.setAttributesMap((Map<String, ?>)JSONObject.toJSON(result));
//        return new ModelAndView(view);
//    }

    /**
     * 自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ModelAndView handleSystemException(SystemException e) {
        FastJsonJsonView view = new FastJsonJsonView();
        Result result = new Result(ResultCode.ERROR.getCode(), e.getMessage(), null);
        logger.warn("ERROR:503 - result: {}", result.toString());
        view.setAttributesMap((Map<String, ?>)JSONObject.toJSON(result));
        return new ModelAndView(view);
    }


    @ExceptionHandler({Exception.class})
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ModelAndView handleException(Exception e) {
        FastJsonJsonView view = new FastJsonJsonView();
        Result result = new Result(ResultCode.UNKNOWN);
        if (logger.isErrorEnabled()) {
            logger.error("ERROR:500 - result: {}", result.toString(), e);
        }
        view.setAttributesMap((Map<String, ?>)JSONObject.toJSON(result));
        return new ModelAndView(view);
    }

    @ExceptionHandler({ServletException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ModelAndView handleNotFoundException(Exception e) {
        FastJsonJsonView view = new FastJsonJsonView();
        Result result = new Result(ResultCode.NOTFOUND);
        if (logger.isErrorEnabled()) {
            logger.error("ERROR:404", result.toString(), e);
        }
        view.setAttributesMap((Map<String, ?>)JSONObject.toJSON(result));
        return new ModelAndView(view);
    }

}
