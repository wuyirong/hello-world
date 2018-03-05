package com.tentcoo.data.api;

import com.tentcoo.data.pojo.Employee;
import com.tentcoo.data.pojo.Role;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/2/7 0007
 */
public interface RoleService {
    int delete(String id);

    int save(Role record);

    Role getOne(String id);

    int update(Role record);

    List<Role> listAll();

    void saveOrUpdate(Role role, Employee employee);

    /**
     * 修改角色状态
     * @param id
     */
    void changeRoleState(String id);

    /**
     * 保存角色与员工关系
     *
     * @param rid
     * @param empIds
     */
    void saveEmployeeRelation(String rid, String[] empIds);

    void removeRoleAndEmpRelation(String rid, String empId);

    /**
     * 根据用户id获取所对应的角色
     * @param id
     * @return
     */
    List<Role> getRoleByEmpId(String employeeId);
}
