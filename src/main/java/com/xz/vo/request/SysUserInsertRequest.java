package com.xz.vo.request;

import com.xz.vo.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author yuansc
 * @date 2019/2/27 0027 下午 3:40
 */
@ApiModel
public class SysUserInsertRequest extends Base {

    private static final long serialVersionUID = -710984702758775277L;

    /**
     * 编号
     */
    private String id;

    /**
     * 登录名
     */
    @NotNull(message = "登录名不能为空")
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "登录名", required = true)
    private String loginNameInsert;

    /**
     * 用户角色ID多个用,分离
     */
    @NotNull(message = "用户角色")
    @ApiModelProperty(value = "用户角色", required = true)
    private String[] roleId;


    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空")
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    /**
     * 用户头像
     */
    private String photo;
    /**
     * 是否可登录
     */
    @NotNull(message = "是否可登录不能为空")
    @Size(min = 1, max = 64)
    @ApiModelProperty(value = "是否可登录", required = true)
    private String loginFlag;

    /**
     * 备注信息
     */
    private String remarks;

    public SysUserInsertRequest() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginNameInsert() {
        return loginNameInsert;
    }

    public void setLoginNameInsert(String loginNameInsert) {
        this.loginNameInsert = loginNameInsert;
    }

    public String[] getRoleId() {
        return roleId;
    }

    public void setRoleId(String[] roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
