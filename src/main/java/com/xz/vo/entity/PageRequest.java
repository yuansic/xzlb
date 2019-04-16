package com.xz.vo.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yuansc
 * @date 2019/2/25 0025 下午 2:37
 */
public class PageRequest implements Serializable {


    /**
     * 当前页
     */
    @NotNull(message="当前页不能为空")
    private String pageNo;
    /**
     * 每页记录数 不可空 每条数
     */
    @NotNull(message="每页记录数不能为空")
    private String pageSize;

    public PageRequest() {
        super();
    }
    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
