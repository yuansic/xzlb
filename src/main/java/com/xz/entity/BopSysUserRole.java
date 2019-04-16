package com.xz.entity;

import com.xz.vo.entity.DataEntity;
import lombok.*;

import java.util.List;

/**
 * 用户角色实体
 * @author xuby
 * @version 2019/2/28 0028
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BopSysUserRole extends DataEntity<BopSysUserRole> {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 用户拥有角色集合
     */
    private List<BopSysRole> sysRoleList;
}
