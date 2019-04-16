package com.xz.entity;

import com.google.common.collect.Lists;
import com.xz.vo.entity.DataEntity;
import lombok.*;

import java.util.List;


/**
 * 系统角色实体
 * @author xuby
 * @version 2019/2/21
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BopSysRole extends DataEntity<BopSysRole> {

    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色英文名称
     */
    private String enname;

    /**
     * 机构ID
     */
    private String officeId;

    /**
     * 权限类型
     */
    private String roleType;
    /**
     * 数据范围
     */
    private String dataScope;
    /**
     * 原角色名称
     */
    private String oldName;
    /**
     * 原英文名称
     */
    private String oldEnname;
    /**
     * 是否是系统数据
     */
    private String sysData;
    /**
     * 是否可用
     */
    private String useable;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 拥有菜单列表
     */
    private List<BopSysMenu> menuList = Lists.newArrayList();

    private List<String> menuIdList = Lists.newArrayList();
}
