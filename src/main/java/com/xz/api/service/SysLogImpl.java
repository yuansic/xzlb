package com.xz.api.service;

import com.alibaba.fastjson.JSON;
import com.xz.api.interfaces.ISysLog;
import com.xz.service.business.interfaces.ISysLogSV;
import com.xz.vo.entity.PageResponse;
import com.xz.vo.request.SysLogQueryListRequest;
import com.xz.vo.response.SysLogQueryListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统操作日志服务实现
 *
 * @author xuby
 * @version 2019/2/28 0028
 */
@Slf4j
@Service
//@Service(interfaceClass = ISysLog.class,retries = 0,version="0.0.1",validation = "true")
public class SysLogImpl implements ISysLog {

    @Autowired
    private ISysLogSV iSysLogSV;

    /**
     * 系统操作日志服务实现
     *
     * @param request
     * @return
     */
    @Override
    public PageResponse<SysLogQueryListResponse> querySysLogPage(SysLogQueryListRequest request) {
        log.info("===============SysLogImpl querySysLogPage request:{}==============", JSON.toJSONString(request));

        PageResponse<SysLogQueryListResponse> responseList = iSysLogSV.findPage(request);

        log.info("===============SysLogImpl querySysLogPage end res:{}==============", JSON.toJSONString(responseList));
        return responseList;
    }
}
