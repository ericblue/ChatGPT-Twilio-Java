package com.ericblue.chatgpt3twilio.web;

import com.ericblue.chatgpt3twilio.config.ChatGPTConfiguration;
import com.ericblue.chatgpt3twilio.domain.ChatMessage;
import com.ericblue.chatgpt3twilio.exception.RestException;
import com.ericblue.chatgpt3twilio.service.ChatGPTService;
import com.ericblue.chatgpt3twilio.util.ConfigUtils;
import com.github.jknack.handlebars.internal.lang3.StringUtils;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import jakarta.inject.Inject;

/**
 * This controller is responsible for returning the ChatGPT configuration
 * and sending messages to the ChatGPTService
 *
 * Note: This is primarily for testing purposes. All ChatGPT functionality is
 * abstracted and called within the SmsController.
 */
@Controller("/chatgpt")
public class ChatGPTController {

    @Inject
    ChatGPTService chatGPTService;

    /** This method returns the ChatGPT configuration */
    @Get("/config")
    public ChatGPTConfiguration getChatGPTConfiguration() {

        if ( (chatGPTService.getChatGPTConfiguration().getApiKey() == null)) {
            throw new RestException("chatGPTConfiguration is empty. CHATGPT_API_KEY environment variable is not set.");
        }

        else {
            ChatGPTConfiguration chatGPTConfiguration = chatGPTService.getChatGPTConfiguration();
            chatGPTConfiguration.setApiKey(ConfigUtils.obfuscateApiKey(chatGPTConfiguration.getApiKey()));

            return chatGPTConfiguration;

        }

    }

    /**
     * This method is called when the user sends a message to the ChatGPTService
     ** @param message Message sent by the user
     * @return ChatMessage response from the ChatGPTService
     */

    @Get("/chat")
    public ChatMessage sentChatMessage(@QueryValue("message") String message) {

        if (!(StringUtils.isEmpty(message))) {
            String response = chatGPTService.askQuestion(message);
            return new ChatMessage(response);
        } else {
            throw new RestException("message is empty");
        }

    }


}
