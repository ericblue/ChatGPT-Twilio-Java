package com.ericblue.chatgpt3twilio.web;

import com.ericblue.chatgpt3twilio.exception.RestException;
import com.ericblue.chatgpt3twilio.service.ChatGPTService;
import com.ericblue.chatgpt3twilio.service.TwilioService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;
import com.twilio.twiml.voice.Record;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;

import java.net.URI;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Map;



/** This controller is responsible for receiving Voice calls from Twilio
 * converting speech to text and sending them to the ChatGPTService.
 * Responses are then converted back from text to speech
 */

@Controller("/voice")
public class VoiceController {

    private static final Logger logger = LoggerFactory.getLogger(VoiceController.class);

    @Inject
    ChatGPTService chatGPTService;

    @Inject
    TwilioService twilioService;


    @Post(consumes = MediaType.APPLICATION_FORM_URLENCODED,
            produces = MediaType.TEXT_XML)
    public String processVoiceMessage(@Body Map<String, String> requestParams) {

        String from = requestParams.get("From");
        String fromCity = requestParams.get("FromCity");
        String fromState = requestParams.get("FromState");
        String fromCountry = requestParams.get("FromCountry");

        logger.info("Received Voice call from " + from);
        logger.info("Location: " + fromCity + ", " + fromState + ", " + fromCountry);

        // TODO Update gather to provide further interaction, transcription and voice responses

        return new VoiceResponse.Builder()
                .say(
                    new Say.Builder("Hello from Chat GPT.  Please ask your question and I'll do my best to answer.").build()
                ).build().toXml();

    }

}


