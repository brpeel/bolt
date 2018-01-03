package com.btn.bolt.data.user;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(UserDBMapper.class)
public interface UserDAO {

    @SqlQuery("SELECT 1 as id, 'Brett' as first_name, 'Peel' as last_name, 'bpeel56@gmail.com' as email, 0 as points")
    User getUser(@Bind("id") long id);
}
