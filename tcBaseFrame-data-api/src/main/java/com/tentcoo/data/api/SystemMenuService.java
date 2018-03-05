package com.tentcoo.data.api;

import com.tentcoo.data.page.PageResult;
import com.tentcoo.data.page.QueryObject;
import com.tentcoo.data.pojo.SystemMenu;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2018/2/1 0001
 */
public interface SystemMenuService {

    int deleteByPrimaryKey(String id);

    int insert(SystemMenu record);

    List<SystemMenu> selectByPrimaryKey(String id);

    List<SystemMenu> selectAll();

    int updateByPrimaryKey(SystemMenu record);

    PageResult query(QueryObject qo);

    List<SystemMenu> getRootMenu();

    void changeState(String id);

    SystemMenu get(String id);

    void saveOrUpdate(SystemMenu entity);

    List<Map<String,Object>> getTreeResult();

}
