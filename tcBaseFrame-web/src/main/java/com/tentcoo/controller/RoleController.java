package com.tentcoo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tentcoo.data.api.EmployeeService;
import com.tentcoo.data.api.RoleService;
import com.tentcoo.data.page.LayUIResult;
import com.tentcoo.data.pojo.Employee;
import com.tentcoo.data.pojo.Role;
import com.tentcoo.security.utils.UserUtil;
import com.tentcoo.utils.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 角色相关Controller
 *
 * @author Administrator
 * @date 2018/2/7 0007
 */
@Controller
@RequestMapping("role")
public class RoleController {
    @Reference
    private RoleService     roleService;
    @Reference
    private EmployeeService employeeService;

    /**
     * 角色列表数据
     *
     * @param model
     * @return
     */
    @RequestMapping("/listAll")
    @ResponseBody
    public LayUIResult listAll(Model model) {
        //判断缓存中有没有该数据
        LayUIResult layUIResult = (LayUIResult) UserUtil.getCache("LAYUI_RESULT_IN_CACHE");
        if (layUIResult == null) {
            layUIResult = new LayUIResult(roleService.listAll());
            //把查询到的角色信息放入缓存中
            UserUtil.putCache("LAYUI_RESULT_IN_CACHE", layUIResult);
        }
        return layUIResult;
    }

    /**
     * 角色CRUD视图
     *
     * @param model
     * @return
     */
    @RequestMapping("/view")
    public String roleView(Model model) {
        return "demo/role/role";
    }

    /**
     * 角色列表
     *
     * @return
     */
    @RequestMapping("/roleList")
    public String roleList() {
        return "demo/role/roleList";
    }

    /**
     * 角色编辑/新增页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/roleForm")
    public String roleForm(Model model, String id) {
        if (id != null) {
            model.addAttribute("role", roleService.getOne(id));
        }
        return "demo/role/roleForm";
    }

    /**
     * 保存/更新方法
     *
     * @param role
     * @return
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Role role) {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        //清除缓存
        UserUtil.removeCache("LAYUI_RESULT_IN_CACHE");
        try {
            roleService.saveOrUpdate(role, employee);
            return new JsonResult(true, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteRole")
    @ResponseBody
    public JsonResult deleteRole(String id) {
        try {
            //清除缓存
            UserUtil.removeCache("LAYUI_RESULT_IN_CACHE");
            roleService.changeRoleState(id);
            return new JsonResult(true, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    /**
     * 角色详情页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/roleAssign")
    public String assign(String id, Model model) {
        //当前角色对应着的用户
        model.addAttribute("empList", employeeService.getEmpByRoleId(id));
        //当前用户
        model.addAttribute("role", roleService.getOne(id));
        return "demo/role/roleAssign";
    }

    /**
     * 角色分配页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/assignForm")
    public String assignForm(@ModelAttribute("id") String id, Model model) {
        //当前角色对应着的用户
        model.addAttribute("roleEmpList", employeeService.getEmpByRoleId(id));
        //公司所有的用户
        model.addAttribute("allEmployee", employeeService.listAll());
        return "demo/role/assignForm";
    }

    /**
     * 保存/更新角色与员工关系
     *
     * @param rid
     * @param empIds
     * @return
     */
    @RequestMapping("/roleAssignSaveOrUpdate")
    @ResponseBody
    public JsonResult roleAssignSaveOrUpdate(String rid, String[] empIds) {
        try {
            roleService.saveEmployeeRelation(rid, empIds);
            return new JsonResult(true, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "操作失败,请稍后再试");
        }
    }

    @RequestMapping("/removeRoleAndEmpRelation")
    @ResponseBody
    public JsonResult removeRoleAndEmpRelation(String rid, String empId) {
        try {
            roleService.removeRoleAndEmpRelation(rid, empId);
            return new JsonResult(true, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "操作失败,请稍后再试");
        }
    }
}
