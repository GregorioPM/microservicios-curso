FROM openjdk:17
VOLUME /tmp
EXPOSE 8888
ADD ./target/servicio-config-server-0.0.1-SNAPSHOT.jar confi-server.jar
ENTRYPOINT ["java","-jar","/confi-server.jar"]