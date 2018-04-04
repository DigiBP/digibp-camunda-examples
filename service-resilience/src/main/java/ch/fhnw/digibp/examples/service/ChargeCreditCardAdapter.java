/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;

@Named
public class ChargeCreditCardAdapter {

    private static Logger logger = LoggerFactory.getLogger(ChargeCreditCardAdapter.class);


    @Autowired
    private RestTemplate rest;
    private String chargeWaitUrl = "http://localhost:8080/creditcard/v1/charge/wait";

    public static class ChargeRequest {
        public long amount;
    }

    public static class ChargeResponse {
        public String transactionId;
        public String errorCode;
    }

    public void chargeWithCircuitBreaker(DelegateExecution execution){
        ChargeRequest request = new ChargeRequest();
        request.amount = Long.parseLong((String) execution.getVariable("amount"));

        ChargeResponse response = new HystrixCommand<ChargeResponse>(HystrixCommandGroupKey.Factory.asKey("chargeWait"), 5 * 1000) {
            protected ChargeResponse run() throws Exception {
                return rest.postForObject(chargeWaitUrl,request,ChargeResponse.class);
            }
        }.execute();

        execution.setVariable("paymentTransactionId", response.transactionId);
    }

    public void customerCredit(DelegateExecution execution) {
        ChargeRequest request = new ChargeRequest();
        request.amount = Long.parseLong((String) execution.getVariable("amount"));

        request.amount = request.amount / 2;

        logger.info("Amount of " + request.amount + " left.");

        execution.setVariable("amount", request.amount);
    }

    public void customerCreditRefund(DelegateExecution execution) {
        logger.info("Customer credit refunded.");
    }
}
