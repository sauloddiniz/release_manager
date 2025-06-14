package br.com.release_manager.core.exceptions;

public class ReleaseNotFoundException extends RuntimeException {

    public ReleaseNotFoundException(String string) {
        super(string);
    }

}
