/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/orchestration/v1")
public class OrchestrationEndpoints {

    private static Logger logger = LoggerFactory.getLogger(OrchestrationEndpoints.class);

    public static class Request {
        public String payload;
    }

    public static class Response {
        public String payload;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/payment", produces = "application/json")
    public Response payment(@RequestBody Request request) {
        Response response = new Response();

        response.payload = "Payment UUID:" + UUID.randomUUID().toString();
        logger.info("Payload received: "+request.payload+". Payment done. Payload sent: " + response.payload + ".");

        return response;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/inventory", produces = "application/json")
    public Response inventory(@RequestBody Request request) {
        Response response = new Response();

        response.payload = "Inventory UUID:" + UUID.randomUUID().toString();
        logger.info("Payload received: "+request.payload+". Inventory done. Payload sent: " + response.payload + ".");

        return response;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/shipping", produces = "application/json")
    public Response shipping(@RequestBody Request request) {
        Response response = new Response();

        response.payload = "Shipping UUID:" + UUID.randomUUID().toString();
        logger.info("Payload received: "+request.payload+". Shipping done. Payload sent: " + response.payload + ".");

        return response;
    }


}
