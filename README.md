[![Build Status](https://travis-ci.org/assertthat/assertthat-bdd-maven-plugin.svg?branch=master)](https://travis-ci.org/assertthat/assertthat-bdd-gradle-tasks)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.assertthat.plugins/assertthat-bdd-gradle-tasks/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.assertthat.plugins/assertthat-bdd-gradle-tasks)

## Description

Gradle plugin for interaction with AssertThat BDD Jira plugin.

Main features are:

- Download feature files before test run
- Filter features to download based on mode (automated/manual/both), or/and JQL
- Upload cucumber json after the run to AsserTthat Jira plugin

## Installation

Full plugin configuration below, optional properties can be omitted

```groovy
buildscript {
    dependencies{
        classpath group: 'com.assertthat.plugins', name: 'assertthat-bdd-gradle-tasks', version: '1.1'
    }
}

task downloadFeatures(type: FeaturesTask){
    /*Jira project id e.g. 10001*/
    projectId ="PROJECT_ID"
    /*Optional can be supplied as environment variable ASSERTTHAT_ACCESS_KEY*/
    accessKey = "ASSERTTHAT_ACCESS_KEY"
    /*Optional can be supplied as environment variable ASSERTTHAT_SECRET_KEY*/
    secretKey = "ASSERTTHAT_SECRET_KEY"
    /*Optional - default ./features*/
    outputFolder = "src/test/resources"
    /*Optional - all features downloaded by default - should be a valid JQL*/
    jql = "project = XX AND key in ('XXX-1')"
    /*Optional - default automated (can be one of: manual/automated/both)*/
    mode = "automated"
}

task submitReport(type: ReportTask){
    dependsOn cucumber
    /*Jira project id e.g. 10001*/
    projectId ="PROJECT_ID"
    /*Optional can be supplied as environment variable ASSERTTHAT_ACCESS_KEY*/
    accessKey = "ASSERTTHAT_ACCESS_KEY"
    /*Optional can be supplied as environment variable ASSERTTHAT_SECRET_KEY*/
    secretKey = "ASSERTTHAT_SECRET_KEY"
    /*The name of the run - default 'Test run dd MMM yyyy HH:mm:ss'*/
    runName = "Dry Tests Run"
    /*Json report folder - default ./reports*/
    jsonReportFolder = "reports"
    /*Regex to search for cucumber reports - default **.json*/
    jsonReportIncludePattern = "**/cucumber.json"
}

```

### Example project 

Refer to example project [assertthat-bdd-gradle-example](https://github.com/assertthat/assertthat-bdd-gradle-example)
