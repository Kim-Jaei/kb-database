package org.scoula.jdbc_ex.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
    static Connection conn = null;

    //초기화 작업을 해주는 static block
    static {
        try{
            //properties 파일 정보 담기
            Properties properties = new Properties();
            properties.load(JDBCUtil.class.getResourceAsStream("/application.properties"));

            // properties 정보를 키값을 이용하여 저장된 값 가져오기
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String id = properties.getProperty("id");
            String password = properties.getProperty("password");

            // 드라이버(mysql) + 연결하기 작업
            Class.forName(driver);
            conn = DriverManager.getConnection(url, id, password);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // 연결 실행, static 꼬옥 써주기
    public static Connection getConnection(){
        return conn;
    }

    // 연결 끝내기
    public static void close(){
        try{
            if(conn != null){
                conn.close();
                conn = null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
