package br.com.release_manager.dependency.security.adapter;

import br.com.release_manager.dependency.security.ContextManagerPort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ContextManagerAdapter implements ContextManagerPort {


    @Override
    public String getName() {
       return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
