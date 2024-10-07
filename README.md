Instructions.

 --First Step: 
 
Update the docker-compose.yml
Open the docker-compose.yml file located in the root of your project directory.
Change the context path to the actual path of your project. For example:

build:
context: /home/kent/Desktop/library-app/Library  # Update this line with your actual path of the project and 
ensure that the path points to the directory containing your Dockerfile.

 --Second Step: 
 
*Build the Project.Navigate to your project directory example:

cd /home/kent/Desktop/library-app/Library

*Perform a Maven install to build your project and create the JAR file:

mvn install

*Make sure application run succesfully: 

cd target

java -jar Library-0.0.1-SNAPSHOT.jar

 --Third step 
 
On a new terminal.

docker-compose up --build

After that you can access swagger at :

http://localhost:8080/swagger-ui/index.html