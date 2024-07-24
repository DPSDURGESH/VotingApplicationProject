package com.voting.application.repository;

import com.voting.application.model.FileItem;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileItem, Integer> {

    // Custom query to find a FileItem by its filename
    Optional<FileItem> findByFileName(String fileName);
}
