package com.sophra.unistone.Service;

import com.sophra.unistone.DTO.FilesDTO;
import com.sophra.unistone.Entity.Files;
import com.sophra.unistone.Entity.Project;
import com.sophra.unistone.Entity.Users;
import com.sophra.unistone.Repository.FilesRepository;
import com.sophra.unistone.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FilesService {

    @Autowired
    private FilesRepository filesRepository;

    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private ProjectRepository projectRepository;

    //파일 업로드 처리

    // 파일 업로드 처리
    public Files saveFile(MultipartFile file, String title, String description, Users user, Long projectId) {

        // 프로젝트 찾기
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("프로젝트를 찾을 수 없습니다"));

        // 파일 저장
        String filePath = fileStorageService.storeFile(file);

        // File 엔티티 생성
        Files newFile = new Files();
        newFile.setTitle(title);
        newFile.setDescription(description);
        newFile.setFilePath(filePath);
        newFile.setFileType(Files.FileType.FILE); // 파일 타입 설정 (파일일 경우)
        newFile.setUser(user);
        newFile.setProject(project);

        // 파일 정보 저장
        return filesRepository.save(newFile);
    }

    // 링크 저장 처리
    public Files saveLink(String link, String title, String description, Users user, Long projectId) {

        // 프로젝트 찾기
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("프로젝트를 찾을 수 없습니다"));


        // File 엔티티 생성
        Files newFile = new Files();
        newFile.setTitle(title);
        newFile.setDescription(description);
        newFile.setFilePath(link); // 링크 경로 저장
        newFile.setFileType(Files.FileType.LINK); // 파일 타입 설정 (링크일 경우)
        newFile.setUser(user);
        newFile.setProject(project);

        // 파일 정보 저장
        return filesRepository.save(newFile);
    }

    // 프로젝트에 해당하는 파일과 링크 목록 가져오기
    public Map<String, List<FilesDTO>> getFilesAndLinksByProjectId(Long projectId, Users user) {
        List<Files> filesList = filesRepository.findByProjectIdAndFileType(projectId, Files.FileType.FILE);
        List<Files> linksList = filesRepository.findByProjectIdAndFileType(projectId, Files.FileType.LINK);

        List<FilesDTO> filesDTOList = filesList.stream()
                .map(file -> new FilesDTO(file, user.getUserName()))
                .collect(Collectors.toList());

        List<FilesDTO> linksDTOList = linksList.stream()
                .map(file -> new FilesDTO(file, user.getUserName()))
                .collect(Collectors.toList());

        Map<String, List<FilesDTO>> response = new HashMap<>();
        response.put("files", filesDTOList);
        response.put("links", linksDTOList);

        return response;
    }


}
