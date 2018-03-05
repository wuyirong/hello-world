package com.tentcoo.data.service.impl;

import com.tentcoo.data.api.SystemMenuService;
import com.tentcoo.data.mapper.SystemMenuMapper;
import com.tentcoo.data.page.PageResult;
import com.tentcoo.data.page.QueryObject;
import com.tentcoo.data.pojo.SystemMenu;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Administrator
 * @date 2018/2/1 0001
 */
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = SystemMenuService.class)
public class SystemMenuServiceImpl implements SystemMenuService {
    @Resource
    private SystemMenuMapper systemMenuMapper;


    @Override
    public int deleteByPrimaryKey(String id) {
        return systemMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SystemMenu> selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int insert(SystemMenu record) {
        //如果没有父级id,就是一个完完全全的新增菜单
        if (record.getParent().getId() == null || record.getParent().getId().equals("")) {
            //如果是新增需要设置父id
            SystemMenu systemMenu = new SystemMenu();
            systemMenu.setId(SystemMenu.DEFAULT_PARENT_ID);
            record.setParent(systemMenu);
        }
        record.setId(UUID.randomUUID().toString());
        //报错 需后续解决
        //SecurityUtils.getSubject().getPrincipal();
        //TODO
        //record.setCreateBy((Employee) CacheUtils.get("EMPLOYEE_IN_CACHE"));
        //record.setUpdateBy((Employee) CacheUtils.get("EMPLOYEE_IN_CACHE"));
        record.setCreateDate(new Date());
        record.setUpdateDate(new Date());
        return systemMenuMapper.insert(record);
    }


    @Override
    public List<SystemMenu> selectAll() {
        return systemMenuMapper.selectAll();
    }


    @Override
    public int updateByPrimaryKey(SystemMenu record) {
        //  Employee loginUser = (Employee) UserUtil.getCache("EMPLOYEE_IN_CACHE");
        //TODO
        //record.setUpdateBy();
        //CacheUtils.get("EMPLOYEE_IN_CACHE");
        record.setUpdateDate(new Date());
        return systemMenuMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = systemMenuMapper.queryForTotal(qo);
        if (total > 0) {
            return new PageResult(total, systemMenuMapper.queryForRows(qo));
        }
        return new PageResult(total, Collections.EMPTY_LIST);
    }

    @Override
    public List<SystemMenu> getRootMenu() {

        return this.handlerMenu(systemMenuMapper.getRootMenu());
    }

    @Override
    public void changeState(String id) {
        systemMenuMapper.changeState(id);
    }

    @Override
    public SystemMenu get(String id) {
        return systemMenuMapper.get(id);
    }

    @Override
    public void saveOrUpdate(SystemMenu entity) {
        if (entity.getId().equals(SystemMenu.DEFAULT_PARENT_ID)) {
            this.insert(entity);
        } else {
            this.updateByPrimaryKey(entity);
        }
    }

    @Override
    public List<Map<String, Object>> getTreeResult() {
        return systemMenuMapper.getTreeResult();
    }

    /**
     * 过滤menu
     *
     * @param allMenus
     * @return
     */
    public List<SystemMenu> handlerMenu(List<SystemMenu> allMenus) {

        //如果菜单对象关联了权限才需要进行判断
        Iterator<SystemMenu> iterator = allMenus.iterator();
        //循环取出每一个菜单
        while (iterator.hasNext()) {
            SystemMenu menu = iterator.next();
            //判断该菜单是否有关联权限
            if (menu.getDelFlag() == SystemMenu.IS_DELETE) {
                //Subject subject = SecurityUtils.getSubject();
                //如果有关联,就判断当前用户是否拥有该权限
               /*if(!subject.isPermitted(menu.getPermission().getResource())){
                   //如果没有权限需要从集合中移除掉
                   iterator.remove();
                   continue;
               }*/
                iterator.remove();
                continue;
            }
            //如果有子菜单需要进行递归判断
            if (menu.getChildren().size() > 0) {
                handlerMenu(menu.getChildren());
            }
        }
        return allMenus;
    }

}
