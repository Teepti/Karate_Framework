Feature: Do Crud Operations and validate JSON Response in different ways
Background:
      * def jsonPayLoad = read('../Data/request.json')

Scenario: GET Method
Given url baseUrl
And path singleUserEndPoint
When method get
Then status 200
And match $ == {"data":{"id":2,"email":"janet.weaver@reqres.in","first_name":"Janet","last_name":"Weaver","avatar":"https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"},"ad":{"company":"StatusCode Weekly","url":"http://statuscode.org/","text":"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things."}}


Scenario: POST Method
Given url baseUrl
And path createUserEndPoint
And request jsonPayLoad[0]
When method post
Then status 201
Then print response
And match response.name == jsonPayLoad[0].name


Scenario: PUT Method
Given url baseUrl
And path createUserEndPoint
And request jsonPayLoad[1]
When method put
Then status 200
Then print response
And match response.job == "QA"

  

  

       
       