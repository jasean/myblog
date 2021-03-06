package com.hbwh.xj.myblog.service;

import com.hbwh.xj.myblog.po.User;

public interface UserService {
    public User getUserByUserid(String userid);
    public boolean addUser(User user);
    public boolean modifyUser(User user);
    public boolean deleteUser(User user);
}
