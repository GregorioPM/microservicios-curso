FROM openjdk:17
VOLUME /tmp
EXPOSE 8002
ADD ./target/servicio-items-0.0.1-SNAPSHOT.jar servicio-item.jar
ENTRYPOINT ["java","-jar","/servicio-item.jar"]