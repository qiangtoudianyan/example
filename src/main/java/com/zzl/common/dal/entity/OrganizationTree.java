package com.zzl.common.dal.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author: 张志龙
 * @Date: 2018/8/28 16:53
 * @Description:
 */
@Getter
@Setter
public class OrganizationTree {
    /**
     * 节点ID
     */
    private Integer id;

    /**
     * 当前节点父ID
     */
    private Integer pId;
    /**
     * 节点编码
     */
    private String code;

    /**
     * 节点名称
     */
    private String name;
    /**
     * 是否选中
     */
    private Boolean checked;
    /**
     * 是否可以选择
     */
    private Boolean chkDisabled;
    /**
     * 是否父节点
     */
    private Boolean isParent;
    /**
     * 该节点所有的子节点
     */
    List<OrganizationTree> children;
}
