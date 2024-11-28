package com.sophra.unistone.Controller;

import com.sophra.unistone.Entity.Files;
import com.sophra.unistone.Entity.Users;
import com.sophra.unistone.Repository.FilesRepository;
import com.sophra.unistone.UserCheck;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
public class FileController {

    @Autowired
    private FilesRepository filesRepository;
    
    // 유저 확인용 클래스
    UserCheck userCheck;

    // 자료 추가 요청

    // 자료 목록 가져오기

}
