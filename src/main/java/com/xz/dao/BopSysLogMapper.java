package com.xz.dao;


import com.xz.entity.BopSysLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统操作日志DAO
 *
 * @author xuby
 * @version 2019/2/21
 */
@Mapper
public interface BopSysLogMapper {

    /**
     * 新增系统操作日志
     * @param syslog {@link BopSysLog}
     */
    void insert(BopSysLog syslog);

    /**
     * 查询所有日志信息
     * @param syslog {@link BopSysLog}
     * @return
     */
    List<BopSysLog> findAllList(BopSysLog syslog);
}
