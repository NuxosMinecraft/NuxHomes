NuxHomes
========

An home plugin, to go back faster at home !


Installation
------------

* Download the latest jar [here](https://github.com/NuxosMinecraft/NuxHomes/downloads).
* Copy the downloaded jar file into the plugins folder and rename it to "NuxHomes.jar".

Configuration
-------------

The configuration file is : plugins/NuxBank/config.yml

Example :

url: mysql://host:port/dbname
user: username
passwd: userpass
maxhomes: 5

Permissions' nodes
------------------

* nuxhomes.* - Gives access to all NuxHomes commands
* nuxhomes.basic.* - Gives access to all NuxHomes commands  for basic use
* nuxhomes.multi.* - Gives access to all NuxHomes commands for multihomes use
* nuxhomes.others.* - Gives access to all NuxHomes commands for "go to others" use

* nuxhomes.basic.go - Allows to go to your default home
* nuxhomes.basic.delete - Allows to delete your default home
* nuxhomes.basic.set - Allows to set your default home
* nuxhomes.multi.go - Allows to go to all your homes
* nuxhomes.multi.delete - Allows to delete all your homes
* nuxhomes.multi.list - Allows to list your homes
* nuxhomes.multi.set - Allows to set all your home
* nuxhomes.others.go - Allows to go to others' homes
* nuxhomes.others.delete - Allows to delete other's home
* nuxhomes.others.list - Allows list others' homes
* nuxhomes.others.set - Allows to set others' home

Commands
--------
        
* /home <player> [name] - Go to the home
  Example: /<command> City - Go to the home with the name "City"

* /homedelete <player> [name] - Delete the home
  Example: /<command> City - Delete the home with the name "City"

* /homelist [player] - List the home
  Example: /<command> Notch - List Notch's homes
   
* /homeset <player> [name] - Set your home
  Example: /<command> City - Set a home with the name "City"
    