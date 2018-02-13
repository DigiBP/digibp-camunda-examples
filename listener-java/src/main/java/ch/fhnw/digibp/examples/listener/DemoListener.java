/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.examples.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

@Named
public class DemoListener {

    private static final Logger log = LoggerFactory.getLogger(DemoListener.class);

    public void executionStart(){
        log.info("Execution Start called!");
    }

    public void executionEnd(){
        log.info("Execution End called!");
    }

    public void taskCreate(){
        log.info("Task Create called!");
    }

    public void taskAssignment(){
        log.info("Task Assignment called!");
    }

    public void taskComplete(){
        log.info("Task Complete called!");
    }

    public void taskDelete(){
        log.info("Task Delete called!");
    }

}
