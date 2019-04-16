package com.xz.service.business.interfaces;


import com.xz.vo.entity.PageResponse;
import com.xz.vo.request.SysDictInsertRequest;
import com.xz.vo.request.SysDictQueryRequest;
import com.xz.vo.response.SysDictQueryResponse;

public interface IBopSysDictSV {

    /**
     * 分页查询字典信息
     *
     * @param sysDictQueryRequest
     * @return
     */
    PageResponse<SysDictQueryResponse> selectSysDictByLimit(SysDictQueryRequest sysDictQueryRequest);


    /**
     * 字典插入
     *
     * @param sysDictInsertRequest
     * @return
     */
    Integer insertSysDictLimit(SysDictInsertRequest sysDictInsertRequest);

    /**
     * 幂等验证 true 有数据 false 无数据
     *
     * @param sysDictInsertRequest
     * @return
     */
    boolean selectPotentSysDict(SysDictInsertRequest sysDictInsertRequest);

    /**
     * 通过主键ID更新数据
     *
     * @param sysDictInsertRequest
     * @return
     */
    Integer updateSysDict(SysDictInsertRequest sysDictInsertRequest);

    /**
     * 通过主键ID删除数据
     *
     * @param sysDictId
     * @param loginName
     * @return
     */
    Integer deleteSysDict(String sysDictId, String loginName);

}
