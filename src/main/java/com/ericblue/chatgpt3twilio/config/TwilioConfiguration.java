package com.ericblue.chatgpt3twilio.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.util.StringUtils;
import io.micronaut.serde.annotation.Serdeable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Configuration for Twilio - Reads variable info from application.yaml, sourced by ENV variables
 */
@Serdeable
@ConfigurationProperties("twilio")
public class TwilioConfiguration {

    String validNumbers;

    public String getValidNumbers() {
        return validNumbers;
    }

    public ArrayList<String> getValidNumbersAsList() {

        if (!StringUtils.isEmpty(validNumbers)) {
            return new ArrayList<String>(Arrays.asList(validNumbers.split(",")));
        } else {
            return new ArrayList<String>();
        }


    }
}
