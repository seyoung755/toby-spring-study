package com.toby.suntory.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DUserDao {
    public Connection getConnection() throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/toby", "root", "950104elql!");
        return c;
    }
}
