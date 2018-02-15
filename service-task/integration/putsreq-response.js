/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

const parsedBody = JSON.parse(request.body);

// Build a response
if (parsedBody.variableA && parsedBody.variableB && parsedBody.businessKey) {
    response.status = 200;
    response.headers['Content-Type'] = 'application/json';
    response.body = {
        variableA: parsedBody.variableA + " ECHO!!!",
        variableB: parsedBody.variableB  + " ECHO!!!",
        businessKey: parsedBody.businessKey,
    };
} else {
    response.status = 404;
    response.headers = {};
    response.body = 'not found';
}