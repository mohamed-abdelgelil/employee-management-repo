# Employee State Managment System

Managing employee state transition, Usin the following States and Events

States:
- `ADDED`
- `IN-CHECK`
  - `SECURITY_CHECK_STARTED`
  - `SECURITY_CHECK_FINISHED`
  - `WORK_PERMIT_CHECK_STARTED`
  - `WORK_PERMIT_CHECK_PENDING_VERIFICATION`
  - `WORK_PERMIT_CHECK_FINISHED`
- `APPROVED`
- `ACTIVE`  

Events:
`BEGIN CHECK`, `FINISH SECURITY CHECK`, `COMPLETE INITIAL WORK PERMIT CHECK`, `FINISH WORK PERMIT CHECK`, `ACTIVATE`

**Technologies:-**

    1- Spring Boot
    2- Spring stateMachine
    3- springdoc-openapi-ui
    4- H2 DB inMemory
    
 
 **How to Use:-**
 
- The application can be used as an executable file exported using spring boot with an embeded tomcat using a pre-configured port "8080"
 
     - URL: http://localhost:8080/{servicePath}
     - ex: http://localhost:8080/employee

- The Application is Supported with OpenAPI Lib which view the API document in an html page 
    - URL: http://localhost:8080/swagger-ui/index.html
    
- Also you Can find a postman collection attachec inside the repository wihch include some samlpes that you can use to test rest APIs
    - path 
