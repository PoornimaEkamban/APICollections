Feature: Test CRUD Methods by using  UserInformation API Collection

Scenario Outline: Create a New User detail
  Given I have an end point
	When I add  user name as <UserName> and job as <Job> in user details
	Then Validating the user name as <UserName> in the name field of the body
	And Checking the status code is <StatusCode> 

  Examples:
      | UserName  | Job			 | StatusCode |
      | john      | Engineer | 201				|
      
      
Scenario Outline: Read an User detail
  Given I have an end point
	When I send the user id 
	Then Validating the user name as <UserName> in the name field of the body
	And Checking the status code is <StatusCode> 

  Examples:
      | UserName  | Job		   | StatusCode |
      | Janet     | Leader   | 200				|
      
Scenario Outline: Update an Existing User detail
  Given I have an end point
	When I change user name as <UserName> and job as <Job> in user details
	Then Validating the user name as <Job> in the name field of the body
	And Checking the status code is <StatusCode> 

  Examples:
      | UserName  | Job			 | StatusCode |
      | Janet     | Engineer | 200				|
      
Scenario Outline: Delete an User detail
  Given I have an end point
	When I send the user id to delete 
	And Checking the status code is <StatusCode> 

  Examples:
      | UserName  | Job		   | StatusCode |
      | Janet     | Leader   | 204				|