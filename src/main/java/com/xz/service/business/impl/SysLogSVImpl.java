package com.xz.service.business.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.xz.entity.BopSysLog;
import com.xz.service.atom.interfaces.ISysLogAtomSV;
import com.xz.service.business.interfaces.ISysLogSV;
import com.xz.util.DateUtils;
import com.xz.vo.entity.PageResponse;
import com.xz.vo.request.SysLogQueryListRequest;
import com.xz.vo.response.SysLogQueryListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统操作日志业务接口实现
 * @author xuby
 * @version 2019/2/28 0028
 */
@Service
@Transactional
@Slf4j
public class SysLogSVImpl implements ISysLogSV {

    @Autowired
    private ISysLogAtomSV iSysLogAtomSV;

    /**
     * 分页查询所有日志信息
     * @param request {@link SysLogQueryListRequest}
     * @return
     */
    @Override
    public PageResponse<SysLogQueryListResponse> findPage(SysLogQueryListRequest request) {
        log.info("-----SysLogSVImpl findPage request:{}------", JSON.toJSONString(request));

        BopSysLog sysLog = new BopSysLog();
        BeanUtils.copyProperties(request,sysLog);
        if(request.getBeginDate() != 0L){
            sysLog.setBeginDate(DateUtils.parseDate(request.getBeginDate()));
        }
        if(request.getEndDate() != 0L){
            sysLog.setEndDate(DateUtils.parseDate(request.getEndDate()));
        }
        PageHelper.startPage(Integer.parseInt(request.getPageNo()),Integer.parseInt(request.getPageSize()));
        // 查询系统操作日志信息
        List<BopSysLog> sysLogList = iSysLogAtomSV.findAllList(sysLog);
        List<SysLogQueryListResponse> responseList = new ArrayList<SysLogQueryListResponse>();

        if(sysLogList != null && sysLogList.size() > 0){
            for(BopSysLog s:sysLogList){
                SysLogQueryListResponse sysLogResponse = new SysLogQueryListResponse();
                BeanUtils.copyProperties(s,sysLogResponse);
                sysLogResponse.setCreateDate(s.getCreateDate().getTime());
                sysLogResponse.setCreateByName(s.getCreateBy().getName());
                responseList.add(sysLogResponse);
            }
        }

        return new PageResponse<SysLogQueryListResponse>(((Page<BopSysLog>)sysLogList).getTotal(),responseList);
    }

}
