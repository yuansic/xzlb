package com.xz.vo.request;

import com.xz.vo.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author yuansc
 * @date 2019/2/26 0026 下午 2:04
 */
@ApiModel
public class SysDictInsertRequest extends Base {


    private static final long serialVersionUID = -4704397303171804381L;

    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "主键ID", required = true)
    private String id;


    /**
     * 键值
     */
    @NotNull(message = "键值不能为空")
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "键值", required = true)
    private String value;

    /**
     * 标签
     */
    @NotNull(message = "标签不能为空")
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "标签", required = true)
    private String label;

    /**
     * 类型
     */
    @NotNull(message = "类型不能为空")
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "类型", required = true)
    private String type;

    /**
     * 描述
     */
    @NotNull(message = "描述不能为空")
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "描述", required = true)
    private String description;

    /**
     * 排序（升序）
     */
    @NotNull(message = "排序不能为空")
    @ApiModelProperty(value = "排序", required = true)
    private Long sort;

    /**
     * 备注信息
     */
    @Size(min = 1, max = 128)
    @ApiModelProperty(value = "备注信息", required = true)
    private String remarks;

    public SysDictInsertRequest() {
        super();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
