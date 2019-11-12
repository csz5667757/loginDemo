package com.csz.service.ServiceImpl;

import com.csz.dao.UserDao;
import com.csz.model.User;
import com.csz.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public User selectUser(int id) {
        return this.userDao.selectUser(id);
    }

    @Override
    public List<User> selectAll() {
        return this.userDao.selectAll();
    }

    @Override
    public User selectUserByusername(String username) {
        return userDao.selectUserByusername(username);
    }

    @Override
    public Boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public Boolean updateUserById(User user) {
        return userDao.updateUserById(user);
    }

    @Override
    public Boolean deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }

}
