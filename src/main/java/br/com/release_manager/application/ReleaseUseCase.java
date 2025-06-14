package br.com.release_manager.application;

import br.com.release_manager.core.domain.Release;

import java.util.List;

public interface ReleaseUseCase {
    List<Release> findAllAndPaginate(final int page, final int totalPage);
}
