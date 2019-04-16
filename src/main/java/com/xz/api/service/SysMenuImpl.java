package com.xz.api.service;

import com.alibaba.fastjson.JSON;
import com.xz.api.interfaces.ISysMenu;
import com.xz.entity.BopSysMenu;
import com.xz.entity.BopSysUser;
import com.xz.entity.BopSysUserRole;
import com.xz.exception.SystemException;
import com.xz.service.BaseService;
import com.xz.service.business.interfaces.ISysMenuSV;
import com.xz.service.business.interfaces.ISysUserSV;
import com.xz.util.BeanUtils;
import com.xz.util.StringUtils;
import com.xz.util.SysLogUtils;
import com.xz.vo.request.*;
import com.xz.vo.response.SysMenuDetailResponse;
import com.xz.vo.response.SysMenuQueryListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 系统菜单服务接口实现
 *
 * @author xuby
 * @version 2019/2/26 0026
 */
@Slf4j
@Service
//@Service(interfaceClass = ISysMenu.class, retries = -1, version = "0.0.1",validation="true")
public class SysMenuImpl extends BaseService implements ISysMenu {

    @Autowired
    private ISysMenuSV iSysMenuSV;
    @Autowired
    private ISysUserSV iSysUserSV;

    /**
     * 保存菜单信息
     *
     * @param sysMenuForm {@link SysMenuFormRequest}
     * @return
     */
    @Override
    public void saveMenuInfo(SysMenuFormRequest sysMenuForm, SysLogHttpRequest request) {
        log.info("===========SysMenuImpl saveMenuInfo sysMenuForm:{}============", JSON.toJSONString(sysMenuForm));
        try {
            //获取当前登录人信息
            BopSysUser bopSysUser = iSysUserSV.selectSysUserByLoginName(sysMenuForm.getLoginName());

            BopSysMenu sysMenu = change(sysMenuForm, BopSysMenu.class);
            sysMenu.setCreateBy(bopSysUser.getId());
            sysMenu.setUpdateBy(bopSysUser.getId());
            //保存菜单信息
            iSysMenuSV.saveMenuInfo(sysMenu);

            // 保存系统操作日志
            SysLogUtils.saveLogInfo(request, "1", "保存菜单信息");
        } catch (Exception e) {
            log.error(e.toString(), e);
            throw new SystemException("保存菜单信息失败!");
        }

        log.info("===========SysMenuImpl saveMenuInfo end=========");
    }

    /**
     * 删除菜单信息
     *
     * @param sysMenuDeleteRequest {@link SysMenuDeleteRequest}
     * @return
     */
    @Override
    public void deleteMenuInfo(SysMenuDeleteRequest sysMenuDeleteRequest, SysLogHttpRequest request) {
        log.info("============SysMenuImpl deleteMenuInfo request:{}==========", JSON.toJSONString(sysMenuDeleteRequest));
        try {
            //根据菜单ID删除菜单信息
            iSysMenuSV.deleteMenuInfo(sysMenuDeleteRequest.getId());

            // 保存系统操作日志
            SysLogUtils.saveLogInfo(request, "1", "删除菜单信息");
        } catch (Exception e) {
            log.error(e.toString(), e);
            throw new SystemException("删除菜单失败!");
        }

        log.info("============SysMenuImpl deleteMenuInfo end============");
    }

    /**
     * 根据菜单ID查询菜单详细信息
     *
     * @param sysMenuQueryDetailRequest {@link SysMenuQueryDetailRequest}
     * @return
     */
    @Override
    public SysMenuDetailResponse queryMenuDetailForId(SysMenuQueryDetailRequest sysMenuQueryDetailRequest, SysLogHttpRequest request) {
        log.info("============SysMenuImpl queryMenuDetailForId request:{}===========", JSON.toJSONString(sysMenuQueryDetailRequest));

        SysMenuDetailResponse sysMenuDetail = new SysMenuDetailResponse();
        try {

            BopSysMenu sysMenu = iSysMenuSV.get(sysMenuQueryDetailRequest.getId());
            if (sysMenu != null) {
                BeanUtils.copyProperties(sysMenuDetail, sysMenu);
                if (sysMenu.getParent() != null) {
                    sysMenuDetail.setParentName(sysMenu.getParent().getName());
                }
            }
            // 保存系统操作日志
            SysLogUtils.saveLogInfo(request, "1", "查询菜单详情信息");
        } catch (Exception e) {
            log.error(e.toString(), e);
            throw new SystemException("查询菜单详情失败!");
        }

        log.info("============SysMenuImpl queryMenuDetailForId end res:{}===========", JSON.toJSONString(sysMenuDetail));
        return sysMenuDetail;
    }

    /**
     * 查询所有菜单信息(平行结构)
     *
     * @param sysMenuQueryRequest {@link SysMenuQueryRequest}
     * @return
     */
    @Override
    public List<SysMenuQueryListResponse> queryAllMenuList(SysMenuQueryRequest sysMenuQueryRequest, SysLogHttpRequest request) {
        log.info("============SysMenuImpl queryAllMenuList sysMenuQueryRequest:{}============", JSON.toJSONString(sysMenuQueryRequest));
        //查询所有菜单信息
        List<SysMenuQueryListResponse> resultList = new ArrayList<SysMenuQueryListResponse>();

        try {
            BopSysMenu sysMenu = change(sysMenuQueryRequest, BopSysMenu.class);

            if (StringUtils.isBlank(sysMenuQueryRequest.getUserId())) {
                //用户ID为空，则查询系统全部菜单
                resultList = iSysMenuSV.queryAllList(sysMenu);
            } else {
                //用户ID不为空，则查询用户拥有菜单
                BopSysUserRole sysUserRole = new BopSysUserRole();
                sysUserRole.setUserId(sysMenuQueryRequest.getUserId());
                resultList = iSysMenuSV.queryUserMenuList(sysUserRole);
            }

            //保存日志信息
            SysLogUtils.saveLogInfo(request, "1", "查询系统菜单信息-平行结构");

        } catch (Exception e) {
            log.error(e.toString(), e);
            throw new SystemException("查询菜单信息失败!");
        }

        log.info("=============SysMenuImpl queryAllMenuList end res:{}=============", JSON.toJSONString(resultList));
        return resultList;
    }

    /**
     * 查询所有菜单信息(树形结构)
     *
     * @param sysMenuQueryRequest {@link SysMenuQueryRequest}
     * @return
     */
    @Override
    public List<SysMenuQueryListResponse> queryAllMenuListTree(SysMenuQueryRequest sysMenuQueryRequest, SysLogHttpRequest request) {
        log.info("============SysMenuImpl queryAllMenuList queryAllMenuListTree:{}============", JSON.toJSONString(sysMenuQueryRequest));

        List<SysMenuQueryListResponse> resultList = new ArrayList<SysMenuQueryListResponse>();
        try {
            BopSysMenu sysMenu = change(sysMenuQueryRequest, BopSysMenu.class);
            //查询所有菜单信息

            if (StringUtils.isBlank(sysMenuQueryRequest.getUserId())) {
                //用户ID为空，则查询系统全部菜单
                resultList = iSysMenuSV.queryAllListForTree(sysMenu);
            } else {
                //用户ID不为空，则查询用户拥有菜单
                BopSysUserRole sysUserRole = new BopSysUserRole();
                sysUserRole.setUserId(sysMenuQueryRequest.getUserId());
                resultList = iSysMenuSV.queryUserMenuListForTree(sysUserRole);
            }

            //保存日志信息
            SysLogUtils.saveLogInfo(request, "1", "查询系统菜单信息-树形结构");

        } catch (Exception e) {
            log.error(e.toString(), e);
            throw new SystemException("查询菜单信息失败!");
        }

        log.info("=============SysMenuImpl queryAllMenuListTree end res:{}=============", JSON.toJSONString(resultList));
        return resultList;
    }

}
