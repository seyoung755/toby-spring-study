package com.toby.suntory.user.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {
    private final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        try (Connection c = dataSource.getConnection(); PreparedStatement ps = stmt.makePreparedStatement(c)) {
            ps.executeUpdate();
        }
    }

    public void executeSql(String query) throws SQLException {
        workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {

                return c.prepareStatement(query);
            }
        });
    }
}
