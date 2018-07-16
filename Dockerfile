FROM openjdk:8-jdk-alpine

EXPOSE 3030

VOLUME /tmp
ADD target/fuber-cabservice-0.0.1-SNAPSHOT.jar target/app.jar
# RUN bash -c 'touch target/app.jar'
# ARG target/fuber-cabservice-0.0.1-SNAPSHOT.jar
# COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","target/app.jar"] 