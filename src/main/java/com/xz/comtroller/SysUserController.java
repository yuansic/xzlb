package com.xz.comtroller;

import com.alibaba.fastjson.JSON;
import com.xz.api.interfaces.ISysUser;
import com.xz.enums.SysUserEnum;
import com.xz.util.DeviceUtils;
import com.xz.util.JWTUtil;
import com.xz.util.RSAUtils;
import com.xz.util.StringUtils;
import com.xz.vo.entity.*;
import com.xz.vo.request.SysUserDeleteRequest;
import com.xz.vo.request.SysUserEnableAndProhibit;
import com.xz.vo.request.SysUserInsertRequest;
import com.xz.vo.request.SysUserQueryRequest;
import com.xz.vo.response.SysUserQueryResponse;
import com.xz.vo.response.SysUserRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.NamingException;
import java.security.PrivateKey;
import java.util.concurrent.TimeUnit;


/**
 * @author yuansc
 * @date 2019/3/1 0001 下午 2:27
 */
@Api("系统用户")
@RestController
@RequestMapping("sysUser")
@Slf4j
public class SysUserController extends BaseController {

    @Autowired
    private ISysUser iSysUser;
    //    @Autowired
//    private LdapUtils ldapUtils;
    @Autowired
    protected RedisTemplate redisTemplate;

    private static final String ADMIN = "admin";

    // token有效时间
    private static final long LASTTIME = 1000 * 60 * 60 * 10;

    @Value("${rsa.private.key}")
    private String rsaPrivateKey;

    @PostMapping("enableAndProhibit")
    @ApiOperation(value = "用户启用和禁用", notes = "用户启用和禁用")
    public Result<String> enableAndProhibit(@Validated @RequestBody SysUserEnableAndProhibit sysUserEnableAndProhibit) {
        log.info("SysUserController.sysUserEnableAndProhibit =====>>>" + JSON.toJSONString(""));
//        SysUserBean sysUser = getSysUser(true);

        SysUserRes sysUserBean = iSysUser.selectSysUserById(sysUserEnableAndProhibit.getId());

        if (sysUserBean == null || StringUtils.isBlank(sysUserBean.getId())) {
            return new Result<>(ResultCode.ERROR.getCode(), "用户无记录。", null);
        }
        if (SysUserEnum.DelFlagEnum.DELETE.getCode().equals(sysUserBean.getDelFlag())) {
            //用户已经删除
            return new Result<>(ResultCode.ERROR.getCode(), "用户已经删除不能操作。", null);
        }
        if (sysUserBean.getLoginFlag().equals(sysUserEnableAndProhibit.getLoginFlag())) {
            return new Result<>(ResultCode.ERROR.getCode(), "用户已是操作状态，请勿重复提交。", null);
        }

        SysUserRes sysUserRes = new SysUserRes();
        sysUserRes.setUpdateBy(sysUserEnableAndProhibit.getId());
        sysUserRes.setId(sysUserBean.getId());
        sysUserRes.setLoginFlag(sysUserEnableAndProhibit.getLoginFlag());
        Integer integer = iSysUser.updateSysUserLoginById(sysUserRes);
        if (integer == 0) {
            return new Result<>(ResultCode.ERROR.getCode(), "操作失败。", null);
        }
        return new Result<>(ResultCode.SUCCESS);
    }

    @PostMapping("deleteSysUser")
    @ApiOperation(value = "用户删除", notes = "用户删除")
    public Result<String> deleteSysUser(@Validated @RequestBody SysUserDeleteRequest sysUserDeleteRequest) {
        log.info("SysUserController.deleteSysUser =====>>>" + JSON.toJSONString(sysUserDeleteRequest));

        SysUserBean sysUser = getSysUser(true);
        sysUserDeleteRequest.setLoginName(sysUser.getLoginName());
        Integer integer = iSysUser.deleteSysUserById(sysUserDeleteRequest);
        if (integer == null || integer == 0) {
            return new Result<>(ResultCode.ERROR.getCode(), "删除失败。", null);
        }
        if (integer == -1) {
            return new Result<>(ResultCode.ERROR.getCode(), "用户已删除。", null);
        }
        return new Result<>(ResultCode.SUCCESS);
    }


    @PostMapping("updateSysUserById")
    @ApiOperation(value = "用户修改", notes = "用户修改")
    public Result<String> updateSysUserById(@Validated @RequestBody SysUserInsertRequest sysUserInsertRequest) {
        log.info("SysUserController.updateSysUserById =====>>>" + JSON.toJSONString(sysUserInsertRequest));

        if (StringUtils.isBlank(sysUserInsertRequest.getId())) {
            return new Result<>(ResultCode.PARAM.getCode(), "ID为空。", null);
        }
        SysUserBean sysUser = getSysUser(true);
//        SysUserInsertRequest sysUserInsertRequest = new SysUserInsertRequest();
//        BeanUtils.copyProperties(sysUserInsertRequest, sysUserInsert);
        String loginName = sysUserInsertRequest.getLoginName();

        sysUserInsertRequest.setLoginNameInsert(loginName);
        sysUserInsertRequest.setLoginName(sysUser.getLoginName());
        sysUserInsertRequest.setLoginName(sysUser.getLoginName());

        Integer integer = iSysUser.updateSysUserById(sysUserInsertRequest);
        if (integer == null || integer == 0) {
            return new Result<>(ResultCode.ERROR.getCode(), "修改失败。", null);
        }
        if (integer == -1) {
            return new Result<>(ResultCode.ERROR.getCode(), "用户已删除。", null);
        }
        return new Result<>(ResultCode.SUCCESS);
    }

    @PostMapping("saveSysUser")
    @ApiOperation(value = "用户新增", notes = "用户新增")
    public Result<String> saveSysUser(@Validated @RequestBody SysUserInsertRequest sysUserInsertRequest) {
        log.info("SysUserController.insertSysUser =====>>>" + JSON.toJSONString(sysUserInsertRequest));

        //获取登陆用户
        SysUserBean sysUser = getSysUser(true);
//        SysUserInsertRequest sysUserInsertRequest = new SysUserInsertRequest();
//        BeanUtils.copyProperties(sysUserInsertRequest, sysUserInsert);
        String loginName = sysUserInsertRequest.getLoginName();
        sysUserInsertRequest.setLoginNameInsert(loginName);
        sysUserInsertRequest.setLoginName(sysUser.getLoginName());
        sysUserInsertRequest.setLoginName(sysUser.getLoginName());
        Integer integer = iSysUser.insertSysUser(sysUserInsertRequest);
        if (integer == null || integer == 0) {
            return new Result<>(ResultCode.ERROR.getCode(), "新增失败。", null);
        }
        if (integer == 2) {
            return new Result<>(ResultCode.ERROR.getCode(), "重复新增。", null);
        }
        if (integer == 1) {
            return new Result<>(ResultCode.SUCCESS);
        }
        return new Result<>(ResultCode.ERROR);
    }


    @PostMapping("querySysUserList")
    @ApiOperation(value = "用户分页查询", notes = "用户分页查询")
    public Result<PageResponse<SysUserQueryResponse>> querySysUserList(@Validated @RequestBody SysUserQueryRequest sysUserQueryRequest) {
        log.info("SysUserController.getSysUserList =====>>>" + JSON.toJSONString(sysUserQueryRequest));
        return iSysUser.querySysUserByLimit(sysUserQueryRequest);
    }

    @GetMapping("getSysUser")
    @ApiOperation(value = "用户查询", notes = "用户查询")
    public Result<Object> getSysUser() {
        log.info("SysUserController.getSysUserById =====>>>");

        //获取token中的用户信息
        SysUserBean sysUser = getSysUser(true);
        //查询用户信息并返回

        SysUserRes sysUserRes = iSysUser.selectSysUserByLoginName(sysUser.getLoginName());
        if (sysUserRes == null) {
            return new Result<>(ResultCode.PARAM.getCode(), "用户不存在。", null);
        }
//        SysUserVO sysUserVO = new SysUserVO();
//        BeanUtils.copyProperties(sysUserVO, sysUserRes);

        return new Result<>(ResultCode.SUCCESS, sysUserRes);
    }

    @GetMapping("getSysUserById")
    @ApiOperation(value = "用户查询", notes = "用户查询")
    public Result<Object> getSysUserById(@RequestParam(required = true) @ApiParam(value = "用户ID") String id) {
        log.info("SysUserController.getSysUserById =====>>>" + JSON.toJSON(id));
        validAuth();
        SysUserRes sysUserRes = iSysUser.selectSysUserById(id);
        if (sysUserRes == null) {
            return new Result<>(ResultCode.PARAM.getCode(), "用户不存在。", null);
        }
//        SysUserVO sysUserVO = new SysUserVO();
//        BeanUtils.copyProperties(sysUserVO, sysUserRes);

        return new Result<>(ResultCode.SUCCESS, sysUserRes);
    }

    /**
     * 用户登陆
     *
     * @param param
     * @return
     */
    @PostMapping("login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public Result<Object> login(@Validated @RequestBody LoginQuery param) {
        log.info("SysUserController.login =====>>>" + JSON.toJSONString(param));

        String passWord;
        try {
            //解密RSA
            PrivateKey privateKey = RSAUtils.getPrivateKey(rsaPrivateKey);
            passWord = RSAUtils.decryptionByPrivateKey(param.getLdapPassword(), privateKey);

            //查询用户
            SysUserRes sysUserRes = iSysUser.selectSysUserByLoginName(param.getLoginName());
            //查询用户是否存在并且是否限制登陆
            if (sysUserRes == null) {
                return new Result<>(ResultCode.ERROR.getCode(), "用户不存在,请联系管理员添加。", null);
            }
            //验证密码
            if (!passWord.equals(sysUserRes.getPassword())) {
                //密码错误
                return new Result<>(ResultCode.PARAM.getCode(), "密码错误。", null);
            }
            if (SysUserEnum.DelFlagEnum.DELETE.getCode().equals(sysUserRes.getDelFlag())) {
                //用户已经删除
                return new Result<>(ResultCode.ERROR.getCode(), "用户已经删除。", null);
            }

            if (SysUserEnum.LoginFlag.NO.getCode().equals(sysUserRes.getLoginFlag())) {
                //限制登陆
                return new Result<>(ResultCode.ERROR.getCode(), "用户被限制登陆。", null);
            }
            //判断是否admin

            //记录登陆信息
            SysUserRes updateSysUser = new SysUserRes();
            updateSysUser.setId(sysUserRes.getId());
            updateSysUser.setLoginIp(param.getLoginIp());
            iSysUser.updateSysUserLoginById(updateSysUser);
            return new Result<>(ResultCode.SUCCESS, new SysUserToken(token(sysUserRes)));
        } catch (Exception e) {
            log.error("SysUserController.login.RSA.error =====>>>", e);
            return new Result<>(ResultCode.PARAM.getCode(), "密钥错误。", null);
        }
    }

    private String token(SysUserRes sysUserRes) {

        boolean isMobile = DeviceUtils.isMobile(request); // 是否为客户端
        final String hasKey = (sysUserRes.getId() + (isMobile ? JWTUtil.TERMINAL_MOB : JWTUtil.TERMINAL_WEB)); // 客户端存储key
        String token = JWTUtil.createJWT(hasKey, LASTTIME);
        SysUserBean vo = new SysUserBean(sysUserRes.getId(), sysUserRes.getLoginName(), token);
        redisTemplate.opsForValue().set(hasKey, JSON.toJSONString(vo));
        redisTemplate.expire(hasKey, LASTTIME, TimeUnit.MILLISECONDS);// 设置过期时间
        return token;
    }


}
