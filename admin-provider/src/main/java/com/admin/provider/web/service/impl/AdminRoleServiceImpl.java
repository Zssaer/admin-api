package com.admin.provider.web.service.impl;

import com.admin.provider.web.mapper.AdminRoleMapper;
import com.admin.provider.model.AdminRole;
import com.admin.provider.web.service.AdminRoleService;
import com.admin.common.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zty on 2021/08/27.
 */
@Service
@Transactional
public class AdminRoleServiceImpl extends AbstractService<AdminRole> implements AdminRoleService {
    @Resource
    private AdminRoleMapper adminRoleMapper;

    @Override
    public List<String> getRole(Integer loginId) {
        List<AdminRole> roles = adminRoleMapper.selectByIds(String.valueOf(loginId));
        List<String> roleList=new ArrayList<>();
        for (AdminRole role:roles) {
            roleList.add(role.getRoleName());
        }
        return roleList;
    }
}
