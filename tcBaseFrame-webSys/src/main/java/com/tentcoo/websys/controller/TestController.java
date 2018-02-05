package com.tentcoo.websys.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tentcoo.common.mybatis.Page;
import com.tentcoo.data.api.EmployeeService;
import com.tentcoo.data.page.EmployeeQueryObject;
import com.tentcoo.data.page.PageResult;
import com.tentcoo.data.pojo.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by rover on 2018/1/24.
 */
@Api(value="后台管理的相关接口")
@Controller
@RequestMapping("/websys")
public class TestController extends BaseController{

    //@Resource
    @Reference
    private EmployeeService employeeService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="世界杯.") String name, ModelMap model) {
        model.addAttribute("name", name);
        logger.info("GRETTINGS, "+ name +"................!");
        logger.info("admin.path="+adminPath);
        return "websys/greeting";
    }
    @GetMapping("list")
    public String listAll(ModelMap model,@ModelAttribute("qo") EmployeeQueryObject qo) {
        PageResult result = employeeService.queryPageResult(qo);
        model.addAttribute("result", result);
        return "employee/list";
    }

    @GetMapping("list2")
    public String list2(ModelMap model,@ModelAttribute("qo") EmployeeQueryObject qo) {
        PageResult result = employeeService.queryPageResult(qo);
        model.addAttribute("result", result);
        return "employee/list2";
    }

    @ApiOperation(value ="查询雇员列表信息", notes="")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "姓名", required = false, dataType = "String"),
        @ApiImplicitParam(name = "age", value = "年龄", required = false, dataType = "Integer")
    })
    @GetMapping("list3")
    public String list3(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        Employee employee = new Employee();
        Page<Employee> page = new Page<Employee>(request, response);
        page.setPageSize(5);
        page = employeeService.findPage(page, employee);
        model.addAttribute("page", page);
        return "employee/list3";
    }

    @ApiOperation(value ="保存or更新雇员信息", notes="")
    @PostMapping("saveOrUpdate")
    public String saveOrUpdate(Employee employee) {
        //employeeService.saveOrUpDate(employee);
        employeeService.save(employee);
        return "redirect:/websys/list";
    }

    @ApiOperation(value ="删除雇员信息", notes="")
    @PostMapping("delete")
    public String delete(Long id) {
        if (id != null) {
            employeeService.delete(id);
        }
        return "redirect:/websys/list";
    }
    @GetMapping("test")
    public String test() {
        return "demo/test";
    }
}
