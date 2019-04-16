package com.xz.api.interfaces;


import com.xz.vo.response.BopSysOfficeListResponse;

import java.util.List;

/**
 * @author yuansc
 * @date 2019/2/25 0025 上午 10:51
 * 组织机构接口
 */
public interface ISysOffice {
    /**
     * 组织机构查询(树形)
     * @return
     */
     BopSysOfficeListResponse querySysOfficeAllListForTree();

    /**
     * 组织结构查询(平行)
     * @return
     */
    List<BopSysOfficeListResponse> querySysOfficeList();





}
