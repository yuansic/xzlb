package com.xz.vo.entity;

/**
 * @author yuansc
 * @date 2019/3/4 0004 下午 7:40
 */
public class SysUserBean {


    private String id;

    private String loginName;

    private String token;

    public SysUserBean() {
        super();
    }

    public SysUserBean(String id, String loginName, String token) {
        this.id = id;
        this.loginName = loginName;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
