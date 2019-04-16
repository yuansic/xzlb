package com.xz.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yuansc
 * @date 2019/2/25 0025 下午 3:25
 */
@ApiModel(value = "用户列表信息")
public class SysUserQueryResponse implements Serializable {


    private static final long serialVersionUID = -2434082350518852943L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户id")
    private String id;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名")
    private String loginName;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    private String photo;

    /**
     * 最后登陆时间
     */
    @ApiModelProperty(value = "最后登陆时间")
    private String loginDate;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createDate;

    /**
     * 是否可登陆
     */
    @ApiModelProperty(value = "是否可登录", required = true)
    private String loginFlag;


    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public SysUserQueryResponse() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
