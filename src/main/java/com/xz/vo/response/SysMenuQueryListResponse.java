package com.xz.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单查询返回对象
 * @author xuby
 * @version 2019/2/26 0026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "菜单查询返回对象")
public class SysMenuQueryListResponse implements Serializable {

    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单ID")
    private String id;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String name;

    /**
     * 菜单链接
     */
    @ApiModelProperty(value = "菜单链接")
    private String href;

    /**
     * 排序
     */
    @ApiModelProperty(value = "菜单排序")
    private Integer sort;

    /**
     * 是否在菜单中显示（1：显示；0：不显示）
     */
    @ApiModelProperty(value = "是否在菜单中显示（1：显示；0：不显示）")
    private String isShow;

    /**
     * 菜单级别
     */
    private Integer menuLevel;

    /**
     * 菜单是否默认选中
     */
    @ApiModelProperty(value = "是否默认选中1：是；0：否")
    private String isDefault;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    private String parentId;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon = "";

    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String permission = "";

    /**
     * 子菜单
     */
    @ApiModelProperty(value = "子菜单集合")
    private List<SysMenuQueryListResponse> children;
}
