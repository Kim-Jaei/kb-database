package org.scoula.todo.dao;

import org.scoula.todo.common.JDBCUtil;
import org.scoula.todo.domain.TodoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoDaoImpl implements TodoDao {
        Connection conn = JDBCUtil.getConnection();

        // 로그인 사용자 id로 count
        // 행이 무조건 1개 발생 : 0을 반환할 수 있음
        @Override
        public int getTotalCount(String userId) throws SQLException {
            String sql = "select count(*) from todo where userid=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, userId);
                try (ResultSet rs = stmt.executeQuery()) {
                    rs.next();
                    // 몇번째 컬럼을 줄지 근데 1개밖에 없음
                    return rs.getInt(1);
                }
            }
        }

        @Override
        public int create(TodoVO todo) throws SQLException {
            String sql = "insert into todo(title, description, done, userid) values(?,?,?,?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, todo.getTitle());
                stmt.setString(2, todo.getDescription());
                stmt.setBoolean(3, todo.getDone());
                stmt.setString(4, todo.getUserId());
                return stmt.executeUpdate();
            }
        }


        // Resultset을 내가 원하는 객체로 매핑 - as?
        private TodoVO map(ResultSet rs) throws SQLException {
            TodoVO todo = new TodoVO();
            todo.setId(rs.getLong("id"));
            todo.setTitle(rs.getString("title"));
            todo.setDescription(rs.getString("description"));
            todo.setDone(rs.getBoolean("done"));
            todo.setUserId(rs.getString("userid"));
            return todo;
        }

        // 서치에서 사용할 예정
        private List<TodoVO> mapList(ResultSet rs) throws SQLException {
            // 비어있는 list 준비
            List<TodoVO> todoList = new ArrayList<TodoVO>();
            while (rs.next()) {
                TodoVO todo = map(rs);
                todoList.add(todo);
            }
            return todoList; // list 반환
        }

        // 로그인한 사용자 목록 읽기
        @Override
        public List<TodoVO> getList(String user) throws SQLException
        {
            String sql = "select * from todo where userId = ?";
            try (PreparedStatement stmt = conn.prepareStatement
                    (sql)){
                stmt.setString(1, user);
                try(ResultSet rs = stmt.executeQuery()) {
                    return mapList(rs);
                }
            }
        }

        @Override
        public Optional<TodoVO> get(String userId, Long id) throws SQLException
        {
            String sql = "select * from todo where userId = ? and id = ?";
            try (PreparedStatement stmt = conn.prepareStatement
                    (sql)) {
                stmt.setString(1, userId);
                stmt.setLong(2, id);
                try(ResultSet rs = stmt.executeQuery()) {
                    if(rs.next()) {
                        return Optional.of(map(rs));
                    }
                }
            }
            return Optional.empty();
        }

        @Override
        public List<TodoVO> search(String userId, String keyword) throws SQLException {
            // like '%?%'(X) 쿼리문에 쓰는 것이 아닌 전달할 때 % 붙이기
            String sql = "select * from todo where userId = ? and (title like ? or description like ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, userId);
                stmt.setString(2, keyword);
                stmt.setString(3, keyword);
                try(ResultSet rs = stmt.executeQuery()) {
                    return mapList(rs);
                }
            }
        }

        @Override
        public int update(String userId, TodoVO todo) throws SQLException {
            String sql = "update todo set title = ?, description = ?, done = ? where userId =? and id = ?";
            try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, todo.getTitle());
                stmt.setString(2, todo.getDescription());
                stmt.setBoolean(3, todo.getDone());
                stmt.setString(4, userId);
                stmt.setLong(5, todo.getId());
                // 영향 받은 행의 개수
                return stmt.executeUpdate();
            }
        }

        @Override
        public int delete(String userId, Long id) throws SQLException {
            String sql = "delete from todo where userId = ? and id = ?";
            try(PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, userId);
                stmt.setLong(2, id);
                // 영향 받은 행의 개수
                return stmt.executeUpdate();
            }
        }
    }
