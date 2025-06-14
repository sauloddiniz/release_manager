package br.com.release_manager.dependency.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReleaseResponseDto(
        String message,
        Long id,
        String system,
        String version,
        List<String> commits,
        String notes,
        String user,
        String userUpdate,
        String releaseAt) {
}
