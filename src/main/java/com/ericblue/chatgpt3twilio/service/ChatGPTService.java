package com.ericblue.chatgpt3twilio.service;

import com.ericblue.chatgpt3twilio.config.ChatGPTConfiguration;
import com.plexpt.chatgpt.ChatGPT;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.runtime.context.scope.ThreadLocal;



/**
 * This class is a wrapper for the ChatGPT client
 */

@ThreadLocal
public class ChatGPTService {

    private ChatGPT chatGPT;

    private final ChatGPTConfiguration chatGPTConfiguration;

    /**
     * Constructor
     * @param chatGPTConfiguration ChatGPT configuration containing the API key
     */
    public ChatGPTService(@Nullable ChatGPTConfiguration chatGPTConfiguration) {
        this.chatGPTConfiguration = chatGPTConfiguration;

        // Initialize ChatGPT client
        chatGPT = ChatGPT.builder()
                .apiKey(chatGPTConfiguration.getApiKey())
                .timeout(900)
                .apiHost("https://api.openai.com/") //Reverse proxy address
                .build()
                .init();

    }

    public ChatGPTConfiguration getChatGPTConfiguration() {
        return chatGPTConfiguration;
    }

    public String askQuestion(String question) {

        return chatGPT.chat(question);

    }

}
