package com.tentcoo.data.service.impl;

import com.tentcoo.common.mybatis.Page;
import com.tentcoo.data.api.EmployeeService;
import com.tentcoo.data.mapper.EmployeeMapper;
import com.tentcoo.data.mybatis.service.CrudService;
import com.tentcoo.data.page.EmployeeQueryObject;
import com.tentcoo.data.page.PageResult;
import com.tentcoo.data.pojo.Employee;
import com.tentcoo.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/1/26 0026
 */
@Service(value = "employeeService")
@Transactional(readOnly = true)
public class EmployeeServiceImpl extends CrudService<EmployeeMapper, Employee> implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Transactional(readOnly = false)
    public Page<Employee> findPage(Page<Employee> page, Employee employee) {
        return super.findPage(page, employee);
    }

    @Override
    public int save2(Employee employee) {
        return employeeMapper.save2(employee);
    }

    @Transactional(readOnly = false)
    public void save(Employee employee) {
        employee.setUpdateDate(new Date());
        if (StringUtils.isBlank(employee.getId())){
            employee.preInsert();
            dao.insert(employee);
        }else{
            employee.preUpdate();
            dao.update(employee);
        }
    }

    @Override
    @Transactional(readOnly = false)
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
