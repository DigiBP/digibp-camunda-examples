/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;

@RestController
@RequestMapping(path = "/choreography/v1")
public class ChoreographyEndpoints {

    private static Logger logger = LoggerFactory.getLogger(ChoreographyEndpoints.class);

    public static class Request {
        public String payload;
    }

    public static class Response {
        public String payload;
    }

    @Autowired
    private RestTemplate rest;

    @RequestMapping(method = RequestMethod.POST, path = "/payment", produces = "application/json")
    public Response payment(@RequestBody Request request) {
        Response response = new Response();

        response.payload = "Payment UUID:" + UUID.randomUUID().toString();
        logger.info("Payload received: "+request.payload+". Payment done. Payload sent: " + response.payload + ".");

        return rest.postForObject("http://localhost:8080/choreography/v1/inventory", response, Response.class);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/inventory", produces = "application/json")
    public Response inventory(@RequestBody Request request) {
        Response response = new Response();

        response.payload = "Inventory UUID:" + UUID.randomUUID().toString();
        logger.info("Payload received: "+request.payload+". Inventory done. Payload sent: " + response.payload + ".");

        return rest.postForObject("http://localhost:8080/choreography/v1/shipping", response, Response.class);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/shipping", produces = "application/json")
    public Response shipping(@RequestBody Request request) {
        Response response = new Response();

        response.payload = "Shipping UUID:" + UUID.randomUUID().toString();
        logger.info("Payload received: "+request.payload+". Shipping done. Payload sent: " + response.payload + ".");

        return response;
    }


}
