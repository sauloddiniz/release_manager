package br.com.release_manager.dependency.persistence.adapter;

import br.com.release_manager.core.domain.Release;
import br.com.release_manager.dependency.persistence.ReleasePersistencePort;
import br.com.release_manager.dependency.persistence.entity.ReleaseEntity;
import br.com.release_manager.dependency.persistence.repository.ReleaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReleasePersistenceAdapter implements ReleasePersistencePort {

    private final ReleaseRepository releaseRepository;

    public ReleasePersistenceAdapter(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    @Override
    public List<Release> findAll(int page, int totalPage) {
        Pageable pageable = PageRequest.of(page, totalPage, Sort.by(Sort.Direction.ASC, "id"));
        Page<ReleaseEntity> findAll = releaseRepository.findAllByDeletedAtIsNull(pageable);
        return null;
    }
}
