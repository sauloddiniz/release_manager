package br.com.release_manager.application;

import br.com.release_manager.core.domain.Release;
import br.com.release_manager.dependency.persistence.ReleasePersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseUseCaseImpl implements ReleaseUseCase {

    private final ReleasePersistencePort releasePersistencePort;

    public ReleaseUseCaseImpl(ReleasePersistencePort releasePersistencePort) {
        this.releasePersistencePort = releasePersistencePort;
    }

    @Override
    public List<Release> findAllAndPaginate(int page, int totalPage) {
        return List.of();
    }
}
