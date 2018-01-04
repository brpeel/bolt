package com.btn.bolt.data.transfer;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferDBMapper implements ResultSetMapper<Transfer> {

    @Override
    public Transfer map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Transfer(r.getLong("id"), r.getLong("userId"), r.getLong("points"));
    }
}
