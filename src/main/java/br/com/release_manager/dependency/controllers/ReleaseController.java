package br.com.release_manager.dependency.controllers;

import br.com.release_manager.application.ReleaseUseCase;
import br.com.release_manager.core.domain.Release;
import br.com.release_manager.dependency.controllers.api.ReleaseControllerApi;
import br.com.release_manager.dependency.dto.*;
import br.com.release_manager.dependency.mapper.ReleaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
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
    public ResponseEntity<ReleaseResponseCreateDto> saveRelease(ReleaseRequestDto releaseRequest,
                                                                String jwt) {

        log.info("Iniciando criação de nova release para o sistema: {} versão: {}",
                releaseRequest.system(), releaseRequest.version());

        Long idReleaseCreated = releaseUseCase.createRelease(releaseRequest, jwt);

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
        return ResponseEntity.ok(ReleaseMapper.releaseToResponse(release));
    }

    @Override
    public ResponseEntity<ReleaseResponseMessageDto> updateNotes(Long id,
                                                                 ReleaseNotesRequestDto releaseNotesRequestDto,
                                                                 String jwt) {
        releaseUseCase.updateNote(id, releaseNotesRequestDto, jwt);
        return ResponseEntity.ok().body(new ReleaseResponseMessageDto("Release atualizado com sucesso."));
    }

    @Override
    public ResponseEntity<ReleaseResponseMessageDto> deleteRelease(Long id,String jwt) {
        releaseUseCase.deleteRelease(id, jwt);
        return ResponseEntity.ok().body(new ReleaseResponseMessageDto("Release deletado com sucesso."));
    }

    @Override
    public ResponseEntity<List<ReleaseResponseDto>> findAllAndPaginate(int page, int totalPage) {
        log.info("Buscando releases com paginação - Página: {}, Total por página: {}", page, totalPage);

        List<ReleaseResponseDto> listRelease = releaseUseCase.findAllAndPaginate(page, totalPage)
                .stream()
                .map(ReleaseMapper::releaseToResponse)
                .toList();

        return ResponseEntity.ok(listRelease);
    }

}
