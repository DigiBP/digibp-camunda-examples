/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.service;

import ch.fhnw.digibp.examples.message.Message;
import ch.fhnw.digibp.examples.message.MessageSender;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.util.UUID;

@Named
public class CheckoutAdapter {

    private static Logger logger = LoggerFactory.getLogger(CheckoutAdapter.class);

    public static class Request {
        public String payload;
    }

    public static class Response {
        public String payload;
    }

    @Autowired
    private RestTemplate rest;

    @Autowired
    private MessageSender messageSender;

    public void checkoutChoreography(DelegateExecution execution){
        Response response = new Response();

        response.payload = "Checkout UUID:" + UUID.randomUUID().toString();
        logger.info("Checkout done. Payload sent: " + response.payload + ".");

        response = rest.postForObject("http://localhost:8080/choreography/v1/payment", response, Response.class);
        execution.setVariable("response", response.payload);
    }

    public void checkoutMessageChoreography(DelegateExecution execution){
        String payload = "Checkout UUID:" + UUID.randomUUID().toString();
        logger.info("Checkout done. Payload sent: " + payload + ".");

        messageSender.send(new Message<String>("CheckoutDone", payload));
    }

}
