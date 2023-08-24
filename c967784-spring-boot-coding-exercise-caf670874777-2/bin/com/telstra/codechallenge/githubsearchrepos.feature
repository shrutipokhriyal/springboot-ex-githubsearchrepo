# See
# https://github.com/intuit/karate#syntax-guide
# for how to write feature scenarios
Feature: As an api user I want to retrieve hottest repositories in the last one week

  Scenario: Get most hottest repositories
    Given url microserviceUrl
    And path '/search/hottestRepos/1'
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'
    # see https://github.com/intuit/karate#schema-validation
    # Define the required schema
    * def repoSearchSchema = { html_url : '#string', 'watchers_count' : '#string', 'language' : '#string', 'description' : '#string', 'name' : '#string' }
    # The response should have an array of repository objects
    And match response == '#[] repoSearchSchema'
    
  Scenario: Invalid format of repository count in the request :: non-numeric
    Given url microserviceUrl
    And path '/search/hottestRepos/abc'
    When method GET
    Then status 400
    # see https://github.com/intuit/karate#schema-validation
    # Define the required schema
    * def repoSearchErrorSchema = { status : '#string', 'code' : '#number', 'errorMessage' : '#string' }
    And match response == '#(^repoSearchErrorSchema)'
    And match $.code == 400
    
  Scenario: Invalid repository count in the request :: 0
    Given url microserviceUrl
    And path '/search/hottestRepos/0'
    When method GET
    Then status 400
    # see https://github.com/intuit/karate#schema-validation
    # Define the required schema
    * def repoSearchErrorSchema = { status : '#string', 'code' : '#number', 'errorMessage' : '#string' }
    And match response == '#(^repoSearchErrorSchema)'
    And match $.code == 400
    
  Scenario: Invalid repository count in the request :: -10
    Given url microserviceUrl
    And path '/search/hottestRepos/-10'
    When method GET
    Then status 400
    # see https://github.com/intuit/karate#schema-validation
    # Define the required schema
    * def repoSearchErrorSchema = { status : '#string', 'code' : '#number', 'errorMessage' : '#string' }
    And match response == '#(^repoSearchErrorSchema)'
    And match $.code == 400
    
  Scenario: Invalid URL :: missing noOfRepos variable in request
    Given url microserviceUrl
    And path '/search/hottestRepos/'
    When method GET
    Then status 404
    # see https://github.com/intuit/karate#schema-validation
    # Define the required schema
    * def repoSearchErrorSchema = { timestamp : '#string', status : '#number', 'error' : '#string', 'path' : '#string' }
    And match response == '#(^repoSearchErrorSchema)'
    And match $.status == 404