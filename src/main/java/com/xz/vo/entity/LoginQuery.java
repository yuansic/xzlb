package com.xz.vo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author yuansc
 * @data 2019年2月20日
 */

@ApiModel
public class LoginQuery extends Base {

    /**
     *
     */
    private static final long serialVersionUID = 2400651158483860863L;


    @NotNull
    @Size(min = 1, max = 256)
    @ApiModelProperty(value = "登录IP", required = true)
    private String loginIp;


    @NotNull
    @Size(min = 1, max = 256)
    @ApiModelProperty(value = "登录密码", required = true)
    private String ldapPassword;

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }


    public String getLdapPassword() {
        return ldapPassword;
    }

    public void setLdapPassword(String ldapPassword) {
        this.ldapPassword = ldapPassword;
    }
}
