package com.tentcoo.data.service.impl;

import com.tentcoo.data.api.EmployeeService;
import com.tentcoo.data.mapper.EmployeeMapper;
import com.tentcoo.data.mybatis.Page;
import com.tentcoo.data.page.EmployeeQueryObject;
import com.tentcoo.data.page.PageResult;
import com.tentcoo.data.pojo.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/1/26 0026
 */
@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public int save(Employee employee) {
        return employeeMapper.save(employee);
    }

    @Override
    public void delete(long empId) {
        employeeMapper.delete(empId);
    }

    @Override
    public void update(Employee employee) {
        employeeMapper.update(employee);
    }

    @Override
    public Employee get(long empId) {
        return employeeMapper.get(empId);
    }

    @Override
    public List<Employee> listAll() {
        return employeeMapper.listAll();
    }

    @Override
    public void saveOrUpDate(Employee employee) {
        if (employee.getId() == null) {
            this.save(employee);
        } else {
            this.update(employee);
        }
    }

    @Override
    public Page queryPage(EmployeeQueryObject qo) {
        List<Employee> list       = employeeMapper.queryForList(qo);
        long            totalCount = employeeMapper.queryForCount(qo);
        return new Page(qo.getCurrentPage(), qo.getPageSize(),totalCount,list);
    }

    @Override
    public PageResult queryPageResult(EmployeeQueryObject qo) {
        List<Employee> list       = employeeMapper.queryForList(qo);
        int            totalCount = employeeMapper.queryForCount(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),list, totalCount);
    }

    @Override
    public Employee getLoginInfoByUserName(String username) {
        return employeeMapper.getLoginInfoByUserName(username);
    }

}
