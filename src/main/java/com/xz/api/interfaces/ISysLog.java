package com.xz.api.interfaces;


import com.xz.vo.entity.PageResponse;
import com.xz.vo.request.SysLogQueryListRequest;
import com.xz.vo.response.SysLogQueryListResponse;

/**
 * 系统操作日志服务接口
 * @author xuby
 * @version 2019/2/28 0028
 */
public interface ISysLog {

    /**
     * 分页查询系统操作日志列表
     * @param request
     * @return
     */
    PageResponse<SysLogQueryListResponse> querySysLogPage(SysLogQueryListRequest request);
}
