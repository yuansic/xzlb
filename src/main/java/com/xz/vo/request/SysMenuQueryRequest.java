package com.xz.vo.request;

import com.xz.vo.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 菜单查询请求对象
 * @author xuby
 * @version 2019/2/26 0026
 */
@ApiModel(value = "菜单查询请求对象")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysMenuQueryRequest extends Base {

    private static final long serialVersionUID = 4954763810080251398L;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String name;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;
}
