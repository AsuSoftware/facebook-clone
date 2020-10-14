package com.asusoftware.facebookClone.exceptions;

public class NotFoundPostException extends RuntimeException {
    public NotFoundPostException() {
        super("Post not found");
    }
}
