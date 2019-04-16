package com.xz.comtroller;

import com.alibaba.fastjson.JSON;

import com.xz.api.interfaces.ISysRole;
import com.xz.exception.SystemException;
import com.xz.util.BeanUtils;
import com.xz.vo.entity.PageResponse;
import com.xz.vo.entity.Result;
import com.xz.vo.entity.ResultCode;
import com.xz.vo.request.*;
import com.xz.vo.response.SysRoleDetailResponse;
import com.xz.vo.response.SysRoleQueryListResponse;
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
 * 系统角色Controller
 * @author xuby
 * @version 2019/3/1 0001
 */
@RestController
@RequestMapping("sysRole")
@Api(value = "系统角色管理")
@Slf4j
public class SysRoleController extends BaseController{

    @Autowired
    private ISysRole iSysRole;

    /**
     * 保存角色信息
     * @param sysRoleWebForm {@link SysRoleFormWebRequest}
     * @param request
     * @return
     */
    @PostMapping(value = "saveSysRoleInfo")
    @ApiOperation(value = "保存角色信息", notes = "保存角色信息")
    public Result<String> saveSysRoleInfo(@Validated @RequestBody SysRoleFormRequest sysRoleWebForm, HttpServletRequest request) {
        log.info("============SysRoleController saveSysRoleInfo sysRoleWebForm:{}===========", JSON.toJSONString(sysRoleWebForm));
        Result<String> res = new Result<String>();
        res.setData(null);

        try{
//            SysRoleFormRequest sysRoleForm = new SysRoleFormRequest();
//            BeanUtils.copyProperties(sysRoleForm,sysRoleWebForm);
            sysRoleWebForm.setLoginName(this.getLoginName());

            SysLogHttpRequest sysLogHttpRequest = new SysLogHttpRequest(request,sysRoleWebForm.getLoginName());
            iSysRole.saveSysRoleInfo(sysRoleWebForm,sysLogHttpRequest);

            res.setStatus(ResultCode.SUCCESS.getCode());
            res.setMessage("保存角色成功!");
        }catch (SystemException e){
            log.error(e.getMessage(),e);
            e.setErrorMessage("保存角色失败!");
        }

        log.info("============SysRoleController saveSysRoleInfo end res:{}=============",JSON.toJSONString(res));
        return res;
    }

    /**
     * 删除角色信息
     * @param sysRoleDeleteWebRequest {@link SysRoleDeleteWebRequest}
     * @param request
     * @return
     */
    @PostMapping(value = "deleteSysRoleInfo")
    @ApiOperation(value = "删除角色信息", notes = "根据角色ID删除角色信息")
    public Result<String> deleteSysRoleInfo(@Validated @RequestBody SysRoleDeleteRequest sysRoleDeleteWebRequest, HttpServletRequest request) {
        log.info("===========SysRoleController deleteSysRoleInfo sysRoleDeleteWebRequest:{}============",JSON.toJSONString(sysRoleDeleteWebRequest));
        Result<String> res = new Result<String>();
        res.setData(null);

        try{
//            SysRoleDeleteRequest sysRoleDeleteRequest = new SysRoleDeleteRequest();
//            BeanUtils.copyProperties(sysRoleDeleteRequest,sysRoleDeleteWebRequest);
            sysRoleDeleteWebRequest.setLoginName(this.getLoginName());

            // 根据角色ID删除角色信息及角色菜单关联信息
            SysLogHttpRequest sysLogHttpRequest = new SysLogHttpRequest(request,sysRoleDeleteWebRequest.getLoginName());
            iSysRole.deleteSysRoleInfo(sysRoleDeleteWebRequest,sysLogHttpRequest);
            res.setStatus(ResultCode.SUCCESS.getCode());
            res.setMessage("删除角色成功!");
        }catch (SystemException e){
            log.error(e.getMessage(),e);
            e.setErrorMessage("删除角色失败!");
        }

        log.info("===========SysRoleController deleteSysRoleInfo end res:{}===========", JSON.toJSONString(res));
        return res;
    }

    /**
     * 查询角色明细信息
     * @param sysRoleQueryDetailWebRequest {@link SysRoleQueryDetailWebRequest}
     * @param request
     * @return
     */
    @PostMapping(value = "querySysRoleDetailInfo")
    @ApiOperation(value = "查询角色明细信息", notes = "根据角色ID查询角色明细信息")
    public Result<SysRoleDetailResponse> querySysRoleDetailInfo(@Validated @RequestBody SysRoleQueryDetailRequest sysRoleQueryDetailWebRequest, HttpServletRequest request) {
        log.info("===========SysRoleController querySysRoleDetailInfo sysRoleQueryDetailWebRequest:{}============",JSON.toJSONString(sysRoleQueryDetailWebRequest));
        Result<SysRoleDetailResponse> res = new Result<SysRoleDetailResponse>();

        try{
//            SysRoleQueryDetailRequest sysRoleQueryDetailRequest = new SysRoleQueryDetailRequest();
//            BeanUtils.copyProperties(sysRoleQueryDetailRequest,sysRoleQueryDetailWebRequest);
            sysRoleQueryDetailWebRequest.setLoginName(this.getLoginName());

            // 根据角色ID查询角色详细信息
            SysLogHttpRequest sysLogHttpRequest = new SysLogHttpRequest(request,sysRoleQueryDetailWebRequest.getLoginName());
            SysRoleDetailResponse response = iSysRole.querySysRoleDetailInfo(sysRoleQueryDetailWebRequest,sysLogHttpRequest);
            res.setData(response);
            res.setStatus(ResultCode.SUCCESS.getCode());
            res.setMessage("查询成功!");
        }catch (Exception e){
            log.error(e.toString(),e);
            res.setData(null);
            res.setStatus(ResultCode.ERROR.getCode());
            res.setMessage("查询失败!");
        }

        log.info("=============SysRoleController querySysRoleDetailInfo end res:{}===============",JSON.toJSONString(res));
        return res;
    }

    /**
     * 分页查询角色列表
     * @param sysRoleQueryListWebRequest {@link SysRoleQueryListWebRequest}
     * @param request
     * @return
     */
    @PostMapping(value = "querySysRoleList")
    @ApiOperation(value = "分页查询角色列表", notes = "分页查询角色列表")
    public Result<PageResponse<SysRoleQueryListResponse>> querySysRoleList(@Validated @RequestBody SysRoleQueryListRequest sysRoleQueryListWebRequest, HttpServletRequest request) {
        log.info("===========SysRoleController querySysRoleList sysRoleQueryListWebRequest:{}============",JSON.toJSONString(sysRoleQueryListWebRequest));
        Result<PageResponse<SysRoleQueryListResponse>> res = new Result<>();

        try{
//            SysRoleQueryListRequest sysRoleQueryListRequest = new SysRoleQueryListRequest();
//            BeanUtils.copyProperties(sysRoleQueryListRequest,sysRoleQueryListWebRequest);
            sysRoleQueryListWebRequest.setLoginName(this.getLoginName());

            // 分页查询角色列表
            SysLogHttpRequest sysLogHttpRequest = new SysLogHttpRequest(request,sysRoleQueryListWebRequest.getLoginName());
            PageResponse<SysRoleQueryListResponse> response = iSysRole.querySysRoleList(sysRoleQueryListWebRequest,sysLogHttpRequest);
            res.setData(response);
            res.setStatus(ResultCode.SUCCESS.getCode());
            res.setMessage("查询成功!");
        }catch (Exception e){
            log.error(e.toString(),e);
            res.setData(null);
            res.setStatus(ResultCode.ERROR.getCode());
            res.setMessage("查询失败!");
        }

        log.info("=============SysRoleController querySysRoleList end res:{}===============",JSON.toJSONString(res));
        return res;
    }

    /**
     * 不分页查询角色列表
     * @param sysRoleQueryListWebRequest {@link SysRoleQueryAllListWebRequest}
     * @param request
     * @return
     */
    @PostMapping(value = "queryAllList")
    @ApiOperation(value = "不分页查询角色列表", notes = "不分页查询角色列表")
    public Result<List<SysRoleQueryListResponse>> queryAllList(@Validated @RequestBody SysRoleQueryAllListRequest sysRoleQueryListWebRequest, HttpServletRequest request) {
        log.info("===========SysRoleController queryAllList request:{}============",JSON.toJSONString(sysRoleQueryListWebRequest));

        Result<List<SysRoleQueryListResponse>> res = new Result<>();

        try{
//            SysRoleQueryAllListRequest sysRoleQueryAllListRequest = new SysRoleQueryAllListRequest();
//            BeanUtils.copyProperties(sysRoleQueryAllListRequest,sysRoleQueryListWebRequest);
            sysRoleQueryListWebRequest.setLoginName(this.getLoginName());

            // 不分页查询角色列表
            SysLogHttpRequest sysLogHttpRequest = new SysLogHttpRequest(request,sysRoleQueryListWebRequest.getLoginName());
            List<SysRoleQueryListResponse> response = iSysRole.queryAllList(sysRoleQueryListWebRequest,sysLogHttpRequest);
            res.setData(response);
            res.setStatus(ResultCode.SUCCESS.getCode());
            res.setMessage("查询成功!");
        }catch (Exception e){
            log.error(e.toString(),e);
            res.setData(null);
            res.setStatus(ResultCode.ERROR.getCode());
            res.setMessage("查询失败!");
        }

        log.info("=============SysRoleController queryAllList end res:{}===============",JSON.toJSONString(res));
        return res;
    }

}
