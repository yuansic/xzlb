package com.xz.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author yuansc
 * @date 2019/3/21 0021 下午 4:33
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel
public class SysUserEnableAndProhibit implements Serializable {

    private static final long serialVersionUID = 4254273138154504085L;
    /**
     * 编号
     */
    @NotNull(message = "ID不能为空")
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "主键ID必填", required = true)
    private String id;

    /**
     * 是否可登录
     */
    @NotNull(message = "是否可登录不能为空")
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "是否可登录必填", required = true)
    private String loginFlag;

}
