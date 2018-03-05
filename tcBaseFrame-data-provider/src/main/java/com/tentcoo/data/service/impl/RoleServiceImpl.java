package com.tentcoo.data.service.impl;

import com.tentcoo.data.api.RoleService;
import com.tentcoo.data.mapper.RoleMapper;
import com.tentcoo.data.pojo.Employee;
import com.tentcoo.data.pojo.Role;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Administrator
 * @date 2018/2/7 0007
 */
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = RoleService.class)
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public int delete(String id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int save(Role role) {
        role.setId(UUID.randomUUID().toString());
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        //保存与菜单关系
        roleMapper.saveMenuRelation(role.getId(),role.getMenuIdList());
        return roleMapper.insert(role);
    }

    @Override
    public Role getOne(String id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Role record) {
        Role old = this.getOne(record.getId());
        record.setCreateDate(old.getCreateDate());
        record.setUpdateDate(new Date());
        record.setCreateBy(old.getCreateBy());
        record.setDelFlag(old.getDelFlag());
        //删除与菜单关系
        roleMapper.deleteMenuRelation(record.getId());
        //保存与菜单关系
        roleMapper.saveMenuRelation(record.getId(),record.getMenuIdList());
        return roleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Role> listAll() {
        return roleMapper.listAll();
    }

    @Override
    public void saveOrUpdate(Role role, Employee employee) {
        role.setUpdateBy(employee);
        if (role.getIsSys().equals("")) {
            role.setIsSys(Role.IS_SYS);
        }
        if (role.getUseable().equals("")) {
            role.setUseable(Role.USEABLE);
        }
        if (null == role.getId() || "".equals(role.getId())) {
            role.setCreateBy(employee);
            this.save(role);
        } else {
            this.update(role);
        }
    }

    @Override
    public void changeRoleState(String id) {
        //判断用户是否有删除角色权限 TODO
        roleMapper.changeRoleState(id);
    }

    @Override
    public void saveEmployeeRelation(String rid, String[] empIds) {
        //删除原有关系,再保存新的关系
        roleMapper.deleteEmpRelation(rid);
        roleMapper.saveEmployeeRelation(rid,empIds);
    }

    @Override
    public void removeRoleAndEmpRelation(String rid, String empId) {
        roleMapper.removeRoleAndEmpRelation(rid,empId);
    }

    @Override
    public List<Role> getRoleByEmpId(String employeeId) {
        return roleMapper.getRoleByEmpId(employeeId,Role.DEL_FLAG_NORMAL);
    }

}
