package com.xz.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 系统操作日志查询返回对象
 * @author xuby
 * @version 2019/2/28 0028
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel
public class SysLogQueryListResponse implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 日志类型（1：操作日志；2：错误日志）
     */
    @ApiModelProperty(value = "日志类型（1：操作日志；2：错误日志）")
    private String type;

    /**
     * 日志标题
     */
    @ApiModelProperty(value = "日志标题")
    private String title;

    /**
     * 操作的URL
     */
    @ApiModelProperty(value = "操作URL")
    private String requestUri;

    /**
     * 操作方式
     */
    @ApiModelProperty(value = "操作方式")
    private String method;

    /**
     * 操作提交数据
     */
    @ApiModelProperty(value = "操作提交数据")
    private String params;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    private long createDate;

    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    private String createByName;
}
