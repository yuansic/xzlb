package com.xz.vo.entity;

import com.xz.enums.DelFlagEnum;
import com.xz.util.IdGen;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 数据Entity类
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
@Getter
@Setter
public abstract class DataEntity<T> extends BaseEntity<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    protected String remarks;
    /**
     * 创建时间
     */
    protected Date createTime;
    protected Date createDate;
    /**
     * 更新时间
     */
    protected Date updateTime;
    protected Date updateDate;
    /**
     * 删除标记DelFlagEnum
     */
    protected String delFlag;

    public DataEntity() {
        super();
        this.delFlag = DelFlagEnum.NORMAL.getValue() + "";
    }

    public DataEntity(String id) {
        super(id);
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert() {
        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
        if (!this.isNewRecord) {
            setId(IdGen.uuid());
        }
        this.updateTime = new Date();
        this.updateDate = new Date();
        this.createTime = this.updateTime;
        this.createDate = this.updateDate;
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate() {
        this.updateTime = new Date();
        this.updateDate = new Date();
    }


}
