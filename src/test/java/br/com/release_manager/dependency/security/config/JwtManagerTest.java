package br.com.release_manager.dependency.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class JwtManagerTest {

    @Value("${jwt.secret}")
    private String secretKey;

    @Test
    void shouldGenerateJwtWithValidClaims() {
        JwtManager jwtManager = new JwtManager();
        ReflectionTestUtils.setField(jwtManager, "secretKey", secretKey);

        OAuth2User oAuth2User = mock(OAuth2User.class);
        when(oAuth2User.getAttribute("sub")).thenReturn("12345");
        when(oAuth2User.getAttribute("email")).thenReturn("user@example.com");
        when(oAuth2User.getAttribute("name")).thenReturn("Test User");

        String token = jwtManager.generateJwt(oAuth2User);

        assertNotNull(token);
        assertTrue(token.startsWith("eyJ"));
    }

    @Test
    void shouldValidateJwtSuccessfully() {
        JwtManager jwtManager = new JwtManager();
        ReflectionTestUtils.setField(jwtManager, "secretKey", secretKey);

        String token = JWT.create()
                .withSubject("12345")
                .withClaim("email", "user@example.com")
                .withClaim("name", "Test User")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
                .sign(Algorithm.HMAC256(secretKey));

        assertTrue(jwtManager.validJwt("Bearer " + token));
    }

    @Test
    void shouldFailValidationForInvalidJwt() {
        JwtManager jwtManager = new JwtManager();
        ReflectionTestUtils.setField(jwtManager, "secretKey", secretKey);

        String invalidToken = "invalid.jwt.token";
        assertFalse(jwtManager.validJwt("Bearer " + invalidToken));
    }

    @Test
    void shouldFailValidationForExpiredJwt() {
        JwtManager jwtManager = new JwtManager();
        ReflectionTestUtils.setField(jwtManager, "secretKey", secretKey);

        String expiredToken = JWT.create()
                .withSubject("12345")
                .withClaim("email", "user@example.com")
                .withClaim("name", "Test User")
                .withIssuedAt(new Date(System.currentTimeMillis() - 7200 * 1000))
                .withExpiresAt(new Date(System.currentTimeMillis() - 3600 * 1000))
                .sign(Algorithm.HMAC256(secretKey));

        assertFalse(jwtManager.validJwt("Bearer " + expiredToken));
    }

    @Test
    void shouldExtractNameFromValidJwt() {
        JwtManager jwtManager = new JwtManager();
        ReflectionTestUtils.setField(jwtManager, "secretKey", secretKey);

        String token = JWT.create()
                .withSubject("12345")
                .withClaim("email", "user@example.com")
                .withClaim("name", "Test User")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
                .sign(Algorithm.HMAC256(secretKey));

        String name = jwtManager.getName("Bearer " + token);
        assertEquals("Test User", name);
    }

    @Test
    void shouldThrowExceptionWhenExtractingNameFromInvalidJwt() {
        JwtManager jwtManager = new JwtManager();
        ReflectionTestUtils.setField(jwtManager, "secretKey", secretKey);

        String invalidToken = "invalid.jwt.token";

        assertThrows(JWTVerificationException.class, () -> jwtManager.getName("Bearer " + invalidToken));
    }
}