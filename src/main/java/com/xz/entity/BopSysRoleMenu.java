package com.xz.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 角色菜单对象
 * @author xuby
 * @version 2019/3/11 0011
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BopSysRoleMenu implements Serializable {

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 菜单ID
     */
    private String menuId;
}
