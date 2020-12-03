# Mock Web Application

This is a web application written in Java that is used to _mock_ a weather api service and a geo-location service.

### Running the application
---
In order to run the application simply run the ```run.sh``` script from bash.
The project uses maven for the build process, so it will try to use the maven installed in the system.
If maven is present, it will use the maven wrapper ```mvnw```

### Packing
---
The application is packaged into 2 files, one jar and one war file. In case the server does not have tomcat installed,
the jar file can be run to start the web server. This is the default behaviour with the ```run.sh``` script.
The jar file is standalone, it contains an embedded tomcat, and the application code.
The war file contains only the application code which can be used with a pre-existing tomcat server.

### App configuration
---
The application configuration files are stored in ```src/main/resources/config.properties``` file. Right now, we can set
the hostname and server port using the configuration file.

### Dependencies
---
The app uses the following libraries
- Tomcat: To run the HTTP Server
- Jackson: To serialize API response to JSON

### Endpoints
---
The app has 2 endpoints to serve requests
- #### ```/weather```
    To serve weather information for a particular city. Only accepts ```GET``` requests
    
    example: ```/weather?citycode=1```
  
    example: ```/weather?cityname=1```
    
    In case where both citycode and city names are present, citycode takes priority
    example: ```/weather?cityname=1&citycode=1```

- #### ```/timezone```
    To serve timezone information for a particular zip code. Only accepts ```POST``` requests
    
    example: ```/timezone```
    request-body: ```zipcode:1``` (application/x-www-form-urlencoded)

    example: ```/timezone```
    request-body: ```cityname:1``` (application/x-www-form-urlencoded)

    In case where both zipcode and city names are present, zip takes priority
    example: ```/timezone```
    request-body: ```zipcode:1 cityname=1``` (application/x-www-form-urlencoded)