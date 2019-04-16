package com.xz.service.business.interfaces;



import com.xz.entity.BopSysRole;
import com.xz.entity.BopSysUserRole;
import com.xz.vo.entity.PageResponse;
import com.xz.vo.request.SysRoleQueryAllListRequest;
import com.xz.vo.request.SysRoleQueryListRequest;
import com.xz.vo.response.SysRoleQueryListResponse;

import java.util.List;
import java.util.Set;

/**
 * 系统角色服务接口
 * @author xuby
 * @version 2019/2/26 0026
 */
public interface ISysRoleSV {

    /**
     * 保存系统角色信息
     * @param sysRole {@link BopSysRole}
     */
    void saveRoleInfo(BopSysRole sysRole);

    /**
     * 删除角色信息
     * @param id 角色ID
     */
    void deleteRoleInfo(String id);

    /**
     * 根据角色ID获取角色信息
     * @param id 角色ID
     * @return
     */
    BopSysRole get(String id);

    /**
     * 分页查询角色信息
     * @param request {@link SysRoleQueryListRequest}
     * @return
     */
    PageResponse<SysRoleQueryListResponse> findPage(SysRoleQueryListRequest request);

    /**
     * 不分页查询角色信息
     * @param request {@link SysRoleQueryListRequest}
     * @return
     */
    List<SysRoleQueryListResponse> findAllList(SysRoleQueryAllListRequest request);

    /**
     * 新增用户角色关联关系
     * @param sysUserRole {@link BopSysUserRole}
     */
    void saveUserRoleInfo(BopSysUserRole sysUserRole);

    /**
     * 根据用户ID查询用户角色ID
     * @param userId
     * @return
     */
    String[] queryUserRoleIdForUserId(String userId);

    /**
     * 系统所有角色都重新赋权
     * @param menuSet 菜单ID集合
     * @param roleId 角色ID
     */
    void saveRoleDefaultMenuInfoForMenuId(Set<String> menuSet, String roleId);

    /**
     * 查询所有角色菜单关联关系信息
     * @return
     */
    List<BopSysRole> fineRoleMenu();

    /**
     * 根据角色ID和菜单ID删除角色菜单关联关系
     * @param menuIds 菜单ID集合
     * @param roleId 角色ID
     */
    void deleteRoleMenuForRoleIdAndMenuId(Set<String> menuIds, String roleId);
}
