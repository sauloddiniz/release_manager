package br.com.release_manager.core.exceptions;

public class JwtAuthenticationException extends RuntimeException {

    public JwtAuthenticationException(String string) {
        super(string);
    }
}
