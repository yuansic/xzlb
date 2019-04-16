package com.xz.service.business.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.xz.entity.BopSysRole;
import com.xz.entity.BopSysUser;
import com.xz.entity.BopSysUserExample;
import com.xz.entity.BopSysUserRole;
import com.xz.enums.SysUserEnum;
import com.xz.service.atom.interfaces.ISysOfficeAtomSV;
import com.xz.service.atom.interfaces.ISysUserAtomSV;
import com.xz.service.business.interfaces.ISysRoleSV;
import com.xz.service.business.interfaces.ISysUserSV;
import com.xz.util.BeanUtils;
import com.xz.util.DateUtils;
import com.xz.util.StringUtils;
import com.xz.vo.entity.PageResponse;
import com.xz.vo.request.SysUserDeleteRequest;
import com.xz.vo.request.SysUserInsertRequest;
import com.xz.vo.request.SysUserQueryRequest;
import com.xz.vo.response.SysUserQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
public class SysUserSVImpl implements ISysUserSV {

    @Autowired
    private ISysUserAtomSV sysUserAtomSV;
    @Autowired
    private ISysOfficeAtomSV sysOfficeAtomSV;
    @Autowired
    private ISysRoleSV iSysRoleSV;


    @Value("${upload.url}")
    private String uploadUrl;


    @Override
    public Integer updateSysUserById(BopSysUser bopSysUser) {
        return sysUserAtomSV.updateByPrimaryKeySelective(bopSysUser);
    }
    @Override
    public BopSysUser selectSysUserById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return sysUserAtomSV.selectByPrimaryKey(id);
    }

    /**
     * 通过loginName查询用户信息
     *
     * @param loginName
     * @return
     */
    @Override
    public BopSysUser selectSysUserByLoginName(String loginName) {
        if (StringUtils.isBlank(loginName)) {
            return null;
        }
        BopSysUserExample example = new BopSysUserExample();
        BopSysUserExample.Criteria criteria = example.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        List<BopSysUser> bopSysUsers = sysUserAtomSV.selectByExample(example);
        if (bopSysUsers == null || bopSysUsers.isEmpty()) {
            return null;
        }
        return bopSysUsers.get(0);
    }

    /**
     * 更新用户及关联表
     *
     * @param sysUserInsertRequest
     * @return
     */
    @Transactional
    @Override
    public Integer updateSysUser(SysUserInsertRequest sysUserInsertRequest) {
        BopSysUser record = new BopSysUser();
        BeanUtils.copyProperties(record, sysUserInsertRequest);
        record.setLoginName(sysUserInsertRequest.getLoginNameInsert());
        record.setUpdateBy(sysUserInsertRequest.getLoginName());
        if (StringUtils.isNotBlank(record.getPhoto()) && record.getPhoto().contains(uploadUrl)) {
            record.setPhoto(record.getPhoto().replace(uploadUrl, ""));
        }
        //更新用户表
        int insert = sysUserAtomSV.updateByPrimaryKeySelective(record);
        if (insert == 0) {
            return 0;
        }
        //更新role关联表
        String[] roleId = sysUserInsertRequest.getRoleId();
        //新增role关联表
        if (roleId.length > 0) {
            saveUserRole(record.getId(), roleId);
        }
        return insert;
    }


    /**
     * 新增用户数据
     *
     * @param sysUserInsertRequest
     * @return
     */
    @Override
    @Transactional
    public Integer insertSysUser(SysUserInsertRequest sysUserInsertRequest) {
        //数据转换判断
        BopSysUser record = new BopSysUser();
        BeanUtils.copyProperties(record, sysUserInsertRequest);
        record.setLoginName(sysUserInsertRequest.getLoginNameInsert());
        SysUserEnum.LoginFlag loginFlag = SysUserEnum.LoginFlag.getMessage(sysUserInsertRequest.getLoginFlag());
        if (loginFlag == null) {
            record.setLoginFlag(SysUserEnum.LoginFlag.YES.getCode());
        } else {
            record.setLoginFlag(loginFlag.getCode());
        }
        record.setPassword(" ");
        record.setCompanyId(" ");
        record.setOfficeId(" ");
        record.setCreateBy(sysUserInsertRequest.getLoginName());
        record.setUpdateBy(sysUserInsertRequest.getLoginName());
        record.setLoginTime(new Date());
        if (StringUtils.isNotBlank(record.getPhoto()) && record.getPhoto().contains(uploadUrl)) {
            record.setPhoto(record.getPhoto().replace(uploadUrl, ""));
        }
        record.setDelFlag(SysUserEnum.DelFlagEnum.NORMAL.getCode());
        //新增用户表
        int insert = sysUserAtomSV.insert(record);

        if (insert == 0) {
            return insert;
        }
        //新增role关联表
        String[] split = sysUserInsertRequest.getRoleId();
        if (split.length > 0) {
            saveUserRole(record.getId(), split);
        }
        return insert;
    }

    private void saveUserRole(String userId, String[] split) {
        log.info("saveUserRole.userId===> " + userId + " ,RoleList===>" + JSON.toJSONString(split));
        BopSysUserRole sysUserRole = new BopSysUserRole();
        sysUserRole.setUserId(userId);
        List<BopSysRole> list = new ArrayList<>();
        // String[] split = sysUserInsertRequest.getRoleId().split(",");
        for (String str : split) {
            BopSysRole sysRole = new BopSysRole();
            sysRole.setId(str);
            list.add(sysRole);
        }
        sysUserRole.setSysRoleList(list);
        iSysRoleSV.saveUserRoleInfo(sysUserRole);
    }

    /**
     * 分页查询用户信息
     *
     * @param sysUserQueryRequest
     * @return
     */
    @Override
    public PageResponse<SysUserQueryResponse> selectSysUserByLimit(SysUserQueryRequest sysUserQueryRequest) {

        BopSysUserExample example = new BopSysUserExample();
        BopSysUserExample.Criteria criteria = example.createCriteria();

        //判断是否为空拼接SQL
        if (StringUtils.isNotBlank(sysUserQueryRequest.getLoginNameQuery())) {
            criteria.andLoginNameLike("%" + sysUserQueryRequest.getLoginNameQuery() + "%");
        }
        if (StringUtils.isNotBlank(sysUserQueryRequest.getName())) {
            criteria.andNameLike("%" + sysUserQueryRequest.getName() + "%");
        }
        //正常用户
        criteria.andDelFlagEqualTo(SysUserEnum.DelFlagEnum.NORMAL.getCode());

        PageInfo<BopSysUser> objectPageInfo = PageHelper.startPage(Integer.valueOf(sysUserQueryRequest.getPageNo()), Integer.valueOf(sysUserQueryRequest.getPageSize())).doSelectPageInfo(() -> sysUserAtomSV.selectByExample(example));

        List<BopSysUser> userList = objectPageInfo.getList();

        if (userList == null || userList.isEmpty()) {
            return new PageResponse<SysUserQueryResponse>(0L, null);
        }
        List<SysUserQueryResponse> listResponse = new ArrayList<SysUserQueryResponse>();
        for (BopSysUser bopSysUser : userList) {
            SysUserQueryResponse sysUserQueryResponse = new SysUserQueryResponse();
            sysUserQueryResponse.setId(bopSysUser.getId());
            sysUserQueryResponse.setLoginName(bopSysUser.getLoginName());
            sysUserQueryResponse.setName(bopSysUser.getName());
            sysUserQueryResponse.setLoginFlag(bopSysUser.getLoginFlag());
            if (bopSysUser.getLoginTime() != null) {
                sysUserQueryResponse.setLoginDate(DateUtils.formatDateTime(bopSysUser.getLoginTime()));
            }
            sysUserQueryResponse.setCreateDate(DateUtils.formatDateTime(bopSysUser.getCreateTime()));
            if (StringUtils.isNotBlank(bopSysUser.getPhoto())) {
                if (bopSysUser.getPhoto().contains(uploadUrl)) {
                    sysUserQueryResponse.setPhoto(bopSysUser.getPhoto());
                } else {
                    sysUserQueryResponse.setPhoto(uploadUrl + bopSysUser.getPhoto());
                }
            }
            listResponse.add(sysUserQueryResponse);
        }

        return new PageResponse<SysUserQueryResponse>(objectPageInfo.getTotal(), listResponse);
    }

    /**
     * 删除用户表信息
     * @param sysUserDeleteRequest
     * @return
     */
    public Integer deleteUserSysById(SysUserDeleteRequest sysUserDeleteRequest){
        //进行用户删除操作，更新状态
        BopSysUser record = new BopSysUser();
        record.setId(sysUserDeleteRequest.getId());
        record.setUpdateBy(sysUserDeleteRequest.getLoginName());
        record.setDelFlag(SysUserEnum.DelFlagEnum.DELETE.getCode());

        return sysUserAtomSV.updateByPrimaryKeySelective(record);
    }

//    /**
//     * 删除用户表信息
//     *
//     * @param sysUserDeleteRequest
//     * @param result
//     */
//    @Override
//    public void deleteUserSysById(SysUserDeleteRequest sysUserDeleteRequest, Result<String> result) {
//        //判断用户是否以删除
//        BopSysUser bopSysUser = sysUserAtomSV.selectByPrimaryKey(sysUserDeleteRequest.getId());
//        if (SysUserEnum.DelFlagEnum.DELETE.getCode().equals(bopSysUser.getDelFlag())) {
//            result.setData(SysUserEnum.DelFlagEnum.DELETE.getMessage());
//            result.setStatus(ResultCode.SUCCESS.getCode());
//            result.setMessage("已删除，请勿重复提交!");
//            return;
//        }
//        //进行用户删除操作，更新状态
//        BopSysUser record = new BopSysUser();
//        record.setId(sysUserDeleteRequest.getId());
//        record.setUpdateBy(sysUserDeleteRequest.getName());
//        record.setDelFlag(SysUserEnum.DelFlagEnum.DELETE.getCode());
//        int i = sysUserAtomSV.updateByPrimaryKeySelective(record);
//        if (i == 0) {
//            result.setStatus(ResultCode.UNKNOWN.getCode());
//            result.setMessage(ResultCode.UNKNOWN.getMessage());
//            return;
//        }
//        result.setStatus(ResultCode.SUCCESS.getCode());
//        result.setMessage(ResultCode.SUCCESS.getMessage());
//    }

}
