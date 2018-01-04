package com.btn.bolt.data.user;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(UserDBMapper.class)
public interface UserDAO {

    @SqlQuery("SELECT * FROM bolt_user WHERE id = :id AND deleted = False")
    User getUser(@Bind("id") long id);


    @SqlUpdate("INSERT INTO bolt_user (email, first_name, last_name, points, deleted) " +
            "VALUES (:i.email, :i.firstName, :i.lastName, :i.points, False)")
    @GetGeneratedKeys
    long insert(@BindBean("i") User i);


    @SqlUpdate("UPDATE bolt_user SET deleted = true WHERE id = :id")
    void delete(@Bind("id") long id);

}
