package com.csz.dao;

import com.csz.model.User;

import java.util.List;

public interface UserDao {
     User selectUser(int id);
     List<User> selectAll();
     User selectUserByusername(String username);
     Boolean addUser(User user);
     Boolean updateUserById(User user);
     Boolean deleteUserById(int id);
}
