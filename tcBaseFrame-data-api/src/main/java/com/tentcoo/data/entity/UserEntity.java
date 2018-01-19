package com.tentcoo.data.entity;

import com.tentcoo.data.jpa.entity.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rover on 2018/1/17.
 */
@Entity
@Table(name="tb_user")
public class UserEntity extends IdEntity {

    @Column(name ="user_name",nullable=false,length=50)
    private String userName;

    @Column(name ="birth_day",nullable=false,length=30)
    private Date birthDay;

    @Column(name ="sex",nullable=false,length=20)
    private String sex;

    @Column(name ="address",nullable=false,length=200)
    private String address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
