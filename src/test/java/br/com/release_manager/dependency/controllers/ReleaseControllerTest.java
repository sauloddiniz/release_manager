package br.com.release_manager.dependency.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ReleaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setupSecurityContext() {
        Authentication auth = new UsernamePasswordAuthenticationToken("fake user", null, List.of());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    void testSaveReleaseSuccess() throws Exception {

        String jsonPost = """
                {
                  "system": "ERP-Financeiro",
                  "version": "v1.2.3",
                  "commits": [
                    "['commit1', 'commit2', 'commit3']"
                  ],
                  "notes": "Inclui melhorias de performance e correções de bugs.",
                  "user": "string"
                }
                """;

        mockMvc.perform(post("/releases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "fakeToken")
                        .content(jsonPost))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Release criada com sucesso."))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testPageableRelease() throws Exception {
        mockMvc.perform(get("/releases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "fakeToken")
                        .param("page", "0")
                        .param("total_page", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testFindById() throws Exception {
        mockMvc.perform(get("/releases/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "fakeToken"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.message").value("Release listado com sucesso."))
                .andExpect(jsonPath("$.version").value("1.0.0"))
                .andExpect(jsonPath("$.system").value("ERP"));
    }

    @Test
    void testFindByIdReleaseNotFound() throws Exception {
        mockMvc.perform(get("/releases/{id}",15)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "fakeToken"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteExistingRelease() throws Exception {
        mockMvc.perform(delete("/releases/{id}",4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "fakeToken"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Release deletado com sucesso."));
    }

    @Test
    void testUpdateNotes() throws Exception {

        String jsonPatch = """
                    {
                     "notes": "new note"
                    }
                """;

        mockMvc.perform(patch("/releases/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "fakeToken")
                        .content(jsonPatch))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Release atualizado com sucesso."));
    }

}