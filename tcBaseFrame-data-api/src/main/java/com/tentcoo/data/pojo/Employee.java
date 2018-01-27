package com.tentcoo.data.pojo;

import java.io.Serializable;

/**
 * 测试domain
 *
 * @author Administrator
 * @date 2018/1/26 0026
 */
public class Employee implements Serializable {
    private Long   id;
    private String name;
    private int    age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
