package com.example.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootTest
class DataSourceConfigTest {
    @Autowired
    private DataSource dataSource2;

    @Autowired
    private DataSource dataSource3;

    @Test
    public void t2() throws SQLException {
        System.out.println(dataSource2);
        Connection conn = dataSource2.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(?,?,?,now())");

        pstmt.setInt(1, 222);
        pstmt.setString(2, "hello2");
        pstmt.setString(3, "john2@name.com");

        pstmt.executeUpdate();
    }

    @Test
    public void t3() throws SQLException {
        System.out.println(dataSource3);
        Connection conn = dataSource3.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(?,?,?,now())");

        pstmt.setInt(1, 333);
        pstmt.setString(2, "hello3");
        pstmt.setString(3, "john3@name.com");

        pstmt.executeUpdate();
    }
}