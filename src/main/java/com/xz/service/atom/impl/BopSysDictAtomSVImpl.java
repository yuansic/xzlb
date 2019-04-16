package com.xz.service.atom.impl;

import com.xz.dao.BopSysDictMapper;
import com.xz.entity.BopSysDict;
import com.xz.entity.BopSysDictExample;
import com.xz.service.atom.interfaces.IBopSysDictAtomSV;
import com.xz.util.IdGen;
import com.xz.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author yuansc
 * @date 2019/2/22 0022 下午 4:57
 */
@Component
public class BopSysDictAtomSVImpl implements IBopSysDictAtomSV {

    @Autowired
    private BopSysDictMapper sysDictMapper;


    @Override
    public int insert(BopSysDict record) {
        if(StringUtils.isBlank(record.getId())){
            record.setId(IdGen.uuid());
        }
        record.setUpdateDate(new Date());
        record.setCreateDate(record.getUpdateDate());
        return sysDictMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(BopSysDict record) {
        record.setUpdateDate(new Date());
        return sysDictMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(BopSysDict record) {
        record.setUpdateDate(new Date());
        return sysDictMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<BopSysDict> selectByExample(BopSysDictExample example) {
        return sysDictMapper.selectByExample(example);
    }

    @Override
    public BopSysDict selectByPrimaryKey(String id) {
        return sysDictMapper.selectByPrimaryKey(id);
    }




}
