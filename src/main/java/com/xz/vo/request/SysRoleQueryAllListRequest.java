package com.xz.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 角色列表分页查询
 * @author xuby
 * @version 2019/2/27 0027
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel
public class SysRoleQueryAllListRequest implements Serializable {

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    private String loginName;
}
