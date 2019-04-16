package com.xz.api.interfaces;


import com.xz.vo.entity.PageResponse;
import com.xz.vo.entity.Result;
import com.xz.vo.request.SysUserDeleteRequest;
import com.xz.vo.request.SysUserInsertRequest;
import com.xz.vo.request.SysUserQueryRequest;
import com.xz.vo.response.SysUserQueryResponse;
import com.xz.vo.response.SysUserRes;

/**
 * @author yuansc
 * @data 2019年2月20日
 * 用户相关
 */

public interface ISysUser {

    /**
     * 查询用户通过ID
     *
     * @param id
     * @return
     */
    SysUserRes selectSysUserById(String id);

    /**
     * 通过ID修改用户
     *
     * @param sysUser
     * @return
     */
    Integer updateSysUserLoginById(SysUserRes sysUser);

    /**
     * 通过登陆名查询用户
     *
     * @param loginName
     * @return
     */
    SysUserRes selectSysUserByLoginName(String loginName);


    /**
     * 用户修改
     *
     * @param sysUserInsertRequest
     * @return
     */
    Integer updateSysUserById(SysUserInsertRequest sysUserInsertRequest);


    /**
     * 用户新增
     *
     * @param sysUserInsertRequest
     * @return
     */
    Integer insertSysUser(SysUserInsertRequest sysUserInsertRequest);

    /**
     * @param sysUserQueryRequest
     * @return 分页用户信息查询
     */
    Result<PageResponse<SysUserQueryResponse>> querySysUserByLimit(SysUserQueryRequest sysUserQueryRequest);


    /**
     * 删除用户信息
     *
     * @param sysUserDeleteRequest
     * @return
     */
    Integer deleteSysUserById(SysUserDeleteRequest sysUserDeleteRequest);

}
