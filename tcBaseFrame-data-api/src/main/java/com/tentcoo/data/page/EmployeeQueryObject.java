package com.tentcoo.data.page;

/**
 * 高级查询
 * @author Administrator
 * @date 2018/1/27 0027
 */
public class EmployeeQueryObject extends QueryObject {

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

}
