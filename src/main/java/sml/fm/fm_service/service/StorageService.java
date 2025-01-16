package sml.fm.fm_service.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sml.fm.fm_service.entity.FileData;
import sml.fm.fm_service.entity.FileDataResponse;
import sml.fm.fm_service.entity.FileDataV2;
import sml.fm.fm_service.repository.StorageRepository;
import sml.fm.fm_service.repository.StorageV2Repository;
import sml.fm.fm_service.util.CompressionUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class StorageService {

    private final StorageRepository repository;
    private final StorageV2Repository repositoryV2;
    private final Environment env;

    protected StorageService(StorageRepository repository, Environment env, StorageV2Repository repositoryV2){
        this.repository = repository;
        this.repositoryV2 = repositoryV2;
        this.env = env;
    }

    public String uploadFile(MultipartFile file) throws IOException {

        repository.save(
                new FileData(file.getOriginalFilename(),
                        file.getContentType(),
                        CompressionUtils.compressData(file.getBytes()))
        );

        return "file uploaded successfully : " + file.getOriginalFilename();
    }

    public FileDataResponse downloadFile(Long id){
        FileData dbFileData = repository.findById(id).orElseThrow(()-> new RuntimeException("File not found"));
        return new FileDataResponse(CompressionUtils.decompressData(dbFileData.getData()), dbFileData.getType());
    }

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=env.getProperty("FOLDER_PATH")+file.getOriginalFilename();

        FileDataV2 fileData = repositoryV2.save(new FileDataV2(file.getOriginalFilename(), file.getContentType(), filePath));

        file.transferTo(new File(filePath));

        return "file uploaded successfully : " + fileData.getFilePath();
    }

    public FileDataResponse downloadImageFromFileSystem(String fileName) throws IOException {
        FileDataV2 fileData = repositoryV2.findByName(fileName).orElseThrow(()-> new RuntimeException("File not found"));
        String filePath=fileData.getFilePath();
        return new FileDataResponse(Files.readAllBytes(new File(filePath).toPath()), fileData.getType());
    }

}
