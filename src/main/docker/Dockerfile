FROM java:8
VOLUME /tmp
ADD kafka-0.0.1-SNAPSHOT.jar kafka.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","kafka.jar"]