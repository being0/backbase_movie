FROM openjdk:11-jre-slim
ENV APP_NAME movie-bestpicture-service

COPY build/libs/${APP_NAME}*.jar /${APP_NAME}.jar

ENTRYPOINT ["java","-jar","/movie-bestpicture-service.jar"]