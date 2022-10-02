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
    
   #### Option #2
    
   #### Option #3
