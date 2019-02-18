package com.zzl.common.dal.mapper;

import com.boco.mis.common.dal.entity.OrganizationTree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: 张志龙
 * @Date: 2018/8/28 17:05
 * @Description:
 */
@Mapper
public interface OrganizationMapper {
    /**
     * 获取所有子节点以及当前节点状态
     *
     * @param parentOrgId    父节点ID
     * @param selectedUserId 选择的用户ID
     * @param loginUserId    当前登录人的用户ID
     * @return 所有子节点的信息
     */
    List<OrganizationTree> getAllChildOrganization(@Param("parentOrgId") Integer parentOrgId,
                                                   @Param("selectedUserId") Integer selectedUserId,
                                                   @Param("loginUserId") Integer loginUserId);
}
