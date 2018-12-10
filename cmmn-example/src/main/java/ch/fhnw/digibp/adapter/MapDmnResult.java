/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.digibp.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

import java.util.*;

public class MapDmnResult implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    List<String> risks = new ArrayList<String>();
    Set<String> risikobewertungen = new HashSet<String>();
    
    //DmnDecisionOutput vs DmnDecisionResult
    List<Map<String, Object>> resultList = (List<Map<String, Object>>) execution.getVariable("risikopruefung");
    for (Map<String, Object> result : resultList) {
      risks.add((String)result.get("risiko"));
      if (result.get("risikobewertung")!=null) {
        risikobewertungen.add(((String)result.get("risikobewertung")).toLowerCase());      
      }
    }

    String risikobewertung = "grün";
    if (risikobewertungen.contains("rot")) {
      risikobewertung = "rot";
    } else if (risikobewertungen.contains("gelb")) {
      risikobewertung = "gelb";
    }
    execution.setVariable("risks", Variables.objectValue(risks).serializationDataFormat(SerializationDataFormats.JSON).create());
    execution.setVariable("risikobewertung", risikobewertung);
  }

}
