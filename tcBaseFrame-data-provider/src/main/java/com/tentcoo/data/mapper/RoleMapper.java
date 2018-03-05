package com.tentcoo.data.mapper;

import com.tentcoo.data.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    int deleteByPrimaryKey(String id);

    int insert(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKey(Role record);

    List<Role> listAll();

    /**
     * 保存菜单与角色关系
     *
     * @param rid
     * @param menuIdList
     */
    void saveMenuRelation(@Param("rid") String rid, @Param("menuIdList") List<String> menuIdList);

    /**
     * 删除菜单与角色关系
     *
     * @param id
     */
    void deleteMenuRelation(String id);

    /**
     * 修改角色状态
     *
     * @param id
     */
    void changeRoleState(String id);

    /**
     * 删除员工与角色关系
     *
     * @param rid
     */
    void deleteEmpRelation(String rid);

    /**
     * 保存员工与角色关系
     *
     * @param rid
     * @param empIds
     */
    void saveEmployeeRelation(@Param("rid") String rid, @Param("empIds") String[] empIds);

    /**
     * 删除单个员工与角色关系
     *
     * @param rid
     * @param empId
     */
    void removeRoleAndEmpRelation(@Param("rid") String rid, @Param("empId") String empId);

    /**
     * 根据用户id获得对应的角色
     * @param employeeId
     * @return
     */
    List<Role> getRoleByEmpId(@Param("employeeId") String employeeId, @Param("roleState") String roleState);
}