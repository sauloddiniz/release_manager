package br.com.release_manager.dependency.persistence.repository;

import br.com.release_manager.dependency.persistence.entity.ReleaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReleaseRepository extends JpaRepository<ReleaseEntity, Long> {
    Page<ReleaseEntity> findAllByDeletedAtIsNull(Pageable pageable);
    Optional<ReleaseEntity> findByIdAndDeletedAtIsNull(Long id);
}
