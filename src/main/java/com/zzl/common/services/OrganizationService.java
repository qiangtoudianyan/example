package com.zzl.common.services;

import com.boco.mis.common.dal.entity.OrganizationTree;

import java.util.List;

/**
 * @author: 张志龙
 * @Date: 2018/8/29 11:10
 * @Description:
 */
public interface OrganizationService {

    /**
     * 获取所有节点
     *
     * @param orgParentId    节点父ID
     * @param selectedUserId 选择的用户ID
     * @param loginUserId    登录人的用户ID
     * @return 所有节点信息
     */
    List<OrganizationTree> getAllOrganization(Integer orgParentId,
                                              Integer selectedUserId,
                                              Integer loginUserId);
}
