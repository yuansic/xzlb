package com.xz.service.business.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.xz.entity.BopSysParams;
import com.xz.entity.BopSysParamsExample;
import com.xz.enums.SysParamsEnum;
import com.xz.service.atom.interfaces.ISysParamsAtomSV;
import com.xz.service.business.interfaces.ISysParamsSV;
import com.xz.util.BeanUtils;
import com.xz.util.StringUtils;
import com.xz.vo.entity.PageResponse;
import com.xz.vo.request.SysParamsInsertRequest;
import com.xz.vo.request.SysParamsQueryRequest;
import com.xz.vo.response.SysParamsQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * @author yuansc
 * @date 2019/2/22 0022 下午 5:06
 */
@Service
@Transactional
public class SysParamsSVImpl implements ISysParamsSV {


    @Autowired
    private ISysParamsAtomSV iSysParamsAtomSV;

    /**
     *通过主键ID更新数据
     * @param sysParamsInsertRequest
     * @return
     */
    @Override
    public Integer updateSysDict(SysParamsInsertRequest sysParamsInsertRequest){
        if(StringUtils.isBlank(sysParamsInsertRequest.getId())){
            return null;
        }
        //查询是否存在数据
        if(isSysParamsNullOrDelFla(sysParamsInsertRequest.getId())){
            return null;
        }
        //进行更新
        BopSysParams bopSysParams = new BopSysParams();
        BeanUtils.copyProperties(bopSysParams,sysParamsInsertRequest);

        SysParamsEnum.IsModifyEnum message = SysParamsEnum.IsModifyEnum.getMessage(sysParamsInsertRequest.getIsModify());
        if(message == null){
            bopSysParams.setIsModify(null);
        }else{
            bopSysParams.setIsModify(message.getCode());
        }
        return iSysParamsAtomSV.updateByPrimaryKeySelective(bopSysParams);
    }

    /**
     * 通过主键ID删除数据
     * @param sysParamsId
     * @return
     */
    @Override
    public Integer deleteSysParams(String sysParamsId){
        if(StringUtils.isBlank(sysParamsId)){
            return null;
        }
        //查询是否存在数据
        if(isSysParamsNullOrDelFla(sysParamsId)){
            return null;
        }
        //删除数据,真删!
        return iSysParamsAtomSV.deleteByPrimaryKey(sysParamsId);
    }

    /**
     * 分页查询系统参数信息
     *
     * @param sysParamsQueryRequest
     * @return
     */
    @Override
    public PageResponse<SysParamsQueryResponse> selectSysParamsByLimit(SysParamsQueryRequest sysParamsQueryRequest) {

        BopSysParamsExample example = new BopSysParamsExample();
        BopSysParamsExample.Criteria criteria = example.createCriteria();
        //取入参信息拼接sql
        if (StringUtils.isNotBlank(sysParamsQueryRequest.getParamCnName())) {
            criteria.andParamCnNameLike("%" + sysParamsQueryRequest.getParamCnName() + "%");
        }
        if (StringUtils.isNotBlank(sysParamsQueryRequest.getParamEnName())) {
            criteria.andParamEnNameLike("%" + sysParamsQueryRequest.getParamEnName() + "%");
        }
        if (StringUtils.isNotBlank(sysParamsQueryRequest.getParamType())) {
            SysParamsEnum.ParamType message = SysParamsEnum.ParamType.getMessage(sysParamsQueryRequest.getParamType());
            if(message != null){
                criteria.andParamTypeEqualTo(message.getCode());
            }
        }

        //分页查询
        PageInfo<BopSysParams> objectPageInfo = PageHelper.startPage(Integer.valueOf(sysParamsQueryRequest.getPageNo()), Integer.valueOf(sysParamsQueryRequest.getPageSize())).doSelectPageInfo(() -> iSysParamsAtomSV.selectByExample(example));

        List<BopSysParams> list = objectPageInfo.getList();
        //为空返回空
        if (list == null || list.isEmpty()) {
            return new PageResponse<>(0L, null);
        }
        //拼接接口需要参数返回
        List<SysParamsQueryResponse> listSysParams = new ArrayList<>();
        for (BopSysParams bopSysParams : list) {
            SysParamsQueryResponse sysParamsQueryResponse = new SysParamsQueryResponse();
            BeanUtils.copyProperties(sysParamsQueryResponse, bopSysParams);
            SysParamsEnum.IsModifyEnum anEnum = SysParamsEnum.IsModifyEnum.getEnum(bopSysParams.getIsModify());
            if (anEnum != null) {
                sysParamsQueryResponse.setIsModify(anEnum.getMessage());
            }
            SysParamsEnum.ParamType ParamTypeEnum = SysParamsEnum.ParamType.getEnum(bopSysParams.getParamType());
            if(ParamTypeEnum != null){
                sysParamsQueryResponse.setParamType(ParamTypeEnum.getMessage());
            }
            listSysParams.add(sysParamsQueryResponse);
        }
        return new PageResponse<>(objectPageInfo.getTotal(), listSysParams);
    }

    /**
     * 系统参数插入
     * @param sysParamsInsertRequest
     * @return
     */
    @Override
    public Integer insertSysParamsLimit(SysParamsInsertRequest sysParamsInsertRequest){
        BopSysParams bopSysParams = new BopSysParams();
        BeanUtils.copyProperties(bopSysParams,sysParamsInsertRequest);
        SysParamsEnum.IsModifyEnum message = SysParamsEnum.IsModifyEnum.getMessage(sysParamsInsertRequest.getIsModify());
        bopSysParams.setParamValue(SysParamsEnum.ParamType.SYSPARAM.getCode());
        if(message == null){
            bopSysParams.setIsModify(SysParamsEnum.IsModifyEnum.YES.getCode());
        }else{
            bopSysParams.setIsModify(message.getCode());
        }
        return iSysParamsAtomSV.insert(bopSysParams);
    }


    /**
     * 幂等验证 true 有数据 false 无数据
     *
     * @param sysParamsInsertRequest
     * @return
     */
    @Override
    public boolean selectPotentSysParams(SysParamsInsertRequest sysParamsInsertRequest) {

        BopSysParamsExample example = new BopSysParamsExample();
        BopSysParamsExample.Criteria criteria = example.createCriteria();

        criteria.andParamValueEqualTo(sysParamsInsertRequest.getParamValue());
        criteria.andParamCnNameEqualTo(sysParamsInsertRequest.getParamCnName());
        criteria.andParamEnNameEqualTo(sysParamsInsertRequest.getParamEnName());

        List<BopSysParams> bopSysParams = iSysParamsAtomSV.selectByExample(example);

        if (bopSysParams == null || bopSysParams.isEmpty()) {
            return false;
        }
        return true;
    }


    /**
     * true 不存在/已删除 false 存在正常数据
     * @param sysParamsId
     * @return
     */
    private boolean isSysParamsNullOrDelFla(String sysParamsId){
        //查询是否存在数据
        BopSysParams bopSysParams = iSysParamsAtomSV.selectByPrimaryKey(sysParamsId);
        if(bopSysParams == null){
            return true;
        }
        return false;
    }

}
