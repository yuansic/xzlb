package com.xz.service.atom.interfaces;


import com.xz.entity.BopSysMenu;
import com.xz.entity.BopSysUserRole;

import java.util.List;

/**
 * 系统菜单原子层接口
 *
 * @author xuby
 * @version 2019/2/22 0022
 */
public interface ISysMenuAtomSV {

    /**
     * 新增系统菜单信息
     * @param sysMenu
     */
    void insert(BopSysMenu sysMenu);

    /**
     * 删除系统菜单信息
     * @param sysMenu
     */
    void delete(BopSysMenu sysMenu);

    /**
     * 更新系统菜单信息
     * @param sysMenu
     */
    void update(BopSysMenu sysMenu);

    /**
     * 更新所有父级ids
     * @param sysMenu
     */
    void updateParentIds(BopSysMenu sysMenu);

    /**
     * 更新菜单排序
     * @param sysMenu
     */
    void updateSort(BopSysMenu sysMenu);

    /**
     * 根据菜单ID查询菜单信息
     * @param id 菜单ID
     * @return
     */
    BopSysMenu get(String id);

    /**
     * 查询系统中所有启用状态的菜单
     * @param sysMenu
     * @return {@link List <SysMenu>}
     */
    List<BopSysMenu> findAllList(BopSysMenu sysMenu);

    /**
     * 查询用户拥有菜单
     * @param sysUserRole
     * @return
     */
    List<BopSysMenu> findUserMenuInfo(BopSysUserRole sysUserRole);
}
