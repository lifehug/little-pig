FROM java:8

RUN apt-get update && apt-get install -y \ 
  gradle

WORKDIR /root/inspector
COPY . /root/inspector
RUN ./gradlew build
CMD ["./gradlew", "run"]