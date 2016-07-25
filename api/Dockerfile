#FROM java:8
FROM hypriot/rpi-java

ENV home /root/api
ENV gradle gradle-2.14
ENV GRADLE_HOME ${home}/${gradle}
ENV PATH=$PATH:$GRADLE_HOME/bin

RUN apt-get update \
  && apt-get install -y mysql-client \
  && apt-get install -y unzip \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

WORKDIR ${home}

ADD https://services.gradle.org/distributions/${gradle}-bin.zip ${gradle}-bin.zip
RUN unzip ${gradle}-bin.zip -d ${home}

COPY . ${home}
RUN gradle build

CMD ["java", "-jar", "build/libs/api.jar", "server", "config.yml"]