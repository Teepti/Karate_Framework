Feature: Post request using Data Driven approach  

Scenario Outline: Create User
Given url baseUrl
And path 'api/users/'
And request {name:'<name>', job: <job>}
And header Content-Type = 'application/json'
When method POST
Then status 201
And print response

Examples:
| name  | job |
| Tim   | QA  |
| Liz   | DEV  |
| Selma | ADMIN  |
| Ted   | TEACHER  |
| Luise | MANAGER  |