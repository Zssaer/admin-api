package com.admin.provider.web.service.impl;

import com.admin.provider.web.mapper.SysLogMapper;
import com.admin.provider.model.SysLog;
import com.admin.provider.web.service.SysLogService;
import com.admin.common.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zty on 2021/08/31.
 */
@Service
@Transactional
public class SysLogServiceImpl extends AbstractService<SysLog> implements SysLogService {
    @Resource
    private SysLogMapper sysLogMapper;

}
