/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.message;

import ch.fhnw.digibp.examples.api.ChoreographyEndpoints;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.ProcessEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@EnableBinding(Sink.class)
public class MessageListenersOrchestration {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private ProcessEngine processEngine;

    private static Logger logger = LoggerFactory.getLogger(ChoreographyEndpoints.class);

    @StreamListener(target = Sink.INPUT,
            condition="payload.messageType.toString()=='RetrievePayment'")
    @Transactional
    public void payment(String messageJson) throws IOException {
        Message<String> message = new ObjectMapper().readValue(messageJson, new TypeReference<Message<String>>(){});

        String payload = message.getPayload();
        logger.info("Payload received: "+payload+". Payment done. Payload sent: " + payload + ".");

        messageSender.send(new Message<String>("PaymentReceived", payload, message.getBusinessKey()));
    }

    @StreamListener(target = Sink.INPUT,
            condition="payload.messageType.toString()=='PaymentReceived'")
    @Transactional
    public void paymentReceived(String messageJson) throws IOException {
        Message<String> message = new ObjectMapper().readValue(messageJson, new TypeReference<Message<String>>(){});

        processEngine.getRuntimeService().createMessageCorrelation(message.getMessageType())
                .processInstanceBusinessKey(message.getBusinessKey())
                .setVariable("PAYLOAD_" + message.getMessageType(), message.getPayload()).correlateWithResult();
    }

    @StreamListener(target = Sink.INPUT,
            condition="payload.messageType.toString()=='FetchGoods'")
    @Transactional
    public void inventory(String messageJson) throws IOException {
        Message<String> message = new ObjectMapper().readValue(messageJson, new TypeReference<Message<String>>(){});

        String payload = message.getPayload();
        logger.info("Payload received: "+payload+". Inventory done. Payload sent: " + payload + ".");


        messageSender.send(new Message<String>("GoodsFetched", payload, message.getBusinessKey()));
    }

    @StreamListener(target = Sink.INPUT,
            condition="payload.messageType.toString()=='GoodsFetched'")
    @Transactional
    public void goodsFetched(String messageJson) throws IOException {
        Message<String> message = new ObjectMapper().readValue(messageJson, new TypeReference<Message<String>>(){});

        processEngine.getRuntimeService().createMessageCorrelation(message.getMessageType())
                .processInstanceBusinessKey(message.getBusinessKey())
                .setVariable("PAYLOAD_" + message.getMessageType(), message.getPayload()).correlateWithResult();
    }

    @StreamListener(target = Sink.INPUT,
            condition="payload.messageType.toString()=='ShipGoods'")
    @Transactional
    public void shipping(String messageJson) throws IOException {
        Message<String> message = new ObjectMapper().readValue(messageJson, new TypeReference<Message<String>>(){});

        String payload = message.getPayload();
        logger.info("Payload received: "+payload+". Shipping done. Payload sent: " + payload + ".");

        messageSender.send(new Message<String>("GoodsShipped", payload, message.getBusinessKey()));
    }

    @StreamListener(target = Sink.INPUT,
            condition="payload.messageType.toString()=='GoodsShipped'")
    @Transactional
    public void goodsShipped(String messageJson) throws IOException {
        Message<String> message = new ObjectMapper().readValue(messageJson, new TypeReference<Message<String>>(){});

        processEngine.getRuntimeService().createMessageCorrelation(message.getMessageType())
                .processInstanceBusinessKey(message.getBusinessKey())
                .setVariable("PAYLOAD_" + message.getMessageType(), message.getPayload()).correlateWithResult();
    }

}
