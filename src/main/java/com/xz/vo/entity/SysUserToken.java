package com.xz.vo.entity;

/**
 * @author yuansc
 * @date 2019/3/4 0004 下午 7:31
 */
public class SysUserToken {

    private String token;

    public SysUserToken(String token) {
        this.token = token;
    }

    public SysUserToken() {
        super();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
