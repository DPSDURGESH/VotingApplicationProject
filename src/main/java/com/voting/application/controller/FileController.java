package com.voting.application.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.voting.application.model.FileItem;
import com.voting.application.repository.FileRepository;


@RestController
public class FileController {
	
	@Autowired
	private FileRepository fileRepository;
	
	private final String FOLDER_PATH = "C:/upload/";
	
	@PostMapping("/upload")
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		Path filePath = Paths.get(FOLDER_PATH).resolve(file.getOriginalFilename()).normalize().toAbsolutePath();
		
		// Ensure the directory exists
		Files.createDirectories(filePath.getParent());
		
		// Save the file to the desired location
		try (var inputStream = file.getInputStream()) {
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		}

		FileItem item = new FileItem();
		item.setFileName(file.getOriginalFilename());
		item.setFileType(file.getContentType());
		item.setFileLocation(filePath.toString());

		fileRepository.save(item);

		return ResponseEntity.status(HttpStatus.CREATED).body("File Upload Successfully");
	}
	
	@GetMapping("/download/{filename:.+}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable("filename") String filename) throws IOException {
		Optional<FileItem> fileItem = fileRepository.findByFileName(filename);
		if (fileItem.isPresent()) {
			FileItem item = fileItem.get();
			Path path = Paths.get(item.getFileLocation());
			
			// Set the Content-Type header based on the file extension
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType(item.getFileType()));
			
			// Read the file content
			byte[] content = Files.readAllBytes(path);
			
			return ResponseEntity.ok()
					.headers(headers)
					.body(content);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
