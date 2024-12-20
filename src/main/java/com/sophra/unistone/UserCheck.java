package com.sophra.unistone;

import com.sophra.unistone.Entity.Users;
import com.sophra.unistone.Service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserCheck {

    private final UsersService usersService;

    // 생성자
    public UserCheck(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * 로그인 유저를 확인하고 반환
     * @param session HttpSession
     * @return 로그인 유저
     * @throws ResponseStatusException 로그인이 필요할 경우 예외 발생
     */
    public Users validateLoggedInUser(HttpSession session) {
        Users loginuser = (Users) session.getAttribute("user");
        if (loginuser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }

        return usersService.findbyEmail(loginuser.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."));
    }

}
