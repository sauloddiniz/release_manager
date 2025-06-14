package br.com.release_manager.dependency.persistence;

import br.com.release_manager.core.domain.Release;

import java.util.List;

public interface ReleasePersistencePort {
    List<Release> findAll(final int page, final int totalPage);
    Release save(Release release);
    Release findById(Long id);
}
