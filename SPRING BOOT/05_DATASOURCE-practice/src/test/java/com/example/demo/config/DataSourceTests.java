package com.example.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootTest
public class DataSourceTests {
    @Autowired
    private DataSource dataSource;

    @Test
    public void t1() throws SQLException {
        System.out.println(dataSource);
        Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(?,?,?,now()");

        pstmt.setInt(1, 111);
        pstmt.setString(2, "hello");
        pstmt.setString(3, "john@name.com");

        pstmt.executeUpdate();
    }
}
