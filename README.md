[![Build Status](https://travis-ci.org/assertthat/assertthat-bdd-maven-plugin.svg?branch=master)](https://travis-ci.org/assertthat/assertthat-bdd-gradle-tasks)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.assertthat.plugins/assertthat-bdd-gradle-tasks/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.assertthat.plugins/assertthat-bdd-gradle-tasks)

## Description

Gradle plugin for interaction with [AssertThat BDD Jira plugin](https://marketplace.atlassian.com/apps/1219033/assertthat-bdd-test-management-in-jira?hosting=cloud&tab=overview).

Main features are:

- Download feature files before test run
- Filter features to download based on mode (automated/manual/both), or/and JQL
- Upload cucumber json after the run to AsserTthat Jira plugin

## Installation

Full plugin configuration below, optional properties can be omitted

```groovy
buildscript {
    dependencies{
        classpath group: 'com.assertthat.plugins', name: 'assertthat-bdd-gradle-tasks', version: '1.7'
    }
}

task downloadFeatures(type: FeaturesTask){
    /*Jira project id e.g. 10001*/
    projectId ="PROJECT_ID"
    /*Optional can be supplied as environment variable ASSERTTHAT_ACCESS_KEY*/
    accessKey = "ASSERTTHAT_ACCESS_KEY"
    /*Optional can be supplied as environment variable ASSERTTHAT_SECRET_KEY*/
    secretKey = "ASSERTTHAT_SECRET_KEY"
    /*Used for jira server integration only. If using cloud remove this option*/
    jiraServerUrl = "http://mycompanyjira.com"
    /*Optional - default ./features*/
    outputFolder = "src/test/resources"
    /*Optional - all features downloaded by default - should be a valid JQL*/
    jql = "project = XX AND key in ('XXX-1')"
    /*Optional - default automated (can be one of: manual/automated/both)*/
    mode = "automated"
    /*Optional - tag expression filter for scenarios. More on tag expressions https://cucumber.io/docs/cucumber/api/#tag-expressions*/
    tags = "(@smoke or @ui) and (not @slow)"
    /*Optional - the value MUST be an instance of {@link String} or {@link java.net.URI}.*/
    proxyURI = "myproxy:8080"
    /*Optional - user name which will be used for proxy authentication.*/
    proxyUsername = "username"
    /*Optional - password which will be used for proxy authentication.*/
    proxyPassword = "password"
    /*Optional - prepend ordinal to feature name (default is true)*/   
    numbered = false

}

task submitReport(type: ReportTask){
    /*Jira project id e.g. 10001*/
    projectId ="PROJECT_ID"
    /*Optional can be supplied as environment variable ASSERTTHAT_ACCESS_KEY*/
    accessKey = "ASSERTTHAT_ACCESS_KEY"
    /*Optional can be supplied as environment variable ASSERTTHAT_SECRET_KEY*/
    secretKey = "ASSERTTHAT_SECRET_KEY"
    /*Used for jira server integration only. If using cloud remove this option*/
    jiraServerUrl = "http://mycompanyjira.com"    
    /*The name of the run - default 'Test run dd MMM yyyy HH:mm:ss'*/
    runName = "Dry Tests Run"
    /*Json report folder - default ./reports*/
    jsonReportFolder = "reports"
    /*Regex to search for cucumber reports - default **.json*/
    jsonReportIncludePattern = "**/cucumber.json"
    /*Optional - the value MUST be an instance of {@link String} or {@link java.net.URI}.*/
    proxyURI = "myproxy:8080"
    /*Optional - user name which will be used for proxy authentication.*/
    proxyUsername = "username"
    /*Optional - password which will be used for proxy authentication.*/
    proxyPassword = "password"
    /*Optional - default cucumber (can be one of: cucumber/karate)*/
    type = "cucumber"
    /*Optional - Run metadata */
    metadata="""{"env ":"uat ","build":"456"}"""
    /*-Optional - all Jira tickets will be updated with test results by default; when JQL is provided only filtered tickets will be updated*/
    jql = "project = XX AND key in ('XXX-1')"
}

```

### Example project 

Refer to example project [assertthat-bdd-gradle-example](https://github.com/assertthat/assertthat-bdd-gradle-example)
