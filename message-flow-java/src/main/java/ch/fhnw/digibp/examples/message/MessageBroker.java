/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.message;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named
public class MessageBroker {

    @Inject
    private RuntimeService runtimeService;

    public void send(DelegateExecution execution, String messageName){
        runtimeService.createMessageCorrelation(messageName)
                .processInstanceBusinessKey(execution.getBusinessKey())
                .setVariables(execution.getVariables())
                .correlateWithResult();
    }

    public void send(DelegateExecution execution, String messageName, String...variableNames){
        Map<String, Object> variables = new HashMap<>();
        for (String variableName : variableNames) {
            variables.put(variableName, execution.getVariable(variableName));
        }
        runtimeService.createMessageCorrelation(messageName)
                .processInstanceBusinessKey(execution.getBusinessKey())
                .setVariables(variables)
                .correlateWithResult();
    }

    public void sendWithoutVariables(DelegateExecution execution, String messageName){
        runtimeService.createMessageCorrelation(messageName)
                .processInstanceBusinessKey(execution.getBusinessKey())
                .correlateWithResult();
    }

    public void sendWithoutVariablesAndBusinessKey(String messageName){
        runtimeService.createMessageCorrelation(messageName)
                .correlateWithResult();
    }
}