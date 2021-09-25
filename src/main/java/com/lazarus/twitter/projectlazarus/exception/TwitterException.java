package com.lazarus.twitter.projectlazarus.exception;

/**
 * custom exception
 */
public class TwitterException extends Exception {

    public TwitterException() {
    }

    public TwitterException(String message) {
        super(message);
    }

    public TwitterException(String message, Throwable cause) {
        super(message, cause);
    }

    public TwitterException(Throwable cause) {
        super(cause);
    }
}
