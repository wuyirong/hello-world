package com.tentcoo.data.pojo;

import com.tentcoo.data.mybatis.entity.DataEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/2/7 0007
 */
public class Role extends DataEntity<Role> {

    //归属机构
    private String officeId;
    //角色名称
    private String name;
    //英文名称
    private String enName;
    //角色类型
    private String roleType;
    //数据范围
    private String dataScope;
    //是否系统数据
    private String isSys   = NO_SYS;
    //是否可用
    private String useable = DISABLED;
    //创建人
    private Employee createBy;
    //更新人
    private Employee updateBy;
    //拥有的权限
    private List<SystemMenu> menuList = new ArrayList();

    //是系统数据
    public static final String IS_SYS   = "1";
    //不是系统数据
    public static final String NO_SYS   = "0";
    //是可用
    public static final String USEABLE  = "1";
    //不可用
    public static final String DISABLED = "0";

    // 数据范围（1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置）
    public static final String DATA_SCOPE_ALL               = "1";
    public static final String DATA_SCOPE_COMPANY_AND_CHILD = "2";
    public static final String DATA_SCOPE_COMPANY           = "3";
    public static final String DATA_SCOPE_OFFICE_AND_CHILD  = "4";
    public static final String DATA_SCOPE_OFFICE            = "5";
    public static final String DATA_SCOPE_SELF              = "8";
    public static final String DATA_SCOPE_CUSTOM            = "9";


    public Employee getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Employee createBy) {
        this.createBy = createBy;
    }

    public Employee getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Employee updateBy) {
        this.updateBy = updateBy;
    }

    public String getIsSys() {
        return isSys;
    }

    public void setIsSys(String isSys) {
        this.isSys = isSys;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
    }

    public List<SystemMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<SystemMenu> menuList) {
        this.menuList = menuList;
    }

    public List<String> getMenuIdList() {
        List<String> menuIdList = new ArrayList();
        for (SystemMenu menu : menuList) {
            menuIdList.add(menu.getId());
        }
        return menuIdList;
    }

    public void setMenuIdList(List<String> menuIdList) {
        menuList = new ArrayList();
        for (String menuId : menuIdList) {
            SystemMenu menu = new SystemMenu();
            menu.setId(menuId);
            menuList.add(menu);
        }
    }

    public String getMenuIds() {
        return StringUtils.join(getMenuIdList(), ",");
    }

    public void setMenuIds(String menuIds) {
        menuList = new ArrayList();
        if (menuIds != null) {
            String[] ids = StringUtils.split(menuIds, ",");
            setMenuIdList(Arrays.asList(ids));
        }
    }
}
