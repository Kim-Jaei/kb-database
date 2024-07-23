package org.scoula.todo.dao;

import org.scoula.todo.common.JDBCUtil;
import org.scoula.todo.domain.TodoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDaoImpl implements TodoDao {
    @Override
    public int getTotalCount(String userId) throws SQLException {
        // 전체 개수 조회
        // select count(*) from todo => int , long

        return 0;
    }

    @Override
    public int create(TodoVO todo) throws SQLException {
        // 커넥션 정보 가져오기
        // insert into todo(컬럼명 지정) values('값1', '값2', '값3', '값4')
        // ? ? ? ? => PreparedStatement
        // 파라미터 세팅 ? 값 매칭
        // 실행
        // int 반환 -> 삽입된 행의 개수 => 자체를 리턴하거나 / 내부적인 처리 (try-catch)
        return 0;
    }


    private TodoVO map(ResultSet rs) throws SQLException {
        TodoVO todo = new TodoVO();
        todo.setId(rs.getLong("id"));
        todo.setTitle(rs.getString("title"));
        todo.setDescription(rs.getString("description"));
        todo.setDone(rs.getBoolean("done"));
        todo.setUserid(rs.getString("userid"));
        return todo;
    }

    private List<TodoVO> mapList(ResultSet rs) throws SQLException {
        List<TodoVO> list = new ArrayList<>();
        while (rs.next()) {
            TodoVO todo = map(rs);
            list.add(todo);
        }
        return list;
    }

    @Override
    public List<TodoVO> getList(String userId) throws SQLException {
        // 커넥션 읽어오기
        Connection conn = JDBCUtil.getConnection();
        // sql 처리할 수 있는 상태로 만들기
        // sql = select * from todo where userid = ?
        // 파라미터 세팅
        // 실행
        // ResultSet 반환 -> 레코드를 커서로 가리킴
        // 여러개면! while
        // 커서를 이용해서 TodoVO =>
        // List에 담아준다 .add([todo])
        String sql = "select * from todo where userid = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }
}
