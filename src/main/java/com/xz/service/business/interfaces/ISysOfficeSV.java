package com.xz.service.business.interfaces;


import com.xz.vo.response.BopSysOfficeListResponse;

import java.util.List;

/**
 * 机构
 *
 */
public interface ISysOfficeSV {

    /**
     * 机构查询树行结构
     * @return
     */
    BopSysOfficeListResponse querySysOfficeAllListForTree();

    /**
     * 机构查询平行结构
     * @return
     */
    List<BopSysOfficeListResponse> querySysOfficeList();

}
