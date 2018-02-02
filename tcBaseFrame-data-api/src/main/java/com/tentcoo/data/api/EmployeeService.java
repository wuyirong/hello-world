package com.tentcoo.data.api;

import com.tentcoo.data.mybatis.Page;
import com.tentcoo.data.page.EmployeeQueryObject;
import com.tentcoo.data.page.PageResult;
import com.tentcoo.data.pojo.Employee;

import java.util.List;

/**
 * 测试crud
 * @author Administrator
 * @date 2018/1/26 0026
 */
public interface EmployeeService {
    int save2(Employee employee);

    void delete(long empId);

    void update(Employee employee);

    Employee get(long empId);

    List<Employee> listAll();

    void saveOrUpDate(Employee employee);

    public void save(Employee employee);
    public Page<Employee> findPage(Page<Employee> page, Employee employee);

    /**
     * 分页
     * @param qo
     * @return
     */
    Page queryPage(EmployeeQueryObject qo);

    /**
     * 自定义分页
     * @param qo
     * @return
     */
    PageResult queryPageResult(EmployeeQueryObject qo);

    Employee getLoginInfoByUserName(String username);

}
