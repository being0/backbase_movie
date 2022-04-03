FROM openjdk:11-jre-slim
ENV APP_NAME movie-toprated-service

COPY build/libs/${APP_NAME}*.jar /${APP_NAME}.jar

ENTRYPOINT ["java","-jar","/movie-toprated-service.jar"]