package com.xz.service.business.interfaces;


import com.xz.entity.BopSysUser;
import com.xz.vo.entity.PageResponse;
import com.xz.vo.request.SysUserDeleteRequest;
import com.xz.vo.request.SysUserInsertRequest;
import com.xz.vo.request.SysUserQueryRequest;
import com.xz.vo.response.SysUserQueryResponse;

public interface ISysUserSV {

    BopSysUser selectSysUserById(String id);

    Integer updateSysUserById(BopSysUser bopSysUser);

    PageResponse<SysUserQueryResponse> selectSysUserByLimit(SysUserQueryRequest sysUserQueryRequest);

    Integer deleteUserSysById(SysUserDeleteRequest sysUserDeleteRequest);

    BopSysUser selectSysUserByLoginName(String loginName);

    Integer insertSysUser(SysUserInsertRequest sysUserInsertRequest);

    Integer updateSysUser(SysUserInsertRequest sysUserInsertRequest);
}
