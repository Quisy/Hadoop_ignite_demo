#!/bin/bash

echo "------- shell apt -------"

apt-get remove --purge openjdk-* -y 
echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
add-apt-repository -y ppa:webupd8team/java
apt-get update

apt-get install -y rsync dsh htop git maven unzip openssh-server oracle-java8-installer
