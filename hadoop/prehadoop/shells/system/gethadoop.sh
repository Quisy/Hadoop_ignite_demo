#!/bin/bash

echo "------- shell gethadoop -------"

HADOOP_VERSION="2.6.4"

[ -e "/vagrant/files/resources/hadoop-$HADOOP_VERSION.tar.gz" ] || ( 
	
	cd /vagrant/files/resources/; curl -O "https://archive.apache.org/dist/hadoop/core/hadoop-$HADOOP_VERSION/hadoop-$HADOOP_VERSION.tar.gz"; )

rm -rf /usr/local/hadoop

cp "/vagrant/files/resources/hadoop-$HADOOP_VERSION.tar.gz" /usr/local/
cd /usr/local/
tar -xzf "hadoop-$HADOOP_VERSION.tar.gz"
mv "hadoop-$HADOOP_VERSION" hadoop
chown vagrant:vagrant -R /usr/local/hadoop

cp /vagrant/files/hadoop/* /usr/local/hadoop/etc/hadoop/
