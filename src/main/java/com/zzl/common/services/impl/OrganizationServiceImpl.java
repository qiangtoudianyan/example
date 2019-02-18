package com.zzl.common.services.impl;

import com.boco.mis.common.dal.entity.OrganizationTree;
import com.boco.mis.common.dal.mapper.OrganizationMapper;
import com.boco.mis.common.services.OrganizationService;
import com.boco.mis.framework.common.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 张志龙
 * @Date: 2018/8/28 16:51
 * @Description:
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public List<OrganizationTree> getAllOrganization(Integer orgParentId,
                                                     Integer selectedUserId,
                                                     Integer loginUserId) {
        List<OrganizationTree> organizationTrees = organizationMapper
                .getAllChildOrganization(orgParentId, selectedUserId, loginUserId);
        organizationTrees.forEach(organizationTree -> {
            List<OrganizationTree> childOrganizationTrees =
                    getAllOrganization(organizationTree.getId(), selectedUserId, loginUserId);
            if (ListUtils.isEmpty(childOrganizationTrees)) {
                organizationTree.setIsParent(false);
            } else {
                organizationTree.setIsParent(true);
            }
            organizationTree.setChildren(childOrganizationTrees);
        });
        return organizationTrees;
    }
}
