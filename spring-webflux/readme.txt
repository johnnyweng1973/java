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
   Configuration annotation tells spring framework this class has methods generating beans with @beans annotation. In this case, bean is a routerfunction and this bean is created with factory method route of utility class routerfunctions
This functioninterface looks like a event machine implemented by two dimensional array. From input of event and state you find the method to execute. 
But this routerinterface won't be implemented by array  
