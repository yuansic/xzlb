package com.xz.api.interfaces;


import com.xz.vo.request.*;
import com.xz.vo.response.SysMenuDetailResponse;
import com.xz.vo.response.SysMenuQueryListResponse;

import java.util.List;

/**
 * 系统菜单服务接口
 *
 * @author xuby
 * @version 2019/2/22 0022
 */
public interface ISysMenu {

    /**
     * 保存菜单信息
     * @param sysMenuForm {@link SysMenuFormRequest}
     * @return
     */
    void saveMenuInfo(SysMenuFormRequest sysMenuForm, SysLogHttpRequest request);

    /**
     * 删除菜单信息
     * @param sysMenuDeleteRequest {@link SysMenuDeleteRequest}
     * @return
     */
    void deleteMenuInfo(SysMenuDeleteRequest sysMenuDeleteRequest, SysLogHttpRequest request);

    /**
     * 根据菜单ID查询菜单详细信息
     * @param sysMenuQueryDetailRequest {@link SysMenuQueryDetailRequest}
     * @return
     */
    SysMenuDetailResponse queryMenuDetailForId(SysMenuQueryDetailRequest sysMenuQueryDetailRequest, SysLogHttpRequest request);

    /**
     * 查询菜单信息(平行结构)
     * @param sysMenuQueryRequest {@link SysMenuQueryRequest}
     * @return
     */
    List<SysMenuQueryListResponse> queryAllMenuList(SysMenuQueryRequest sysMenuQueryRequest, SysLogHttpRequest request);

    /**
     * 查询菜单信息(树形结构)
     * @param sysMenuQueryRequest {@link SysMenuQueryRequest}
     * @return
     */
    List<SysMenuQueryListResponse> queryAllMenuListTree(SysMenuQueryRequest sysMenuQueryRequest, SysLogHttpRequest request);

}
