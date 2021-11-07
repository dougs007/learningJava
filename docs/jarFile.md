# How to make a jar using Spring Boot application.


- Using eclipse IDE
Run as > Maven Build > Main/Goals = clean package

- Using cmd  ``` mvn clean package ```

When the finish, the folder "target" has been created, then, inside her you will see a jar from you project.



## How to execute a jar
``` java -jar "JAR_FILE.jar" ```


# Using vars from application.properties

- Why we shouldn't put a real data about setting of your project ?

Cause anyone can see this settings values and made a bad thing, like drop all data of your database or anything like this.

- One of the best practices to do in this case is use vars.

In your application.properties, in the propertie value, you should use ${NAME_VAR}, see the example bellow:

spring.datasource.username=${DB_USERNAME}


## How to execute jar if project has profiles

- Execute this command below:
``` java -jar "-Dspring.profiles.active=prod" JAR.jar ```


## How to execute jar if project has vars.

- Execute this command below for unix SO .

``` export DB_USERNAME=article_user ```

or 

``` java -jar -DDB_USERNAME=article_user JAR.jar ```


