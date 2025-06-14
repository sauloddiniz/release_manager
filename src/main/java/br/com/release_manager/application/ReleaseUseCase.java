package br.com.release_manager.application;

import br.com.release_manager.core.domain.Release;
import br.com.release_manager.dependency.dto.ReleaseNotesRequestDto;
import br.com.release_manager.dependency.dto.ReleaseRequestDto;

import java.util.List;

public interface ReleaseUseCase {
    List<Release> findAllAndPaginate(final int page, final int totalPage);
    Release findById(Long id);
    Long createRelease(ReleaseRequestDto releaseRequest);
    void updateNote(Long id, ReleaseNotesRequestDto releaseNotesRequestDto);
    void deleteRelease(Long id);
}
