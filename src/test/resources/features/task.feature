Feature: Task management
  Scenario: Create a new task
    Given a category exists
    When a user creates a new task with a title, description, and deadline
    Then the task is successfully created

  Scenario: Fail to create task without category
    Given no category exists
    When a user tries to create a new task
    Then an error is returned
