package com.xz.vo.response;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 角色详情返回对象
 * @author xuby
 * @version 2019/2/26 0026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel
public class SysRoleDetailResponse implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
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
     * 数据范围
     */
    @ApiModelProperty(value = "数据范围")
    private String dataScope;

    /**
     * 是否可用
     */
    @ApiModelProperty(value = "是否可用")
    private String useable;

    /**
     * 拥有菜单列表
     */
    @ApiModelProperty(value = "拥有菜单列表")
    private List<SysMenuDetailResponse> menuList = Lists.newArrayList();
}
