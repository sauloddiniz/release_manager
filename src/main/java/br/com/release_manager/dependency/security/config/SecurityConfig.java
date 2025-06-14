package br.com.release_manager.dependency.security.config;

import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final OAuth2AuthorizedClientService authorizedClientService;
    private final JwtManager jwtManager;

    public SecurityConfig(OAuth2AuthorizedClientService authorizedClientService, JwtManager jwtManager) {
        this.authorizedClientService = authorizedClientService;
        this.jwtManager = jwtManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> {
                        authorize.requestMatchers(
                                "/release-manager/swagger-ui/**",
                                "/release-manager/v3/api-docs/**",
                                "/release-manager/api-docs.yaml",
                                "/release-manager/auth/generate-token"
                        ).permitAll();
                        authorize.anyRequest().authenticated();
                    }
            )
            .oauth2Login(
                    oAuth2Configurer ->
                            oAuth2Configurer
                                    .successHandler((request, response, authentication) -> {
                                        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

                                        String jwt = jwtManager.generateJwt(oauthToken.getPrincipal());

                                        Cookie cookie = new Cookie("jwt", jwt);
                                        cookie.setHttpOnly(false);
                                        cookie.setPath("/");
                                        response.addCookie(cookie);

                                        response.sendRedirect("/release-manager/swagger-ui/index.html");
                                    })
            );
        return http.build();
    }
}