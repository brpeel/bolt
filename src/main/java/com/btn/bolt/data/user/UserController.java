package com.btn.bolt.data.user;

import com.btn.bolt.data.Controller;

public class UserController implements Controller<User> {

    private final UserDAO dao;

    public UserController(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public User get(long id) {
        return dao.getUser(id);
    }

    @Override
    public boolean insert(User obj) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
