package br.com.release_manager.dependency.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ContextManagerAdapter implements ContextManagerPort {


    @Override
    public String getName() {
       return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
