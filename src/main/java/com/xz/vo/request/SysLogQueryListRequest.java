package com.xz.vo.request;

import com.xz.vo.entity.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 日志列表查询请求参数
 * @author xuby
 * @version 2019/2/28 0028
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel
public class SysLogQueryListRequest extends PageRequest implements Serializable {

    /**
     * 查询开始时间
     */
    @ApiModelProperty(value = "查询开始时间",required = true)
    private long beginDate;

    /**
     * 查询结束时间
     */
    @ApiModelProperty(value = "查询结束时间",required = true)
    private long endDate;
}
