FROM adoptopenjdk/openjdk11:ubi
COPY ./target/*.jar ../../docker-demo/demo.jar
WORKDIR ../../docker-demo/
RUN sh -c 'touch demo.jar'
ENTRYPOINT ["java","-jar","demo.jar"]