/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.message;

import java.io.IOException;

import ch.fhnw.digibp.examples.api.ChoreographyEndpoints;
import ch.fhnw.digibp.examples.message.Message;
import ch.fhnw.digibp.examples.message.MessageSender;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@EnableBinding(Sink.class)
public class MessageListenersChoreography {

    @Autowired
    private MessageSender messageSender;

    private static Logger logger = LoggerFactory.getLogger(ChoreographyEndpoints.class);

    @StreamListener(target = Sink.INPUT,
            condition="payload.messageType.toString()=='CheckoutDone'")
    @Transactional
    public void payment(String messageJson) throws IOException {
        Message<String> message = new ObjectMapper().readValue(messageJson, new TypeReference<Message<String>>(){});

        String payload = message.getPayload();
        logger.info("Payload received: "+payload+". Payment done. Payload sent: " + payload + ".");

        messageSender.send(new Message<String>("PaymentDone", payload));
    }

    @StreamListener(target = Sink.INPUT,
            condition="payload.messageType.toString()=='PaymentDone'")
    @Transactional
    public void inventory(String messageJson) throws IOException {
        Message<String> message = new ObjectMapper().readValue(messageJson, new TypeReference<Message<String>>(){});

        String payload = message.getPayload();
        logger.info("Payload received: "+payload+". Inventory done. Payload sent: " + payload + ".");


        messageSender.send(new Message<String>("InventoryDone", payload));
    }

    @StreamListener(target = Sink.INPUT,
            condition="payload.messageType.toString()=='InventoryDone'")
    @Transactional
    public void shipping(String messageJson) throws IOException {
        Message<String> message = new ObjectMapper().readValue(messageJson, new TypeReference<Message<String>>(){});

        String payload = message.getPayload();
        logger.info("Payload received: "+payload+". Shipping done.");

    }

}
