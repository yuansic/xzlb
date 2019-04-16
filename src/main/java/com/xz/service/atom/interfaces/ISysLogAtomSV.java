package com.xz.service.atom.interfaces;


import com.xz.entity.BopSysLog;

import java.util.List;

/**
 * 系统操作日志原子层接口
 *
 * @author xuby
 * @version 2019/2/22 0022
 */
public interface ISysLogAtomSV {

    /**
     * 新增系统操作日志
     *
     * @param syslog
     */
    void insert(BopSysLog syslog);

    /**
     * 查询所有日志信息
     *
     * @param syslog {@link BopSysLog}
     * @return
     */
    List<BopSysLog> findAllList(BopSysLog syslog);
}
