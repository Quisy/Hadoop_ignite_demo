#!/bin/bash

echo "------- shell generic -------"

cp /vagrant/files/system/bashrc /home/vagrant/.bashrc
cp /vagrant/files/system/hosts /etc/hosts
cp /vagrant/files/system/config /home/vagrant/.ssh/config

cat /vagrant/files/hadoop/masters > /etc/dsh/group/cluster
cat /vagrant/files/hadoop/slaves >> /etc/dsh/group/cluster
cat /vagrant/files/hadoop/slaves > /etc/dsh/group/slaves
