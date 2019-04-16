package com.xz.service.atom.impl;


import com.xz.dao.BopSysMenuMapper;
import com.xz.entity.BopSysMenu;
import com.xz.entity.BopSysUserRole;
import com.xz.service.atom.interfaces.ISysMenuAtomSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单原子层实现
 *
 * @author xuby
 * @version 2019/2/22 0022
 */
@Component
public class SysMenuAtomSVImpl implements ISysMenuAtomSV {


    @Autowired
    private BopSysMenuMapper sysMenuMapper;


    /**
     * 新增系统菜单信息
     * @param sysMenu {@link BopSysMenu}
     */
    @Override
    public void insert(BopSysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);
    }

    /**
     * 删除系统菜单信息
     * @param sysMenu {@link BopSysMenu}
     */
    @Override
    public void delete(BopSysMenu sysMenu) {
        sysMenuMapper.delete(sysMenu);
    }

    /**
     * 更新系统菜单信息
     * @param sysMenu {@link BopSysMenu}
     */
    @Override
    public void update(BopSysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }

    /**
     * 更新所有父级ids
     * @param sysMenu {@link BopSysMenu}
     */
    @Override
    public void updateParentIds(BopSysMenu sysMenu) {
        sysMenuMapper.updateParentIds(sysMenu);
    }

    /**
     * 更新菜单排序
     * @param sysMenu {@link BopSysMenu}
     */
    @Override
    public void updateSort(BopSysMenu sysMenu) {
        sysMenuMapper.updateSort(sysMenu);
    }

    /**
     * 根据菜单ID查询菜单信息
     * @param id 菜单ID
     * @return {@link BopSysMenu}
     */
    @Override
    public BopSysMenu get(String id) {
        return sysMenuMapper.get(id);
    }

    /**
     * 查询系统中所有启用的菜单信息
     * @param sysMenu
     * @return {@link List<BopSysMenu>}
     */
    @Override
    public List<BopSysMenu> findAllList(BopSysMenu sysMenu) {
        return sysMenuMapper.findAllList(sysMenu);
    }

    /**
     * 查询用户拥有菜单
     * @param sysUserRole {@link BopSysUserRole}
     * @return
     */
    @Override
    public List<BopSysMenu> findUserMenuInfo(BopSysUserRole sysUserRole) {
        //根据用户ID查询拥有菜单集合
        List<BopSysMenu> menuList = sysMenuMapper.findUserMenuInfo(sysUserRole);
        // 去重
        List<BopSysMenu> resultList = new ArrayList<>();
        for(BopSysMenu s:menuList){
            if(!resultList.contains(s)){
                resultList.add(s);
            }
        }

        return resultList;
    }
}
