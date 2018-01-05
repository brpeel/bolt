# bolt
Button Loyalty Transactions
The application overview:
This REST API is built on top of the DropWizard framework and a PostGres database.
There are two entities: the bolt_user entity and the transfer entity.
Users can be created and deleted (this is soft delete aka marked as deleted) and for informational purposes can get retrieved
Transfers can only be created. This means that in order to undo a transfer then a ne transfer with the opposite values must be created.
This is a concept borrowed from accounting practices where values are never modified or deleted, they are backed out.

#Getting started
## Key Technologies
1. [DropWizard](http://www.dropwizard.io/1.2.2/docs/) - Java Framewwork for RESTful APIs 
2. [Gradle] (https://gradle.org) - Build and Dependency management system
3. [Gradle Wrapper] (https://docs.gradle.org/current/userguide/gradle_wrapper.html) - A self-contained version of Gradle so that developers do not have to install Gradle.
    The gradle wrapper is invoked on Linux and Mac OSX by the prepending any commands with ``bash ./gradlew``
    On Windows the wrapper is leveraged using the gradlew.bat file
    This README assumes that the developer with use the gradle wrapper

## Setup
1. Checkout project from GitHub into the IDE of your choice
2. Run ```bash
    ./gradlew clean build
   ```
   Running this command will:
    .. 1. Download all the dependencies from the Maven repos specified in the build.gradle file
    .. 2. Clean and compile the project
    .. 3. Execute all unit tests
3. Create a PostGres database and then execute the sql file bolt/scripts/alters/db_init.sql    
4. Setup your local database access by running
 ```bash
 ./gradlew setup -PdbUser=<user_name> -PdbPassword=<user_password> -PdbHost=<database_host>:<database_port> -PdbName=<database_name>
 ```    
5. Code away and run ```bash ./gradlew clean build ``` whenever you wish to run the unit tests

## Running the project locally
To run the server locally ``bash
           ./gradlew clean build run
          ```
If the server is running successfully you should see console output that contains somthing like this:
```bash
INFO  [2018-01-05 03:21:45,092] io.dropwizard.jersey.DropwizardResourceConfig: The following paths were found for the configured resources:

    POST    /api/transfer (com.btn.bolt.resources.TransferResource)
    GET     /api/transfer/{id} (com.btn.bolt.resources.TransferResource)
    POST    /api/user (com.btn.bolt.resources.UserResource)
    DELETE  /api/user/{id} (com.btn.bolt.resources.UserResource)
    GET     /api/user/{id} (com.btn.bolt.resources.UserResource)
...
```

#Deployment
To do to a production environment we will build a shadow jar. This shadow jar is a java jar file that contains the code and all dependencies to execute the code.
Therefore to run on production we just need to make sure that Java 8 is installed. No other jars need to be installed on the server or loaded on to the classpath
To build the shadow jar run the run ```bash ./gradlew clean build shadowJar``` this command will create jar and write it to **build/libs/bolt-api.jar**
Once the shadowJar is built copy it any production server.
You will need to also setup a production configuration. To do this copy and edit the config-template.yaml under src/main/resources
On the production server you run the following command ```bash java jar bolt-api.jar server <path to config file> ```. Please note that this command could change
from environment to environment.  


#Assumptions
1. Authentication is needed but for evaluation purposes and getting reviewers up and going it is okay to leave out.
2. There is no review process for transfers once the transfer is created the users points are automatically updated.
3. Points are duplicated on the user object, since it is assumed that there will we a large number of transfers.
