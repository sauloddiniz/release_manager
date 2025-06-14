package br.com.release_manager.dependency.controllers.api;

import br.com.release_manager.core.domain.Release;
import br.com.release_manager.dependency.dto.ReleaseRequestDto;
import br.com.release_manager.dependency.dto.ReleaseResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Releases", description = "Operações para gerenciamento de releases")
@RequestMapping("/release")
public interface ReleaseControllerApi {

    @Operation(
        summary = "Cadastra uma nova release",
        description = "Recebe os dados de uma release e realiza o cadastro.",
        requestBody = @RequestBody(
            required = true,
            description = "Informações necessárias para o cadastro da release",
            content = @Content(
                schema = @Schema(implementation = ReleaseRequestDto.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Release cadastrada com sucesso"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Dados inválidos ou faltantes",
                content = @Content
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Erro inesperado no servidor",
                content = @Content
            )
        }
    )
    ResponseEntity<Void> saveRelease(@RequestBody ReleaseRequestDto releaseRequest);

    ResponseEntity<List<ReleaseResponseDto>> findAllAndPaginate(int page, int totalPage);
}