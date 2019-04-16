package com.xz.vo.request;

import com.xz.vo.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色信息保存请求对象
 * @author xuby
 * @version 2019/2/22 0022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel
public class SysRoleFormRequest extends Base {
    private static final long serialVersionUID = 5056167212861259512L;
    /**
     * 主键Id
     */
    @ApiModelProperty(value = "主键ID,编辑时必填")
    private String id;

    /**
     * 角色名称
     */
    @NotNull
    @ApiModelProperty(value = "角色名称",required = true)
    private String name;

    /**
     * 角色英文名称
     */
    @ApiModelProperty(value = "角色英文名称")
    private String enname;


    /**
     * 数据范围
     */
    @NotNull
    @ApiModelProperty(value = "数据范围",required = true)
    private String dataScope;


    /**
     * 是否可用
     */
    @NotNull
    @ApiModelProperty(value = "是否可用(0：不可用,1:可用)",required = true)
    private String useable;

    /**
     * 拥有菜单ID
     */
    @ApiModelProperty(value = "拥有菜单ids")
    private List<String> menuIdList;
}
