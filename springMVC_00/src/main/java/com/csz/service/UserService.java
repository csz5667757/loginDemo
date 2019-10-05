package com.csz.service;

import com.csz.model.User;

import java.util.List;

public interface UserService {
    public User selectUser(int id);
    public List<User> selectAll();
    public User selectUserByusername(String username);
    public Boolean addUser(User user);
    public Boolean updateUserById(User user);
    public Boolean deleteUserById(int id);
}
