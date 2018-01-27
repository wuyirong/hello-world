package com.tentcoo.data.page;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2018/1/27 0027
 */
public class EmployeeQueryObject implements Serializable {

    private Integer currentPage = 1;
    private Integer pageSize    = 5;
    private String  name;
    private String  age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return (this.currentPage - 1) * this.pageSize;
    }
}
