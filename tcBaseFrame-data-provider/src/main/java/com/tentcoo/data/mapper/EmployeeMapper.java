package com.tentcoo.data.mapper;

import com.tentcoo.data.page.EmployeeQueryObject;
import com.tentcoo.data.pojo.Employee;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/1/26 0026
 */
public interface EmployeeMapper {

    int save(Employee employee);

    void delete(long empId);

    void update(Employee employee);

    Employee get(long empId);

    List<Employee> listAll();

    //查询分页后的数据
    List<Employee> queryForList(EmployeeQueryObject qo);

    //查询总数据条数
    int queryForCount(EmployeeQueryObject qo);

    Employee getLoginInfoByUserName(String username);
}
