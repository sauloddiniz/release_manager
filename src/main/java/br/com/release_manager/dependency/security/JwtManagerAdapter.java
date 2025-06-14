package br.com.release_manager.dependency.security;

import br.com.release_manager.dependency.security.config.JwtManager;
import org.springframework.security.oauth2.jwt.JwtEncodingException;
import org.springframework.stereotype.Component;

@Component
public class JwtManagerAdapter implements JwtManagerPort{

    private final JwtManager jwtManager;

    public JwtManagerAdapter(JwtManager jwtManager) {
        this.jwtManager = jwtManager;
    }

    @Override
    public String getName(String token) {
        if (!jwtManager.validJwt(token)) {
            throw new JwtEncodingException("Invalid JWT");
        }
        return jwtManager.getName(token);
    }
}
