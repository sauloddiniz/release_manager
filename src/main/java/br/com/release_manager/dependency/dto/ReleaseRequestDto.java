package br.com.release_manager.dependency.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "DTO para cadastro de uma nova release")
public record ReleaseRequestDto(

    @Schema(
        description = "Nome do sistema",
        example = "ERP-Financeiro",
        maxLength = 255,
        required = true
    )
    String system,

    @Schema(
        description = "Versão da release",
        example = "v1.2.3",
        maxLength = 30,
        required = true
    )
    String version,

    @ArraySchema(
        schema = @Schema(
            description = "Commits incluídos na release",
            example = "['commit1', 'commit2', 'commit3']"
        ),
        minItems = 0,
        uniqueItems = true
    )
    List<String> commits,

    @Schema(
        description = "Notas adicionais da release",
        example = "Inclui melhorias de performance e correções de bugs.",
        required = false
    )
    String notes,
    String user

) {
}