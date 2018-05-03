#!/bin/bash

echo "------- shell copydebs -------"

[ -e "/vagrant/files/resources/archives.zip" ] && { 

	cd /vagrant/files/resources
	unzip archives.zip
	dpkg -i /vagrant/files/resources/archives/*.deb
	apt-get -f install
	rm -rf archives

} || {
	sh /vagrant/shells/system/apt.sh;
}
