package com.xz.comtroller;

import com.alibaba.fastjson.JSON;

import com.xz.api.interfaces.ISysLog;
import com.xz.vo.entity.PageResponse;
import com.xz.vo.entity.Result;
import com.xz.vo.entity.ResultCode;
import com.xz.vo.request.SysLogQueryListRequest;
import com.xz.vo.response.SysLogQueryListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统操作日志Controller
 * @author xuby
 * @version 2019/3/1 0001
 */
@RestController
@RequestMapping("sysLog")
@Api(value = "系统操作日志管理")
@Slf4j
public class SysLogController extends BaseController {

    @Autowired
    private ISysLog iSysLog;

    /**
     * 系统操作日志服务实现
     * @param request {@link SysLogQueryListRequest}
     * @return
     */
    @PostMapping(value = "querySysLogPage")
    @ApiOperation(value = "分页查询系统操作日志列表",notes = "分页查询系统操作日志列表")
    public Result<PageResponse<SysLogQueryListResponse>> querySysLogPage(@Validated @ModelAttribute SysLogQueryListRequest request) {
        log.info("===============SysLogController querySysLogPage request:{}==============", JSON.toJSONString(request));
        Result<PageResponse<SysLogQueryListResponse>> res = new Result<PageResponse<SysLogQueryListResponse>>();

        try{
            PageResponse<SysLogQueryListResponse> responseList = iSysLog.querySysLogPage(request);
            res.setData(responseList);
            res.setStatus(ResultCode.SUCCESS.getCode());
            res.setMessage("查询成功!");
        }catch (Exception e){
            log.error(e.toString(),e);
            res.setData(null);
            res.setStatus(ResultCode.ERROR.getCode());
            res.setMessage("查询失败!");
        }

        log.info("===============SysLogController querySysLogPage end res:{}==============",JSON.toJSONString(res));
        return res;
    }
}
