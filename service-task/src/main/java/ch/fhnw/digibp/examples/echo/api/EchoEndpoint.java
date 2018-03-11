/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.echo.api;

import ch.fhnw.digibp.examples.echo.dto.EchoData;
import ch.fhnw.digibp.examples.echo.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/echo/v1")
public class EchoEndpoint {
    @Autowired
    private EchoService echoService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public EchoData echo(EchoData echoData) {
        return echoService.echo(echoData);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{businessKey}/{processInstanceId}", produces = "application/json")
    public EchoData make(@PathVariable("businessKey") String businessKey, @PathVariable("processInstanceId") String processInstanceId) {
        return echoService.make(businessKey, processInstanceId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{businessKey}", produces = "application/json")
    public EchoData make(@PathVariable("businessKey") String businessKey) {
        return echoService.make(businessKey);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public EchoData make() {
        return echoService.make();
    }
}
