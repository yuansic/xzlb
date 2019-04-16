package com.xz.api.interfaces;


import com.xz.vo.entity.PageResponse;
import com.xz.vo.request.*;
import com.xz.vo.response.SysRoleDetailResponse;
import com.xz.vo.response.SysRoleQueryListResponse;

import java.util.List;

/**
* 系统角色服务接口
*
* @author xuby
* @date 2019-02-21
*/
public interface ISysRole {

    /**
     * 保存角色信息
     * @param sysRoleForm {@link SysRoleFormRequest}
     * @return
     */
    void saveSysRoleInfo(SysRoleFormRequest sysRoleForm, SysLogHttpRequest request);

    /**
     * 删除角色信息
     * @param sysRoleDeleteRequest {@link SysRoleDeleteRequest}
     * @return
     */
    void deleteSysRoleInfo(SysRoleDeleteRequest sysRoleDeleteRequest, SysLogHttpRequest request);

    /**
     * 查询角色明细
     * @param sysRoleQueryDetailRequest {@link SysRoleQueryDetailRequest}
     * @return
     */
    SysRoleDetailResponse querySysRoleDetailInfo(SysRoleQueryDetailRequest sysRoleQueryDetailRequest, SysLogHttpRequest request);

    /**
     * 查询角色列表(分页查询)
     * @param sysRoleQueryListRequest {@link SysRoleQueryListRequest}
     * @return
     */

    PageResponse<SysRoleQueryListResponse> querySysRoleList(SysRoleQueryListRequest sysRoleQueryListRequest, SysLogHttpRequest request);

    /**
     * 不分页查询角色信息
     * @param sysRoleQueryAllListRequest {@link SysRoleQueryAllListRequest}
     * @return
     */
    List<SysRoleQueryListResponse> queryAllList(SysRoleQueryAllListRequest sysRoleQueryAllListRequest, SysLogHttpRequest request);
}
