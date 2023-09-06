FROM  openjdk:17
LABEL authors="kevin_913@qq.com"
WORKDIR /app
ADD target/uim-1.0-SNAPSHOT-kevin.sample.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080