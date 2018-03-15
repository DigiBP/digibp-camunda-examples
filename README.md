# Camunda Examples

[![DigiBP GitHub](https://img.shields.io/badge/DigiBP-GitHub-lightgrey.svg?longCache=true)](https://github.com/DigiBP)
[![DigiBP Portal](https://img.shields.io/badge/DigiBP-Portal-brightgreen.svg?longCache=true)](https://digibp.github.io)
[![DigiBP Wiki](https://img.shields.io/badge/DigiBP-Wiki-yellow.svg?longCache=true)](https://github.com/DigiBP/digibp.github.io/wiki)
[![DigiBP Camunda Template](https://img.shields.io/badge/DigiBP-Camunda%20Template-red.svg?longCache=true)](https://github.com/DigiBP/digibp-camunda-template)
[![DigiBP Camunda Examples](https://img.shields.io/badge/DigiBP-Camunda%20Examples-blue.svg?longCache=true)](https://github.com/DigiBP/digibp-camunda-examples)

## Examples

| Scenario | Image |
|--- | --- |
| **Model References**<br><br>[model-references](model-references) shows how models such as BPMN, DMN and CMMN can be linked.<br><br>✔ Business Rule Task<br>✔ Call Activity | [![](model-references/images/model-references-to-dmn.png)](model-references) |

#### Sub-Module Heroku Deployment

Since this repository consists of several sub-modules and a Maven parent, the Heroku GitHub deployment would be slightly different:
1. Fork this repository
2. Connect your Heroku-Account to your GitHub-Account
3. Create a new app and choose under *Deploy* the forked repository
4. Add the following two **Config Vars** under *Settings*:
   1. `MAVEN_CUSTOM_OPTS` = `-pl xyz-submodule-path`
   2. `PATH_TO_PROJECT` = `xyz-submodule-path`
5. Deploy the app