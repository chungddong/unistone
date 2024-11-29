package com.sophra.unistone.DTO;

import com.sophra.unistone.Entity.Files;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FilesDTO {

    private Long id;
    private String title;
    private String description;
    private String filePath;
    private Files.FileType fileType;
    private LocalDateTime createdAt;
    private String userName; // 유저 이름만 포함

    public FilesDTO(Files file, String userName) {
        this.id = file.getId();
        this.title = file.getTitle();
        this.description = file.getDescription();
        this.filePath = file.getFilePath();
        this.fileType = file.getFileType();
        this.createdAt = file.getCreatedDate();
        this.userName = userName; // 유저 이름만 포함
    }

}
