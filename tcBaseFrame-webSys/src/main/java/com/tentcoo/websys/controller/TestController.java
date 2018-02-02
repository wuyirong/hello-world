package com.tentcoo.websys.controller;

import com.tentcoo.data.api.EmployeeService;
import com.tentcoo.data.mybatis.Page;
import com.tentcoo.data.page.EmployeeQueryObject;
import com.tentcoo.data.page.PageResult;
import com.tentcoo.data.pojo.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by rover on 2018/1/24.
 */
@Controller
@RequestMapping("/websys")
public class TestController extends BaseController{

    @Resource
    private EmployeeService employeeService;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="世界杯.") String name, ModelMap model) {
        model.addAttribute("name", name);
        logger.info("GRETTINGS, "+ name +"................!");
        logger.info("admin.path="+adminPath);
        return "websys/greeting";
    }
    @RequestMapping("list")
    public String listAll(ModelMap model,@ModelAttribute("qo") EmployeeQueryObject qo) {
        PageResult result = employeeService.queryPageResult(qo);
        model.addAttribute("result", result);
        return "employee/list";
    }

    @RequestMapping("list2")
    public String list2(ModelMap model,@ModelAttribute("qo") EmployeeQueryObject qo) {
        PageResult result = employeeService.queryPageResult(qo);
        model.addAttribute("result", result);
        return "employee/list2";
    }

    @RequestMapping("list3")
    public String list3(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        Employee employee = new Employee();
        Page<Employee> page = new Page<Employee>(request, response);
        page.setPageSize(5);
        page = employeeService.findPage(page, employee);
        model.addAttribute("page", page);
        return "employee/list3";
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Employee employee) {
        //employeeService.saveOrUpDate(employee);
        employeeService.save(employee);
        return "redirect:/websys/list";
    }

    @RequestMapping("delete")
    public String delete(Long id) {
        if (id != null) {
            employeeService.delete(id);
        }
        return "redirect:/websys/list";
    }
    @RequestMapping("test")
    public String test() {
        return "demo/test";
    }
}
