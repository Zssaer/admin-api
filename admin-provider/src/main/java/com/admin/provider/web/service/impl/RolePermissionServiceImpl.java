package com.admin.provider.web.service.impl;

import com.admin.common.service.AbstractService;
import com.admin.provider.dto.MenuDTO;
import com.admin.provider.enums.PermisssionTypeEnum;
import com.admin.provider.model.Admin;
import com.admin.provider.model.AdminPermission;
import com.admin.provider.model.RolePermission;
import com.admin.provider.vo.MenuTreeVO;
import com.admin.provider.vo.MetaVO;
import com.admin.provider.web.controller.request.SaveRoleMenuReq;
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
     * @param loginId 登录id
     */
    @Override
    public List<String> getRolePermissionById(Integer loginId) {
        List<String> permissionMethodList = new ArrayList<>();

        Admin admin = adminService.findById(loginId);
        Integer roleId = admin.getRoleId();
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
     */
    @Override
    public List<MenuDTO> getMenuDto(Integer roleId) {
        Condition rolePermissionCdt = new Condition(RolePermission.class);
        rolePermissionCdt.createCriteria().andEqualTo("roleId", roleId);
        List<RolePermission> rolePermissionList = rolePermissionMapper.selectByCondition(rolePermissionCdt);

        List<MenuDTO> list = fillMenu(rolePermissionList);
        return list;
    }

    /**
     * 获取所有菜单列表(用于分配菜单权限显示)
     *
     * @return
     */
    @Override
    public List<MenuDTO> getAllMenuList() {
        List<RolePermission> rolePermissions = rolePermissionMapper.selectAll();
        List<MenuDTO> list = fillMenu(rolePermissions);
        return list;
    }

    /**
     * 获取角色的菜单列表树(用于分配菜单权限显示)
     *
     * @param roleId 角色id
     * @return
     */
    @Override
    public MenuTreeVO getMenuList(Integer roleId) {
        //获取所有菜单(不考虑一级、二级)
        Condition condition = new Condition(AdminPermission.class);
        condition.createCriteria().andEqualTo("type", 1);
        List<AdminPermission> menuList = permissionService.findByCondition(condition);

        //获取所有一级菜单
        Condition condition2 = new Condition(AdminPermission.class);
        condition2.createCriteria().andEqualTo("type", 1);
        List<AdminPermission> parentMenuList = permissionService.findByCondition(condition2);

        //获取角色拥有的权限(权限包括菜单)
        Condition con = new Condition(RolePermission.class);
        con.createCriteria().andEqualTo("roleId", roleId);
        List<RolePermission> list = rolePermissionMapper.selectByCondition(con);

        //查询角色拥有的菜单ID,作为拥有菜单ID列表
        List<Integer> hasMenuIdList = new ArrayList<>();
        for (RolePermission p : list) {
            Integer permissionId = p.getPermissionId();
            for (AdminPermission permission : menuList) {
                if (permissionId.equals(permission.getId())) {
                    hasMenuIdList.add(permissionId);
                }
            }
        }
        //查询拥有的父类菜单ID,作为展开菜单ID列表
        List<Integer> parentMenuIdList = new ArrayList<Integer>();
        for (RolePermission p : list) {
            Integer permissionId = p.getPermissionId();
            for (AdminPermission permission : parentMenuList) {
                if (permissionId.equals(permission.getId())) {
                    parentMenuIdList.add(permissionId);
                }
            }
        }
        //创建树,将其展开id列表和拥有id列表放入
        MenuTreeVO menuTreeVO = new MenuTreeVO();
        menuTreeVO.setCheckedIds(hasMenuIdList);
        menuTreeVO.setExpandedIds(parentMenuIdList);
        return menuTreeVO;
    }

    @Override
    public void saveRoleMenu(SaveRoleMenuReq req) {
        Integer roleId = req.getRoleId();
        List<Integer> menuIds = req.getMenuids();
        //获取分配菜单下的功能权限ID
        for (Integer id : menuIds) {
            Condition condition = new Condition(AdminPermission.class);
            condition.createCriteria().andEqualTo("pid", id).andEqualTo("type", PermisssionTypeEnum.METHOD.getCode());

            List<AdminPermission> permissionList = permissionService.findByCondition(condition);
            for (AdminPermission p : permissionList) {
                Integer permissionId = p.getId();
                menuIds.add(permissionId);
            }
        }

        // 删除角色所有菜单权限,重新分配权限
        Condition condition = new Condition(RolePermission.class);
        condition.createCriteria().andEqualTo("roleId", roleId);
        rolePermissionMapper.deleteByCondition(condition);

        SavePer(roleId, menuIds);


    }

    /**
     * 保存权限到角色权限表
     */
    private void SavePer(Integer roleId, List<Integer> menuIds) {
        for (Integer permissionId : menuIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setId(0);
            // 默认为能够使用
            rolePermission.setIsUse(1);
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionMapper.insert(rolePermission);
        }
    }

    /**
     * 拼装菜单
     *
     * @param rolePermissionList 角色权限列表
     * @return
     */
    public List<MenuDTO> fillMenu(List<RolePermission> rolePermissionList) {
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
                menu.setId(permissionList.get(0).getId());
                menu.setName(permissionList.get(0).getName());
                menu.setPath("/" + permissionList.get(0).getPath());
                menu.setComponent("LAYOUT");
                menu.setPage(menu.getPath() + "/" + "Index.vue");
                menu.setMeta(new MetaVO(permissionList.get(0).getName(), permissionList.get(0).getIcon()));

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
            menu.setId(permission.getId());
            menu.setName(permission.getName());
            menu.setPath(menuDTO.getPath() + "/" + permission.getPath());
            // 子类菜单的访问路径为其父菜单路径下的 对于名称文件夹的index.vue文件
            menu.setPage(menuDTO.getPath() + "/" + permission.getPath() + "/" + "Index.vue");
            menu.setMeta(new MetaVO(permission.getName(), permission.getIcon()));
            childMenuList.add(menu);
        }
        menuDTO.setChildren(childMenuList);
    }


}
