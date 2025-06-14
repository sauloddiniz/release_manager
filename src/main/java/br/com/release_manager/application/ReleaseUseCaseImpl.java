package br.com.release_manager.application;

import br.com.release_manager.core.domain.Release;
import br.com.release_manager.core.exception.ReleaseCreateException;
import br.com.release_manager.dependency.dto.ReleaseRequestDto;
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
    public Long createRelease(ReleaseRequestDto releaseRequest) {
        Release release;
        try {
            release = Release.create(releaseRequest, "userUpdate");
            release = releasePersistencePort.save(release);
            return release.getId();
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public Release findById(Long id) {
        Release release = releasePersistencePort.findById(id);
        return null;
    }

    @Override
    public List<Release> findAllAndPaginate(int page, int totalPage) {
        List<Release> releases = releasePersistencePort.findAll(page, totalPage);
        return releases;
    }

}
