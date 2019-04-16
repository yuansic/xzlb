package com.xz.vo.entity;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author yuansc
 * @date 2019/2/26 0026 下午 2:10
 */
public class Base implements Serializable {

    //登陆用户名，必填
    @Size(min = 1, max = 64)
    private String loginName;

    public Base() {
        super();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
