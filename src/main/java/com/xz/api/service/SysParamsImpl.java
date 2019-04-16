package com.xz.api.service;

import com.alibaba.fastjson.JSON;
import com.xz.api.interfaces.ISysParams;
import com.xz.service.business.interfaces.ISysParamsSV;
import com.xz.vo.entity.PageResponse;
import com.xz.vo.entity.Result;
import com.xz.vo.entity.ResultCode;
import com.xz.vo.request.SysParamsDeleteRequest;
import com.xz.vo.request.SysParamsInsertRequest;
import com.xz.vo.request.SysParamsQueryRequest;
import com.xz.vo.response.SysParamsQueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuansc
 * @date 2019/2/26 0026 下午 4:56
 */

@Service
public class SysParamsImpl implements ISysParams {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysParamsImpl.class);

    @Autowired
    private ISysParamsSV iSysParamsSV;

    @Override
    public Result<PageResponse<SysParamsQueryResponse>> selectSysParamsByLimit(SysParamsQueryRequest sysParamsQueryRequest) {
        LOGGER.info("SysParamsImpl.selectSysParamsByLimit:" + JSON.toJSONString(sysParamsQueryRequest));
        Result<PageResponse<SysParamsQueryResponse>> responseResult = new Result<PageResponse<SysParamsQueryResponse>>();
        PageResponse<SysParamsQueryResponse> pageResponse = null;
        try {
            pageResponse = iSysParamsSV.selectSysParamsByLimit(sysParamsQueryRequest);
            responseResult.setData(pageResponse);
            responseResult.setStatus(ResultCode.SUCCESS.getCode());
            responseResult.setMessage(ResultCode.SUCCESS.getMessage());
        } catch (Exception e) {
            LOGGER.error("SysParamsImpl.selectSysParamsByLimit.error" + e);
            responseResult.setStatus(ResultCode.ERROR.getCode());
            responseResult.setMessage(ResultCode.ERROR.getMessage());
        }
        return responseResult;
    }

    @Override
    public Result<String> insertSysParams(SysParamsInsertRequest sysParamsInsertRequest) {
        LOGGER.info("SysParamsImpl.insertSysParams:" + JSON.toJSONString(sysParamsInsertRequest));
        Result<String> result = new Result<>();
        result.setStatus(ResultCode.ERROR.getCode());
        result.setMessage(ResultCode.ERROR.getMessage());

        try {
            //幂等验证
            if (iSysParamsSV.selectPotentSysParams(sysParamsInsertRequest)) {
                result.setData("数据已存在请勿重复提交！");
                return result;
            }
            //插入数据
            Integer integer = iSysParamsSV.insertSysParamsLimit(sysParamsInsertRequest);
            //封装回参
            if (integer != null && integer == 1) {
                result.setStatus(ResultCode.SUCCESS.getCode());
                result.setMessage(ResultCode.SUCCESS.getMessage());
                result.setData("新增成功！");
            }
        } catch (Exception e) {
            LOGGER.error("SysParamsImpl.insertSysParams.error" + e);
        }
        LOGGER.info("SysParamsImpl.insertSysParams.result:" + JSON.toJSONString(result));
        return result;
    }

    @Override
    public Result<String> deleteSysParamsById(SysParamsDeleteRequest sysParamsDeleteRequest) {

        LOGGER.info("SysParamsImpl.deleteSysParamsById:" + JSON.toJSONString(sysParamsDeleteRequest));
        Result<String> result = new Result<String>();
        result.setStatus(ResultCode.ERROR.getCode());
        result.setMessage(ResultCode.ERROR.getMessage());
        result.setData("删除失败！");
        try {
            Integer integer = iSysParamsSV.deleteSysParams(sysParamsDeleteRequest.getId());
            if (integer != null && integer == 1) {
                result.setStatus(ResultCode.SUCCESS.getCode());
                result.setMessage(ResultCode.SUCCESS.getMessage());
                result.setData("删除成功！");
            }
        } catch (Exception e) {
            LOGGER.error("SysParamsImpl.deleteSysParamsById.error" + e);
            result.setData("内部异常！");
        }
        //进行用户删除操作，更新状态
        LOGGER.info("SysParamsImpl.deleteSysParamsById.result:" + JSON.toJSONString(result));
        return result;

    }

    @Override
    public Result<String> updateSysParamsById(SysParamsInsertRequest sysParamsDeleteRequest) {
        LOGGER.info("SysParamsImpl.updateSysParamsById:" + JSON.toJSONString(sysParamsDeleteRequest));
        Result<String> result = new Result<>();
        result.setStatus(ResultCode.ERROR.getCode());
        result.setMessage(ResultCode.ERROR.getMessage());
        result.setData("更新失败！");
        try {
            Integer integer = iSysParamsSV.updateSysDict(sysParamsDeleteRequest);
            if (integer != null && integer == 1) {
                result.setStatus(ResultCode.SUCCESS.getCode());
                result.setMessage(ResultCode.SUCCESS.getMessage());
                result.setData("更新成功！");
            }
        } catch (Exception e) {
            LOGGER.error("SysDictImpl.updateSysDict.error" + e);
            result.setData("更新异常！");
        }
        LOGGER.info("SysParamsImpl.updateSysParamsById.result:" + JSON.toJSONString(result));
        return result;
    }
}
