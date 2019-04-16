package com.xz.comtroller;


import com.xz.util.JWTUtil;
import com.xz.vo.entity.Result;
import com.xz.vo.entity.ResultCode;
import com.xz.vo.entity.SysUserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BaseController是所有控制器的基类,封装了常用返回的方法
 *
 * @author wubei
 */
public abstract class BaseController {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected RedisTemplate redisTemplate;
//    @Autowired
//    protected StringRedisTemplate stringRedisTemplate;
//    @Autowired
//    protected JedisPool stringRedisTemplate;//= SpringContextHolder.getBean("jedisPool");

    /**
     * 获得token中的companyId
     */
    protected SysUserBean getSysUser(boolean flag) {
        return JWTUtil.getSysUser(request, redisTemplate, flag);
    }

    /**
     * 强制校验token
     */
    protected String getLoginName() {
        return getSysUser(true).getLoginName();
    }

    /**
     * 非强制校验token
     */
    protected String getSysUserIdByNot() {
        return getSysUser(false).getLoginName();
    }

    /**
     * 校验token是否有效
     */
    protected void validAuth() {
        JWTUtil.getSysUser(request, redisTemplate, true);
    }

    /**
     * 返回定义的成功或失败信息
     *
     * @param code {@link ResultCode}
     * @return {@link Result}
     */
    protected Result<Object> toJson(ResultCode code) {
        return toJson(code, null);
    }

    /**
     * 返回定义的成功或失败信息
     *
     * @param code  {@link ResultCode}
     * @param value 返回的json对象
     * @return {@link Result}
     */
    protected <T> Result<T> toJson(ResultCode code, T value) {
        return new Result<T>(code.getCode(), code.getMessage(), value);
    }

    /**
     * 返回定义的成功或失败信息 不需要全局成功或失败消息时,message传null
     *
     * @param code    {@link ResultCode}
     * @param message 自定义返回的消息
     * @return {@link Result}
     */
    protected Result<Object> toJson(ResultCode code, String message) {
        return toJson(code.getCode(), message == null ? code.getMessage() : message, null);
    }

    /**
     * 返回定义的成功或失败信息 不需要全局成功或失败消息时,message传null
     *
     * @param code    {@link ResultCode}
     * @param message 自定义返回的消息
     * @param value   自定义返回的json对象
     * @return {@link Result}
     */
    protected <T> Result<T> toJson(ResultCode code, String message, T value) {
        return toJson(code.getCode(), message, value);
    }

    /**
     * 返回定义的成功或失败信息
     *
     * @param code    自定义code
     * @param message 自定义返回的消息
     * @param value   自定义返回的json对象
     * @return {@link Result}
     */
    protected <T> Result<T> toJson(int code, String message, T value) {
        return new Result<T>(code, message, value);
    }

}
