Webflux project note 2:
Project webflux:
1. different from project 1:
   first project has two endpoints defined in controller class. static data is defined locally. 
   This project defines service class which will interact with repository classes.
   for http request handling, two types are defined. one is restcontroller and the other is functional interface.
   for functional interface, a handler class is defined.
   model dir has a user class file. model is shorthand of data model which represent data structure and behavior.

2. call flows
   reactive http server receive a http request (create a user)
   it find the end point for this request from RestController and call the method. input paramter object User is created by Calling factory method provided by Json library since User is annotated as a bean.
   controller calls method from Service object, service call method from repository
   repository return a mono object
   spring framework will subscribe this mono with a method which will send out httprespond
   after repository receive data response from database, it will run the method registered in  this mono.

3. Configuration class. 
   Configuration annotation tells spring framework this class has methods generating beans with @beans annotation. 
   In this case, bean is a routerfunction and this bean is created with factory method route of utility class routerfunctions
This functioninterface looks like a event machine implemented by two dimensional array. From input of event and state you find the method to execute. 
But this routerinterface won't be implemented by array  
   the WebClientUtil class in the same dir is not used in the project

4. Client class
   1. WebClientUtil
  
   WebClientUtil is annotated with @Component and its instance is created as a bean through Context.getBean(WebClientUtil.class). In java every class has a class object which include the meta info .
      1.1 WebClient builder
   WebClientUtil includes an instance of WebClient. It's constructor required a WebClient.builder. Spring framework will create an instance of WebClient.Builder and push it into this constructor and a WebClient will be created with this builder.
   WebClient is an interface and Builder is another interface defined inside this interface. 
   WebClient is a high-level and fluent API for making Http requests and handle responses. 
   Builder is a mutable one which means it supports chained method calls on it and step by step configuring the WebClient.
      1.2 send http request and handle response
    through a chained method calls http request sending and response handling are implmented.
    this.client.get().uri("/api/users").accept(MediaType.APPLICATION_JSON)
				 .retrieve()
				 .bodyToMono(String.class)
			     .subscribe(customName -> System.out.println("webclient recevie " + customName))
		         ;
   retrieve() will send out http request and retrun a mono object
   the following calls are used to manupilate the object. In this case, it will change mono data to http response body in string format and print it out.
     1.3 logging and ExchangeFilterFunction
     ExchangeFilterFunction is a functional interface. it has two types: request and response filter fucntions. you can declare it with lambda function. it is a paratmer of method filter(). This filter function will apply to all http request and response in this webclient.
     you can  
     logging function can be implmeneted in ExchangeFilterFunction and call method filter() to set it to WebClient
     logging function come from slf4j module. all instances of WebclientUtil share one logger instance and it is thread-safe.
     

