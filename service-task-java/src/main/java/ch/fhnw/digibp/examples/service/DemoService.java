/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import javax.inject.Named;

@Named
public class DemoService {

    public void execute(DelegateExecution execution){
        execution.setVariable("executeData", "execute result: " + execution.getVariable("startData"));
    }

    public String executeResultVariable(String data){
        return "executeResultVariable result: " + data;
    }
}
