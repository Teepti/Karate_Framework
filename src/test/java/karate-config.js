function fn() {
	
	var env = karate.env; // get java system property 'karate.env'
    karate.log('karate.env system property was:', env);
    if (!env) {
    env = 'dev'; // a custom 'intelligent' default
    
    } 
	
	var config = { 
			
		 baseUrl: 'https://reqres.in/',
		 anotherUrlBase: 'https://reqres.in/',
		 listAllUsersEndPoint: 'api/users?page=1',
		 singleUserEndPoint: '/api/users/2',
		 singleUserNotFoundEndPoint: '/api/users/23',
		 createUserEndPoint: 'api/users/'	 

	};
	
	if (env == 'dev') {
		config.baseUrl = 'https://reqres.in/';
		karate.log('karate.env system property was:', env);
	}else if (env == 'staging') {
		config.baseUrl = 'https://reqres.in/';
		karate.log('karate.env system property was:', env);
	} else if (env == 'e2e') {
		config.baseUrl = 'https://reqres.in/';
		karate.log('karate.env system property was:', env);
	} else if (env == 'qa') {
		config.baseUrl = 'https://reqres.in/';
		karate.log('karate.env system property was:', env);
	}
	
	karate.configure('connectTimeout', 5000);
	karate.configure('readTimeout', 10000);
	return config;

}
