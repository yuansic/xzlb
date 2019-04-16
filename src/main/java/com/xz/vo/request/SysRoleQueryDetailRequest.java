package com.xz.vo.request;

import com.xz.vo.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * 角色明细查询请求对象
 * @author xuby
 * @version 2019/2/27 0027
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel
public class SysRoleQueryDetailRequest extends Base {

    private static final long serialVersionUID = 875133284217526233L;
    /**
     * 角色ID
     */
    @NotNull
    @ApiModelProperty(value = "角色ID,NotNull")
    private String id;
}
