FROM maven:3.8.6-openjdk-18 AS build
COPY challenge6/src /home/app/src
COPY challenge6/pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/app/target/bootcamp-0.0.1-SNAPSHOT.jar"]