package com.toby.suntory.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnetionMaker implements ConnectionMaker {
    @Override
    public Connection makeConnection() throws SQLException {
        System.out.println("D사의 독자적인 방법으로 Connection을 생성");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/toby", "root", "950104elql!");
        return c;
    }
}
