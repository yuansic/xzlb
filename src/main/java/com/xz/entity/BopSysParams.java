package com.xz.entity;

public class BopSysParams {
    /**
     * 主键
     */
    private String id;

    /**
     * 参数中文名
     */
    private String paramCnName;

    /**
     * 参数英文名
     */
    private String paramEnName;

    /**
     * 
     */
    private String paramValue;

    /**
     * 参数类型
     */
    private String paramType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否可修改
     */
    private String isModify;

    /**
     * 主键
     * @return id 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 参数中文名
     * @return param_cn_name 参数中文名
     */
    public String getParamCnName() {
        return paramCnName;
    }

    /**
     * 参数中文名
     * @param paramCnName 参数中文名
     */
    public void setParamCnName(String paramCnName) {
        this.paramCnName = paramCnName;
    }

    /**
     * 参数英文名
     * @return param_en_name 参数英文名
     */
    public String getParamEnName() {
        return paramEnName;
    }

    /**
     * 参数英文名
     * @param paramEnName 参数英文名
     */
    public void setParamEnName(String paramEnName) {
        this.paramEnName = paramEnName;
    }

    /**
     * 
     * @return param_value 
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 
     * @param paramValue 
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * 参数类型
     * @return param_type 参数类型
     */
    public String getParamType() {
        return paramType;
    }

    /**
     * 参数类型
     * @param paramType 参数类型
     */
    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 是否可修改
     * @return is_modify 是否可修改
     */
    public String getIsModify() {
        return isModify;
    }

    /**
     * 是否可修改
     * @param isModify 是否可修改
     */
    public void setIsModify(String isModify) {
        this.isModify = isModify;
    }
}