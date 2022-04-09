FROM openjdk:11
ADD /target/periodicals-0.0.1-SNAPSHOT.jar periodicals-springboot-app.jar
ENTRYPOINT ["java", "-jar", "periodicals-springboot-app"]