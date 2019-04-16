package com.xz.dao;


import com.xz.entity.BopSysMenu;
import com.xz.entity.BopSysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统菜单DAO
 *
 * @author xuby
 * @version 2019/2/21
 */
@Mapper
public interface BopSysMenuMapper {

    /**
     * 新增系统菜单信息
     * @param sysMenu {@link BopSysMenu}
     */
    void insert(BopSysMenu sysMenu);

    /**
     * 删除系统菜单信息
     * @param sysMenu {@link BopSysMenu}
     */
    void delete(BopSysMenu sysMenu);

    /**
     * 更新系统菜单信息
     * @param sysMenu {@link BopSysMenu}
     */
    void update(BopSysMenu sysMenu);

    /**
     * 更新所有父级ids
     * @param sysMenu {@link BopSysMenu}
     */
    void updateParentIds(BopSysMenu sysMenu);

    /**
     * 更新菜单排序
     * @param sysMenu {@link BopSysMenu}
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
     * @return {@link List<BopSysMenu>}
     */
    List<BopSysMenu> findAllList(BopSysMenu sysMenu);

    /**
     * 查询用户拥有菜单权限
     * @param sysUserRole
     * @return
     */
    List<BopSysMenu> findUserMenuInfo(BopSysUserRole sysUserRole);
}
