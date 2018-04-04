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
public class MessageOrchestrationOrderAdapter {

    private static Logger logger = LoggerFactory.getLogger(MessageOrchestrationOrderAdapter.class);

    @Autowired
    private MessageSender messageSender;

    public void retrievePayment(DelegateExecution execution){
        String payload = "UUID:" + UUID.randomUUID().toString();
        messageSender.send(new Message<String>("RetrievePayment", payload, execution.getBusinessKey()));
    }

    public void fetchGoods(DelegateExecution execution){
        String payload = "UUID:" + UUID.randomUUID().toString();
        messageSender.send(new Message<String>("FetchGoods", payload, execution.getBusinessKey()));
    }

    public void shipGoods(DelegateExecution execution){
        String payload = "UUID:" + UUID.randomUUID().toString();
        messageSender.send(new Message<String>("ShipGoods", payload, execution.getBusinessKey()));
    }

}
