package br.com.release_manager.dependency.controllers.api;

import br.com.release_manager.dependency.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Releases", description = "Operações para gerenciamento de releases")
@RequestMapping("/releases")
public interface ReleaseControllerApi {

    @Operation(
        summary = "Cadastra uma nova release",
        description = "Recebe os dados de uma release e realiza o cadastro.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
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
    @PostMapping
    ResponseEntity<ReleaseResponseCreateDto> saveRelease(@RequestBody ReleaseRequestDto releaseRequest);

    @GetMapping("/{id}")
    ResponseEntity<ReleaseResponseDto> findReleaseById(@PathVariable("id") Long id);

    @PatchMapping("/{id}")
    ResponseEntity<ReleaseResponseMessageDto> updateNotes(@PathVariable("id") Long id,
                                                          @RequestBody ReleaseNotesRequestDto releaseNotesRequestDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ReleaseResponseMessageDto> deleteRelease(@PathVariable("id") Long id);

    @GetMapping()
    ResponseEntity<List<ReleaseResponseDto>> findAllAndPaginate(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "total_page", defaultValue = "50", required = false)  int totalPage);
}