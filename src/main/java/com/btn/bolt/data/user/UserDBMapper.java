package com.btn.bolt.data.user;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDBMapper implements ResultSetMapper<User> {

    @Override
    public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new User(
                r.getLong("id"),
                r.getString("email"),
                r.getString("first_name"),
                r.getString("last_name"),
                r.getLong("points"));
    }
}
