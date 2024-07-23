package org.scoula.todo.dao;
import org.scoula.todo.common.JDBCUtil;
import org.scoula.todo.domain.UserVO;
import org.scoula.todo.dao.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    static Connection conn = JDBCUtil.getConnection();

    // 무슨 정보 필요?
    // driver 클래스의 위치, 로드
    // url / user / password

    static String driver = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/jdbc_ex"; // 마지막에 디비 이름
    static String user = "root";
    static String password = "1234";


    // 커넥션 객체 생성
    static {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // 여기까지 JAVD와 DB 연결 -> sql문을 쓸 준비가 됨 * 모두 static


    // 회원 정보 삽입
    // insert
    @Override
    public int create(UserVO user) throws SQLException {
        String sql = "insert into users values(?,?,?,?)";
        int count = -1;
         // 준비한 애를 준비된 상태 안에 넣기
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getId());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getRole());
            count = ps.executeUpdate();


            ps.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }return count;
    }

    // 회원 목록 전체 조회
    @Override
    public List<UserVO> getList() throws SQLException {
        String sql = "select * from users";
        List<UserVO> list = new ArrayList<>();

        try{
            PreparedStatement ps = conn.prepareStatement(sql);

            // 테이블 커서 만들기
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                // 공간 확보
                // VO/DTO 생성
                UserVO user = new UserVO();

                // DB에서 레코드 가져와서 새로운 공간 양식에 맞추기
                // 괄호 안에 것부터 우선적으로 처리
                // VO/DTO 데이터 세팅 -> 커서가 가리키는 레코드
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));

                // 리스트에 추가 (주소 유지)
                list.add(user);
            }
            rs.close();
            ps.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<UserVO> get(String id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public int update(UserVO user) throws SQLException {
        return 0;
    }

    @Override
    public int delete(String id) throws SQLException {
        return 0;
    }


//    Connection conn = JDBCUtil.getConnection();
//
//    // USERS 테이블 관련 SQL 명령어
//    private String USER_LIST = "select * from users";
//    private String USER_GET = "select * from users where id = ?";
//    private String USER_INSERT = "insert into users values(?, ?, ?, ?)";
//    private String USER_UPDATE = "update users set name = ?, role = ? where id = ?";
//    private String USER_DELETE = "delete from users where id = ?";
//
//    // 회원 등록
//    @Override
//    public int create(UserVO user) throws SQLException {
//        try (PreparedStatement stmt = conn.prepareStatement(USER_INSERT)) {
//            stmt.setString(1, user.getId());
//            stmt.setString(2, user.getPassword());
//            stmt.setString(3, user.getName());
//            stmt.setString(4, user.getRole());
//            return stmt.executeUpdate();
//        }
//    }
//    private UserVO map(ResultSet rs) throws SQLException {
//        UserVO user = new UserVO();
//        user.setId(rs.getString("ID"));
//        user.setPassword(rs.getString("PASSWORD"));
//        user.setName(rs.getString("NAME"));
//        user.setRole(rs.getString("ROLE"));
//        return user;
//    }
//    // 회원 목록 조회
//    @Override
//    public List<UserVO> getList() throws SQLException{
//        List<UserVO> userList = new ArrayList<UserVO>();
//        Connection conn = JDBCUtil.getConnection();
//        try (PreparedStatement stmt = conn.prepareStatement(USER_LIST);
//             ResultSet rs = stmt.executeQuery()) {
//            while(rs.next()) {
//                UserVO user = map(rs);
//                userList.add(user);
//            }
//        }
//        return userList;
//    }
//    // 회원 정보 조회
//    @Override
//    public Optional<UserVO> get(String id) throws SQLException{
//        try (PreparedStatement stmt = conn.prepareStatement(USER_GET)) {
//            stmt.setString(1, id);
//            try(ResultSet rs = stmt.executeQuery()) {
//                if(rs.next()) {
//                    return Optional.of(map(rs));
//                }
//            }
//        }
//        return Optional.empty();
//    }
//    // 회원 수정
//    @Override
//    public int update(UserVO user) throws SQLException{
//        Connection conn = JDBCUtil.getConnection();
//        try ( PreparedStatement stmt = conn.prepareStatement(USER_UPDATE)) {
//            stmt.setString(1, user.getName());
//            stmt.setString(2, user.getRole());
//            stmt.setString(3, user.getId());
//            return stmt.executeUpdate();
//        }
//    }
//    // USERS 테이블 관련 CRUD 메소드
//// 회원 삭제
//    @Override
//    public int delete(String id) throws SQLException{
//        try(PreparedStatement stmt = conn.prepareStatement(USER_DELETE)) {
//            stmt.setString(1, id);
//            return stmt.executeUpdate();
//        }
//    }
}
