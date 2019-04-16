package com.xz.vo.request;

import com.xz.vo.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author yuansc
 * @date 2019/2/26 0026 下午 2:04
 */
@ApiModel
public class SysParamsInsertRequest extends Base {


    private static final long serialVersionUID = -5163286986949508624L;
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "主键ID", required = true)
    private String id;

    /**
     * 参数中文名
     */
    @Size(min = 1, max = 128)
    @ApiModelProperty(value = "参数中文名")
    private String paramCnName;

    /**
     * 参数英文名
     */
    @Size(min = 1, max = 128)
    @ApiModelProperty(value = "参数英文名")
    private String paramEnName;

    /**
     *参数值
     */
    @Size(min = 1, max = 128)
    @ApiModelProperty(value = "参数值")
    private String paramValue;


    /**
     * 备注
     */
    @Size(min = 1, max = 128)
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 是否可修改
     */
    @NotNull
    @Size(min = 1, max = 32)
    @ApiModelProperty(value = "是否可修改")
    private String isModify;

    public SysParamsInsertRequest() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParamCnName() {
        return paramCnName;
    }

    public void setParamCnName(String paramCnName) {
        this.paramCnName = paramCnName;
    }

    public String getParamEnName() {
        return paramEnName;
    }

    public void setParamEnName(String paramEnName) {
        this.paramEnName = paramEnName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsModify() {
        return isModify;
    }

    public void setIsModify(String isModify) {
        this.isModify = isModify;
    }


}
