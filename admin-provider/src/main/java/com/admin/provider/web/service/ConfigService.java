package com.admin.provider.web.service;
import com.admin.provider.model.Config;
import com.admin.common.service.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by zty on 2021/08/27.
 */
public interface ConfigService extends Service<Config> {
    String getSysconfig(String key);
    boolean isRedisCache();
}
