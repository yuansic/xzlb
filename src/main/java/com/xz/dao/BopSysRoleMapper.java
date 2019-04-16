package com.xz.dao;


import com.xz.entity.BopSysRole;
import com.xz.entity.BopSysRoleMenu;
import com.xz.entity.BopSysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色DAO
 *
 * @author xuby
 * @version 2019/2/21
 */
@Mapper
public interface BopSysRoleMapper {

    /**
     * 删除角色菜单信息
     * @param sysRole {@link BopSysRole}
     */
    void deleteRoleMenu(BopSysRole sysRole);

    /**
     * 新增角色菜单信息
     * @param sysRole {@link BopSysRole}
     */
    void insertRoleMenu(BopSysRole sysRole);

    /**
     * 新增角色信息
     * @param sysRole {@link BopSysRole}
     */
    void insert(BopSysRole sysRole);

    /**
     * 更新角色信息
     * @param sysRole {@link BopSysRole}
     */
    void update(BopSysRole sysRole);

    /**
     * 删除角色信息
     * @param sysRole {@link BopSysRole}
     */
    void delete(BopSysRole sysRole);

    /**
     * 根据角色ID获取角色信息
     * @param id 角色ID
     * @return
     */
    BopSysRole get(String id);

    /**
     * 查询所有角色信息
     * @param sysRole {@link BopSysRole}
     * @return
     */
    List<BopSysRole> findAllList(BopSysRole sysRole);

    /**
     * 新增用户角色关联关系
     * @param sysUserRole {@link BopSysUserRole}
     */
    void insertUserRole(BopSysUserRole sysUserRole);

    /**
     * 删除用户角色关联关系
     * @param sysUserRole {@link BopSysUserRole}
     */
    void deleteUserRole(BopSysUserRole sysUserRole);

    /**
     * 根据用户ID查询用户角色ID
     * @param sysUserRole {@link BopSysUserRole}
     * @return
     */
    List<BopSysUserRole> findUserRoleByUserId(BopSysUserRole sysUserRole);

    /**
     * 查询所有角色菜单关联关系信息
     * @return
     */
    List<BopSysRole> fineRoleMenu();

    /**
     * 新增用户角色关联关系
     * @param sysRole
     */
    void insertRoleMenuForMenuId(BopSysRole sysRole);

    /**
     * 根据角色ID和菜单ID删除角色菜单关联关系
     * @param bopSysRoleMenu
     */
    void deleteRoleMenuForRoleIdAndMenuId(BopSysRoleMenu bopSysRoleMenu);
}
