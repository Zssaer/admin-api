package com.admin.provider.web.service;
import com.admin.provider.model.ConfigGroup;
import com.admin.common.service.Service;
import com.admin.provider.web.controller.response.ConfigTreeResp;

import java.util.List;


/**
 * Created by zty on 2021/10/26.
 */
public interface ConfigGroupService extends Service<ConfigGroup> {

    List<ConfigGroup> getCommonConfigGroupList();

    List<ConfigTreeResp> fillConfigGroupList(List<ConfigGroup> configGrouplist);

    List<ConfigGroup> getSysfigGroupList();
}
