# Employee State Managment System

Managing employee state transition, Usin the following States and Events

<img src="https://github.com/mohamed-abdelgelil/employee-management-repo/raw/main/requirments/state_transition_diagram.png" alt="state_transition_diagram" width="500" height="700">


**Technologies**


    1- Java 11
    2- Spring Boot
    3- Spring stateMachine
    4- springdoc-openapi-ui
    5- H2 DB inMemory
    
 
 ## **How to Use**
 
- The application can be used as an executable file exported using spring boot with an embeded tomcat using a pre-configured port "8080"
 
     - URL: http://localhost:8080/{servicePath}
     - ex: http://localhost:8080/employee
     
- You can Check application rest API document using the OpenAPI url
    - URL: http://localhost:8080/swagger-ui/index.html
    
- Also you Can find a postman collection attachec inside the repository wihch include some samlpes that you can use to test rest APIs
    - URL: https://github.com/mohamed-abdelgelil/employee-management-repo/blob/develop/employee_state_management.postman_collection.json
    - Hint:
        - make sure to add a variable to the global section called "employeeID" as it is used inside all the servces and it's value is modified after the employee registration success
        - you can use the postman run to execute the collection in a sequential
        
        
## **Test**

All the Test Cases is implemented including the happy & unhappy scenarios


## Other Requirments

### **Review Comments**
- Features
    - Maintain the state history is missed
    - Reload state machine for each employee at the real time
    - State to be dynamic based on a configuration file or a database table
- Code & Design
    - Logging need to be enhanced on different levels
    - Use mapstsruc for objects conversion
- Test
    - Need more unit tests for service layer with a MOC
    - Need more unit tests for controller layer with a MVC MOC


### **Production Readiness Criteria**
- App
    - Add a profile for each environment (developmet, testing, production)
    - Add Caching tool to enhance the performance and may be used for session management   
- Environmemt
    - Use a stable Database Engine like (oracle) or even a nosql database based on the amount of data and its structure
    - Also you need to maintain the database replica using 3D party (oracel embeded) tool or manullay by the code
    - Use a high availability installtion with a loadbalancer (hard/software)
    - Use a messageing tool (kafka, activeMq) for integration with other services with a persistant store (to avoid data lose)
    - Use Kubernates to orchestrate on the docker images based on the configured logic
    - API Gateway & Service Discovery
    - External Configuration Server
    - Use a CI/CD Pipeline
    - Use a monitor tool like (spring boot actuator) or you can build the ELK stack with extra logging details (requitID) to monitor all the requests life cycle


### **Integration Solutions**
   #### Option #1
        - As the Objective of the integration is Statics services that based on database queries, we can set the integration on the database level
        - we can have 2 approches here
        
        Approach #1
            1. Using a relational DB straming tool replicate all the data on another DB node in a periodic time
            2. Create a read only user on the other node
            3. the statistics services can access the replica DB usingthe created user and perform all statistics, queries, and reports
            
        Approach #2    
            1. Build a an inteerceptor layer on the top of the JPA layer
            2. intercept all saved entries on another configured datastore
            3. here we can choose between a nosql or sql db and customize the data struture which may facilitate the statisitcs service and enhance the queries performance
            
        Option #1 Advantages:
            - no implementation need especially approach #1 
            - save data concistansy & no datalose
            - data secured with the read only
            - replica db can be reusable in other features
            - no hits on the live database that may affect the system main functionalities
    
   #### Option #2
        - We can expose a get rest api from inside our service to getall employees
        - the service can be used by the statistics users with a limited privilages
        - then they can save the result in a temp store to be cleaned after finish statistics
    
   #### Option #3
        - we can exopse a start get rest api to start its function asynchronusly
        - once the service gets hit it will start a new thread and start get result in patches in side it
        - then send the result patch by patch on a messaging tool asynchronusly to be fetched one by one at the other side
        - and to avoid the data lose and consistancy it is recomended to set a presistant store with message tool
