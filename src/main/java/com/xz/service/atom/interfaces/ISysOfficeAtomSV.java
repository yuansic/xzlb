package com.xz.service.atom.interfaces;


import com.xz.entity.BopSysOffice;
import com.xz.entity.BopSysOfficeExample;

import java.util.List;

public interface ISysOfficeAtomSV {

    List<BopSysOffice> selectByExample(BopSysOfficeExample example);

    BopSysOffice selectByPrimaryKey(String id);

    int insert(BopSysOffice record);

    int updateByPrimaryKey(BopSysOffice record);

    int updateByPrimaryKeySelective(BopSysOffice record);
}
