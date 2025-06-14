package br.com.release_manager.core.exception;

public class ReleaseNotFoundException extends RuntimeException {

    public ReleaseNotFoundException(String string) {
        super(string);
    }

}
