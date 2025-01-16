package sml.fm.fm_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sml.fm.fm_service.entity.FileData;
import sml.fm.fm_service.entity.FileDataResponse;
import sml.fm.fm_service.repository.StorageRepository;
import sml.fm.fm_service.util.CompressionUtils;

import java.io.IOException;

@Service
public class StorageService {

    private final StorageRepository repository;

    protected StorageService(StorageRepository repository){
        this.repository = repository;
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
}
