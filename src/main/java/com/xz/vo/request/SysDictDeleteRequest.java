package com.xz.vo.request;

import com.xz.vo.entity.Base;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author yuansc
 * @date 2019/2/26 0026 下午 4:33
 */
public class SysDictDeleteRequest extends Base {

    private static final long serialVersionUID = -1459632464001695916L;

    @NotNull(message = "主键ID不能为空")
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "主键ID", required = true)
    private String id;

    public SysDictDeleteRequest() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
