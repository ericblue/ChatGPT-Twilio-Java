package com.ericblue.chatgpt3twilio.web;

import com.ericblue.chatgpt3twilio.exception.RestException;
import com.ericblue.chatgpt3twilio.service.ChatGPTService;
import com.ericblue.chatgpt3twilio.service.TwilioService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Controller("/sms")

/** This controller is responsible for receiving SMS messages from Twilio and sending them to the ChatGPTService */

public class SmsController {

    private static final Logger logger = LoggerFactory.getLogger(SmsController.class);

    @Inject
    ChatGPTService chatGPTService;

    @Inject
    TwilioService twilioService;


    /**
     * This method is called when Twilio sends an SMS message to the application.
     *  It validates the phone number and then sends the message to the ChatGPTService.
     *
     * @param requestParams Map of the request parameters
     * @return String response from the ChatGPTService
     *
     */
    @Post(consumes = MediaType.APPLICATION_FORM_URLENCODED,
            produces = MediaType.TEXT_PLAIN)
    public String processSMSMessage(@Body Map<String, String> requestParams) {

        String from = requestParams.get("From");
        String fromCity = requestParams.get("FromCity");
        String fromState = requestParams.get("FromState");
        String fromCountry = requestParams.get("FromCountry");
        String body = requestParams.get("Body");

        logger.info("Received SMS from " + from + " with body " + body);
        logger.info("Location: " + fromCity + ", " + fromState + ", " + fromCountry);

        if (!twilioService.validatePhoneNumber(from)) {
            logger.warn("Received SMS from invalid phone number: " + from);
            throw new RestException("Invalid phone number");
        }

        String response = chatGPTService.askQuestion(body);
        logger.debug("Response: " + response);

        return response;

    }
}


