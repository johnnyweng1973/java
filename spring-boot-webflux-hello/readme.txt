﻿Webflux project note 1:
Project hello:
1. Spring boot project file generation
Visit spring initlzr website and you can get a spring project tarfile there.
2. Spring boot project  structure
            src/main/java for java source files, src/test/java for test files
            Target will be generated by build tools to store target files
            Pom.xml in the root
            Main package is in root dir such as com/example/springbootwebfluxhello, this dir should only has one file that is the classname of application.
            Other packages should be subdirs for classes of controller, service, repo, etc.
3. Springbootwebfluxhello project structure
It has three packages: 
      main package: one file with application class annotated with “@SpringBootApplication”
      controller package: one file with controller class annotated with “@RestController”.
       entity package: one file with class for user definition 
4. Controller class
Controller is entry point of handling http request. All methods are annotated with “@GetMapping("/path")”
In this example, it can handle request with synchronous way which return a string directly. And asynchronous way which return a mono<User>. It calls factory method “just”  of Mono to generate an data
       Return Mono.just(User)


5. Json library
As shown above, when you return a Mono<User> to Spring, spring will use json library jackson to serialize this object. It will output name-value pairs for all private fields of this object who has public getter method.

6. annotation
annotation is defined as @interface in java. Spring framework defines a lot of these annotations. Java will add annotations of classes to meta info of a class file and during runtime spring framework will check and decode these meta info. 
spring framework annotations:
  1. core annotations:  @Component, @RestController, @Service, @Repository
  2. web annotations:  @PathVirable, @GetMapping 

7. Restful API
   it uses HTTP requests to perform standard database functions like creating, reading, updating and deleting records within a resource. User info are in message body in JSon format. it is a light-weighted compared to SOAP which uses XML message 
   the following are mapping we used:
   1 http post with user info in request body to create a user, user info in http response body.
   2.http get to read user info, user info in http response body
   3. http put with user info in request body to update a user, user info in response body
   4. http delete to delete a user
   
