#!/bin/bash

echo "------- shell ssh -------"

cp /vagrant/files/system/id_rsa /home/vagrant/.ssh/id_rsa
cp /vagrant/files/system/id_rsa.pub /home/vagrant/.ssh/id_rsa.pub
cp /vagrant/files/system/config /home/vagrant/.ssh/config

chown vagrant:vagrant /home/vagrant/.ssh/id_rsa
chown vagrant:vagrant /home/vagrant/.ssh/id_rsa.pub

cat /home/vagrant/.ssh/id_rsa.pub >> /home/vagrant/.ssh/authorized_keys

chmod 600 /home/vagrant/.ssh/id_rsa
chmod 644 /home/vagrant/.ssh/id_rsa.pub
chmod 644 /home/vagrant/.ssh/config

cp -r /home/vagrant/.ssh /root/
