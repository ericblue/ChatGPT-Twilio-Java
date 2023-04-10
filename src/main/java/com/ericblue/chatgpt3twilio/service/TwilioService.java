package com.ericblue.chatgpt3twilio.service;

import com.ericblue.chatgpt3twilio.config.TwilioConfiguration;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


@Singleton
public class TwilioService {

    private static final Logger logger = LoggerFactory.getLogger(TwilioService.class);

    private final TwilioConfiguration twilioConfiguration;
    public TwilioService(@Nullable TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    public ArrayList<String> getvalidNumbers() {
        return twilioConfiguration.getValidNumbersAsList();
    }

    /**
     * Validate that the phone number is in the list of valid phone numbers.
     *  If no valid phone numbers are configured, all phone numbers are valid.
     */
    public boolean validatePhoneNumber(String phoneNumber) {
        ArrayList<String> validPhoneNumbers = this.getvalidNumbers();

        if (validPhoneNumbers.size() >=1) {

            logger.info("Valid phone numbers for Twilio: " + validPhoneNumbers);
            boolean valid = validPhoneNumbers.contains(phoneNumber);
            logger.info("Phone number " + phoneNumber + " is valid: " + valid);

            return valid;

        } else {
            logger.info("No phone numbers configured for Twilio. All phone numbers are valid.");
            return true;
        }
    }

}
