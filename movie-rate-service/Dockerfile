FROM openjdk:11-jre-slim
ENV APP_NAME movie-rate-service

COPY build/libs/${APP_NAME}*.jar /${APP_NAME}.jar

ENTRYPOINT ["java","-jar","/movie-rate-service.jar"]