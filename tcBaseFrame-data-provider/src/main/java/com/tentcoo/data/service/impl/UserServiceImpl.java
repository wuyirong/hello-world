package com.tentcoo.data.service.impl;

import com.tentcoo.data.api.UserService;
import com.tentcoo.data.dao.UserDao;
import com.tentcoo.data.entity.UserEntity;
import com.tentcoo.data.jpa.dao.BaseDao;
import com.tentcoo.data.jpa.service.BaseService;
import com.tentcoo.data.jpa.service.BaseServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rover on 2018/1/17.
 */
//@Component("userService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=UserService.class)//(version="1.0")
//@Service
//@Transactional
public class UserServiceImpl implements UserService {//extends BaseServiceImpl<UserEntity>

    @Resource
    private UserDao userDao;

    @Resource
    private BaseService<UserEntity> baseService;

    public List<UserEntity> all(){
        return baseService.all();
    }

    //@Transactional
    public void save(UserEntity userEntity){
        this.baseService.save(userEntity);
    }

}
