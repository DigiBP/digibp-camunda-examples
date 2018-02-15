/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

// Camunda HTTP Connector JavaScript emulation:
eval(pm.environment.get("camunda"));

// Camunda set process variables, process id and business key:
execution.setVariable("processVariableA", "Data A");
execution.setVariable("processVariableB", "Data B");
execution.setProcessInstanceId(100);
execution.setProcessBusinessKey("case-500");

// Camunda HTTP Connector YOUR Script:
var out = JSON.stringify({
    "variableA": execution.getVariable("processVariableA"),
    "variableB": execution.getVariable("processVariableB"),
    "businessKey": execution.getProcessBusinessKey()
});

// Postman Body {{payload}}:
payload.set(out);