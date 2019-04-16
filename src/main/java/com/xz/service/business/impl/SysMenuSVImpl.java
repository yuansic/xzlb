package com.xz.service.business.impl;

import com.alibaba.fastjson.JSON;

import com.xz.entity.BopSysMenu;
import com.xz.entity.BopSysRole;
import com.xz.entity.BopSysUserRole;
import com.xz.enums.CommonEnum;
import com.xz.service.BaseService;
import com.xz.service.atom.interfaces.ISysMenuAtomSV;
import com.xz.service.business.interfaces.ISysMenuSV;
import com.xz.service.business.interfaces.ISysRoleSV;
import com.xz.util.StringUtils;
import com.xz.vo.response.SysMenuQueryListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

/**
 * 菜单查询服务实现类
 * @author xuby
 * @version 2019/2/26 0026
 */
@Slf4j
@Service
@Transactional
public class SysMenuSVImpl extends BaseService implements ISysMenuSV {

    @Autowired
    private ISysMenuAtomSV iSysMenuAtomSV;
    @Autowired
    private ISysRoleSV iSysRoleSV;
    //顶级菜单ID
    private static String SYS_MENU_ID = "0";

    /**
     * 保存菜单信息
     * @param sysMenu
     */
    @Override
    @Transactional(readOnly = false)
    public void saveMenuInfo(BopSysMenu sysMenu) {
        log.info("---SysMenuSVImpl saveMenuInfo sysMenu:{}---", JSON.toJSONString(sysMenu));

        // 查询系统中所有角色的菜单合集
        List<BopSysRole> sysRoleList = iSysRoleSV.fineRoleMenu();

        BopSysMenu parentMenu = null;
        if(StringUtils.isNotBlank(sysMenu.getParentId())){
            // 根据父级ID查询父级菜单信息，并拼接parentIds字段
            parentMenu = iSysMenuAtomSV.get(sysMenu.getParentId());
        }else{
            sysMenu.setParentId(SYS_MENU_ID);
        }
        if(StringUtils.isNotBlank(sysMenu.getId())){
            // 查询更新前的菜单信息
            BopSysMenu oldSysMenu = iSysMenuAtomSV.get(sysMenu.getId());

            if(parentMenu != null){
                sysMenu.setParentIds(parentMenu.getParentIds()+sysMenu.getId()+",");
            }else{
                sysMenu.setParentIds(sysMenu.getId()+",");
            }

            sysMenu.preUpdate();
            iSysMenuAtomSV.update(sysMenu);

            // 如果菜单从默认调整为非默认，则删除系统中所有该菜单的角色关联关系
            boolean isDefault = CommonEnum.YesOrNoEnum.YES.getCode().equals(oldSysMenu.getIsDefault())
                    && CommonEnum.YesOrNoEnum.NO.getCode().equals(sysMenu.getIsDefault());
            if(isDefault){
                //从新遍历所有角色菜单
                for(BopSysRole sysRole:sysRoleList){
                    List<String> newRoleMenu = new ArrayList<String>();
                    for(BopSysMenu sysMenu1:sysRole.getMenuList()){
                        if(!sysMenu.getParentIds().contains(sysMenu1.getId())){
                            newRoleMenu.add(sysMenu1.getParentIds());
                        }
                    }

                    Set<String> deleteSet = new HashSet<>();
                    for(String deleteMenuIds:sysMenu.getParentIds().split(",")){
                        int count = 0;
                        for(String parentIds:newRoleMenu){
                            if(parentIds.contains(deleteMenuIds)){
                                count++;
                            }
                        }
                        if(count == 0){
                            deleteSet.add(deleteMenuIds);
                        }
                    }

                    log.debug("deleteSet 3:",JSON.toJSONString(deleteSet));
                    // 删除菜单角色的关联关系
                    iSysRoleSV.deleteRoleMenuForRoleIdAndMenuId(deleteSet,sysRole.getId());
                }
            }

            // 非默认菜单的父级改变,删除已分配角色的权限，并重新保存
            boolean isNotDefaultParentChange = !oldSysMenu.getParentId().equals(sysMenu.getParentId()) &&
                    CommonEnum.YesOrNoEnum.NO.getCode().equals(sysMenu.getIsDefault()) &&
                    CommonEnum.YesOrNoEnum.NO.getCode().equals(oldSysMenu.getIsDefault());

//            if(isNotDefaultParentChange){
//                for(BopSysRole sysRole:sysRoleList){
//                    List<String> newRoleMenu = new ArrayList<String>();
//                    Set<String> newRoleId = new HashSet<>();
//
//
//                    for(BopSysMenu sysMenu1:sysRole.getMenuList()){
//                        if(sysMenu.getId().equals(sysMenu1.getId())){
//
//                            if(!oldSysMenu.getParentIds().contains(sysMenu1.getId())){
//                                newRoleMenu.add(sysMenu1.getParentIds());
//                            }
//
//                        }
//                    }
//
//                    Set<String> deleteSet = new HashSet<>();
//                    for(String deleteMenuIds:oldSysMenu.getParentIds().split(",")){
//                        int count = 0;
//                        for(String parentIds:newRoleMenu){
//                            if(parentIds.contains(deleteMenuIds)){
//                                count++;
//                            }
//                        }
//                        if(count == 0){
//                            deleteSet.add(deleteMenuIds);
//                        }
//                    }
//                    if(deleteSet != null && deleteSet.size() > 0){
//                        // 删除之前的关联关系
//                        iSysRoleSV.deleteRoleMenuForRoleIdAndMenuId(deleteSet,sysRole.getId());
//                    }
//                }
//            }

            // 默认菜单的父级改变
            boolean isDefaultAndChangeParent = !oldSysMenu.getParentId().equals(sysMenu.getParentId()) &&
                    CommonEnum.YesOrNoEnum.YES.getCode().equals(sysMenu.getIsDefault()) &&
                    CommonEnum.YesOrNoEnum.YES.getCode().equals(oldSysMenu.getIsDefault());

            //如果从非默认调整为默认
            boolean isDefault1 = CommonEnum.YesOrNoEnum.NO.getCode().equals(oldSysMenu.getIsDefault())
                    && CommonEnum.YesOrNoEnum.YES.getCode().equals(sysMenu.getIsDefault());
            if(isDefault1 || isDefaultAndChangeParent){
                for(BopSysRole sysRole:sysRoleList){
                    // 重新保存该角色的菜单信息
                    Set<String> saveSet = new HashSet<>();
                    for(String menuId:sysMenu.getParentIds().split(",")){
                        saveSet.add(menuId);
                    }
                    for(BopSysMenu sysMenu1:sysRole.getMenuList()){
                        saveSet.add(sysMenu1.getId());
                    }
                    log.debug("saveSet 2:",JSON.toJSONString(saveSet));
                    // 删除之前的关联关系
                    iSysRoleSV.deleteRoleMenuForRoleIdAndMenuId(saveSet,sysRole.getId());
                    //重新新增关联关系
                    iSysRoleSV.saveRoleDefaultMenuInfoForMenuId(saveSet,sysRole.getId());
                }
            }

        }else{
            sysMenu.preInsert();
            if(parentMenu != null){
                sysMenu.setParentIds(parentMenu.getParentIds()+sysMenu.getId()+",");
            }else{
                sysMenu.setParentIds(sysMenu.getId()+",");
            }
            iSysMenuAtomSV.insert(sysMenu);

            //如果新增菜单为默认菜单，则对系统中所有角色进行赋权,（保存用户角色权限表）
            if(CommonEnum.YesOrNoEnum.YES.getCode().equals(sysMenu.getIsDefault())){
                for(BopSysRole sysRole:sysRoleList){
                    // 重新保存该角色的菜单信息
                    Set<String> saveSet = new HashSet<>();
                    for(String menuId:sysMenu.getParentIds().split(",")){
                        saveSet.add(menuId);
                    }
                    for(BopSysMenu sysMenu1:sysRole.getMenuList()){
                        saveSet.add(sysMenu1.getId());
                    }
                    log.debug("saveSet 1:",JSON.toJSONString(saveSet));
                    // 删除之前的关联关系
                    iSysRoleSV.deleteRoleMenuForRoleIdAndMenuId(saveSet,sysRole.getId());
                    //重新新增关联关系
                    iSysRoleSV.saveRoleDefaultMenuInfoForMenuId(saveSet,sysRole.getId());
                }
            }
        }

    }


    /**
     * 删除菜单信息
     * @param id
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteMenuInfo(String id) {
        log.info("---SysMenuSVImpl deleteMenuInfo id:{}---",id);
        BopSysMenu sysMenu = new BopSysMenu();
        sysMenu.setId(id);
        iSysMenuAtomSV.delete(sysMenu);
    }

    /**
     * 根据菜单ID查询菜单信息
     * @param id 菜单ID
     * @return {@link BopSysMenu}
     */
    @Override
    public BopSysMenu get(String id) {
        return iSysMenuAtomSV.get(id);
    }

    /**
     * 查询所有系统菜单(平行结构)
     * @param sysMenu {@link BopSysMenu}
     * @return {@link List<SysMenuQueryListResponse>}
     */
    @Override
    public List<SysMenuQueryListResponse> queryAllList(BopSysMenu sysMenu) {
        List<SysMenuQueryListResponse> resultList = new ArrayList<SysMenuQueryListResponse>();

        List<BopSysMenu> sysMenuList = iSysMenuAtomSV.findAllList(sysMenu);
        if(sysMenuList != null && sysMenuList.size() > 0){
            for(BopSysMenu s:sysMenuList){
                SysMenuQueryListResponse res = change(s,SysMenuQueryListResponse.class);
                resultList.add(res);
            }
        }
        return resultList;
    }

    /**
     * 查询用户所有系统菜单(平行结构)
     * @param sysUserRole {@link BopSysUserRole}
     * @return {@link List<SysMenuQueryListResponse>}
     */
    @Override
    public List<SysMenuQueryListResponse> queryUserMenuList(BopSysUserRole sysUserRole) {
        List<SysMenuQueryListResponse> resultList = new ArrayList<SysMenuQueryListResponse>();

        List<BopSysMenu> sysMenuList = iSysMenuAtomSV.findUserMenuInfo(sysUserRole);
        if(sysMenuList != null && sysMenuList.size() > 0){
            for(BopSysMenu s:sysMenuList){
                SysMenuQueryListResponse res = change(s,SysMenuQueryListResponse.class);
                resultList.add(res);
            }
        }
        return resultList;
    }


    /**
     * 查询所有系统菜单(树形结构)
     * @param sysMenu {@link BopSysMenu}
     * @return {@link List<SysMenuQueryListResponse>}
     */
    @Override
    public List<SysMenuQueryListResponse> queryAllListForTree(BopSysMenu sysMenu) {

        //查询系统所有菜单信息
        List<SysMenuQueryListResponse> resultList = new ArrayList<SysMenuQueryListResponse>();

        List<BopSysMenu> sysMenuList = iSysMenuAtomSV.findAllList(sysMenu);
        if(sysMenuList != null && sysMenuList.size() > 0){
            for(BopSysMenu s:sysMenuList){
                // 标记菜单层级
                if(StringUtils.isNotBlank(s.getParentIds())){
                    s.setMenuLevel(s.getParentIds().split(",").length);
                }else{
                    s.setMenuLevel(0);
                }

                SysMenuQueryListResponse res = change(s,SysMenuQueryListResponse.class);
                resultList.add(res);
            }
        }

        //顶级菜单集合
        List<SysMenuQueryListResponse> topMenuList = new ArrayList<SysMenuQueryListResponse>();
        //非顶级菜单集合
        List<SysMenuQueryListResponse> childMenuList = new ArrayList<SysMenuQueryListResponse>();
        for(SysMenuQueryListResponse m1:resultList){
            //如果父级ID不存在，则插入顶级菜单集合中
            if(StringUtils.isBlank(m1.getParentId()) || SYS_MENU_ID.equals(m1.getParentId())){
                topMenuList.add(m1);
            }else{
                childMenuList.add(m1);
            }
        }
        // 父级菜单排序
        if(topMenuList.size() > 0){
            topMenuList.sort(new Comparator<SysMenuQueryListResponse>() {
                @Override
                public int compare(SysMenuQueryListResponse o1, SysMenuQueryListResponse o2) {
                    Integer s1 = o1.getSort();
                    Integer s2 = o2.getSort();
                    return s1.compareTo(s2);
                }
            });
        }

        for(SysMenuQueryListResponse m2:topMenuList){
            // 递归获取子菜单
            this.getChild(m2,childMenuList);
        }

        return topMenuList;
    }

    /**
     * 查询用户所有菜单(树形结构)
     * @param sysUserRole {@link BopSysUserRole}
     * @return
     */
    @Override
    public List<SysMenuQueryListResponse> queryUserMenuListForTree(BopSysUserRole sysUserRole) {
        //查询系统所有菜单信息
        List<SysMenuQueryListResponse> resultList = new ArrayList<SysMenuQueryListResponse>();

        List<BopSysMenu> sysMenuList = iSysMenuAtomSV.findUserMenuInfo(sysUserRole);
        if(sysMenuList != null && sysMenuList.size() > 0){
            for(BopSysMenu s:sysMenuList){
                // 标记菜单层级
                if(StringUtils.isNotBlank(s.getParentIds())){
                    s.setMenuLevel(s.getParentIds().split(",").length);
                }else{
                    s.setMenuLevel(0);
                }

                SysMenuQueryListResponse res = change(s,SysMenuQueryListResponse.class);
                resultList.add(res);
            }
        }

        //顶级菜单集合
        List<SysMenuQueryListResponse> topMenuList = new ArrayList<SysMenuQueryListResponse>();
        //非顶级菜单集合
        List<SysMenuQueryListResponse> childMenuList = new ArrayList<SysMenuQueryListResponse>();
        for(SysMenuQueryListResponse m1:resultList){
            //如果父级ID不存在，则插入顶级菜单集合中
            if(StringUtils.isBlank(m1.getParentId()) || SYS_MENU_ID.equals(m1.getParentId())){
                topMenuList.add(m1);
            }else{
                childMenuList.add(m1);
            }
        }

        // 父级菜单排序
        if(topMenuList.size() > 0){
            topMenuList.sort(new Comparator<SysMenuQueryListResponse>() {
                @Override
                public int compare(SysMenuQueryListResponse o1, SysMenuQueryListResponse o2) {
                    Integer s1 = o1.getSort();
                    Integer s2 = o2.getSort();
                    return s1.compareTo(s2);
                }
            });
        }

        for(SysMenuQueryListResponse m2:topMenuList){
            // 递归获取子菜单
            this.getChild(m2,childMenuList);
        }

        return topMenuList;
    }

    /**
     * 递归填充子菜单
     * @param sysMenu {@link SysMenuQueryListResponse}
     * @param childMenuList {@link List<SysMenuQueryListResponse>}
     */
    private void getChild(SysMenuQueryListResponse sysMenu,List<SysMenuQueryListResponse> childMenuList){
        List<SysMenuQueryListResponse> cList = new ArrayList<SysMenuQueryListResponse>();
        for(SysMenuQueryListResponse childMenu:childMenuList){
            if (StringUtils.isNotBlank(childMenu.getParentId()) &&
                    sysMenu.getId().equals(childMenu.getParentId())) {
                getChild(childMenu,childMenuList);
                cList.add(childMenu);
            }
        }
        if(cList != null && cList.size() > 0){
            cList.sort(new Comparator<SysMenuQueryListResponse>() {
                @Override
                public int compare(SysMenuQueryListResponse o1, SysMenuQueryListResponse o2) {
                    Integer s1 = o1.getSort();
                    Integer s2 = o2.getSort();
                    return s1.compareTo(s2);
                }
            });
        }
        sysMenu.setChildren(cList);
    }

}
