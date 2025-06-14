package br.com.release_manager.dependency.controllers;

import br.com.release_manager.application.ReleaseUseCase;
import br.com.release_manager.dependency.controllers.api.ReleaseControllerApi;
import br.com.release_manager.dependency.dto.ReleaseRequestDto;
import br.com.release_manager.dependency.dto.ReleaseResponseDto;
import br.com.release_manager.dependency.mapper.ReleaseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class ReleaseController implements ReleaseControllerApi {

    private final ReleaseUseCase releaseUseCase;

    public ReleaseController(ReleaseUseCase releaseUseCase) {
        this.releaseUseCase = releaseUseCase;
    }

    @Override
    public ResponseEntity<Void> saveRelease(ReleaseRequestDto releaseRequest) {

        URI uri = URI.create("/release/" + releaseRequest.system() + "/" + releaseRequest.version());

        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<List<ReleaseResponseDto>> findAllAndPaginate(int page, int totalPage) {

        List<ReleaseResponseDto> response = releaseUseCase.findAllAndPaginate(page, totalPage)
                .stream()
                .map(ReleaseMapper::toResponse)
                .toList();

        return null;
    }

}
