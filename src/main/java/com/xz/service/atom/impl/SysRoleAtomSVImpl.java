package com.xz.service.atom.impl;


import com.xz.dao.BopSysRoleMapper;
import com.xz.entity.BopSysRole;
import com.xz.entity.BopSysRoleMenu;
import com.xz.entity.BopSysUserRole;
import com.xz.service.atom.interfaces.ISysRoleAtomSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统角色原子层实现
 *
 * @author xuby
 * @version 2019/2/22 0022
 */
@Component
public class SysRoleAtomSVImpl implements ISysRoleAtomSV {

    @Autowired
    private BopSysRoleMapper sysRoleMapper;

    /**
     * 删除角色菜单信息
     * @param sysRole {@link BopSysRole}
     */
    @Override
    public void deleteRoleMenu(BopSysRole sysRole) {
        sysRoleMapper.deleteRoleMenu(sysRole);
    }

    /**
     * 新增角色菜单信息
     * @param sysRole {@link BopSysRole}
     */
    @Override
    public void insertRoleMenu(BopSysRole sysRole) {
        sysRoleMapper.insertRoleMenu(sysRole);
    }

    /**
     * 新增角色信息
     * @param sysRole {@link BopSysRole}
     */
    @Override
    public void insert(BopSysRole sysRole) {
        sysRoleMapper.insert(sysRole);
    }

    /**
     * 更新角色信息
     * @param sysRole {@link BopSysRole}
     */
    @Override
    public void update(BopSysRole sysRole) {
        sysRoleMapper.update(sysRole);
    }

    /**
     * 删除角色信息
     * @param sysRole {@link BopSysRole}
     */
    @Override
    public void delete(BopSysRole sysRole) {
        sysRoleMapper.delete(sysRole);
    }

    /**
     * 根据角色ID获取角色信息
     * @param id 角色ID
     * @return
     */
    @Override
    public BopSysRole get(String id) {
        return sysRoleMapper.get(id);
    }

    /**
     * 查询所有角色信息
     * @param sysRole {@link BopSysRole}
     * @return
     */
    @Override
    public List<BopSysRole> findAllList(BopSysRole sysRole) {
        return sysRoleMapper.findAllList(sysRole);
    }

    /**
     * 新增用户角色关联关系
     * @param sysUserRole {@link BopSysUserRole}
     */
    @Override
    public void insertUserRole(BopSysUserRole sysUserRole) {
        sysRoleMapper.insertUserRole(sysUserRole);
    }

    /**
     * 删除用户角色关联关系
     * @param sysUserRole {@link BopSysUserRole}
     */
    @Override
    public void deleteUserRole(BopSysUserRole sysUserRole) {
        sysRoleMapper.deleteUserRole(sysUserRole);
    }

    /**
     * 根据用户ID查询用户角色ID
     * @param sysUserRole {@link BopSysUserRole}
     * @return
     */
    @Override
    public List<BopSysUserRole> findUserRoleByUserId(BopSysUserRole sysUserRole) {
        return sysRoleMapper.findUserRoleByUserId(sysUserRole);
    }

    /**
     * 查询所有角色菜单关联信息
     * @return
     */
    @Override
    public List<BopSysRole> fineRoleMenu() {
        return sysRoleMapper.fineRoleMenu();
    }

    /**
     * 新增用户角色关联关系
     * @param sysRole {@link BopSysRole}
     */
    @Override
    public void insertRoleMenuForMenuId(BopSysRole sysRole) {
        sysRoleMapper.insertRoleMenuForMenuId(sysRole);
    }

    /**
     * 根据菜单ID和角色ID删除菜单角色关联关系
     * @param bopSysRoleMenu
     */
    @Override
    public void deleteRoleMenuForRoleIdAndMenuId(BopSysRoleMenu bopSysRoleMenu) {
        sysRoleMapper.deleteRoleMenuForRoleIdAndMenuId(bopSysRoleMenu);
    }


}
