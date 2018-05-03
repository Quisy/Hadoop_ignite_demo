#!/bin/bash

echo "------- shell monitoring -------"


[ -e "/vagrant/files/resources/monitoring.tar" ] || ( 
	
	cd /vagrant/files/resources/; curl -O "http://fiona.dmcs.pl/~psn/clouds/monitoring.tar"; )

cd /vagrant/files/resources
tar xf monitoring.tar
cd monitoring
sh install.sh

rm -rf /vagrant/files/resources/monitoring
