package com.xz.service.atom.impl;


import com.xz.dao.BopSysParamsMapper;
import com.xz.entity.BopSysParams;
import com.xz.entity.BopSysParamsExample;
import com.xz.service.atom.interfaces.ISysParamsAtomSV;
import com.xz.util.IdGen;
import com.xz.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * @author yuansc
 * @date 2019/2/22 0022 下午 4:58
 */

@Component
public class SysParamsAtomSVImpl implements ISysParamsAtomSV {

    @Autowired
    private BopSysParamsMapper bopSysParamsMapper;


    @Override
    public int insert(BopSysParams record) {
        if(StringUtils.isBlank(record.getId())){
            record.setId(IdGen.uuid());
        }
        return bopSysParamsMapper.insert(record);
    }

    @Override
    public List<BopSysParams> selectByExample(BopSysParamsExample example) {
        return bopSysParamsMapper.selectByExample(example);
    }

    @Override
    public BopSysParams selectByPrimaryKey(String id) {
        return bopSysParamsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(BopSysParams record) {
        return bopSysParamsMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return bopSysParamsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BopSysParams record) {
        return bopSysParamsMapper.updateByPrimaryKeySelective(record);
    }
}
