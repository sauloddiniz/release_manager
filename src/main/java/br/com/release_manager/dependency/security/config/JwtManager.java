package br.com.release_manager.dependency.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class JwtManager {

    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String SUB = "sub";
    @Value("${jwt.secret}")
    private String secretKey;

    public String generateJwt(OAuth2User oAuth2User) {
        return JWT.create()
                .withSubject(oAuth2User.getAttribute(SUB))
                .withClaim(EMAIL, Objects.requireNonNull(oAuth2User.getAttribute(EMAIL)).toString())
                .withClaim(NAME, Objects.requireNonNull(oAuth2User.getAttribute(NAME)).toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
                .sign(Algorithm.HMAC256(secretKey));
    }

    public boolean validJwt(String token) {
        try {
            token = token.substring(7);
            JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getName(String token) {
        DecodedJWT decodedJWT = decode(token);
        return decodedJWT.getClaim(NAME).asString();
    }

    private DecodedJWT decode(String token) {
        token = token.substring(7);
        return JWT.decode(token);
    }

}