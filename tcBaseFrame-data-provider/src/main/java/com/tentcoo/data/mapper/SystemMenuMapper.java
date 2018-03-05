package com.tentcoo.data.mapper;

import com.tentcoo.data.page.QueryObject;
import com.tentcoo.data.pojo.SystemMenu;

import java.util.List;
import java.util.Map;

public interface SystemMenuMapper {

    int deleteByPrimaryKey(String id);

    int insert(SystemMenu record);

    List<SystemMenu> selectByPrimaryKey(String id);

    List<SystemMenu> selectAll();

    int updateByPrimaryKey(SystemMenu record);

    List<SystemMenu> getRootMenu();

    int queryForTotal(QueryObject qo);

    List<SystemMenu> queryForRows(QueryObject qo);

    void changeState(String id);

    SystemMenu get(String id);

    List<Map<String,Object>> getTreeResult();
}