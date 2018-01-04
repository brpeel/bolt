package com.btn.bolt.data.user;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlCall;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(UserDBMapper.class)
public interface UserDAO {

    @SqlQuery("SELECT * FROM bolt_user WHERE id = :id")
    User getUser(@Bind("id") long id);


    @SqlCall("INSERT INTO bolt_user (email, first_name, last_name, points, deleted) " +
            "VALUES (:i.email, :i.first_name, :i.last_name, :i.points, False) " +
            "RETURNING id")
    long insert(@BindBean("i") User i);


    @SqlCall("UPDATE bolt_user SET deleted = true WHERE id = :id")
    void delete(@Bind("id") long id);


}
