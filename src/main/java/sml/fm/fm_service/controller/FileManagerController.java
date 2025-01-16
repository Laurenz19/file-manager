package sml.fm.fm_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sml.fm.fm_service.entity.FileDataResponse;
import sml.fm.fm_service.service.StorageService;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileManagerController {

    private final StorageService service;

    protected FileManagerController(StorageService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String uploadedFile = service.uploadFile(file);
        return new ResponseEntity<>(uploadedFile, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id){
        FileDataResponse fileData = service.downloadFile(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(fileData.getContentType()))
                .body(fileData.getData());
    }

    @PostMapping("/fileSystem")
    public ResponseEntity<String> uploadFileToFIleSystem(@RequestParam("file")MultipartFile file) throws IOException {
        String uploadedFile = service.uploadImageToFileSystem(file);
        return new ResponseEntity<>(uploadedFile, HttpStatus.OK);
    }

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<byte[]> downloadFileFromFileSystem(@PathVariable String fileName) throws IOException {
        FileDataResponse fileData = service.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(fileData.getContentType()))
                .body(fileData.getData());

    }
}
