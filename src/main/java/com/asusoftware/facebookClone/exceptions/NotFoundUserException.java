package com.asusoftware.facebookClone.exceptions;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException() {
        super("User not found");
    }
}
