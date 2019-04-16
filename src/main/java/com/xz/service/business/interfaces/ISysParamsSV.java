package com.xz.service.business.interfaces;


import com.xz.vo.entity.PageResponse;
import com.xz.vo.request.SysParamsInsertRequest;
import com.xz.vo.request.SysParamsQueryRequest;
import com.xz.vo.response.SysParamsQueryResponse;

public interface ISysParamsSV {

    /**
     * 通过主键ID删除数据
     *
     * @param sysParamsId
     * @return
     */
    Integer deleteSysParams(String sysParamsId);

    /**
     * 通过主键ID更新数据
     *
     * @param sysParamsInsertRequest
     * @return
     */
    Integer updateSysDict(SysParamsInsertRequest sysParamsInsertRequest);

    /**
     * 分页查询系统参数信息
     *
     * @param sysParamsQueryRequest
     * @return
     */
    PageResponse<SysParamsQueryResponse> selectSysParamsByLimit(SysParamsQueryRequest sysParamsQueryRequest);

    /**
     * 系统参数插入
     *
     * @param sysParamsInsertRequest
     * @return
     */
    Integer insertSysParamsLimit(SysParamsInsertRequest sysParamsInsertRequest);


    /**
     * 幂等验证 true 有数据 false 无数据
     *
     * @param sysParamsInsertRequest
     * @return
     */
    boolean selectPotentSysParams(SysParamsInsertRequest sysParamsInsertRequest);

}
