# Grade Tracker (cis2232_f26_project_trenholme_chandler)
Project Repo for CIS-2232 Fall 2026

## Project Team

Business Client: Alex Fendyur
Lead Developer: Chandler Trenholme
Project Manager: Julian Keith

## Description

This project’s goal is to create a grade tracking web app. The app should have a home page that links to individual classes.

## Color

Main color: #2F4A3A

## Required Fields

| Field Name    | Data Type     | Desciption                                                                         |
| ------------- |:-------------:| -----------------------------------------------------------------------------------|
| id            | int           | database id                                                                        |
| maxScore      | float         | total marks for the gradable item                                                  |
| actScore      | float         | achieved marks for the gradable item                                               |
| weight        | float         | weight towards total class grade                                                   |
| name          | String        | name of gradable item                                                              |
| className     | String        | name of class for the gradable item                                                |
| priority      | String        | enumeration of "HIGH", MODERATE" and "LOW"                                         |
| type          | String        | enumeration of "ASSIGNMENT", "PROJECT", "GROUP_PROJECT", "QUIZ", "TEST" and "EXAM" |
| percent       | float         | percentage of marks achieved on the gradable item                                  |

### Removed Fields

| Field Name     | Data Type     | Reason for Removal                         |                                                        |
| -------------- |:-------------:| -------------------------------------------|
| currentDate    | String        | Purely temporal, independent of records    |
| weightAchieved | float         | Derived value with little analytical value |
| classMark      | float         | Derived value, independent of records      |