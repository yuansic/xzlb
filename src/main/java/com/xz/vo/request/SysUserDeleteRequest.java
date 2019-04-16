package com.xz.vo.request;

import com.xz.vo.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author yuansc
 * @date 2019/2/25 0025 下午 4:47
 */
@ApiModel
public class SysUserDeleteRequest extends Base {

    private static final long serialVersionUID = -1227459124465438730L;

    @NotNull(message = "用户ID不能为空")
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "用户ID", required = true)
    private String id;


    @ApiModelProperty(value = "登陆操作用户", required = true)
    private String name;

    public SysUserDeleteRequest() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
