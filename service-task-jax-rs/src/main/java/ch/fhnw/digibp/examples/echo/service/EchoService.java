/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.echo.service;

import ch.fhnw.digibp.examples.echo.dto.EchoData;
import ch.fhnw.digibp.examples.echo.util.NameGenerator;
import org.springframework.stereotype.Service;

@Service
public class EchoService {
    public EchoData echo(EchoData echoData){
        echoData.setVariableA(echoData.getVariableA() + " with ECHO...");
        echoData.setVariableB(echoData.getVariableB() + " with ECHO...");
        return echoData;
    }

    public EchoData make(String businessKey, String processInstanceId){
        EchoData echoData = new EchoData();
        echoData.setBusinessKey(businessKey);
        echoData.setProcessInstanceId(processInstanceId);
        echoData.setVariableA(NameGenerator.getName());
        echoData.setVariableB(NameGenerator.getName());
        return echoData;
    }

    public EchoData make(String businessKey){
        return make(businessKey, "");
    }

    public EchoData make(){
        return make("", "");
    }

}
