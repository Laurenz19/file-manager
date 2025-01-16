package sml.fm.fm_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sml.fm.fm_service.entity.FileData;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<FileData, Long> {
    Optional<FileData> findByName(String name);
}
