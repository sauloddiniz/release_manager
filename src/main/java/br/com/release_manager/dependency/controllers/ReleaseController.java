package br.com.release_manager.dependency.controllers;

import br.com.release_manager.application.ReleaseUseCase;
import br.com.release_manager.dependency.controllers.api.ReleaseControllerApi;
import br.com.release_manager.dependency.dto.ReleaseListResponseDto;
import br.com.release_manager.dependency.dto.ReleaseRequestDto;
import br.com.release_manager.dependency.dto.ReleaseResponseCreateDto;
import br.com.release_manager.dependency.dto.ReleaseResponseDto;
import br.com.release_manager.dependency.mapper.ReleaseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ReleaseController implements ReleaseControllerApi {

    private final ReleaseUseCase releaseUseCase;

    public ReleaseController(ReleaseUseCase releaseUseCase) {
        this.releaseUseCase = releaseUseCase;
    }

    @Override
    public ResponseEntity<ReleaseResponseCreateDto> saveRelease(ReleaseRequestDto releaseRequest) {

        Long idReleaseCreated = releaseUseCase.createRelease(releaseRequest);

        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(idReleaseCreated)
                .toUri();

        return ResponseEntity.created(uri).body(new ReleaseResponseCreateDto("Release criada com sucesso.", idReleaseCreated));
    }

    @Override
    public ResponseEntity<ReleaseListResponseDto> findAllAndPaginate(int page, int totalPage) {

        List<ReleaseResponseDto> listRelease = releaseUseCase.findAllAndPaginate(page, totalPage)
                .stream()
                .map(ReleaseMapper::releaseToResponseDto)
                .toList();

        ReleaseListResponseDto response = new ReleaseListResponseDto("Releases listados com sucesso.", listRelease);

        return ResponseEntity.ok(response);
    }

}
