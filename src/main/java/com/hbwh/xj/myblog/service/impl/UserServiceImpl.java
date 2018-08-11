package com.hbwh.xj.myblog.service.impl;

import com.hbwh.xj.myblog.bean.User;
import com.hbwh.xj.myblog.dao.UserMapper;
import com.hbwh.xj.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    private UserMapper userMapper = null;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUserByUserid(String userid) {
        User user = userMapper.selectByPrimaryKey(userid);
        return user;
    }

    @Override
    public boolean addUser(User user) {
       int row = userMapper.insert(user);
        return row == 1;
    }


    public boolean modifyUser(User user){
       int row = userMapper.updateByPrimaryKeySelective(user);
       return row == 1;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }
}
