package com.xz.service.business.impl;


import com.xz.entity.BopSysOffice;
import com.xz.entity.BopSysOfficeExample;
import com.xz.enums.SysOfficeEnum;
import com.xz.service.atom.interfaces.ISysOfficeAtomSV;
import com.xz.service.business.interfaces.ISysOfficeSV;
import com.xz.util.BeanUtils;
import com.xz.util.StringUtils;
import com.xz.vo.request.SysOfficeInsertRequest;
import com.xz.vo.response.BopSysOfficeListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * @author yuansc
 * @date 2019/2/22 0022 下午 5:07
 */
@Service
@Transactional
public class SysOfficeSVImpl implements ISysOfficeSV {

    @Autowired
    private ISysOfficeAtomSV iSysOfficeAtomSV;


    /**
     * 通过id获取机构信息
     * @param id
     * @return
     */
    public BopSysOfficeListResponse querySysOfficeById(String id){
        if(StringUtils.isBlank(id)){
            return null;
        }
        BopSysOfficeListResponse bopSysOfficeListResponse = new BopSysOfficeListResponse();
        BopSysOffice bopSysOffice = iSysOfficeAtomSV.selectByPrimaryKey(id);
        BeanUtils.copyProperties(bopSysOfficeListResponse,bopSysOffice);
        return bopSysOfficeListResponse;
    }

    /**
     * 机构新增
     * @param sysOfficeInsertRequest
     * @return
     */
    public Integer saveSysOffice(SysOfficeInsertRequest sysOfficeInsertRequest){

        BopSysOffice record = new BopSysOffice();
        BeanUtils.copyProperties(record,sysOfficeInsertRequest);
        if(StringUtils.isNotBlank(sysOfficeInsertRequest.getParentId())){
            // 根据父级ID查询父级菜单信息，并拼接parentIds字段
            BopSysOffice bopSysOffice = iSysOfficeAtomSV.selectByPrimaryKey(sysOfficeInsertRequest.getParentId());
            sysOfficeInsertRequest.setParentIds(bopSysOffice.getParentIds()+bopSysOffice.getId()+",");
        }
        record.setUpdateBy(sysOfficeInsertRequest.getLoginName());
        if(StringUtils.isNotBlank(record.getId())){
            return iSysOfficeAtomSV.updateByPrimaryKey(record);
        }
        record.setCreateBy(sysOfficeInsertRequest.getLoginName());
        return iSysOfficeAtomSV.insert(record);
    }

    /**
     * 查询机构(树形结构)
     */
    public BopSysOfficeListResponse querySysOfficeAllListForTree(){

        BopSysOfficeExample example = new BopSysOfficeExample();
        BopSysOfficeExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(SysOfficeEnum.DelFlagEnum.NORMAL.getCode());
        example.setOrderByClause("sort asc");
        List<BopSysOffice> bopSysOffices = iSysOfficeAtomSV.selectByExample(example);

        if(bopSysOffices == null || bopSysOffices.isEmpty() ){
            return null;
        }
        //父节点
        BopSysOfficeListResponse bopSysOfficeListResponse = new BopSysOfficeListResponse();
        //所有节点除了父节点
        List<BopSysOfficeListResponse> bopSysOfficeListResponseList = new ArrayList<>();

        for (BopSysOffice bopSysOffice : bopSysOffices) {
            //取出父节点
            String[] split = bopSysOffice.getParentIds().split(",");
            if(split.length == 1 && bopSysOffice.getParentId().equals(split[0])){
                BeanUtils.copyProperties(bopSysOfficeListResponse,bopSysOffice);
                continue;
            }
            BopSysOfficeListResponse bean = new BopSysOfficeListResponse();
            BeanUtils.copyProperties(bean,bopSysOffice);
            bopSysOfficeListResponseList.add(bean);
        }
        //递归调用取出父节点机构
        String id = bopSysOfficeListResponse.getId();

        List<BopSysOfficeListResponse> bopSysOfficeListResponseTree = new ArrayList<>();
        for (BopSysOfficeListResponse sysOfficeListResponse : bopSysOfficeListResponseList) {
            if(id.equals(sysOfficeListResponse.getParentId())){
                BopSysOfficeListResponse bean = new BopSysOfficeListResponse();
                BeanUtils.copyProperties(bean,sysOfficeListResponse);
                bopSysOfficeListResponseTree.add(bean);
            }
        }
        for (BopSysOfficeListResponse sysOfficeListResponse : bopSysOfficeListResponseTree) {
            getChild(sysOfficeListResponse,bopSysOfficeListResponseList);
        }
        bopSysOfficeListResponse.setChildren(bopSysOfficeListResponseTree);

        return bopSysOfficeListResponse;
    }

    /**
     * 查询机构(平行结构)
     */
    public List<BopSysOfficeListResponse> querySysOfficeList(){

        BopSysOfficeExample example = new BopSysOfficeExample();
        BopSysOfficeExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(SysOfficeEnum.DelFlagEnum.NORMAL.getCode());
        example.setOrderByClause("sort asc");
        List<BopSysOffice> bopSysOffices = iSysOfficeAtomSV.selectByExample(example);
        if(bopSysOffices == null || bopSysOffices.isEmpty()){
            return null;
        }
        List<BopSysOfficeListResponse> list = new ArrayList<>();
        for (BopSysOffice bopSysOffice : bopSysOffices) {
            BopSysOfficeListResponse bean = new BopSysOfficeListResponse();
            BeanUtils.copyProperties(bean,bopSysOffice);
            list.add(bean);
        }
        return list;
    }


    /**
     * 递归填充
     *
     */
    private void getChild(BopSysOfficeListResponse bopSys, List<BopSysOfficeListResponse> childMenuList){
        List<BopSysOfficeListResponse> cList = new ArrayList<>();
        for(BopSysOfficeListResponse childMenu:childMenuList){
            if (StringUtils.isNotBlank(childMenu.getParentId()) &&
                    bopSys.getId().equals(childMenu.getParentId())) {
                getChild(childMenu,childMenuList);
                cList.add(childMenu);
            }
        }
        bopSys.setChildren(cList);
    }



}
