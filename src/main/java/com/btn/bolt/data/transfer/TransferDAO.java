package com.btn.bolt.data.transfer;

import com.btn.bolt.data.user.User;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(TransferDBMapper.class)
public interface TransferDAO {
    @SqlQuery("SELECT id, user_id, points FROM transfer WHERE id = :id")
    Transfer get(@Bind("id") long id);

    @SqlUpdate("INSERT INTO transfer (user_id, points) " +
            "VALUES (:i.userId, :i.points)")
    @GetGeneratedKeys
    long insert(@BindBean("i") Transfer i);
}
