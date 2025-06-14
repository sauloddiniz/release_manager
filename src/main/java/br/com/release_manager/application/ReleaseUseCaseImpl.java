package br.com.release_manager.application;

import br.com.release_manager.core.domain.Release;
import br.com.release_manager.dependency.dto.ReleaseNotesRequestDto;
import br.com.release_manager.dependency.dto.ReleaseRequestDto;
import br.com.release_manager.dependency.persistence.ReleasePersistencePort;
import br.com.release_manager.dependency.security.JwtManagerPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseUseCaseImpl implements ReleaseUseCase {

    private final ReleasePersistencePort releasePersistencePort;
    private final JwtManagerPort jwtManagerPort;

    public ReleaseUseCaseImpl(ReleasePersistencePort releasePersistencePort, JwtManagerPort jwtManagerPort) {
        this.releasePersistencePort = releasePersistencePort;
        this.jwtManagerPort = jwtManagerPort;
    }

    @Override
    public Long createRelease(ReleaseRequestDto releaseRequest, String jwt) {
        Release release;
        String userUpdate = jwtManagerPort.getName(jwt);
        release = Release.create(releaseRequest, userUpdate);
        release = releasePersistencePort.save(release);
        return release.getId();
    }

    @Override
    public Release findById(Long id) {
        return releasePersistencePort.findById(id);
    }

    @Override
    @Transactional
    public void updateNote(Long id, ReleaseNotesRequestDto releaseNotesRequestDto, String jwt) {
        String userUpdate = jwtManagerPort.getName(jwt);
        Release release = releasePersistencePort.findById(id);
        release.updateRelease(releaseNotesRequestDto.notes(), userUpdate);
        releasePersistencePort.save(release);
    }

    @Override
    @Transactional
    public void deleteRelease(Long id, String jwt) {
        String userUpdate = jwtManagerPort.getName(jwt);
        Release release = releasePersistencePort.findById(id);
        release.deleteRelease(userUpdate);
        releasePersistencePort.save(release);
    }

    @Override
    public List<Release> findAllAndPaginate(int page, int totalPage) {
        return releasePersistencePort.findAll(page, totalPage);
    }

}
