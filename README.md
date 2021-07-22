**************** Micronaut Sample Application with Cassandra ******************

Run Docker compose up command to start casssandra and micronaut at the same time
command to start : docker-compose up
Above command will start Micronaut 

===================== Containers ================
1) Micronaut
2) Cassandra
3) cqlsh

=====================Sample APIs =====================
1) POST request for adding new object
  Request :  http://<ip>:<port>/createStoreUser
  RequestMethod: POST
   definition: This is user for sample user creation
   body:
        {
       "userId":"userId",
       "count":21
        }


2) GET request for getting all Users 
 
  Request: http://ip:port/showUsers
  RequestMethod: GET

 definition: This is user for returning all Users
   
3) POST request for deleting  new object
  Request :  http://<ip>:<port>/deleteUser
  RequestMethod: POST
   definition: This is user for sample user creation
   body:
        {
       "userId":"userId",
       "count":21
        }
 

============    Micronaut references ===========
- [User Guide](https://docs.micronaut.io/2.5.4/guide/index.html)
- [API Reference](https://docs.micronaut.io/2.5.4/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/2.5.4/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)
