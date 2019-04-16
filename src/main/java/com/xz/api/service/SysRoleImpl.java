package com.xz.api.service;

import com.alibaba.fastjson.JSON;
import com.xz.api.interfaces.ISysRole;
import com.xz.entity.BopSysMenu;
import com.xz.entity.BopSysRole;
import com.xz.entity.BopSysUser;
import com.xz.exception.SystemException;
import com.xz.service.BaseService;
import com.xz.service.business.interfaces.ISysRoleSV;
import com.xz.service.business.interfaces.ISysUserSV;
import com.xz.util.BeanUtils;
import com.xz.util.StringUtils;
import com.xz.util.SysLogUtils;
import com.xz.vo.entity.PageResponse;
import com.xz.vo.request.*;
import com.xz.vo.response.SysRoleDetailResponse;
import com.xz.vo.response.SysRoleQueryListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统角色服务接口实现类
 *
 * @author xuby
 * @version 2019/2/26 0026
 */
@Slf4j
@Service
public class SysRoleImpl extends BaseService implements ISysRole {

    @Autowired
    private ISysRoleSV iSysRoleSV;
    @Autowired
    private ISysUserSV iSysUserSV;

    /**
     * 保存角色信息
     *
     * @param sysRoleForm {@link SysRoleFormRequest}
     * @return
     */
    @Override
    public void saveSysRoleInfo(SysRoleFormRequest sysRoleForm, SysLogHttpRequest request) {
        log.info("============SysRoleImpl saveSysRoleInfo sysRoleForm:{}===========", JSON.toJSONString(sysRoleForm));
        try {
            //获取当前登录人信息
            BopSysUser bopSysUser = iSysUserSV.selectSysUserByLoginName(sysRoleForm.getLoginName());

            BopSysRole sysRole = change(sysRoleForm, BopSysRole.class);
            sysRole.setCreateBy(bopSysUser.getId());
            sysRole.setUpdateBy(bopSysUser.getId());

            if (sysRoleForm.getMenuIdList() != null && sysRoleForm.getMenuIdList().size() > 0) {
                List<BopSysMenu> sysMenuList = new ArrayList<>();
                for (String id : sysRoleForm.getMenuIdList()) {
                    BopSysMenu sysMenu = new BopSysMenu();
                    sysMenu.setId(id);
                    sysMenuList.add(sysMenu);
                }
                sysRole.setMenuList(sysMenuList);
            }

            // 保存角色信息及角色菜单关联信息
            iSysRoleSV.saveRoleInfo(sysRole);

            // 保存系统操作日志
            SysLogUtils.saveLogInfo(request, "1", "保存角色信息");

        } catch (Exception e) {
            log.error(e.toString(), e);
            throw new SystemException("保存角色失败!");
        }

        log.info("============SysRoleImpl saveSysRoleInfo end end:{}=============");
    }

    /**
     * 删除角色信息
     *
     * @param sysRoleDeleteRequest {@link SysRoleDeleteRequest}
     * @return
     */
    @Override
    public void deleteSysRoleInfo(SysRoleDeleteRequest sysRoleDeleteRequest, SysLogHttpRequest request) {
        log.info("===========SysRoleImpl deleteSysRoleInfo request:{}============", JSON.toJSONString(sysRoleDeleteRequest));
        try {
            // 根据角色ID删除角色信息及角色菜单关联信息
            iSysRoleSV.deleteRoleInfo(sysRoleDeleteRequest.getId());

            // 保存系统操作日志
            SysLogUtils.saveLogInfo(request, "1", "删除角色信息");
        } catch (Exception e) {
            log.error(e.toString(), e);
            throw new SystemException("删除角色失败!");
        }

        log.info("===========SysRoleImpl deleteSysRoleInfo end end:{}===========");
    }

    /**
     * 查询角色明细信息
     *
     * @param request {@link SysRoleQueryDetailRequest}
     * @return
     */
    @Override
    public SysRoleDetailResponse querySysRoleDetailInfo(SysRoleQueryDetailRequest sysRoleQueryDetailRequest, SysLogHttpRequest request) {
        log.info("=============SysRoleImpl querySysRoleDetailInfo request:{}==============", JSON.toJSONString(request));

        try {
            // 根据角色ID查询角色详细信息
            BopSysRole sysRole = iSysRoleSV.get(sysRoleQueryDetailRequest.getId());

            if (sysRole != null && sysRole.getMenuList() != null && sysRole.getMenuList().size() > 0) {
                for (BopSysMenu sysMenu : sysRole.getMenuList()) {
                    if (StringUtils.isNotBlank(sysMenu.getParentIds())) {
                        sysMenu.setMenuLevel(sysMenu.getParentIds().split(",").length);
                    } else {
                        sysMenu.setMenuLevel(0);
                    }
                }
            }

            SysRoleDetailResponse response = new SysRoleDetailResponse();
            BeanUtils.copyProperties(response, sysRole);

            // 保存系统操作日志
            SysLogUtils.saveLogInfo(request, "1", "查询角色详细信息");

            log.info("=============SysRoleImpl querySysRoleDetailInfo end res:{}===============", JSON.toJSONString(response));
            return response;
        } catch (Exception e) {
            log.error(e.toString(), e);
            throw new SystemException("查询角色详情失败!");
        }
    }

    /**
     * 分页查询角色列表
     *
     * @param sysRoleQueryListRequest {@link SysRoleQueryListRequest}
     * @return
     */
    @Override
    public PageResponse<SysRoleQueryListResponse> querySysRoleList(SysRoleQueryListRequest sysRoleQueryListRequest, SysLogHttpRequest request) {
        log.info("=============SysRoleImpl querySysRoleList request:{}==============", JSON.toJSONString(sysRoleQueryListRequest));
        try {
            PageResponse<SysRoleQueryListResponse> pageResponse = iSysRoleSV.findPage(sysRoleQueryListRequest);

            // 保存系统操作日志
            SysLogUtils.saveLogInfo(request, "1", "分页查询角色列表");

            log.info("=============SysRoleImpl querySysRoleList end res:{}==============", JSON.toJSONString(pageResponse));
            return pageResponse;
        } catch (Exception e) {
            log.error(e.toString(), e);
            throw new SystemException("分页查询角色列表失败!");
        }

    }

    /**
     * 不分页查询所有的角色信息
     *
     * @param sysRoleQueryAllListRequest {@link SysRoleQueryAllListRequest}
     * @return
     */
    @Override
    public List<SysRoleQueryListResponse> queryAllList(SysRoleQueryAllListRequest sysRoleQueryAllListRequest, SysLogHttpRequest request) {
        log.info("=============SysRoleImpl queryAllList request:{}==============", JSON.toJSONString(sysRoleQueryAllListRequest));
        try {
            List<SysRoleQueryListResponse> resultList = iSysRoleSV.findAllList(sysRoleQueryAllListRequest);

            // 保存系统操作日志
            SysLogUtils.saveLogInfo(request, "1", "不分页查询所有的角色信息");

            log.info("=============SysRoleImpl queryAllList end res:{}==============", JSON.toJSONString(resultList));
            return resultList;
        } catch (Exception e) {
            log.error(e.toString(), e);
            throw new SystemException("不分页查询角色信息失败!");
        }

    }

}
