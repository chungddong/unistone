package com.sophra.unistone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sophra.unistone.Entity.Users;
import com.sophra.unistone.Service.UsersService;

import jakarta.servlet.http.HttpSession;

@RestController
public class testController {

    @GetMapping("/api/test")
    public String test() {
        return "Hello, world!";
    }

    // pull request 테스트


    @Autowired
    private UsersService usersService;

    // 회원가입 요청 처리
    @PostMapping("/api/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody Users user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        // 이메일로 기존 사용자 있는지 확인
        if (usersService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("이미 사용중인 이메일입니다.");
        }

        // 회원가입 처리
        Users registeredUser = usersService.registerUser(user);


        return ResponseEntity.ok(registeredUser);

    }

    // 로그인 요청
    @PostMapping("/api/login")
    public ResponseEntity<?> loginUser(@RequestBody Users loginUser, HttpSession session) {

        if (usersService.confirmUser(loginUser)) {
            session.setAttribute("user", loginUser); //세션설정

            System.out.println("로그인 완료");


            return ResponseEntity.ok("Confirm");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이메일 또는 비밀번호 오류");
        }

    }

    // 로그아웃 요청
    @GetMapping("/api/logout")
    public ResponseEntity<?> logoutUser(HttpSession session) {
        session.invalidate(); // 세션 무효화 처리 - 로그아웃
        return ResponseEntity.ok("로그아웃 성공");
    }

}
