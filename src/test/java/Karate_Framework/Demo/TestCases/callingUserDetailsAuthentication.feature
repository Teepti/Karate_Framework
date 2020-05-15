Feature: call to token generation feature
Background:
		* def myFeature = call read('userDetailsAuthentication.feature'){email: 'eve.holt@reqres.in', password: 'pistol'}
		* def authToken = myFeature.accessToken
		
Scenario: GET Call
When url 'https://reqres.in/api/users?page=2'
And header Authorization = authToken
When method GET
Then status 200		