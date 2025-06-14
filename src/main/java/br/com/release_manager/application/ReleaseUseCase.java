package br.com.release_manager.application;

import br.com.release_manager.core.domain.Release;
import br.com.release_manager.dependency.dto.ReleaseRequestDto;
import br.com.release_manager.dependency.dto.ReleaseResponseDto;

import java.util.List;

public interface ReleaseUseCase {
    List<Release> findAllAndPaginate(final int page, final int totalPage);
    Long createRelease(ReleaseRequestDto releaseRequest);
    Release findById(Long id);
}
