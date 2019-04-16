package com.xz.vo.response;

import lombok.*;

import java.io.Serializable;

/**
 * @author yuansc
 * @date 2019/3/28 0028 上午 10:30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysDictResponse implements Serializable {

    /**
     * 编号
     */
    private String id;
    /**
     * 数据值
     */
    private String value;
    /**
     * 标签名
     */
    private String label;
    /**
     * 类型
     */
    private String type;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序（升序）
     */
    private Long sort;
    /**
     * 父级编号
     */
    private String parentId;
    /**
     * 备注信息
     */
    private String remarks;

}
