package com.xz.service.atom.impl;


import com.xz.dao.BopSysLogMapper;
import com.xz.entity.BopSysLog;
import com.xz.service.atom.interfaces.ISysLogAtomSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统操作日志原子层实现
 *
 * @author xuby
 * @version 2019/2/22
 */
@Component
public class SysLogAtomSVImpl implements ISysLogAtomSV {

    @Autowired
    private BopSysLogMapper sysLogMapper;

    /**
     * 新增系统操作日志
     * @param syslog {@link BopSysLog}
     */
    @Override
    public void insert(BopSysLog syslog) {
        sysLogMapper.insert(syslog);
    }

    /**
     * 查询所有系统日志
     * @param syslog {@link BopSysLog}
     * @return
     */
    @Override
    public List<BopSysLog> findAllList(BopSysLog syslog) {
        return sysLogMapper.findAllList(syslog);
    }
}
