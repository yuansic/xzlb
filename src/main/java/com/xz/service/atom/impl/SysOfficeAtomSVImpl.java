package com.xz.service.atom.impl;


import com.xz.dao.BopSysOfficeMapper;
import com.xz.entity.BopSysOffice;
import com.xz.entity.BopSysOfficeExample;
import com.xz.service.atom.interfaces.ISysOfficeAtomSV;
import com.xz.util.IdGen;
import com.xz.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author yuansc
 * @date 2019/2/22 0022 上午 11:17
 */
@Component
public class SysOfficeAtomSVImpl implements ISysOfficeAtomSV {

    @Autowired
    private BopSysOfficeMapper bopSysOfficeMapper;

    @Override
    public List<BopSysOffice> selectByExample(BopSysOfficeExample example) {
        return bopSysOfficeMapper.selectByExample(example);
    }

    @Override
    public BopSysOffice selectByPrimaryKey(String id) {
        return bopSysOfficeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(BopSysOffice record) {
        if(StringUtils.isBlank(record.getId())){
            record.setId(IdGen.uuid());
        }
        record.setUpdateDate(new Date());
        record.setCreateDate(record.getUpdateDate());
        return bopSysOfficeMapper.insert(record);
    }
    @Override
    public int updateByPrimaryKey(BopSysOffice record) {
        record.setUpdateDate(new Date());
        return bopSysOfficeMapper.updateByPrimaryKey(record);
    }
    @Override
    public int updateByPrimaryKeySelective(BopSysOffice record) {
        record.setUpdateDate(new Date());
        return bopSysOfficeMapper.updateByPrimaryKeySelective(record);
    }


}
