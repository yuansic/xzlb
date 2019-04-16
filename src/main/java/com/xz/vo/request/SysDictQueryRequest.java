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
public class SysDictQueryRequest extends PageRequest {


    private static final long serialVersionUID = -6354487332080781925L;

    /**
     * 类型
     */
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "类型", required = true)
    private String type;

    /**
     * 描述
     */
    @Size(min = 1, max = 128)
    @ApiModelProperty(value = "描述", required = true)
    private String description;

    public SysDictQueryRequest() {
        super();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
