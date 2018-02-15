/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.echo.dto;

import java.io.Serializable;

public class EchoData implements Serializable{
    private String variableA;
    private String variableB;
    private String businessKey;
    private String processInstanceId;

    public String getVariableA() {
        return variableA;
    }

    public void setVariableA(String variableA) {
        this.variableA = variableA;
    }

    public String getVariableB() {
        return variableB;
    }

    public void setVariableB(String variableB) {
        this.variableB = variableB;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
}
