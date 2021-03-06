package com.tentcoo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tentcoo.data.api.EmployeeService;
import com.tentcoo.data.page.EmployeeQueryObject;
import com.tentcoo.data.page.PageResult;
import com.tentcoo.data.pojo.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by rover on 2018/1/24.
 */
@Controller
@RequestMapping("/view")
public class TestController {

    @Reference
    private EmployeeService employeeService;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="世界杯.") String name, ModelMap model) {
        model.addAttribute("name", name);
        System.out.println("GRETTINGS, "+ name +"................!");
        return "demo/greeting";
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

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Employee employee) {
        employeeService.saveOrUpDate(employee);
        return "redirect:/view/list";
    }

    @RequestMapping("delete")
    public String delete(Long id) {
        if (id != null) {
            employeeService.delete(id);
        }
        return "redirect:/view/list";
    }
    @RequestMapping("test")
    public String test() {
        return "demo/test";
    }
}
