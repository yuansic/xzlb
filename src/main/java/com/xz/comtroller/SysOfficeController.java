package com.xz.comtroller;


import com.xz.api.interfaces.ISysOffice;
import com.xz.vo.entity.Result;
import com.xz.vo.entity.ResultCode;
import com.xz.vo.response.BopSysOfficeListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuansc
 * @date 2019/3/5 0005 下午 6:43
 */

@Api("系统机构")
@RestController
@RequestMapping("sysOffice")
@Slf4j
public class SysOfficeController extends BaseController {

    @Autowired
    private ISysOffice iSysOffice;

    @PostMapping("querySysOfficeList")
    @ApiOperation(value = "机构查询平行", notes = "机构查询平行")
    public Result<Object> querySysOfficeList(){
        log.info("querySysOfficeList");

        Result<Object> re = new Result<Object>();
        re.setStatus(ResultCode.ERROR.getCode());
        re.setMessage(ResultCode.ERROR.getMessage());

        try {
            List<BopSysOfficeListResponse> bopSysOfficeListResponseList = iSysOffice.querySysOfficeList();
            if(bopSysOfficeListResponseList != null && !bopSysOfficeListResponseList.isEmpty()){
                re.setStatus(ResultCode.SUCCESS.getCode());
                re.setMessage(ResultCode.SUCCESS.getMessage());
                re.setData(bopSysOfficeListResponseList);
            }
        } catch (Exception e) {
            log.error("querySysOfficeList.error" + e);
            re.setData("内部异常");        }
        return re;
    }


    @PostMapping("querySysOfficeAllListForTree")
    @ApiOperation(value = "机构查询树形", notes = "机构查询树形")
    public Result<Object> querySysOfficeAllListForTree() {
        log.info("querySysOfficeAllListForTree");

        Result<Object> re = new Result<Object>();
        re.setStatus(ResultCode.ERROR.getCode());
        re.setMessage(ResultCode.ERROR.getMessage());
        try {
            //校验token
            validAuth();
            BopSysOfficeListResponse bopSysOfficeListResponse = iSysOffice.querySysOfficeAllListForTree();
            if (bopSysOfficeListResponse != null) {
                re.setData(bopSysOfficeListResponse);
                re.setStatus(ResultCode.SUCCESS.getCode());
                re.setMessage(ResultCode.SUCCESS.getMessage());
            }
        } catch (Exception e) {
            log.error("querySysOfficeAllListForTree.error" + e);
            re.setData("内部异常");
        }
        return re;

    }


}
