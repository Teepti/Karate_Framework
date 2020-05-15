function fn() { 
	
	var config = { 
			
		 baseUrl: 'https://reqres.in/',
		 anotherUrlBase: 'https://reqres.in/',
		 listAllUsersEndPoint: 'api/users?page=1',
		 singleUserEndPoint: '/api/users/2',
		 singleUserNotFoundEndPoint: '/api/users/23',
		 createUserEndPoint: 'api/users/'	 

	};
	
	karate.configure('connectTimeout', 5000);
	karate.configure('readTimeout', 10000);
	return config;

}
