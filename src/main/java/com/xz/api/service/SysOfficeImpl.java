package com.xz.api.service;

import com.alibaba.fastjson.JSON;
import com.xz.api.interfaces.ISysOffice;
import com.xz.service.business.interfaces.ISysOfficeSV;
import com.xz.vo.response.BopSysOfficeListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuansc
 * @date 2019/3/5 0005 下午 6:26
 */
@Service
public class SysOfficeImpl implements ISysOffice {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysOfficeImpl.class);

    @Autowired
    private ISysOfficeSV iSysOfficeSV;

    public BopSysOfficeListResponse querySysOfficeAllListForTree() {
        LOGGER.info("SysOfficeImpl.querySysOfficeAllListForTree");
        BopSysOfficeListResponse bopSysOfficeListResponse = null;
        try {
            bopSysOfficeListResponse = iSysOfficeSV.querySysOfficeAllListForTree();
        } catch (Exception e) {
            LOGGER.info("SysOfficeImpl.querySysOfficeAllListForTree.error" + e);
        }
        LOGGER.info("SysOfficeImpl.querySysOfficeAllListForTree.response" + JSON.toJSONString(bopSysOfficeListResponse));
        return bopSysOfficeListResponse;
    }

    public List<BopSysOfficeListResponse> querySysOfficeList() {
        LOGGER.info("SysOfficeImpl.querySysOfficeList");
        List<BopSysOfficeListResponse> bopSysOfficeListResponseList = null;
        try {
            bopSysOfficeListResponseList = iSysOfficeSV.querySysOfficeList();
        } catch (Exception e) {
            LOGGER.info("SysOfficeImpl.querySysOfficeList.error" + e);
        }
        return bopSysOfficeListResponseList;
    }

}
