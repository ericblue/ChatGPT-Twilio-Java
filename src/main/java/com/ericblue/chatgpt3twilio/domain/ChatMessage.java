package com.ericblue.chatgpt3twilio.domain;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class ChatMessage {

    private String response;

    public ChatMessage () {
    }
    public ChatMessage(String response) {
        this.response = response;
    }
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
