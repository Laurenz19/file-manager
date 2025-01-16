package sml.fm.fm_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sml.fm.fm_service.entity.FileDataV2;

import java.util.Optional;

@Repository
public interface StorageV2Repository extends JpaRepository<FileDataV2, Long> {
    Optional<FileDataV2> findByName(String fileName);
}
