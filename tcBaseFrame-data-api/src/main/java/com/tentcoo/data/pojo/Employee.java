package com.tentcoo.data.pojo;

import com.tentcoo.data.mybatis.entity.DataEntity;

import java.io.Serializable;

/**
 * 测试domain
 *
 * @author Administrator
 * @date 2018/1/26 0026
 */
public class Employee extends DataEntity<Employee> implements Serializable {
//    private Long   id;
    private String name;
    private int    age;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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
