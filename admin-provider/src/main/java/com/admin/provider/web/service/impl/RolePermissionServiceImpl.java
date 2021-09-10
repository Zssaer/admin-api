package com.admin.provider.web.service.impl;

import com.admin.common.service.AbstractService;
import com.admin.provider.dto.MenuDTO;
import com.admin.provider.model.Admin;
import com.admin.provider.model.AdminPermission;
import com.admin.provider.model.RolePermission;
import com.admin.provider.vo.Meta;
import com.admin.provider.web.mapper.RolePermissionMapper;
import com.admin.provider.web.service.AdminPermissionService;
import com.admin.provider.web.service.AdminService;
import com.admin.provider.web.service.RolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zty on 2021/08/27.
 */
@Service
@Transactional
public class RolePermissionServiceImpl extends AbstractService<RolePermission> implements RolePermissionService {
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Resource
    private AdminPermissionService permissionService;
    @Resource
    private AdminService adminService;

    /**
     * 根据登录的用户id来获取权限
     *
     * @param loginId
     * @return
     */
    @Override
    public List<String> getRolePermissionById(Integer loginId) {
        List<String> permissionMethodList = new ArrayList<>();

        Admin admin = adminService.findById(loginId);
        Integer roleId = admin.getRole();
        //获取角色权限表
        Condition rolePermissionCd = new Condition(RolePermission.class);
        rolePermissionCd.createCriteria().andEqualTo("roleId", roleId);
        List<RolePermission> rolePermissionList = rolePermissionMapper.selectByCondition(rolePermissionCd);
        //获取对应功能权限的值
        for (RolePermission rolePermission : rolePermissionList) {
            AdminPermission menuPermission = permissionService.findById(rolePermission.getPermissionId());
            Condition condition = new Condition(AdminPermission.class);
            condition.createCriteria().andEqualTo("pid", menuPermission.getId()).andIsNotNull("method");
            List<AdminPermission> permissionList = permissionService.findByCondition(condition);
            for (AdminPermission permission : permissionList) {
                String path = permission.getPath();
                String method = permission.getMethod();
                permissionMethodList.add(path + "-" + method);
            }
        }
        return permissionMethodList;
    }

    /**
     * 根据用户ID来获取菜单列表
     * 用作管理端动态路由
     *
     * @param adminId
     * @return
     */
    @Override
    public List<MenuDTO> getMenu(Integer adminId) {
        Admin admin = adminService.findById(adminId);
        Condition rolePermissionCdt = new Condition(RolePermission.class);
        rolePermissionCdt.createCriteria().andEqualTo("roleId", admin.getRole());
        List<RolePermission> rolePermissionList = rolePermissionMapper.selectByCondition(rolePermissionCdt);

        List<MenuDTO> menuList = new ArrayList<>();
        for (RolePermission rolePermission : rolePermissionList) {
            //查询一级菜单
            Integer permissionId = rolePermission.getPermissionId();
            Condition permissionCdt = new Condition(AdminPermission.class);
            permissionCdt.createCriteria().andEqualTo("id", permissionId).andIsNull("pid");
            List<AdminPermission> permissionList = permissionService.findByCondition(permissionCdt);
            if (!permissionList.isEmpty()) {
                //组装菜单
                MenuDTO menu = new MenuDTO();
                menu.setName(permissionList.get(0).getName());
                menu.setPath("/" + permissionList.get(0).getPath());
                menu.setComponent("LAYOUT");
                menu.setPage(menu.getPath() + "/" + "Index.vue");
                menu.setMeta(new Meta(permissionList.get(0).getName(), permissionList.get(0).getIcon()));

                Condition childrenCdt = new Condition(AdminPermission.class);
                childrenCdt.createCriteria().andEqualTo("pid", permissionList.get(0).getId()).andEqualTo("type", 1);
                List<AdminPermission> childPermissionList = permissionService.findByCondition(childrenCdt);
                fillChildren(menu, childPermissionList);

                menuList.add(menu);
            }
        }
        return menuList;
    }

    /**
     * 拼装子类菜单
     */
    public void fillChildren(MenuDTO menuDTO, List<AdminPermission> childList) {
        List<MenuDTO> childMenuList = new ArrayList<>();
        for (AdminPermission permission : childList) {
            MenuDTO menu = new MenuDTO();
            menu.setName(permission.getName());
            menu.setPath(menuDTO.getPath()+"/"+ permission.getPath());
            // 子类菜单的访问路径为其父菜单路径下的 对于名称文件夹的index.vue文件
            menu.setPage(menuDTO.getPath() + "/" + permission.getPath()  + "/" +  "Index.vue");
            menu.setMeta(new Meta(permission.getName(), permission.getIcon()));
            childMenuList.add(menu);
        }
        menuDTO.setChildren(childMenuList);
    }


}
