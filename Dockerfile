FROM openjdk:8-jdk-alpine
EXPOSE 8088
ADD target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.1.war Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.1.war
ENTRYPOINT ["java", "-jar", "/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.1.war"] 