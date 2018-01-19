package com.tentcoo.data.api;

import com.tentcoo.data.entity.UserEntity;

import java.util.List;

/**
 * Created by rover on 2018/1/17.
 */
public interface UserService {

    public List<UserEntity> all();

    public void save(UserEntity userEntity);
}
