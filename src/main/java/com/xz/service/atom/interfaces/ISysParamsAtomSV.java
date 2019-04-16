package com.xz.service.atom.interfaces;


import com.xz.entity.BopSysParams;
import com.xz.entity.BopSysParamsExample;

import java.util.List;

public interface ISysParamsAtomSV {

    int insert(BopSysParams record);

    List<BopSysParams> selectByExample(BopSysParamsExample example);

    BopSysParams selectByPrimaryKey(String id);

    int updateByPrimaryKey(BopSysParams record);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BopSysParams record);
}
