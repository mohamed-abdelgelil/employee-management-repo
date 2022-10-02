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

**Review Comments**


**Production Readiness Criteria**


**Integration Solutions**
