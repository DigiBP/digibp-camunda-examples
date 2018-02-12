/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class DemoServiceDelegate implements JavaDelegate{

    public void execute(DelegateExecution execution){
        execution.setVariable("delegateExecuteData", "delegate execute result: " + execution.getVariable("startData"));
    }
}
