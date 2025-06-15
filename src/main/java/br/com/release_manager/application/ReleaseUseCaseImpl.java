package br.com.release_manager.application;

import br.com.release_manager.core.domain.Release;
import br.com.release_manager.dependency.dto.ReleaseNotesRequestDto;
import br.com.release_manager.dependency.dto.ReleaseRequestDto;
import br.com.release_manager.dependency.persistence.ReleasePersistencePort;
import br.com.release_manager.dependency.security.ContextManagerPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseUseCaseImpl implements ReleaseUseCase {

    private static final Logger log = LoggerFactory.getLogger(ReleaseUseCaseImpl.class);
    private final ReleasePersistencePort releasePersistencePort;
    private final ContextManagerPort contextManagerPort;

    public ReleaseUseCaseImpl(ReleasePersistencePort releasePersistencePort, ContextManagerPort contextManagerPort) {
        this.releasePersistencePort = releasePersistencePort;
        this.contextManagerPort = contextManagerPort;
    }

    @Override
    public Long createRelease(ReleaseRequestDto releaseRequest) {
        log.info("Iniciando criação de release para o request: {}", releaseRequest);
        String userUpdate = contextManagerPort.getName();
        Release release = Release.create(releaseRequest, userUpdate);
        release = releasePersistencePort.save(release);
        log.info("Release criada com sucesso. ID: {}", release.getId());
        return release.getId();
    }

    @Override
    public Release findById(Long id) {
        log.info("Obtendo Release. ID: {}", id);
        Release release = releasePersistencePort.findById(id);
        log.info("Release obtida com sucesso. ID: {}", release.getId());
        return release;
    }

    @Override
    @Transactional
    public void updateNote(Long id, ReleaseNotesRequestDto releaseNotesRequestDto) {
        log.info("Atualizando notes da release. ID: {}", id);
        String userUpdate = contextManagerPort.getName();
        Release release = releasePersistencePort.findById(id);
        release.updateRelease(releaseNotesRequestDto.notes(), userUpdate);
        releasePersistencePort.save(release);
        log.info("Release atualizada com sucesso. ID: {}", release.getId());
    }

    @Override
    @Transactional
    public void deleteRelease(Long id) {
        log.info("Deletando release. ID: {}", id);
        String userUpdate = contextManagerPort.getName();
        Release release = releasePersistencePort.findById(id);
        release.deleteRelease(userUpdate);
        releasePersistencePort.save(release);
        log.info("Release deletado com sucesso. ID: {}", release.getId());
    }

    @Override
    public List<Release> findAllAndPaginate(int page, int totalPage) {
        log.info("Obtendo lista de release: page: {}, totalPage: {}", page, totalPage);
        return releasePersistencePort.findAll(page, totalPage);
    }

}
