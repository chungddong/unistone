package com.sophra.unistone.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    // 파일을 저장할 기본 디렉토리 (application.properties에서 설정)
    @Value("${file.upload-dir}")
    private String uploadDir;

    // 파일을 저장하는 메소드
    public String storeFile(MultipartFile file) {
        // 파일 이름을 유니크하게 하기 위해 UUID 사용
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // 저장할 파일 경로
        Path targetLocation = Paths.get(uploadDir).resolve(fileName);

        try {
            // 디렉토리가 없으면 생성
            Files.createDirectories(targetLocation.getParent());

            // 파일을 지정된 위치에 저장
            file.transferTo(targetLocation);

        } catch (IOException e) {
            throw new RuntimeException("파일을 저장하는 데 실패했습니다.", e);
        }

        // 저장된 파일 경로 반환
        return targetLocation.toString();
    }

}
