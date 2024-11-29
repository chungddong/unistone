package com.sophra.unistone.Repository;

import com.sophra.unistone.Entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilesRepository extends JpaRepository<Files, Long> {
    List<Files> findByProjectId(Long projectId);

    List<Files> findByProjectIdAndFileType(Long projectId, Files.FileType fileType);
}
