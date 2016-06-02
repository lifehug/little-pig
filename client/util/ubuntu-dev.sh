#!/bin/bash

apt-get -y install qemu-user-static
apt-get -y install apt-transport-https ca-certificates
apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
echo "deb https://apt.dockerproject.org/repo ubuntu-trusty main" > /etc/apt/sources.list.d/docker.list
apt-get -y update
apt-cache policy docker-engine
apt-get -y install linux-image-extra-$(uname -r)
apt-get -y install apparmor
apt-get -y install docker-engine
export DOCKER_HOST=tcp://192.168.99.100:2376
export DOCKER_TLS_VERIFY=1
export DOCKER_CERT_PATH=/Users/{userName}/.docker/machine/machines/default
service docker start