FROM java:8
#hypriot/rpi-java

ENV home /root/inspector
#ENV gradle gradle-2.14-bin.zip
#ENV GRADLE_HOME ${home}/${gradle}/bin

RUN apt-get update \
  && apt-get install nmap \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

WORKDIR ${home}

#ADD https://services.gradle.org/distributions/${gradle} ${gradle}
#RUN unzip ${gradle} -d ${home}

COPY . ${home}
RUN ./gradlew build

# run the command with input from the docker file