package com.xz.vo.request;

import com.xz.vo.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author yuansc
 * @date 2019/3/6 0006 下午 2:13
 */
@ApiModel(value = "机构保存请求对象")
public class SysOfficeInsertRequest extends Base {

    private static final long serialVersionUID = -2863459023228203514L;
    /**
     * 编号
     */
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "主键ID", required = true)
    private String id;

    /**
     * 父级编号
     */
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "父级编号", required = true)
    private String parentId;

    /**
     * 所有父级编号
     */
    @Size(min = 1, max = 2000)
    @ApiModelProperty(value = "所有父级编号", required = true)
    private String parentIds;

    /**
     * 名称
     */
    @NotNull
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    /**
     * 排序
     */
    @NotNull
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "排序", required = true)
    private Long sort;


    /**
     * 机构类型
     */
    @NotNull
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "机构类型", required = true)
    private String type;

    /**
     * 机构等级
     */
    @NotNull
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "机构等级", required = true)
    private String grade;

    /**
     * 联系地址
     */
    @Size(min = 1, max = 128)
    @ApiModelProperty(value = "联系地址", required = true)
    private String address;

    /**
     * 邮政编码
     */
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "邮政编码", required = true)
    private String zipCode;

    /**
     * 负责人
     */
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "负责人", required = true)
    private String master;

    /**
     * 电话
     */
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "电话", required = true)
    private String phone;

    /**
     * 传真
     */
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "传真", required = true)
    private String fax;

    /**
     * 邮箱
     */
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;

    /**
     * 主负责人
     */
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "主负责人", required = true)
    private String primaryPerson;

    /**
     * 副负责人
     */
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "副负责人", required = true)
    private String deputyPerson;
    /**
     * 备注信息
     */
    @Size(min = 1, max = 256)
    @ApiModelProperty(value = "备注信息", required = true)
    private String remarks;

    public SysOfficeInsertRequest() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimaryPerson() {
        return primaryPerson;
    }

    public void setPrimaryPerson(String primaryPerson) {
        this.primaryPerson = primaryPerson;
    }

    public String getDeputyPerson() {
        return deputyPerson;
    }

    public void setDeputyPerson(String deputyPerson) {
        this.deputyPerson = deputyPerson;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
