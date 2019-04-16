package com.xz.api.service;

import com.alibaba.fastjson.JSON;
import com.xz.api.interfaces.ISysUser;
import com.xz.entity.BopSysUser;
import com.xz.enums.SysUserEnum;
import com.xz.service.business.interfaces.ISysRoleSV;
import com.xz.service.business.interfaces.ISysUserSV;
import com.xz.util.BeanUtils;
import com.xz.util.StringUtils;
import com.xz.vo.entity.PageResponse;
import com.xz.vo.entity.Result;
import com.xz.vo.entity.ResultCode;
import com.xz.vo.request.SysUserDeleteRequest;
import com.xz.vo.request.SysUserInsertRequest;
import com.xz.vo.request.SysUserQueryRequest;
import com.xz.vo.response.SysUserQueryResponse;
import com.xz.vo.response.SysUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author yuansc
 * @data 2019年2月20日
 */
@Service
public class SysUserImpl implements ISysUser {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserImpl.class);

    private static final String ADMIN = "admin";

    @Autowired
    private ISysUserSV iSysUserSV;

    @Autowired
    private ISysRoleSV iSysRoleSV;

    @Value("${upload.url}")
    private String uploadUrl;

    @Override
    public Integer updateSysUserLoginById(SysUserRes sysUser) {
        BopSysUser bopSysUserVo = new BopSysUser();
        BeanUtils.copyProperties(bopSysUserVo, sysUser);
        bopSysUserVo.setLoginTime(new Date());
        return iSysUserSV.updateSysUserById(bopSysUserVo);
    }

    public SysUserRes selectSysUserById(String id) {
        LOGGER.info("SysUserImpl.selectSysUserById :" + JSON.toJSONString(id));
        BopSysUser bopSysUser = iSysUserSV.selectSysUserById(id);
        if (bopSysUser == null) {
            return null;
        }
        SysUserRes sysUserRes = new SysUserRes();
        BeanUtils.copyProperties(sysUserRes, bopSysUser);
        //添加照片信息
        if (StringUtils.isNotBlank(bopSysUser.getPhoto())) {
            if (bopSysUser.getPhoto().contains("http")) {
                sysUserRes.setPhoto(bopSysUser.getPhoto());
            } else {
                sysUserRes.setPhoto(uploadUrl + bopSysUser.getPhoto());
            }
        }
        //查询用户角色
        String[] roleId = iSysRoleSV.queryUserRoleIdForUserId(id);
        sysUserRes.setRoleId(roleId);
        LOGGER.info("SysUserImpl.selectSysUserById.res :" + JSON.toJSONString(sysUserRes));
        return sysUserRes;
    }

    @Override
    public SysUserRes selectSysUserByLoginName(String loginName) {
        LOGGER.info("SysUserImpl.selectSysUserByLoginName :" + JSON.toJSONString(loginName));
        BopSysUser bopSysUser = iSysUserSV.selectSysUserByLoginName(loginName);

        if (bopSysUser == null) {
            return null;
        }

        SysUserRes sysUserRes = new SysUserRes();
        BeanUtils.copyProperties(sysUserRes, bopSysUser);

        if (StringUtils.isNotBlank(bopSysUser.getPhoto())) {
            if (bopSysUser.getPhoto().contains("http")) {
                sysUserRes.setPhoto(bopSysUser.getPhoto());
            } else {
                sysUserRes.setPhoto(uploadUrl + bopSysUser.getPhoto());
            }
        }

        //查询用户角色
        LOGGER.info("SysUserImpl.selectSysUserByLoginName.res :" + JSON.toJSONString(sysUserRes));
        return sysUserRes;
    }

    /**
     * 修改用户
     *
     * @param sysUserInsertRequest
     * @return
     */
    @Override
    public Integer updateSysUserById(SysUserInsertRequest sysUserInsertRequest) {
        LOGGER.info("SysUserImpl.updateSysUserById:" + JSON.toJSONString(sysUserInsertRequest));
        Integer integer;
        try {
            //查询用户
            BopSysUser bopSysUser = iSysUserSV.selectSysUserById(sysUserInsertRequest.getId());
            if (bopSysUser == null) {
                return null;
            }
            if (SysUserEnum.DelFlagEnum.DELETE.getCode().equals(bopSysUser.getDelFlag())) {
                return -1;
            }
            integer = iSysUserSV.updateSysUser(sysUserInsertRequest);
        } catch (Exception e) {
            LOGGER.error("SysDictImpl.updateSysDict.error" + e);
            return null;
        }
        return integer;
    }

    @Override
    public Integer insertSysUser(SysUserInsertRequest sysUserInsertRequest) {
        LOGGER.info("SysUserImpl.insertSysUser:" + JSON.toJSONString(sysUserInsertRequest));
        //幂等验证防止用户重复提交
        Integer integer;
        try {
            BopSysUser bopSysUser = iSysUserSV.selectSysUserByLoginName(sysUserInsertRequest.getLoginNameInsert());
            if (bopSysUser != null) {
                return 2;
            }
            integer = iSysUserSV.insertSysUser(sysUserInsertRequest);
        } catch (Exception e) {
            LOGGER.error("SysUserImpl.insertSysUser.error:" + e);
            return 3;
        }
        LOGGER.info("SysUserImpl.insertSysUser.result:" + JSON.toJSONString(integer));
        return integer;
    }

    @Override
    public Result<PageResponse<SysUserQueryResponse>> querySysUserByLimit(SysUserQueryRequest sysUserQueryRequest) {

        LOGGER.info("SysUserImpl.userList:" + JSON.toJSONString(sysUserQueryRequest));

        Result<PageResponse<SysUserQueryResponse>> result = new Result<PageResponse<SysUserQueryResponse>>();

        result.setStatus(ResultCode.ERROR.getCode());

        //判断参数
        if (sysUserQueryRequest == null) {
            result.setMessage(ResultCode.ERROR.getMessage());
            return result;
        }
        //参数转换
        PageResponse<SysUserQueryResponse> sysUserQueryResponsePageResponse = null;
        try {
            sysUserQueryResponsePageResponse = iSysUserSV.selectSysUserByLimit(sysUserQueryRequest);
            result.setStatus(ResultCode.SUCCESS.getCode());
            result.setMessage(ResultCode.SUCCESS.getMessage());
            result.setData(sysUserQueryResponsePageResponse);
            return result;
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("SysUserImpl.userList.error:" + e);
            result.setStatus(ResultCode.UNKNOWN.getCode());
            result.setMessage(ResultCode.UNKNOWN.getMessage());
        }

        return result;
    }

    @Override
    public Integer deleteSysUserById(SysUserDeleteRequest sysUserDeleteRequest) {
        LOGGER.info("SysUserImpl.deleteSysUserById:" + JSON.toJSONString(sysUserDeleteRequest));
        Integer integer;
        try {
            //判断用户是否以删除
            BopSysUser bopSysUser = iSysUserSV.selectSysUserById(sysUserDeleteRequest.getId());
            if (bopSysUser == null) {
                return null;
            }
            if (SysUserEnum.DelFlagEnum.DELETE.getCode().equals(bopSysUser.getDelFlag())) {
                return -1;
            }
            //进行用户删除操作，更新状态
            integer = iSysUserSV.deleteUserSysById(sysUserDeleteRequest);
        } catch (Exception e) {
            LOGGER.error("SysUserImpl.deleteSysUserById.error" + e);
            return null;
        }
        return integer;
    }


}
