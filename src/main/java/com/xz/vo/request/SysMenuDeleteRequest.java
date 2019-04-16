package com.xz.vo.request;

import com.xz.vo.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 菜单删除请求对象
 * @author xuby
 * @version 2019/2/27 0027
 */
@ApiModel
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysMenuDeleteRequest extends Base implements Serializable {

    /**
     * 菜单ID
     */
    @NotNull
    @ApiModelProperty(value = "菜单ID",required = true)
    private String id;
}
