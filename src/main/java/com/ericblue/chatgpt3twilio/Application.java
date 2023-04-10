package com.ericblue.chatgpt3twilio;

import io.micronaut.core.util.StringUtils;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


@OpenAPIDefinition(
    info = @Info(
            title = "chatgpt3twilio",
            version = "0.1"
    )
)
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        String chatGPTApiKey= System.getenv("CHATGPT_API_KEY");
        if ( StringUtils.isEmpty(chatGPTApiKey)) {
            throw new RuntimeException("CHATGPT_API_KEY environment variable not set");
        }
        
        logger.info("Starting Micronaut application");
        Micronaut.run(Application.class, args);
    }
}