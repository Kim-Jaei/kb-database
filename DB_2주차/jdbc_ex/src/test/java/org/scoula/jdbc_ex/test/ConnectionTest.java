package org.scoula.jdbc_ex.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.scoula.jdbc_ex.common.JDBCUtil;

import java.sql.Connection;

public class ConnectionTest {
    @Test
    @DisplayName("jdbc_ex에 접속한다.(자동 닫기)")
    public void testConnection2() throws Exception {
        try(Connection conn = JDBCUtil.getConnection()){
            System.out.println("디비 연결 성공");
        }
    }
}
