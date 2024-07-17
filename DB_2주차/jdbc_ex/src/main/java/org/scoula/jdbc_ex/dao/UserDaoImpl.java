package org.scoula.jdbc_ex.dao;

import org.scoula.jdbc_ex.common.JDBCUtil;
import org.scoula.jdbc_ex.domain.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    Connection conn = JDBCUtil.getConnection();

    @Override
    public int create(UserVO user) throws SQLException{
        String sql = "insert into users(id, password, name, role) values(?,?,?,?)";
        try(PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getRole());
            return pstmt.executeUpdate();
        }
    }

    public UserVO map(ResultSet rs) throws SQLException{
        UserVO user = new UserVO(); // rs를 담을 빈 그릇 {} 형태
        user.setId(rs.getString("id"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setRole(rs.getString("Role"));
        return user; // 담아서 반환
    }

    @Override
    public List<UserVO> getList() throws SQLException {
        String sql = "select * from users";
        List<UserVO> list = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();){
            while(rs.next()){
                UserVO user = map(rs); // {}에 한 레코드씩 담김
                list.add(user); // 리스트 형태로 담김
            }
        }
        return list;
    }

    @Override
    public Optional<UserVO> get(String id) throws SQLException {
        String sql = "select * from users where id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1, id);
            try(ResultSet rs = ps.executeQuery();){
                if(rs.next()){
                    return Optional.of(map(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public int update(UserVO user) throws SQLException {
        String sql = "update users set name = ?, role = ? where id = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql);){
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getRole());
                pstmt.setString(4, user.getId());
                return pstmt.executeUpdate();
            }
    }

    @Override
    public int delete(String id) throws SQLException {
        String sql = "delete from users where id = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1, id);
            return pstmt.executeUpdate();
        }
    }
}
