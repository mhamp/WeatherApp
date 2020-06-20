# Weather App Demo
## Project Overview
This web application allows to set up some cities in order to get the current weather information as temperature and humidity for them.

## Implementation details 

**Page 1: Homepage**

Login Screen: Username, Password
Create a user database / storage which contains the default users: 
- foo:123456
- bar:654321

There is no further registration, only these two users can log in. 

After successful login go to page 2. If login unsuccessful, show an error message


**Page 2: Weather app main page**

This page lists all cities for the logged in user from the cities table with the city name, the current temperature and humidity
If there is no city there should be an empty line like this:

| city name | temperature | humiddity  | action |
| ------------- | ------------- | ------------- | ------------- |
|   | not editable  | not editable  | (Button: *Add city*)  |


**Page 3: Add cities screen**
This screen allows the userto  enter a city name by clicking the add city button

TODO:
Validate if the city exists in the weather webservice (see below) and the user has not yet added this city.

- If not ok, show message (invalid city name, please try again) and user can enter a new city.
- If ok, store the city in the database if it's not there and link it to the corresponding user

## Procedure Details 
**Adding cities:**

The entered cities should be saved in your local DB (table name is cities) and the info about temperature and humidity should be
provided from http://openweathermap.org (temperature and humidity should not be saved in DB); Provided communication protocol is
REST.

In order to provide for your web page correct API please first register on http://openweathermap.org/api and generate API key.

After successful adding a city, do a page reload.
All city temperature and humidity data gets updated (on every reload). 
There should be again an empty row to enter the next city.

**Deleting cities:**
After adding a few cities it could look like this:

| city name | temperature | humiddity  | action |
| ------------- | ------------- | ------------- | ------------- |
|  Munich | 10°C  | 85%  | (Button: *Delete city*)  |
|  Belgrade | 12°C  | 80%  | (Button: *Delete city*)  |
|   | not editable  | not editable  | (Button: *Add city*)  |


When clicking delete the city should be deleted. Delete only the link between the user and city.
It should only delete the line in the table (no page refresh / reload) - use AJAX.

## Used Tech Stack
This Projectis  based on Java n-tier architecture (persistence Layer, DAO Layer, Service Layer, Facade and UI layer)
Make use of the following technologies:
1. Tomcat
2. Spring Boot: Spring MVC, Spring Security
3. Hibernate / JPA
4. In memory database H2
5. JSP
6. Javascript / Ajax


## Deployment notice
Follow the following steps to install and run the application: 

**1. How to install the application**

1. Download or clone app from GitHub repository https://github.com/mhamp/weatherapp.git to your preferred IDE. Note app was developed on Intellij Community edition with its inherent limitations.  
2. App is set to work with H2. The database is packaged with in the project jar and can be accessed through http://localhost:8080/h2-console. (If you want to use a different RDBMS you have to change the respective dependencies in the pom.xml and application properties under src/main/resources/application.properties.) 
3. Have the user table populated with required fields foo and bar for username. For password use the service  by https://www.browserling.com/tools/bcrypt to encrypt the passwords: 
    - '123455' for foo
    - '654321' for bar
    - 'admin' for admin


**2. How to start the application**
1. Build Maven project by using `mvn clean install` and run application either by running `'WeatherApplication....main()'` or hitting `mvn spring-boot:run` in your terminal.
2. You should be able to access the running application on an embedded Tomcat server on http://localhost:8080. NOTE: Ports might already be in use by different applications in your local environment.    
3. For login enter credentials foo:123456 or bar:654321 to access the application.             