package com.xz.vo.request;

import com.xz.vo.entity.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yuansc
 * @date 2019/2/25 0025 下午 2:16
 */
@ApiModel
public class SysUserQueryRequest extends PageRequest {


    private static final long serialVersionUID = 7286508423077043684L;
    /**
     * 登录名
     */
    //@Size(min = 1, max = 64)
    @ApiModelProperty(value = "登录名", required = true)
    private String loginNameQuery;
    /**
     * 姓名
     */
   // @Size(min = 1, max = 64)
    @ApiModelProperty(value = "姓名", required = true)
    private String name;


    public SysUserQueryRequest() {
        super();
    }

    public String getLoginNameQuery() {
        return loginNameQuery;
    }

    public void setLoginNameQuery(String loginNameQuery) {
        this.loginNameQuery = loginNameQuery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
