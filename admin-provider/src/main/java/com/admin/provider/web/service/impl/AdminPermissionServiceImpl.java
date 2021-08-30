package com.admin.provider.web.service.impl;

import com.admin.provider.web.mapper.AdminPermissionMapper;
import com.admin.provider.model.AdminPermission;
import com.admin.provider.web.service.AdminPermissionService;
import com.admin.common.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zty on 2021/08/27.
 */
@Service
@Transactional
public class AdminPermissionServiceImpl extends AbstractService<AdminPermission> implements AdminPermissionService {
    @Resource
    private AdminPermissionMapper adminPermissionMapper;

}
