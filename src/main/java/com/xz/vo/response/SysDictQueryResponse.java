package com.xz.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yuansc
 * @date 2019/2/25 0025 下午 5:50
 */
@ApiModel(value = "字典表表信息")
public class SysDictQueryResponse implements Serializable {

    private static final long serialVersionUID = 6952368723478875552L;
    /**
     * 编号
     */
    @ApiModelProperty(value = "用户id")
    private String id;

    /**
     * 键值
     */
    @ApiModelProperty(value = "键值")
    private String value;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    private String label;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String type;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 排序（升序）
     */
    @ApiModelProperty(value = "排序")
    private Long sort;

    /**
     * 父级编号
     */
    @ApiModelProperty(value = "父级编号")
    private String parentId;

    public SysDictQueryResponse() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
