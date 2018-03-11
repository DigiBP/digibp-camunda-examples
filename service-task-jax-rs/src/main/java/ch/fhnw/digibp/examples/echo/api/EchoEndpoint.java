/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.echo.api;

import ch.fhnw.digibp.examples.echo.dto.EchoData;
import ch.fhnw.digibp.examples.echo.service.EchoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Service
@Path("/echo/v1")
@Api("/echo/v1")
public class EchoEndpoint {
    @Autowired
    private EchoService echoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EchoData echo(EchoData echoData) {
        return echoService.echo(echoData);
    }

    @GET
    @Path("/{businessKey}/{processInstanceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public EchoData make(@PathParam("businessKey") String businessKey, @PathParam("processInstanceId") String processInstanceId) {
        return echoService.make(businessKey, processInstanceId);
    }

    @GET
    @Path("/{businessKey}")
    @Produces(MediaType.APPLICATION_JSON)
    public EchoData make(@PathParam("businessKey") String businessKey) {
        return echoService.make(businessKey);
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public EchoData make() {
        return echoService.make();
    }
}
