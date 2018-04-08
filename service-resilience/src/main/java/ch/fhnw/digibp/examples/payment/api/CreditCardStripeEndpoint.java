/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.payment.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import java.util.UUID;
import java.util.concurrent.Callable;

@RestController
@RequestMapping(path = "/creditcard/v1")
public class CreditCardStripeEndpoint {

    private static Logger logger = LoggerFactory.getLogger(CreditCardStripeEndpoint.class);

    public static class ChargeRequest {
        public long amount;
    }

    public static class ChargeResponse {
        public String transactionId;
        public String errorCode;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/charge", produces = "application/json")
    public ChargeResponse chargeCreditCard(@RequestBody ChargeRequest chargeRequest) throws Exception {
        ChargeResponse response = new ChargeResponse();

        response.transactionId = UUID.randomUUID().toString();
        logger.info("Amount of " + chargeRequest.amount + " charged on credit card. Transaction Id: " + response.transactionId);

        return response;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/charge/wait", produces = "application/json")
    public Callable<ChargeResponse> chargeCreditCardWait(@RequestBody ChargeRequest chargeRequest) throws Exception {

        return () -> {
            ChargeResponse response = new ChargeResponse();

            long waitTimeMillis = Math.round(Math.random() * 20 * 1000); // up to 20 seconds
            logger.info("Charge on credit card will take " + waitTimeMillis / 1000 + " seconds");
            Thread.sleep(waitTimeMillis);

            response.transactionId = UUID.randomUUID().toString();
            logger.info("Amount of " + chargeRequest.amount + " charged on credit card. Transaction Id: " + response.transactionId);

            return response;
        };
    }

    @RequestMapping(method = RequestMethod.POST, path = "/charge/timeout", produces = "application/json")
    public Callable<ChargeResponse> chargeCreditCardTimeout(@RequestBody ChargeRequest chargeRequest) throws Exception {

        return () -> {
            ChargeResponse response = new ChargeResponse();

            long waitTimeMillis = 5 * 1000;

            if (Math.random() > 0.3d) {
                waitTimeMillis = Math.round(35 * 1000); // 35 seconds, default timeout after 30 seconds; default changeable in application.yaml by spring:mvc:async:request-timeout: 10000
            }

            logger.info("Charge on credit card will take " + waitTimeMillis / 1000 + " seconds");
            if((waitTimeMillis / 1000)>=10){ // timeout after 10 seconds
                Thread.sleep(10 * 1000);
                throw new AsyncRequestTimeoutException();
            }
            Thread.sleep(waitTimeMillis);

            response.transactionId = UUID.randomUUID().toString();
            logger.info("Amount of " + chargeRequest.amount + " charged on credit card. Transaction Id: " + response.transactionId);

            return response;
        };
    }

    @RequestMapping(method = RequestMethod.POST, path = "/charge/expired", produces = "application/json")
    public ChargeResponse chargeCreditCardExpired(@RequestBody ChargeRequest chargeRequest) throws Exception {

        ChargeResponse response = new ChargeResponse();

        if (Math.random() > 0.3d) {
            response.errorCode = "credit card expired";
            logger.info(response.errorCode);
            return response;
        }

        response.transactionId = UUID.randomUUID().toString();
        logger.info("Amount of " + chargeRequest.amount + " charged on credit card. Transaction Id: " + response.transactionId);

        return response;
    }
}
