package br.com.release_manager.dependency.mapper;

import br.com.release_manager.core.domain.Release;
import br.com.release_manager.dependency.dto.ReleaseResponseDto;
import br.com.release_manager.dependency.persistence.entity.ReleaseEntity;

public class ReleaseMapper {
    private ReleaseMapper() {
    }

    public static ReleaseResponseDto releaseToResponse(Release release) {
        if (release == null) {
            return null;
        }
        return new ReleaseResponseDto(
                "Release listado com sucesso.",
                release.getId(),
                release.getSystem(),
                release.getVersion(),
                release.getCommits(),
                release.getNotes(),
                release.getUser(),
                release.getUserUpdate(),
                release.getReleasedAt()
        );
    };

    public static Release entityToRelease(ReleaseEntity releaseEntity) {
        if (releaseEntity == null) {
            return null;
        }
        return new Release(
                releaseEntity.getId(),
                releaseEntity.getSystem(),
                releaseEntity.getVersion(),
                releaseEntity.getNotes(),
                releaseEntity.getCommits(),
                releaseEntity.getUser(),
                releaseEntity.getUserUpdate(),
                releaseEntity.getReleasedAt());
    }

    public static ReleaseEntity releaseToEntity(Release release) {
        if (release == null) {
            return null;
        }
        return new ReleaseEntity(
                release.getId(),
                release.getSystem(),
                release.getVersion(),
                release.getNotes(),
                release.getCommits(),
                release.getUser(),
                release.getUserUpdate(),
                release.getReleasedAt());
    }
}
