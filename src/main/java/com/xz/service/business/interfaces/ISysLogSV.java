package com.xz.service.business.interfaces;


import com.xz.vo.entity.PageResponse;
import com.xz.vo.request.SysLogQueryListRequest;
import com.xz.vo.response.SysLogQueryListResponse;

/**
 * 系统操作日志业务服务接口
 *
 * @author xuby
 * @version 2019/2/28 0028
 */
public interface ISysLogSV {

    /**
     * 分页查询所有日志信息
     *
     * @param request {@link SysLogQueryListRequest}
     * @return
     */
    PageResponse<SysLogQueryListResponse> findPage(SysLogQueryListRequest request);

}
