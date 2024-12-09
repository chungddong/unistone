package com.sophra.unistone.Controller;

import com.sophra.unistone.DTO.FilesDTO;
import com.sophra.unistone.Entity.Files;
import com.sophra.unistone.Entity.Users;
import com.sophra.unistone.Repository.FilesRepository;
import com.sophra.unistone.Service.FilesService;
import com.sophra.unistone.UserCheck;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class FileController {

    @Autowired
    private FilesRepository filesRepository;
    
    // 유저 확인용 클래스
    UserCheck userCheck;
    @Autowired
    private FilesService filesService;

    // 파일 업로드 요청
    @PostMapping("/api/files/create/file")
    public ResponseEntity<?> createFile(@RequestParam("file") MultipartFile file,
                                        @RequestParam("title") String title,
                                        @RequestParam("description") String description,
                                        @RequestParam("projectId") Long projectId,
                                        HttpSession session) {

        // 유저 확인
        Users user = userCheck.validateLoggedInUser(session);

        // 새로운 파일 저장
        Files savedFile = filesService.saveFile(file, title, description, user, projectId);
        return ResponseEntity.ok("저장 완료");
    }

    // 링크 업로드 요청
    @PostMapping("/api/files/create/link")
    public ResponseEntity<?> createLink(@RequestParam("link") String link,
                                        @RequestParam("title") String title,
                                        @RequestParam("description") String description,
                                        @RequestParam("projectId") Long projectId,
                                        HttpSession session) {

        System.out.println(link);

        // 유저 확인
        Users user = userCheck.validateLoggedInUser(session);

        // 새로운 링크 저장
        Files savedLink = filesService.saveLink(link, title, description, user, projectId);
        return ResponseEntity.ok("저장 완료");
    }

    // 자료 목록 가져오기
    @PostMapping("/api/files/list")
    public ResponseEntity<?> getFileList(@RequestBody Map<String, Long> request, HttpSession session) {

        // 유저 확인
        Users user = userCheck.validateLoggedInUser(session);

        Long projectId = request.get("projectId");

        // projectId에 해당하는 파일과 링크 목록 가져오기
        Map<String, List<FilesDTO>> fileList = filesService.getFilesAndLinksByProjectId(projectId, user);

        return ResponseEntity.ok(fileList);
    }


}
