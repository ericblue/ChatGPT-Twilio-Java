package com.ericblue.chatgpt3twilio.util;

public class ConfigUtils {

    /**
     * Obfuscate API Key
     * @param apiKey API Key
     * @return Obfuscated API Key
     */
    public static String obfuscateApiKey(String apiKey) {

        int apiKeyLength = apiKey.length();
        int obfuscationLength = (int) Math.ceil(apiKeyLength / 2.0);
        String obfuscationString = "*".repeat(obfuscationLength);
        String obfuscatedApiKey = apiKey.substring(0, obfuscationLength) + obfuscationString + apiKey.substring(apiKeyLength - obfuscationLength);
        return obfuscatedApiKey;

    }

}
