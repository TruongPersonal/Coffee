package com.project.BUS;

import java.util.List;

import com.project.DAO.UserDao;
import com.project.DTO.User;

public class UserService {
    UserDao userdao;

    public UserService() {
        userdao = new UserDao();
    }

    public List<User> getAllUser() {
        return userdao.getAllUser();
    }

    public boolean addUser(User user) {
        return userdao.addUser(user);
    }

    public boolean deleteUser(int id) {
        return userdao.deleteUser(id);
    }

    public boolean updateUser(User user) {
        return userdao.updateUser(user);
    }

    public User getIdUser(int id) {
        return userdao.getIdUser(id);
    }

    public User getNameUser(String name) {
        return userdao.getNameUser(name);
    }

    public List<User> searchAllUserByName(String name) {
        return userdao.SearchAllUserByName(name);
    }

    public List<User> searchAllUserById(int id) {
        return userdao.SearchAllUserById(id);
    }

    public List<User> searchAllUserByPhone(String phone) {
        return userdao.SearchAllUserByPhone(phone);
    }
}
