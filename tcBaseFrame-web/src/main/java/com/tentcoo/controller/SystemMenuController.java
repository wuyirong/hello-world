package com.tentcoo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tentcoo.data.api.SystemMenuService;
import com.tentcoo.data.page.PageResult;
import com.tentcoo.data.page.SystemMenuQueryObject;
import com.tentcoo.data.pojo.Employee;
import com.tentcoo.data.pojo.SystemMenu;
import com.tentcoo.security.utils.UserUtil;
import com.tentcoo.utils.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2018/2/1 0001
 */
@Controller
@RequestMapping("/systemMenu")
public class SystemMenuController {
    @Reference
    private SystemMenuService systemMenuService;

    /**
     * 菜单数据
     */
    @RequestMapping("/listAll")
    @ResponseBody
    public List<SystemMenu> listAll() {
        //得到缓存对象
        List<SystemMenu> menus = (List<SystemMenu>) UserUtil.getCache("MENU_LIST");
        if (menus == null) {
            //查询全部菜单
            menus = systemMenuService.getRootMenu();
            //放入缓存中
            UserUtil.putCache("MENU_LIST", menus);
        }
        return menus;
    }

    /**
     * 菜单视图
     *
     * @return
     */
    @RequestMapping("/view")
    public String view() {
        return "demo/systemMenu/systemMenu";
    }

    /**
     * 菜单增改
     *
     * @return
     */
    @RequestMapping("/systemMenuForm")
    public String systemMenuForm() {
        return "demo/systemMenu/systemMenuForm";
    }

    /**
     * 查询全部菜单数据
     *
     * @param qo
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(SystemMenuQueryObject qo) {
        return systemMenuService.query(qo);
    }

    /**
     * 查询某个菜单数据
     *
     * @return
     */
    @RequestMapping("/listOne")
    @ResponseBody
    public List<SystemMenu> listOne() {
        return systemMenuService.selectAll();
    }

    /**
     * 菜单的保存或者更新
     *
     * @param entity
     * @return
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(SystemMenu entity) {
        try {
            //清除缓存
            UserUtil.removeCache("MENU_LIST");
            //当前登录的用户
            Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
            //service中获取当前登录用户报错,待修复后再把设置当前登录用户的逻辑写在service中
            //如果没有父级id或者id==null就是新增一个根菜单
            if (entity.getParent().getId() == null ||
                    entity.getParent().getId().equals("") ||
                    entity.getId() == null ||
                    entity.getId().equals("")) {
                entity.setCreateBy(employee);
                entity.setUpdateBy(employee);
                systemMenuService.insert(entity);
            } else {
                SystemMenu oldMenu = systemMenuService.get(entity.getId());
                entity.setCreateBy(oldMenu.getCreateBy());
                entity.setCreateDate(oldMenu.getCreateDate());
                entity.setUpdateBy(employee);
                systemMenuService.updateByPrimaryKey(entity);
            }
            return new JsonResult().setSuccessMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult().setErrorMsg("操作失败");
        }
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(String id) {
        try {
            //清除缓存
            UserUtil.removeCache("MENU_LIST");
            if (id != null) {
                systemMenuService.changeState(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult().setErrorMsg("操作失败");
        }
        return new JsonResult().setSuccessMsg("操作成功");
    }

    /**
     * 查询某个菜单数据
     *
     * @return
     */
    @RequestMapping("/getOne")
    @ResponseBody
    public List<SystemMenu> getOne(String id) {
        return systemMenuService.selectByPrimaryKey(id);
    }

    @RequestMapping("tree")
    public String tree() {
        return "demo/systemMenu/systemMenuTree";
    }

    @RequestMapping("/getTreeResult")
    @ResponseBody
    public List<Map<String, Object>> getTreeResult() {
        return systemMenuService.getTreeResult();
    }

}

