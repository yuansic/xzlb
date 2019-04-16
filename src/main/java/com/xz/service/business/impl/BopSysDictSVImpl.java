package com.xz.service.business.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.xz.entity.BopSysDict;
import com.xz.entity.BopSysDictExample;
import com.xz.enums.SysDictEnum;
import com.xz.service.atom.interfaces.IBopSysDictAtomSV;
import com.xz.service.business.interfaces.IBopSysDictSV;
import com.xz.util.StringUtils;
import com.xz.vo.entity.PageResponse;
import com.xz.vo.request.SysDictInsertRequest;
import com.xz.vo.request.SysDictQueryRequest;
import com.xz.vo.response.SysDictQueryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * @author yuansc
 * @date 2019/2/22 0022 下午 5:08
 */
@Service
@Transactional
public class BopSysDictSVImpl implements IBopSysDictSV {

    @Autowired
    private IBopSysDictAtomSV iBopSysDictAtomSV;

    /**
     *通过主键ID更新数据
     * @param sysDictInsertRequest
     * @return
     */
    @Override
    public Integer updateSysDict(SysDictInsertRequest sysDictInsertRequest){
        if(StringUtils.isBlank(sysDictInsertRequest.getId())){
            return null;
        }
        //查询是否存在数据
        if(isSysDictNullOrDelFla(sysDictInsertRequest.getId())){
            return null;
        }
        //进行更新
        BopSysDict bopSysDictUpdate = new BopSysDict();
        BeanUtils.copyProperties(bopSysDictUpdate,sysDictInsertRequest);
        bopSysDictUpdate.setUpdateBy(sysDictInsertRequest.getLoginName());
        return iBopSysDictAtomSV.updateByPrimaryKeySelective(bopSysDictUpdate);
    }

    /**
     * 通过主键ID删除数据
     * @param sysDictId
     * @param loginName
     * @return
     */
    @Override
    public Integer deleteSysDict(String sysDictId,String loginName){

        if(StringUtils.isBlank(sysDictId) || StringUtils.isBlank(loginName) ){
            return null;
        }
        //查询是否存在数据
        if(isSysDictNullOrDelFla(sysDictId)){
            return null;
        }
        //更新数据为删除状态
        BopSysDict bopSysDictUpdate = new BopSysDict();
        bopSysDictUpdate.setId(sysDictId);
        bopSysDictUpdate.setUpdateBy(loginName);
        bopSysDictUpdate.setDelFlag(SysDictEnum.DelFlagEnum.DELETE.getCode());
        return iBopSysDictAtomSV.updateByPrimaryKeySelective(bopSysDictUpdate);

    }


    /**
     * 字典插入
     * @param sysDictInsertRequest
     * @return
     */
    @Override
    public Integer insertSysDictLimit(SysDictInsertRequest sysDictInsertRequest){
        BopSysDict record = new BopSysDict();
        BeanUtils.copyProperties(record,sysDictInsertRequest);
        record.setParentId("0");
        record.setDelFlag(SysDictEnum.DelFlagEnum.NORMAL.getCode());
        record.setUpdateBy(sysDictInsertRequest.getLoginName());
        record.setCreateBy(sysDictInsertRequest.getLoginName());
        return iBopSysDictAtomSV.insert(record);
    }

    /**
     * 幂等验证 true 有数据 false 无数据
     * @param sysDictInsertRequest
     * @return
     */
    @Override
    public boolean selectPotentSysDict(SysDictInsertRequest sysDictInsertRequest){

        BopSysDictExample example = new BopSysDictExample();
        BopSysDictExample.Criteria criteria = example.createCriteria();

        //取入参信息拼接sql
        criteria.andValueEqualTo(sysDictInsertRequest.getValue());
        criteria.andLabelEqualTo(sysDictInsertRequest.getLabel());
        criteria.andTypeEqualTo(sysDictInsertRequest.getType());
        criteria.andDescriptionEqualTo(sysDictInsertRequest.getDescription());
        criteria.andSortEqualTo(sysDictInsertRequest.getSort());
        List<BopSysDict> bopSysDicts = iBopSysDictAtomSV.selectByExample(example);
        if(bopSysDicts == null || bopSysDicts.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * 分页查询字典信息
     * @param sysDictQueryRequest
     * @return
     */
    @Override
    public PageResponse<SysDictQueryResponse> selectSysDictByLimit(SysDictQueryRequest sysDictQueryRequest){

        BopSysDictExample example = new BopSysDictExample();
        BopSysDictExample.Criteria criteria = example.createCriteria();
        //取入参信息拼接sql
        if(StringUtils.isNotBlank(sysDictQueryRequest.getType())){
            criteria.andTypeLike("%"+sysDictQueryRequest.getType()+"%");
        }
        if(StringUtils.isNotBlank(sysDictQueryRequest.getDescription())){
            criteria.andDescriptionLike("%"+sysDictQueryRequest.getDescription()+"%");
        }
        criteria.andDelFlagEqualTo(SysDictEnum.DelFlagEnum.NORMAL.getCode());
        //分页查询
        PageInfo<BopSysDict> objectPageInfo = PageHelper.startPage(Integer.valueOf(sysDictQueryRequest.getPageNo()), Integer.valueOf(sysDictQueryRequest.getPageSize()),"sort asc").doSelectPageInfo(() -> iBopSysDictAtomSV.selectByExample(example));

        List<BopSysDict> list = objectPageInfo.getList();
        //为空返回空
        if(list == null || list.isEmpty()){
            return  new PageResponse<>(0L, null);
        }
        //拼接接口需要参数返回
        List<SysDictQueryResponse> listSysDict = new ArrayList<SysDictQueryResponse>();
        for (BopSysDict bopSysDict : list) {
            SysDictQueryResponse sysDictQueryResponse = new SysDictQueryResponse();
            BeanUtils.copyProperties(sysDictQueryResponse, bopSysDict);
            listSysDict.add(sysDictQueryResponse);
        }

        return new PageResponse<>(objectPageInfo.getTotal(), listSysDict);
    }


    /**
     * true 不存在/已删除 false 存在正常数据
     * @param sysDictId
     * @return
     */
    private boolean isSysDictNullOrDelFla(String sysDictId){
        //查询是否存在数据
        BopSysDict bopSysDict = iBopSysDictAtomSV.selectByPrimaryKey(sysDictId);

        if(bopSysDict == null){
            return true;
        }
        //已删除不能进行更新
        if(SysDictEnum.DelFlagEnum.DELETE.getCode().equals(bopSysDict.getDelFlag())){
            return true;
        }
        return false;
    }



}
