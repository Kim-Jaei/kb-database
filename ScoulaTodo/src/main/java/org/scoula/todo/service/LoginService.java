package org.scoula.todo.service;

import org.scoula.lib.cli.ui.Input;
import org.scoula.todo.dao.UserDao;
import org.scoula.todo.dao.UserDaoImpl;
import org.scoula.todo.domain.UserVO;
import org.scoula.todo.exception.LoginFailException;

import org.scoula.todo.context.Context;
import java.sql.SQLException;

public class LoginService {
    // 1. 로그인하려면 DB 갔다와야함 => Access => DAO => UserDao
    // 1-2. dao 생성
    // 1-3. dao에서 특정 사용자 정보 가져와야 함 => get.orElseThrow(new exception)
    // 2. 패스워드 비교 실행문
    // 2-2-1. 성공했다면 세션/쿠키처럼 Context에 저장
    // 2-2-2. 실패했다면 에러메시지 -> 초기 화면으로

    UserDao dao = new UserDaoImpl();

    public void login() throws SQLException, LoginFailException {
        String username = Input.getLine("사용자 ID: ");
        String password = Input.getLine("비밀번호: ");
        UserVO user = dao.get(username).orElseThrow(LoginFailException::new);
        if(user.getPassword().equals(password)) {
            Context ctx = Context.getContext();
            ctx.setUser(user);
        } else {
            throw new LoginFailException();
        }
    }
}
