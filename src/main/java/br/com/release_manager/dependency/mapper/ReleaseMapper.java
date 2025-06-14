package br.com.release_manager.dependency.mapper;

import br.com.release_manager.core.domain.Release;
import br.com.release_manager.dependency.dto.ReleaseResponseDto;

public class ReleaseMapper {
    private ReleaseMapper() {
    }

    public static ReleaseResponseDto toResponse(Release release) {
        return new ReleaseResponseDto(
                release.getId(),
                release.getSystem(),
                release.getVersion());
    };
}
