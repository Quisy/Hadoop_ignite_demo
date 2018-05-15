#!/bin/bash

echo "------- shell apt -------"

apt-get install --reinstall ca-certificates

echo "------- cert done -------"
apt-get remove --purge openjdk-* -y 
echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
add-apt-repository -y ppa:webupd8team/java
#add-apt-repository -y ppa:openjdk-r/ppa
#add-apt-repository -y ppa:jochenkemnade/openjdk-8
apt-get update

#apt-get install -y rsync dsh htop git maven unzip openssh-server install openjdk-8-jdk install openjdk-8-jre
apt-get install -y rsync dsh htop git maven unzip openssh-server oracle-java8-installer
