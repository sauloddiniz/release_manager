package br.com.release_manager.dependency.security.config;

import br.com.release_manager.core.exceptions.JwtAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Collections;

@Configuration
public class CustomFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver resolver;
    private final JwtManager jwtManager;

    public CustomFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver,
                           JwtManager jwtManager) {
        this.resolver = resolver;
        this.jwtManager = jwtManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/release-manager/swagger-ui/")
                || requestURI.startsWith("/release-manager/v3/api-docs")
                || requestURI.equals("/release-manager/api-docs.yaml")
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = request.getHeader("Authorization");
            if (token == null || !jwtManager.validJwt(token)) {
                throw new JwtAuthenticationException("Invalid JWT");
            }

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(jwtManager.getName(token), token,
                            Collections.singletonList(new SimpleGrantedAuthority("SIMPLE_USER")));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);

        } catch (Exception exception) {
            resolver.resolveException(request, response, null, exception);
        }
    }
}
