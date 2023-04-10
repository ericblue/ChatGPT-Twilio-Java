package com.ericblue.chatgpt3twilio.web.handler;

import com.ericblue.chatgpt3twilio.exception.RestException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = { RestExceptionHandler.class, ExceptionHandler.class })
public class RestExceptionHandler implements ExceptionHandler<RestException, HttpResponse<ErrorMessage>> {
    @Override
    public HttpResponse<ErrorMessage>
    handle(HttpRequest request, RestException exception) {

        ErrorMessage message = new ErrorMessage();
        message.setMessage(exception.getMessage());
        message.setStatus(false);
        return HttpResponse.serverError(message).
                status(HttpStatus.BAD_REQUEST);
    }
}
