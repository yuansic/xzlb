package com.xz.vo.request;

import com.xz.vo.entity.PageRequest;
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
public class SysRoleQueryListRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = -2784037026405833021L;
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    private String loginName;
}
