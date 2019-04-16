package com.xz.api.service;

import com.alibaba.fastjson.JSON;
import com.xz.api.interfaces.IBopSysDict;
import com.xz.service.business.interfaces.IBopSysDictSV;
import com.xz.vo.entity.PageResponse;
import com.xz.vo.entity.Result;
import com.xz.vo.entity.ResultCode;
import com.xz.vo.request.SysDictDeleteRequest;
import com.xz.vo.request.SysDictInsertRequest;
import com.xz.vo.request.SysDictQueryRequest;
import com.xz.vo.response.SysDictQueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuansc
 * @date 2019/2/25 0025 下午 5:43
 */
@Service
public class BopSysDictImpl implements IBopSysDict {

    private static final Logger LOGGER = LoggerFactory.getLogger(BopSysDictImpl.class);

    @Autowired
    private IBopSysDictSV iSysDictSV;

    /**
     * 通过ID更新数据
     *
     * @param sysDictInsertRequest
     * @return
     */
    @Override
    public Result<String> updateSysDictById(SysDictInsertRequest sysDictInsertRequest) {
        LOGGER.info("SysDictImpl.updateSysDict:" + JSON.toJSONString(sysDictInsertRequest));

        Result<String> result = new Result<>();
        result.setStatus(ResultCode.ERROR.getCode());
        result.setMessage(ResultCode.ERROR.getMessage());

        try {
            Integer integer = iSysDictSV.updateSysDict(sysDictInsertRequest);
            if (integer != null && integer == 1) {
                result.setStatus(ResultCode.SUCCESS.getCode());
                result.setMessage(ResultCode.SUCCESS.getMessage());
                result.setData("更新成功！");
            }
        } catch (Exception e) {
            LOGGER.error("SysDictImpl.updateSysDict.error" + e);
        }
        LOGGER.info("SysDictImpl.updateSysDict.result:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * 通过ID删除数据
     *
     * @param sysDictDeleteRequest
     * @return
     */
    @Override
    public Result<String> deleteSysDictById(SysDictDeleteRequest sysDictDeleteRequest) {
        LOGGER.info("SysDictImpl.deleteSysDict:" + JSON.toJSONString(sysDictDeleteRequest));

        Result<String> result = new Result<>();
        result.setStatus(ResultCode.ERROR.getCode());
        result.setMessage(ResultCode.ERROR.getMessage());

        try {
            Integer integer = iSysDictSV.deleteSysDict(sysDictDeleteRequest.getId(), sysDictDeleteRequest.getLoginName());
            if (integer != null && integer == 1) {
                result.setStatus(ResultCode.SUCCESS.getCode());
                result.setMessage(ResultCode.SUCCESS.getMessage());
                result.setData("删除成功！");
            }
        } catch (Exception e) {
            LOGGER.error("SysDictImpl.deleteSysDict.error" + e);
        }
        LOGGER.info("SysDictImpl.deleteSysDict.result:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * 插入sys_dict
     *
     * @param sysDictInsertRequest
     * @return
     */
    @Override
    public Result<String> insertSysDict(SysDictInsertRequest sysDictInsertRequest) {
        LOGGER.info("SysDictImpl.insertSysDict:" + JSON.toJSONString(sysDictInsertRequest));
        Result<String> result = new Result<>();
        result.setStatus(ResultCode.ERROR.getCode());
        result.setMessage(ResultCode.ERROR.getMessage());
        try {
            //幂等验证
            if (iSysDictSV.selectPotentSysDict(sysDictInsertRequest)) {
                result.setData("数据已存在请勿重复提交！");
                return result;
            }
            //插入数据
            Integer i = iSysDictSV.insertSysDictLimit(sysDictInsertRequest);
            //封装回参
            if (i != null && i == 1) {
                result.setStatus(ResultCode.SUCCESS.getCode());
                result.setMessage(ResultCode.SUCCESS.getMessage());
                result.setData("新增成功！");
            }
        } catch (Exception e) {
            LOGGER.error("SysDictImpl.insertSysDict.error" + e);
        }

        LOGGER.info("SysDictImpl.insertSysDict.result:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询带分页
     *
     * @param sysDictQueryRequest
     * @return
     */
    @Override
    public Result<PageResponse<SysDictQueryResponse>> selectSysDictByLimit(SysDictQueryRequest sysDictQueryRequest) {
        LOGGER.info("SysDictImpl.selectSysDictByLimit:" + JSON.toJSONString(sysDictQueryRequest));
        Result<PageResponse<SysDictQueryResponse>> responseResult = new Result<PageResponse<SysDictQueryResponse>>();
        PageResponse<SysDictQueryResponse> pageResponse = null;

        try {
            pageResponse = iSysDictSV.selectSysDictByLimit(sysDictQueryRequest);
            responseResult.setData(pageResponse);
            responseResult.setStatus(ResultCode.SUCCESS.getCode());
            responseResult.setMessage(ResultCode.SUCCESS.getMessage());
        } catch (Exception e) {
            LOGGER.error("SysDictImpl.selectSysDictByLimit.error" + e);
            responseResult.setStatus(ResultCode.ERROR.getCode());
            responseResult.setMessage(ResultCode.ERROR.getMessage());
        }
        return responseResult;
    }
}
