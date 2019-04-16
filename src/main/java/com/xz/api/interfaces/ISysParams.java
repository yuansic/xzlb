package com.xz.api.interfaces;


import com.xz.vo.entity.PageResponse;
import com.xz.vo.entity.Result;
import com.xz.vo.request.SysParamsDeleteRequest;
import com.xz.vo.request.SysParamsInsertRequest;
import com.xz.vo.request.SysParamsQueryRequest;
import com.xz.vo.response.SysParamsQueryResponse;

/**
 * @author yuansc
 * @date 2019/2/25 0025 上午 10:56
 * 系统参数接口
 */
public interface ISysParams {


    /**
     * 查询带分页
     */
    Result<PageResponse<SysParamsQueryResponse>> selectSysParamsByLimit(SysParamsQueryRequest sysParamsQueryRequest);


    /**
     * 插入sys_params
     */
    Result<String> insertSysParams(SysParamsInsertRequest sysParamsInsertRequest);


    /**
     * 通过ID删除数据
     *
     * @return
     */
    Result<String> deleteSysParamsById(SysParamsDeleteRequest sysParamsDeleteRequest);

    /**
     * 通过ID更新数据
     *
     * @return
     */
    Result<String> updateSysParamsById(SysParamsInsertRequest sysParamsDeleteRequest);


}
