package com.xz.entity;

import java.util.Date;

public class BopSysOffice {
    /**
     * 编号
     */
    private String id;

    /**
     * 父级编号
     */
    private String parentId;

    /**
     * 所有父级编号
     */
    private String parentIds;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 归属区域
     */
    private String areaId;

    /**
     * 区域编码
     */
    private String code;

    /**
     * 机构类型
     */
    private String type;

    /**
     * 机构等级
     */
    private String grade;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 邮政编码
     */
    private String zipCode;

    /**
     * 负责人
     */
    private String master;

    /**
     * 电话
     */
    private String phone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否启用
     */
    private String useable;

    /**
     * 主负责人
     */
    private String primaryPerson;

    /**
     * 副负责人
     */
    private String deputyPerson;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 编号
     * @return id 编号
     */
    public String getId() {
        return id;
    }

    /**
     * 编号
     * @param id 编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 父级编号
     * @return parent_id 父级编号
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 父级编号
     * @param parentId 父级编号
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 所有父级编号
     * @return parent_ids 所有父级编号
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * 所有父级编号
     * @param parentIds 所有父级编号
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 排序
     * @return sort 排序
     */
    public Long getSort() {
        return sort;
    }

    /**
     * 排序
     * @param sort 排序
     */
    public void setSort(Long sort) {
        this.sort = sort;
    }

    /**
     * 归属区域
     * @return area_id 归属区域
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * 归属区域
     * @param areaId 归属区域
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    /**
     * 区域编码
     * @return code 区域编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 区域编码
     * @param code 区域编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 机构类型
     * @return type 机构类型
     */
    public String getType() {
        return type;
    }

    /**
     * 机构类型
     * @param type 机构类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 机构等级
     * @return grade 机构等级
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 机构等级
     * @param grade 机构等级
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 联系地址
     * @return address 联系地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 联系地址
     * @param address 联系地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 邮政编码
     * @return zip_code 邮政编码
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * 邮政编码
     * @param zipCode 邮政编码
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * 负责人
     * @return master 负责人
     */
    public String getMaster() {
        return master;
    }

    /**
     * 负责人
     * @param master 负责人
     */
    public void setMaster(String master) {
        this.master = master;
    }

    /**
     * 电话
     * @return phone 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 电话
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 传真
     * @return fax 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 传真
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 邮箱
     * @return email 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 是否启用
     * @return USEABLE 是否启用
     */
    public String getUseable() {
        return useable;
    }

    /**
     * 是否启用
     * @param useable 是否启用
     */
    public void setUseable(String useable) {
        this.useable = useable;
    }

    /**
     * 主负责人
     * @return PRIMARY_PERSON 主负责人
     */
    public String getPrimaryPerson() {
        return primaryPerson;
    }

    /**
     * 主负责人
     * @param primaryPerson 主负责人
     */
    public void setPrimaryPerson(String primaryPerson) {
        this.primaryPerson = primaryPerson;
    }

    /**
     * 副负责人
     * @return DEPUTY_PERSON 副负责人
     */
    public String getDeputyPerson() {
        return deputyPerson;
    }

    /**
     * 副负责人
     * @param deputyPerson 副负责人
     */
    public void setDeputyPerson(String deputyPerson) {
        this.deputyPerson = deputyPerson;
    }

    /**
     * 创建者
     * @return create_by 创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 创建者
     * @param createBy 创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 创建时间
     * @return create_date 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 更新者
     * @return update_by 更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 更新者
     * @param updateBy 更新者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 更新时间
     * @return update_date 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 备注信息
     * @return remarks 备注信息
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注信息
     * @param remarks 备注信息
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 删除标记
     * @return del_flag 删除标记
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标记
     * @param delFlag 删除标记
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }



}