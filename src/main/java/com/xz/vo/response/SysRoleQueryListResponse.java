package com.xz.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 角色列表查询返回对象
 * @author xuby
 * @version 2019/2/27 0027
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel
public class SysRoleQueryListResponse implements Serializable {

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private String id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 角色英文名称
     */
    @ApiModelProperty(value = "角色英文名称")
    private String enname;

    /**
     * 是否可用
     */
    @ApiModelProperty(value = "是否可用")
    private String useable;
}
