# Example: Model References

[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Deploy to Heroku](https://img.shields.io/badge/deploy%20to-Heroku-6762a6.svg?longCache=true)](https://heroku.com/deploy)
[![DigiBP Camunda Examples](https://img.shields.io/badge/DigiBP-Camunda%20Examples-blue.svg?longCache=true)](https://github.com/DigiBP/digibp-camunda-examples)

This project shows how models such as BPMN, DMN and CMMN can be linked.

✔ Business Rule Task<br>✔ Call Activity

**Contents:**

- [BPMN linking DMN](#bpmn-linking-dmn)
    - [Scenario](#scenario)
    - [Modelling of DRD and Decision Tables](#modelling-of-drd-and-decision-tables)
    - [Modelling and Linking of BPMN and DMN](#modelling-and-linking-of-bpmn-and-dmn)
- [BPMN calling BPMN](#bpmn-calling-bpmn)
    - [Called BPMN](#called-bpmn)
    - [Caller BPMN](#caller-bpmn)
- [BPMN calling CMMN](#bpmn-calling-cmmn)
    - [Called CMMN](#called-cmmn)
    - [Caller BPMN](#caller-bpmn)
- [Maintainer](#maintainer)
- [License](#license)

## BPMN linking DMN

The following example shows how a DMN model, containing a DRD and two decision tables, can be called a business rule task.

### Scenario

We have a fictive process where an overall grade needs to be assessed, and a decision is made whether the overall grade is sufficient or not. The assessment will be done using two grades A and B, where B consists of two sub-grades part 1 and 2. All grades are based on the Swiss grading scheme.

[![](images/model-references-to-dmn.png)](images/model-references-to-dmn.png)

### Modelling of DRD and Decision Tables

The scenario mentioned above is realized using two decision tables. The association is modelled using DRD:

[![](images/2018-03-14_21h43_22.png)](images/2018-03-14_21h43_22.png)

Decision table one is used to assess the grade B is the preceding table before assessing the overall result:

[![](images/2018-03-14_21h43_02.png)](images/2018-03-14_21h43_02.png)

> Make sure that the variable **names** and **data types** are **consistent** with possible workflow variables (or form fields).

Decision table two does the overall assessment and takes the output (variable `gradeB`) of the preceding decision table as an input:
 
[![](images/2018-03-14_21h43_12.png)](images/2018-03-14_21h43_12.png)

> Make sure that the variable **names** and **data types** are **consistent** with the **preceding decision table** or possible workflow variables (or form fields).

### Modelling and Linking of BPMN and DMN

You may start your process with some form data. In this case, there are variables (input fields) for assessing the grades:

[![](images/2018-03-15_09h00_40.png)](images/2018-03-15_09h00_40.png)

Embed the DMN with a business rule task and reference the overall decision table by `id`. A result variable can be defined, which is, in this case, a single result, to make use of the result (decision recommendation):

[![](images/2018-03-15_09h01_34.png)](images/2018-03-15_09h01_34.png)

Further, you may want to use the decision result variable at a gateway:

[![](images/2018-03-15_09h02_41.png)](images/2018-03-15_09h02_41.png)

And finally, you may want to use the decision result variable in a form as well:

[![](images/2018-03-15_09h03_30.png)](images/2018-03-15_09h03_30.png)

## BPMN calling BPMN

The following example shows how a BPMN model can be called by a call activity.

### Called BPMN

First, we define an exemplary process that will be called from another BPMN process:

[![](images/2018-03-27_17h15_29.png)](images/2018-03-27_17h15_29.png)

You may use (from the caller) and provide some data (from the called process):

[![](images/2018-03-15_11h42_58.png)](images/2018-03-15_11h42_58.png)

### Caller BPMN

Then we can define the caller process, and we may collect some data of the caller process:

[![](images/2018-03-15_11h41_42.png)](images/2018-03-15_11h41_42.png)

#### Call Activity to BPMN

We can use the call activity to link and call another BPMN model by the to-be-called process id:

[![](images/2018-03-15_11h42_08.png)](images/2018-03-15_11h42_08.png)

Besides, you may pass over the data to the to-be-called process and map the resulting variables back to the caller process:

[![](images/2018-03-15_11h42_35.png)](images/2018-03-15_11h42_35.png)

Finally, you maybe want to present the exchanged/processed data to the user:

[![](images/2018-03-15_11h43_32.png)](images/2018-03-15_11h43_32.png)

## BPMN calling CMMN

The following example shows how a CMMN model can be called by a call activity.

### Called CMMN

First, we define an exemplary case that will be called from a BPMN process:

[![](images/2018-03-27_18h40_44.png)](images/2018-03-27_18h40_44.png)

#### CMMN calling BPMN

In [this called CMMN case](#called-cmmn) it is shown how a BPMN process can be called using a process task:

[![](images/2018-03-27_18h50_15.png)](images/2018-03-27_18h50_15.png)

> You may want to transfer the case [variables](#call-activity-to-cmmn) to the to-be-called process.

#### CMMN calling CMMN

Besides, in [this called CMMN case](#called-cmmn) it is shown how a further CMMN model can be called using a case task:

[![](images/2018-03-27_18h40_06.png)](images/2018-03-27_18h40_06.png)

> You may want to transfer the case [variables](#call-activity-to-cmmn) to the to-be-called case as well.

Reference the to-be-called CMMN model by case id:

[![](images/2018-03-27_18h41_11.png)](images/2018-03-27_18h41_11.png)

### Caller BPMN

Finally, we can define the caller process.

#### Call Activity to CMMN

We can use the call activity to link and call the CMMN model by the to-be-called case id:

[![](images/2018-03-27_18h38_40.png)](images/2018-03-27_18h38_40.png)

You may want to transfer the process variables to the to-be-called case as well:

[![](images/2018-03-27_18h39_15.png)](images/2018-03-27_18h39_15.png)

## Maintainer
- [Digitalisation of Business Processes](https://github.com/digibp)

## License

- [Apache License, Version 2.0](https://github.com/DigiBP/digibp-archetype-camunda-boot/blob/master/LICENSE)