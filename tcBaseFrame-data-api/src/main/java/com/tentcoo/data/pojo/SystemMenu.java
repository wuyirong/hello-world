package com.tentcoo.data.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SystemMenu implements Serializable {

    //显示菜单
    public static final boolean SHOW_MENU         = true;
    //不显示菜单
    public static final boolean OUT_SHOW_MENU     = false;
    //删除标记
    public static final boolean IS_DELETE         = true;
    //不删除标记
    public static final boolean NOT_DELETE        = false;
    //默认父id
    public static final String  DEFAULT_PARENT_ID = "0";

    private String     id;
    //父级id
    private SystemMenu parent;
    //名称
    private String     text;
    //排序
    private Integer    sort;
    //相对路径
    private String     href;
    //绝对路径地址
    private String     target;
    //图标
    private String     icon;
    //是否显示
    private boolean isShow = SHOW_MENU;
    //权限表达式
    private String   permission;
    //创建人
    private Employee createBy;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date     createDate;
    //更新人
    private Employee updateBy;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date     updateDate;
    //备注信息
    private String   remarks;
    //删除标记
    private Boolean delFlag = NOT_DELETE;

    //子菜单
    private List<SystemMenu> children = new ArrayList<>();

    public List<SystemMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SystemMenu> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public SystemMenu getParent() {
        return parent;
    }

    public void setParent(SystemMenu parent) {
        this.parent = parent;
    }


    public String getText() {
        return text;
    }

    public void setText(String name) {
        this.text = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

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


    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }


    public static List<SystemMenu> sortList(List<SystemMenu> sourcelist, String parentId, boolean cascade) {
        List<SystemMenu> list = new ArrayList<>();
        for (int i = 0; i < sourcelist.size(); i++) {
            SystemMenu e = sourcelist.get(i);
            if (e.getParent() != null && e.getParent().getId() != null
                    && e.getParent().getId().equals(parentId)) {
                list.add(e);
                if (cascade) {
                    // 判断是否还有子节点, 有则继续获取子节点
                    for (int j = 0; j < sourcelist.size(); j++) {
                        SystemMenu child = sourcelist.get(j);
                        if (child.getParent() != null && child.getParent().getId() != null
                                && child.getParent().getId().equals(e.getId())) {
                           // sortList(list, sourcelist, e.getId(), true);
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    public static String getRootId() {
        return "1";
    }
}