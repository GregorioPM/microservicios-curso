FROM openjdk:17
VOLUME /tmp
EXPOSE 8761
ADD ./target/servicio-productos-0.0.1-SNAPSHOT.jar servicio-productos.jar
ENTRYPOINT ["java","-jar","/servicio-productos.jar"]