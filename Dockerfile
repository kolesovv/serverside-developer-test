# Base image containing the JRE
FROM adoptopenjdk:11-jre-hotspot
# Application jar file
ARG JAR_FILE
# Add application jar file to container
COPY ${JAR_FILE} app.jar
# Run application
ENTRYPOINT ["java","-jar","/app.jar"]