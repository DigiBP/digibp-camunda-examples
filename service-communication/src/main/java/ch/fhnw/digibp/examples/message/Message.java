/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.message;

import java.util.Date;
import java.util.UUID;

public class Message<T> {

    private String messageType;
    private String id = UUID.randomUUID().toString();
    private String sender;
    private Date timestamp = new Date();
    private String businessKey;
    private T payload;

    public Message() {
    }

    public Message(String messageType, T payload) {
        this.messageType = messageType;
        this.payload = payload;
    }

    public Message(String messageType, T payload, String businessKey) {
        this.messageType = messageType;
        this.businessKey = businessKey;
        this.payload = payload;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }
}
