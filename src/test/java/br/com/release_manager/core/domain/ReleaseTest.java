package br.com.release_manager.core.domain;

import br.com.release_manager.core.exceptions.ReleaseCreateException;
import br.com.release_manager.dependency.dto.ReleaseRequestDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReleaseTest {

    @Test
    void shouldCreateReleaseSuccessfully() {
        ReleaseRequestDto releaseRequestDto = new ReleaseRequestDto(
                "ERP-Financeiro",
                "v1.2.3",
                List.of("commit1", "commit2"),
                "Inclui melhorias de performance e correções de bugs.",
                "testUser");
        String userUpdate = "userUpdater";

        Release release = Release.create(releaseRequestDto, userUpdate);

        assertNotNull(release);
        assertEquals("ERP-Financeiro", release.getSystem());
        assertEquals("v1.2.3", release.getVersion());
        assertEquals("Inclui melhorias de performance e correções de bugs.", release.getNotes());
        assertEquals(List.of("commit1", "commit2"), release.getCommits());
        assertEquals("testUser", release.getUser());
        assertEquals("userUpdater", release.getUserUpdate());
        assertNotNull(release.getReleasedAt());
    }

    @Test
    void shouldThrowExceptionWhenSystemIsNull() {
        ReleaseRequestDto releaseRequestDto = new ReleaseRequestDto(
                null,
                "v1.2.3",
                List.of("commit1", "commit2"),
                "Inclui melhorias de performance e correções de bugs.",
                "testUser");
        String userUpdate = "userUpdater";

        ReleaseCreateException exception = assertThrows(ReleaseCreateException.class, () -> Release.create(releaseRequestDto, userUpdate));
        assertTrue(exception.getMessage().contains("The 'system' field is required."));
    }

    @Test
    void shouldThrowExceptionWhenVersionIsNull() {
        ReleaseRequestDto releaseRequestDto = new ReleaseRequestDto(
                "ERP-Financeiro",
                null,
                List.of("commit1", "commit2"),
                "Inclui melhorias de performance e correções de bugs.",
                "testUser");
        String userUpdate = "userUpdater";

        ReleaseCreateException exception = assertThrows(ReleaseCreateException.class, () -> Release.create(releaseRequestDto, userUpdate));
        assertTrue(exception.getMessage().contains("The 'version' field is required."));
    }

    @Test
    void shouldThrowExceptionWhenCommitsAreEmpty() {
        ReleaseRequestDto releaseRequestDto = new ReleaseRequestDto(
                "ERP-Financeiro",
                "v1.2.3",
                List.of(),
                "Inclui melhorias de performance e correções de bugs.",
                "testUser");
        String userUpdate = "userUpdater";

        ReleaseCreateException exception = assertThrows(ReleaseCreateException.class, () -> Release.create(releaseRequestDto, userUpdate));
        assertTrue(exception.getMessage().contains("The 'commits' field is required."));
    }

    @Test
    void shouldThrowExceptionWhenUserIsEmpty() {
        ReleaseRequestDto releaseRequestDto = new ReleaseRequestDto(
                "ERP-Financeiro",
                "v1.2.3",
                List.of("commit1", "commit2"),
                "Inclui melhorias de performance e correções de bugs.",
                "");
        String userUpdate = "userUpdater";

        ReleaseCreateException exception = assertThrows(ReleaseCreateException.class, () -> Release.create(releaseRequestDto, userUpdate));
        assertTrue(exception.getMessage().contains("The 'user' field is required."));
    }

    @Test
    void shouldThrowExceptionWhenUserUpdateIsEmpty() {
        ReleaseRequestDto releaseRequestDto = new ReleaseRequestDto(
                "ERP-Financeiro",
                "v1.2.3",
                List.of("commit1", "commit2"),
                "Inclui melhorias de performance e correções de bugs.",
                "testUser");
        String userUpdate = "";

        ReleaseCreateException exception = assertThrows(ReleaseCreateException.class, () -> Release.create(releaseRequestDto, userUpdate));
        assertTrue(exception.getMessage().contains("The 'userUpdate' field is required."));
    }

    @Test
    void shouldTrimSystemFieldSuccessfullyDuringCreate() {
        ReleaseRequestDto releaseRequestDto = new ReleaseRequestDto(
                "  ERP-Financeiro  ",
                "v1.2.3",
                List.of("commit1", "commit2"),
                "Inclui melhorias de performance e correções de bugs.",
                "testUser");
        String userUpdate = "userUpdater";

        Release release = Release.create(releaseRequestDto, userUpdate);

        assertNotNull(release);
        assertEquals("ERP-Financeiro", release.getSystem());
    }
}