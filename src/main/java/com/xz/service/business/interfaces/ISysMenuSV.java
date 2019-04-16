package com.xz.service.business.interfaces;



import com.xz.entity.BopSysMenu;
import com.xz.entity.BopSysUserRole;
import com.xz.vo.response.SysMenuQueryListResponse;

import java.util.List;

/**
 * 系统菜单service接口
 * @author xuby
 * @version 2019/2/26 0026
 */
public interface ISysMenuSV {

    /**
     * 保存菜单信息
     * @param sysMenu
     */
    void saveMenuInfo(BopSysMenu sysMenu);

    /**
     * 删除菜单信息
     * @param id
     */
    void deleteMenuInfo(String id);

    /**
     * 根据菜单ID查询菜单信息
     * @param id 菜单ID
     * @return {@link BopSysMenu}
     */
    BopSysMenu get(String id);

    /**
     * 查询所有系统菜单(平行结构)
     * @param sysMenu {@link BopSysMenu}
     * @return {@link List<BopSysMenu>}
     */
    List<SysMenuQueryListResponse> queryAllList(BopSysMenu sysMenu);

    /**
     * 查询用户所有菜单(平行结构)
     * @param sysUserRole {@link BopSysUserRole}
     * @return
     */
    List<SysMenuQueryListResponse> queryUserMenuList(BopSysUserRole sysUserRole);

    /**
     * 查询所有系统菜单(树形结构)
     * @param sysMenu {@link BopSysMenu}
     * @return {@link List<BopSysMenu>}
     */
    List<SysMenuQueryListResponse> queryAllListForTree(BopSysMenu sysMenu);

    /**
     * 查询用户所有系统菜单(树形结构)
     * @param sysUserRole {@link BopSysUserRole}
     * @return {@link List<BopSysMenu>}
     */
    List<SysMenuQueryListResponse> queryUserMenuListForTree(BopSysUserRole sysUserRole);


}
