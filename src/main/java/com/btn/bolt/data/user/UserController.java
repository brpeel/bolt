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
    public long create(User user) {
        return dao.insert(user);
    }

    @Override
    public void delete(long id) {
        dao.delete(id);
    }
}
