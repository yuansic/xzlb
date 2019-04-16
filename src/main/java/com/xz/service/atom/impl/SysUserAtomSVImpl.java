package com.xz.service.atom.impl;


import com.xz.dao.BopSysUserMapper;
import com.xz.entity.BopSysUser;
import com.xz.entity.BopSysUserExample;
import com.xz.service.atom.interfaces.ISysUserAtomSV;
import com.xz.util.IdGen;
import com.xz.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.List;

/**
 * @author yuansc
 * @date 2019/2/21 0021 下午 1:55
 */
@Component
public class SysUserAtomSVImpl implements ISysUserAtomSV {

    @Autowired
    private BopSysUserMapper bopSysUserMapper;

    @Override
    public List<BopSysUser> selectByExample(BopSysUserExample example) {
        return bopSysUserMapper.selectByExample(example);
    }

    @Override
    public BopSysUser selectByPrimaryKey(String id) {
        return bopSysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(BopSysUser record) {
        if(StringUtils.isBlank(record.getId())){
            return 0;
        }
        record.setUpdateTime(new Date());
        return bopSysUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(BopSysUser record) {
        if(StringUtils.isBlank(record.getId())){
                return 0;
        }
        record.setUpdateTime(new Date());
        return bopSysUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insert(BopSysUser record) {
        if(StringUtils.isBlank(record.getId())){
            record.setId(IdGen.uuid());
        }
        record.setUpdateTime(new Date());
        record.setCreateTime(record.getUpdateTime());
        return bopSysUserMapper.insert(record);
    }
}
