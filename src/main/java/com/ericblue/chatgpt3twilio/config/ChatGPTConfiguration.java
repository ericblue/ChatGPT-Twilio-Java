package com.ericblue.chatgpt3twilio.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.serde.annotation.Serdeable;

/**
 * Configuration for ChatGPT - Reads variable info from application.yaml, sourced by ENV variables
 */
@Serdeable
@ConfigurationProperties("chatgpt")
public class ChatGPTConfiguration {

    String apiKey;
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
