/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

// Camunda HTTP Connector JavaScript emulation:
eval(pm.environment.get("camunda"));

// Camunda HTTP Connector YOUR Script:
var body = JSON.parse(connector.getVariable("response"));

// Postman Test:
pm.test("Test variableA: " + body.variableA, function() {
    pm.expect(body.variableA).to.include("ECHO");
});
pm.test("Test variableB: " + body.variableB, function() {
    pm.expect(body.variableB).to.include("ECHO");
});
pm.test("Test businessKey: " + body.businessKey, function() {
    pm.expect(body.businessKey).to.include("case-500");
});