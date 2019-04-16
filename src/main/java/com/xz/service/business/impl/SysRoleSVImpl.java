package com.xz.service.business.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.xz.entity.BopSysRole;
import com.xz.entity.BopSysRoleMenu;
import com.xz.entity.BopSysUserRole;
import com.xz.service.BaseService;
import com.xz.service.atom.interfaces.ISysRoleAtomSV;
import com.xz.service.business.interfaces.ISysRoleSV;
import com.xz.util.BeanUtils;
import com.xz.util.StringUtils;
import com.xz.vo.entity.PageResponse;
import com.xz.vo.request.SysRoleQueryAllListRequest;
import com.xz.vo.request.SysRoleQueryListRequest;
import com.xz.vo.response.SysRoleQueryListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 系统角色服务实现类
 * @author xuby
 * @version 2019/2/26 0026
 */
@Service
@Slf4j
@Transactional
public class SysRoleSVImpl extends BaseService implements ISysRoleSV {

    @Autowired
    private ISysRoleAtomSV iSysRoleAtomSV;

    /**
     * 保存系统角色信息
     * @param sysRole {@link BopSysRole}
     */
    @Override
    @Transactional(readOnly = false)
    public void saveRoleInfo(BopSysRole sysRole) {
        log.info("---SysRoleSVImpl saveRoleInfo sysRole:{}---", JSON.toJSONString(sysRole));

        if(StringUtils.isNotBlank(sysRole.getId())){
            sysRole.preUpdate();
            iSysRoleAtomSV.update(sysRole);
            // 删除角色菜单信息表
            iSysRoleAtomSV.deleteRoleMenu(sysRole);
        }else{
            sysRole.preInsert();
            iSysRoleAtomSV.insert(sysRole);
        }
        if(sysRole != null && sysRole.getMenuList() != null && sysRole.getMenuList().size() > 0){
            // 保存角色菜单信息表
            iSysRoleAtomSV.insertRoleMenu(sysRole);
        }
    }

    /**
     * 删除角色信息
     * @param id 角色ID
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteRoleInfo(String id) {
        log.info("----SysRoleSVImpl deleteRoleInfo id:{}----",id);

        BopSysRole sysRole = new BopSysRole();
        sysRole.setId(id);
        //删除角色信息
        iSysRoleAtomSV.delete(sysRole);
        //删除角色菜单信息
        iSysRoleAtomSV.deleteRoleMenu(sysRole);
    }

    /**
     * 根据角色ID获取角色信息
     * @param id 角色ID
     * @return
     */
    @Override
    public BopSysRole get(String id) {
        log.info("----SysRoleSVImpl get id:{}-----",id);
        return iSysRoleAtomSV.get(id);
    }

    /**
     * 分页查询角色信息
     * @param request {@link SysRoleQueryListRequest}
     * @return
     */
    @Override
    public PageResponse<SysRoleQueryListResponse> findPage(SysRoleQueryListRequest request) {
        log.info("---SysRoleSVImpl findPage request:{}---",JSON.toJSONString(request));
        BopSysRole sysRole = new BopSysRole();
        BeanUtils.copyProperties(sysRole,request);
        PageHelper.startPage(Integer.valueOf(request.getPageNo()),Integer.valueOf(request.getPageSize()));

        List<BopSysRole> sysRoleList = iSysRoleAtomSV.findAllList(sysRole);
        List<SysRoleQueryListResponse> resultList = new ArrayList<SysRoleQueryListResponse>();
        if(sysRoleList != null && sysRoleList.size() > 0){
            for(BopSysRole s:sysRoleList){
                SysRoleQueryListResponse sysRoleResponse = new SysRoleQueryListResponse();
                BeanUtils.copyProperties(sysRoleResponse,s);
                resultList.add(sysRoleResponse);
            }
        }

        return new PageResponse<SysRoleQueryListResponse>(((Page<BopSysRole>)sysRoleList).getTotal(),resultList);
    }

    /**
     * 不分页查询所有的角色信息
     * @param request {@link SysRoleQueryListRequest}
     * @return
     */
    @Override
    public List<SysRoleQueryListResponse> findAllList(SysRoleQueryAllListRequest request) {
        log.info("---SysRoleSVImpl findAllList request:{}---",JSON.toJSONString(request));
        BopSysRole sysRole = new BopSysRole();
        BeanUtils.copyProperties(sysRole,request);

        List<BopSysRole> sysRoleList = iSysRoleAtomSV.findAllList(sysRole);
        List<SysRoleQueryListResponse> resultList = new ArrayList<SysRoleQueryListResponse>();
        if(sysRoleList != null && sysRoleList.size() > 0){
            for(BopSysRole s:sysRoleList){
                if("assignment".equals(s.getRoleType())){
                    continue;
                }
                SysRoleQueryListResponse sysRoleResponse = new SysRoleQueryListResponse();
                BeanUtils.copyProperties(sysRoleResponse,s);
                resultList.add(sysRoleResponse);
            }
        }

        return resultList;
    }

    /**
     * 保存用户角色关联关系
     * @param sysUserRole {@link BopSysUserRole}
     */
    @Override
    public void saveUserRoleInfo(BopSysUserRole sysUserRole) {
        log.info("---SysRoleSVImpl saveUserRoleInfo sysUserRole:{}---",JSON.toJSONString(sysUserRole));
        if(StringUtils.isNotBlank(sysUserRole.getUserId())){
            // 删除用户角色关联关系
            iSysRoleAtomSV.deleteUserRole(sysUserRole);
            //新增用户角色关联关系
            iSysRoleAtomSV.insertUserRole(sysUserRole);
        }

    }

    /**
     * 根据用户ID查询用户角色ID
     * @param userId 用户ID
     * @return
     */
    @Override
    public String[] queryUserRoleIdForUserId(String userId) {
        log.info("---SysRoleSVImpl queryUserRoleIdForUserId userId:{}---",userId);
        if(StringUtils.isNotBlank(userId)){
            BopSysUserRole sysUserRole = new BopSysUserRole();
            sysUserRole.setUserId(userId);
            // 根据用户ID查询用户角色ID
            List<BopSysUserRole> userRoleList = iSysRoleAtomSV.findUserRoleByUserId(sysUserRole);
            if(userRoleList != null && userRoleList.size() > 0){
                String[] roleIds = new String[userRoleList.size()];
                for(int i = 0;i<userRoleList.size();i++){
                    roleIds[i] = userRoleList.get(i).getRoleId();
                }
                return roleIds;
            }
        }
        return null;
    }

    /**
     * 系统所有角色都重新赋权
     * @param menuSet 菜单ID集合
     * @param roleId 角色ID
     */
    @Override
    public void saveRoleDefaultMenuInfoForMenuId(Set<String> menuSet, String roleId){
        List<String> menuIdList = new ArrayList<>(menuSet);
        BopSysRole sysRole = new BopSysRole();
        sysRole.setId(roleId);
        sysRole.setMenuIdList(menuIdList);
        iSysRoleAtomSV.insertRoleMenuForMenuId(sysRole);
    }

    /**
     * 查询所有角色菜单关联信息
     * @return
     */
    @Override
    public List<BopSysRole> fineRoleMenu() {
        return iSysRoleAtomSV.fineRoleMenu();
    }

    /**
     * 根据菜单ID和角色ID删除菜单角色关联关系
     * @param menuIds 菜单ID集合
     * @param roleId 角色ID
     */
    @Override
    public void deleteRoleMenuForRoleIdAndMenuId(Set<String> menuIds, String roleId) {
        List<String> menuIdList = new ArrayList<>(menuIds);
        for(String menuId:menuIdList){
            BopSysRoleMenu bopSysRoleMenu = new BopSysRoleMenu();
            bopSysRoleMenu.setMenuId(menuId);
            bopSysRoleMenu.setRoleId(roleId);
            iSysRoleAtomSV.deleteRoleMenuForRoleIdAndMenuId(bopSysRoleMenu);
        }
    }

}
