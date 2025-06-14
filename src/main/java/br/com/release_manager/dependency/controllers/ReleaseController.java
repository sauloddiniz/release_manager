package br.com.release_manager.dependency.controllers;

import br.com.release_manager.dependency.controllers.api.ReleaseControllerApi;
import br.com.release_manager.dependency.dto.ReleaseRequestDto;
import br.com.release_manager.dependency.dto.ReleaseResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class ReleaseController implements ReleaseControllerApi {

    @Override
    public ResponseEntity<Void> saveRelease(ReleaseRequestDto releaseRequest) {

        URI uri = URI.create("/release/" + releaseRequest.system() + "/" + releaseRequest.version());

        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<List<ReleaseResponseDto>> findAllAndPaginate(int page, int totalPage) {
        return null;
    }

}
