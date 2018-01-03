package com.btn.bolt.data.user;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(UserDBMapper.class)
public interface UserDAO {

    @SqlQuery("SELECT * FROM bolt_user")
    User getUser(@Bind("id") long id);
}
