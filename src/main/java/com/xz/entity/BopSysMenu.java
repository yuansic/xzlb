package com.xz.entity;

import com.xz.vo.entity.DataEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 系统菜单实体
 * @author xuby
 * @version 2019/2/21
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class BopSysMenu extends DataEntity<BopSysMenu> {

    /**
     * 父级菜单
     */
    private BopSysMenu parent;

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 所有父级编号
     */
    private String parentIds;

    /**
     * 菜单级别
     */
    private Integer menuLevel;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单链接
     */
    private String href;

    /**
     * 是否默认选中(0:否,1:是)
     */
    private String isDefault;

    /**
     * 目标
     */
    private String target;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否在菜单中显示（1：显示；0：不显示）
     */
    private String isShow;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 子菜单
     */
    private List<BopSysMenu> children;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String updateBy;

    public BopSysMenu(){
        super();
        this.isShow = "1";
    }

    public BopSysMenu(String id){
        this.id = id;
    }
}
