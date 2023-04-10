package com.ericblue.chatgpt3twilio.web.handler;


import io.micronaut.serde.annotation.Serdeable;

@Serdeable.Serializable
public class ErrorMessage  {

    private String message;
    private Boolean status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
