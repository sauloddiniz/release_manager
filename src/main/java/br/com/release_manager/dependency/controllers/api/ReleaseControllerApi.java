package br.com.release_manager.dependency.controllers.api;

import br.com.release_manager.dependency.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.media.ArraySchema;

import java.util.List;

@Tag(name = "Releases", description = "Endpoints para gerenciamento de releases, incluindo criação, consulta, modificação e exclusão.")
@RequestMapping("/releases")
public interface ReleaseControllerApi {

    @Operation(
        summary = "Cadastra uma nova release",
        description = """
            Recebe os dados de uma release e realiza o cadastro. É necessário informar ao menos o nome do sistema, a 
            versão da release e os commits incluídos. Este endpoint é ideal para criar novas versões de sistemas.""",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Objeto contendo as informações necessárias para registro de uma nova release.",
            content = @Content(
                schema = @Schema(implementation = ReleaseRequestDto.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "A release foi cadastrada com sucesso. O objeto da resposta contém as informações da nova release."
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Erro de validação devido a dados inválidos ou faltantes. Certifique-se de que os campos obrigatórios estão preenchidos.",
                content = @Content
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Erro inesperado no servidor durante o processamento.",
                content = @Content
            )
        }
    )
    @PostMapping
    ResponseEntity<ReleaseResponseCreateDto> saveRelease(@RequestBody ReleaseRequestDto releaseRequest);

    @Operation(
        summary = "Consulta uma release pelo ID",
        description = "Este endpoint permite buscar as informações completas da release com base no seu ID.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Release encontrada com sucesso.",
                content = @Content(
                    schema = @Schema(implementation = ReleaseResponseDto.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "A release com o ID fornecido não foi encontrada.",
                content = @Content
            )
        }
    )
    @GetMapping("/{id}")
    ResponseEntity<ReleaseResponseDto> findReleaseById(@PathVariable("id") Long id);

    @Operation(
        summary = "Atualiza as notas de uma release",
        description = """
            Este endpoint permite atualizar as notas adicionais de uma release existente. O ID da release deve ser 
            fornecido na URL.""",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Notas a serem atualizadas para a release.",
            content = @Content(
                schema = @Schema(implementation = ReleaseNotesRequestDto.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Notas da release atualizadas com sucesso.",
                content = @Content(
                    schema = @Schema(implementation = ReleaseResponseMessageDto.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "A release com o ID fornecido não foi encontrada.",
                content = @Content
            )
        }
    )
    @PatchMapping("/{id}")
    ResponseEntity<ReleaseResponseMessageDto> updateNotes(@PathVariable("id") Long id,
                                                          @RequestBody ReleaseNotesRequestDto releaseNotesRequestDto);

    @Operation(
        summary = "Exclui uma release",
        description = "Remove a release do sistema com base no ID informado.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Release deletada com sucesso.",
                content = @Content(
                    schema = @Schema(implementation = ReleaseResponseMessageDto.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Release não encontrada para o ID fornecido.",
                content = @Content
            )
        }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<ReleaseResponseMessageDto> deleteRelease(@PathVariable("id") Long id);

    @Operation(
        summary = "Lista todas as releases com paginação",
        description = """
            Obtém a lista de todas as releases registradas no sistema. A resposta é paginada, e os parâmetros de 
            paginação podem ser customizados.""",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de releases obtida com sucesso.",
                content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = ReleaseResponseDto.class))
                )
            )
        }
    )
    @GetMapping()
    ResponseEntity<List<ReleaseResponseDto>> findAllAndPaginate(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "total_page", defaultValue = "50", required = false) int totalPage);
}