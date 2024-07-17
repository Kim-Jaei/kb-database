package org.scoula.jdbc_ex.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.scoula.jdbc_ex.common.JDBCUtil;

import java.sql.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrudTest {
    Connection conn = JDBCUtil.getConnection();

    @AfterAll
    static void tearDown(){
        JDBCUtil.close();
    }

    @Test
    @DisplayName("새로운 user 등록")
    @Order(1)
    public void insertUser() throws SQLException {
        String sql = "insert into users(id, password, name, role) values(?, ?, ?, ?)";
        try(PreparedStatement pstmt = conn.prepareStatement((sql))){
            pstmt.setString(1, "1");
            pstmt.setString(2, "1234");
            pstmt.setString(3, "김재이");
            pstmt.setString(4, "사람");

            int count = pstmt.executeUpdate();
            // insert의 성공 개수
            Assertions.assertEquals(1, count);
        }
    }

    @Test
    @DisplayName("user 목록 읽기")
    @Order(2)
    public void selectUser() throws SQLException {
        String sql = "select * from users";
        // try(PreparedStatement pstmt = conn.prepareStatement(sql));
        // ResultSet rs = pstmt.executeQuery();
        try(Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);){
            while(rs.next()){
                System.out.println(rs.getString("id") + " " + rs.getString("password") + " " + rs.getString("name"));
           }
        }
    }

    @Test
    @DisplayName("특정 user 목록 읽기")
    @Order(3)
    public void selectUserById() throws SQLException {
        String sql = "select * from users where id = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, "1");
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    System.out.println((rs.getString("name")));
                }else{
                    throw new SQLException("scoula not found");
                }
            }
        }
    }

    @Test
    @DisplayName("name 수정")
    @Order(4)
    public void updateUser() throws SQLException {
        String sql = "update users set name=? where id=?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "김철수");
            pstmt.setString(2, "1");

            int count = pstmt.executeUpdate();
            Assertions.assertEquals(1, count);
        }
    }

    @Test
    @DisplayName("행 삭제")
    @Order(5)
    public void deleteUser() throws SQLException {
        String sql = "delete from users where id = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "1");

            int count = pstmt.executeUpdate();
            Assertions.assertEquals(1, count);
        }
    }
}
