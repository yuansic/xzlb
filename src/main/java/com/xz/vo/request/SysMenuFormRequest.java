package com.xz.vo.request;

import com.xz.vo.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * 系统菜单保存请求对象
 * @author xuby
 * @version 2019/2/22 0022
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class SysMenuFormRequest extends Base {

    private static final long serialVersionUID = -6920930539610158875L;
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID,编辑时必填")
    private String id;

    /**
     * 父级菜单
     */
    @ApiModelProperty(value = "父级ID",required = true)
    private String parentId;

    /**
     * 菜单名称
     */
    @NotNull
    @ApiModelProperty(value = "菜单名称",required = true)
    private String name;

    /**
     * 菜单链接
     */
    @NotNull
    @ApiModelProperty(value = "菜单链接",required = true)
    private String href;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序",required = true)
    private Integer sort;

    /**
     * 是否在菜单中显示（1：显示；0：不显示）
     */
    @ApiModelProperty(value = "菜单是否显示1：显示；0：不显示")
    private String isShow = "1";

    /**
     * 菜单是否默认选中
     */
    @ApiModelProperty(value = "是否默认选中1：是；0：否")
    private String isDefault = "0";

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String permission;
}
