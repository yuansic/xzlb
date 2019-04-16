package com.xz.api.interfaces;


import com.xz.vo.entity.PageResponse;
import com.xz.vo.entity.Result;
import com.xz.vo.request.SysDictDeleteRequest;
import com.xz.vo.request.SysDictInsertRequest;
import com.xz.vo.request.SysDictQueryRequest;
import com.xz.vo.response.SysDictQueryResponse;

/**
 *   @author yuansc
 *   @date 2019/2/25 0025 上午 10:51
 *   字典相关
 */
public interface IBopSysDict {


    /**
     * 查询带分页
     * @param sysDictQueryRequest
     * @return
     */
    Result<PageResponse<SysDictQueryResponse>> selectSysDictByLimit(SysDictQueryRequest sysDictQueryRequest);


    /**
     * 插入sys_dict
     * @param sysDictInsertRequest
     * @return
     */
    Result<String> insertSysDict(SysDictInsertRequest sysDictInsertRequest);


    /**
     * 通过ID删除数据
     *
     * @param sysDictDeleteRequest
     * @return
     */
    Result<String> deleteSysDictById(SysDictDeleteRequest sysDictDeleteRequest);

    /**
     * 通过ID更新数据
     *
     * @param sysDictInsertRequest
     * @return
     */
    Result<String> updateSysDictById(SysDictInsertRequest sysDictInsertRequest);

}
