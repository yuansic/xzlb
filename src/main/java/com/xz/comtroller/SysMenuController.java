package com.xz.comtroller;

import com.alibaba.fastjson.JSON;

import com.xz.api.interfaces.ISysMenu;
import com.xz.exception.SystemException;
import com.xz.util.BeanUtils;
import com.xz.util.StringUtils;
import com.xz.vo.entity.Result;
import com.xz.vo.entity.ResultCode;
import com.xz.vo.request.*;
import com.xz.vo.response.SysMenuDetailResponse;
import com.xz.vo.response.SysMenuQueryListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 菜单管理Controller
 * @author xuby
 * @version 2019/3/1 0001
 */
@RestController
@RequestMapping("sysMenu")
@Api(value = "系统菜单管理管理")
@Slf4j
public class SysMenuController extends BaseController {

    @Autowired
    private ISysMenu iSysMenu;

    private static final String ADMIN = "admin";
    /**
     * 保存菜单信息
     * @param sysMenuForm
     * @return res {@link Result <String>}
     */
    @PostMapping(value = "saveMenuInfo")
    @ApiOperation(value = "保存菜单信息", notes = "保存菜单信息")
    public Result<String> saveMenuInfo(@Validated @RequestBody SysMenuFormRequest sysMenuForm, HttpServletRequest request) {
        log.info("===========SysMenuController saveMenuInfo sysMenuForm:{}============", JSON.toJSONString(sysMenuForm));

        Result<String> res = new Result<String>();
        res.setData(null);
        try{
//            SysMenuFormRequest sysMenuFormRequest = new SysMenuFormRequest();
//            BeanUtils.copyProperties(sysMenuFormRequest,sysMenuForm);
            sysMenuForm.setLoginName(this.getLoginName());
            SysLogHttpRequest sysLogHttpRequest = new SysLogHttpRequest(request,sysMenuForm.getLoginName());

            iSysMenu.saveMenuInfo(sysMenuForm,sysLogHttpRequest);

            res.setStatus(ResultCode.SUCCESS.getCode());
            res.setMessage("保存菜单成功!");
        }catch (SystemException e){
            log.error(e.getMessage(),e);
            e.setErrorMessage("保存菜单失败!");
        }

        log.info("===========SysMenuController saveMenuInfo end res:{}=========",JSON.toJSONString(res));
        return res;
    }

    /**
     * 删除菜单信息
     * @param sysMenuDeleteWebRequest
     * @return res {@link Result<String>}
     */
    @PostMapping(value = "deleteMenuInfo")
    @ApiOperation(value = "删除菜单信息", notes = "删除菜单信息")
    public Result<String> deleteMenuInfo(@Validated @RequestBody SysMenuDeleteRequest sysMenuDeleteWebRequest, HttpServletRequest request) {
        log.info("============SysMenuController deleteMenuInfo request:{}==========",JSON.toJSONString(sysMenuDeleteWebRequest));
        Result<String> res = new Result<String>();
        res.setData(null);

        try{
//            SysMenuDeleteRequest sysMenuDeleteRequest = new SysMenuDeleteRequest();
//            BeanUtils.copyProperties(sysMenuDeleteRequest,sysMenuDeleteWebRequest);
            sysMenuDeleteWebRequest.setLoginName(this.getLoginName());

            //根据菜单ID删除菜单信息
            SysLogHttpRequest sysLogHttpRequest = new SysLogHttpRequest(request,sysMenuDeleteWebRequest.getLoginName());
            iSysMenu.deleteMenuInfo(sysMenuDeleteWebRequest, sysLogHttpRequest);
            res.setStatus(ResultCode.SUCCESS.getCode());
            res.setMessage("删除菜单成功!");
        }catch (SystemException e){
            log.error(e.getMessage(),e);
            e.setErrorMessage("删除菜单失败!");
        }

        log.info("============SysMenuController deleteMenuInfo end res:{}============",JSON.toJSONString(res));
        return res;
    }

    /**
     * 根据菜单ID查询菜单详细信息
     * @param sysMenuQueryDetailWebRequest
     * @return res {@link Result<  SysMenuDetailResponse  >}
     */
    @PostMapping(value = "queryMenuDetailForId")
    @ApiOperation(value = "查询菜单详情信息", notes = "根据菜单ID查询菜单详细信息")
    public Result<SysMenuDetailResponse> queryMenuDetailForId(@Validated @RequestBody SysMenuQueryDetailRequest sysMenuQueryDetailWebRequest, HttpServletRequest request) {
        log.info("============SysMenuController queryMenuDetailForId request:{}===========",JSON.toJSONString(sysMenuQueryDetailWebRequest));
        Result<SysMenuDetailResponse> res = new Result<SysMenuDetailResponse>();

        try{
//            SysMenuQueryDetailRequest sysMenuQueryDetailRequest = new SysMenuQueryDetailRequest();
//            BeanUtils.copyProperties(sysMenuQueryDetailRequest,sysMenuQueryDetailWebRequest);
            sysMenuQueryDetailWebRequest.setLoginName(this.getLoginName());

            SysLogHttpRequest sysLogHttpRequest = new SysLogHttpRequest(request,sysMenuQueryDetailWebRequest.getLoginName());
            SysMenuDetailResponse response = iSysMenu.queryMenuDetailForId(sysMenuQueryDetailWebRequest, sysLogHttpRequest);
            res.setData(response);
            res.setStatus(ResultCode.SUCCESS.getCode());
            res.setMessage("查询成功!");

        }catch (SystemException e){
            log.error(e.getMessage(),e);
            e.setErrorMessage("菜单详情查询失败!");
        }

        log.info("============SysMenuController queryMenuDetailForId end res:{}===========",JSON.toJSONString(res));
        return res;
    }

    /**
     * 查询所有菜单信息(平行结构)
     * @param sysMenuQueryWebRequest
     * @return
     */
    @PostMapping(value = "queryAllMenuList")
    @ApiOperation(value = "查询菜单信息", notes = "查询所有菜单信息(平行结构)")
    public Result<List<SysMenuQueryListResponse>> queryAllMenuList(@Validated @RequestBody SysMenuQueryRequest sysMenuQueryWebRequest, HttpServletRequest request) {
        log.info("============SysMenuController queryAllMenuList sysMenuQueryRequest:{}============",JSON.toJSONString(sysMenuQueryWebRequest));

        Result<List<SysMenuQueryListResponse>> res = new Result<List<SysMenuQueryListResponse>>();
        try{
//            SysMenuQueryRequest sysMenuQueryRequest = new SysMenuQueryRequest();
//            BeanUtils.copyProperties(sysMenuQueryRequest,sysMenuQueryWebRequest);
            sysMenuQueryWebRequest.setLoginName(this.getLoginName());

            //查询所有菜单信息
            SysLogHttpRequest sysLogHttpRequest = new SysLogHttpRequest(request,sysMenuQueryWebRequest.getLoginName());
            List<SysMenuQueryListResponse> resultList = iSysMenu.queryAllMenuList(sysMenuQueryWebRequest, sysLogHttpRequest);
            res.setData(resultList);
            res.setStatus(ResultCode.SUCCESS.getCode());
            res.setMessage("查询成功!");
        }catch (SystemException e){
            log.error(e.getMessage(),e);
            e.setErrorMessage("菜单查询失败!");
        }

        log.info("=============SysMenuController queryAllMenuList end res:{}=============",JSON.toJSONString(res));
        return res;
    }

    /**
     * 查询所有菜单信息(树形结构)
     * @param sysMenuQueryWebRequest
     * @return
     */
    @PostMapping(value = "queryAllMenuListTree")
    @ApiOperation(value = "查询菜单信息", notes = "查询所有菜单信息(树形结构)")
    public Result<List<SysMenuQueryListResponse>> queryAllMenuListTree(@Validated @RequestBody SysMenuQueryRequest sysMenuQueryWebRequest, HttpServletRequest request) {
        log.info("============SysMenuController queryAllMenuList queryAllMenuListTree:{}============",JSON.toJSONString(sysMenuQueryWebRequest));

        Result<List<SysMenuQueryListResponse>> res = new Result<List<SysMenuQueryListResponse>>();
        try{
//            SysMenuQueryRequest sysMenuQueryRequest = new SysMenuQueryRequest();
//            BeanUtils.copyProperties(sysMenuQueryRequest,sysMenuQueryWebRequest);
            sysMenuQueryWebRequest.setLoginName(this.getLoginName());

            //查询所有菜单信息
            SysLogHttpRequest sysLogHttpRequest = new SysLogHttpRequest(request,sysMenuQueryWebRequest.getLoginName());
            List<SysMenuQueryListResponse> resultList = iSysMenu.queryAllMenuListTree(sysMenuQueryWebRequest,sysLogHttpRequest);
            res.setData(resultList);
            res.setStatus(ResultCode.SUCCESS.getCode());
            res.setMessage("查询成功!");
        }catch (SystemException e){
            log.error(e.getMessage(),e);
            e.setErrorMessage("菜单查询失败!");
        }

        log.info("=============SysMenuController queryAllMenuListTree end res:{}=============",JSON.toJSONString(res));
        return res;
    }


    /**
     * 查询用户菜单信息(树形结构)
     * @param sysMenuQueryWebRequest
     * @return
     */
    @PostMapping(value = "queryUserMenuListTree")
    @ApiOperation(value = "查询用户菜单信息", notes = "查询用户菜单信息(树形结构)")
    public Result<List<SysMenuQueryListResponse>> queryUserMenuListTree(@Validated @RequestBody SysMenuQueryRequest sysMenuQueryWebRequest, HttpServletRequest request) {
        log.info("============SysMenuController queryAllMenuList queryUserMenuListTree:{}============",JSON.toJSONString(sysMenuQueryWebRequest));
        Result<List<SysMenuQueryListResponse>> res = new Result<List<SysMenuQueryListResponse>>();
        if(StringUtils.isBlank(sysMenuQueryWebRequest.getUserId())){
            res.setData(null);
            res.setStatus(ResultCode.ERROR.getCode());
            res.setMessage("用户ID不能为空!");
        }

        try{
//            SysMenuQueryRequest sysMenuQueryRequest = new SysMenuQueryRequest();
//            BeanUtils.copyProperties(sysMenuQueryRequest,sysMenuQueryWebRequest);
            sysMenuQueryWebRequest.setLoginName(this.getLoginName());

            if(this.getLoginName().equals(ADMIN)){
                // admin默认查询全部
                sysMenuQueryWebRequest.setUserId("");
            }

            //查询所有菜单信息
            SysLogHttpRequest sysLogHttpRequest = new SysLogHttpRequest(request,sysMenuQueryWebRequest.getLoginName());
            List<SysMenuQueryListResponse> resultList = iSysMenu.queryAllMenuListTree(sysMenuQueryWebRequest,sysLogHttpRequest);
            res.setData(resultList);
            res.setStatus(ResultCode.SUCCESS.getCode());
            res.setMessage("查询成功!");
        }catch (SystemException e){
            log.error(e.getMessage(),e);
            e.setErrorMessage("菜单查询失败!");
        }

        log.info("=============SysMenuController queryUserMenuListTree end res:{}=============",JSON.toJSONString(res));
        return res;
    }

}
