package com.xz.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author yuansc
 * @date 2019/3/5 0005 下午 5:25
 */
@ApiModel(value = "机构查询返回对象")
public class BopSysOfficeListResponse implements Serializable {

    private static final long serialVersionUID = -3732127475114287200L;
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private String id;

    /**
     * 父级编号
     */
    @ApiModelProperty(value = "父级编号")
    private String parentId;

    /**
     * 所有父级编号
     */
    @ApiModelProperty(value = "所有父级编号")
    private String parentIds;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Long sort;

    /**
     * 机构类型
     */
    @ApiModelProperty(value = "机构类型")
    private String type;

    /**
     * 机构等级
     */
    @ApiModelProperty(value = "机构等级")
    private String grade;

    /**
     * 联系地址
     */
    @ApiModelProperty(value = "联系地址")
    private String address;

    /**
     * 邮政编码
     */
    @ApiModelProperty(value = "邮政编码")
    private String zipCode;

    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人")
    private String master;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 传真
     */
    @ApiModelProperty(value = "传真")
    private String fax;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    private String useable;

    /**
     * 主负责人
     */
    @ApiModelProperty(value = "主负责人")
    private String primaryPerson;

    /**
     * 副负责人
     */
    @ApiModelProperty(value = "副负责人")
    private String deputyPerson;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    private String remarks;

    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记")
    private String delFlag;


    /**
     * 子菜单
     */
    @ApiModelProperty(value = "子机构集合")
    private List<BopSysOfficeListResponse> children;


    public BopSysOfficeListResponse() {
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

    public String getName() {
        return name;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
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

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public List<BopSysOfficeListResponse> getChildren() {
        return children;
    }

    public void setChildren(List<BopSysOfficeListResponse> children) {
        this.children = children;
    }

}
