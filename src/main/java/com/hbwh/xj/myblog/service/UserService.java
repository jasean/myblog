package com.hbwh.xj.myblog.service;

import com.hbwh.xj.myblog.bean.User;

public interface UserService {
    public User getUserByUserid(String userid);
    public boolean addUser(User user);
}
