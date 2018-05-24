Feature: Using OrangeHRM
  As an administrator
  I want to manage the database
  So that the details it holds are up-to-date and relevant
 
Scenario: Add an employee to the CRM
  Given the Add Employee Tab
  When I fill out the Employee Details correctly with firstname "David" lastname "Smith" employeeID "7834"
  And I choose to create Login Details
  And I fill out the Login Details correctly username "david583" password "passdavid"
  And I click the Save button
  Then I can see information about the user
  And I Logout
  Given I login as the newly created user username "david583" password "passdavid"
  When i go to the my info page
  Then the users details are displayed