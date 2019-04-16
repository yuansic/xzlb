package com.xz.vo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author yuansc
 * @date 2019/2/25 0025 下午 3:21
 */
public class PageResponse<T> implements Serializable {

    /**
     * 总页数
     */
    private long count;
    /**
     * 数据
     */
    private List<T> list;

    public PageResponse() {
    }

    public PageResponse(long count, List<T> list) {
        this.count = count;
        this.list = list;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
