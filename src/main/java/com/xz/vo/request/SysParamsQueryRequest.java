package com.xz.vo.request;

import com.xz.vo.entity.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;

/**
 * @author yuansc
 * @date 2019/2/25 0025 下午 5:46
 */
@ApiModel
public class SysParamsQueryRequest extends PageRequest {


    private static final long serialVersionUID = -543302037542425848L;
    /**
     * 参数中文名
     */
    @Size(min = 1, max = 128)
    @ApiModelProperty(value = "参数中文名", required = true)
    private String paramCnName;

    /**
     * 参数英文名
     */
    @Size(min = 1, max = 128)
    @ApiModelProperty(value = "参数英文名", required = true)
    private String paramEnName;


    /**
     * 参数类型
     */
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "参数类型", required = true)
    private String paramType;

    public SysParamsQueryRequest() {
        super();
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

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }
}
