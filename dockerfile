FROM adoptopenjdk/openjdk11:alpine

VOLUME /tmp

EXPOSE 8080
USER root
ENV TZ='GMT-3'
ARG JAR_FILE="target/conta-0.*.jar"

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]