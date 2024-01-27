# Family-tree #
A simple command-line interface family tree application implemented using
Java 17 and Spring Boot (maven).

## How to Run ##
- Build the application from the root folder with Maven mvn clean install 
- Go to the /target folder
- Run the application via java -jar <generated-jarfile>

## Adding People and Relationships ##
- To add people use this command : **family-tree add person {firstname} {secondname}**
(Their must be a first and Second name)
- To add relationships use this command : **family-tree add relationship {type}**
- To add a relationship between people use this : **family-tree connect {name1} as {type} of {name2}**
  (name1 and name2 are full names (first and last name seperated by space))

## Querying ##
- To ask for the number of sons, daughters or wives use this command : family-tree count sons/daughters/wives of {name}
- To ask for the father use this command : family-tree father of {name}

