package com.ericblue.chatgpt3twilio.exception;

import java.io.Serializable;

/** Exception class for REST API */
public class RestException extends RuntimeException  implements Serializable {
    private static final long serialVersionUID = 1L;

    public RestException() {
    }

    public RestException(String message) {
        super(message);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestException(Throwable cause) {
        super(cause);
    }

    public RestException(String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
