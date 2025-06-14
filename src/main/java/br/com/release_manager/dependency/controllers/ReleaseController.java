package br.com.release_manager.dependency.controllers;

import br.com.release_manager.application.ReleaseUseCase;
import br.com.release_manager.core.domain.Release;
import br.com.release_manager.dependency.controllers.api.ReleaseControllerApi;
import br.com.release_manager.dependency.dto.ReleaseListResponseDto;
import br.com.release_manager.dependency.dto.ReleaseRequestDto;
import br.com.release_manager.dependency.dto.ReleaseResponseCreateDto;
import br.com.release_manager.dependency.dto.ReleaseResponseDto;
import br.com.release_manager.dependency.mapper.ReleaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ReleaseController implements ReleaseControllerApi {

    private static final Logger log = LoggerFactory.getLogger(ReleaseController.class);
    private final ReleaseUseCase releaseUseCase;

    public ReleaseController(ReleaseUseCase releaseUseCase) {
        this.releaseUseCase = releaseUseCase;
    }

    @Override
    public ResponseEntity<ReleaseResponseCreateDto> saveRelease(ReleaseRequestDto releaseRequest) {

        log.info("Iniciando criação de nova release para o sistema: {} versão: {}",
                releaseRequest.system(), releaseRequest.version());

        Long idReleaseCreated = releaseUseCase.createRelease(releaseRequest);

        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(idReleaseCreated)
                .toUri();

        return ResponseEntity.created(uri).body(new ReleaseResponseCreateDto("Release criada com sucesso.", idReleaseCreated));
    }

    @Override
    public ResponseEntity<ReleaseResponseDto> findReleaseById(Long id) {
        Release release = releaseUseCase.findById(id);
        return null;
    }

    @Override
    public ResponseEntity<ReleaseListResponseDto> findAllAndPaginate(int page, int totalPage) {
        log.info("Buscando releases com paginação - Página: {}, Total por página: {}", page, totalPage);

        List<ReleaseResponseDto> listRelease = releaseUseCase.findAllAndPaginate(page, totalPage)
                .stream()
                .map(ReleaseMapper::releaseToResponseDto)
                .toList();

        ReleaseListResponseDto response = new ReleaseListResponseDto("Releases listados com sucesso.", listRelease);

        return ResponseEntity.ok(response);
    }

}
