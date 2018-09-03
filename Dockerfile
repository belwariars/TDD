FROM openjdk:8
ADD target/tddjar.jar tddjar.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "tddjar.jar"]