#!/bin/bash

# conteneurs de production
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker run -d --name derby lecoz/derbysiolapie
docker run -d --name mongodb mongo:latest
docker run -d --hostname mon-rabbit --name rabbitmq rabbitmq:3-management
docker run -d --name tomcatauthentificateur --link rabbitmq:rabbitmq --link derby:derby lecoz/tomcatsshsiolapie 
docker run -d --name tomcatjournalisateur --link rabbitmq:rabbitmq --link mongodb:mongodb lecoz/tomcatsshsiolapie 
docker run -d --name tomcatgui --link tomcatauthentificateur:tomcatauthentificateur lecoz/tomcatsshsiolapie 

# conteneur de supervision (cadvisor) des conteneurs Docker (interface de supervision http://172.17.0.x:8080) 
docker run   --volume=/:/rootfs:ro   --volume=/var/run:/var/run:rw   --volume=/sys:/sys:ro   --volume=/var/lib/docker/:/var/lib/docker:ro  --privileged=true  --publish=8080:8080   --detach=true   --name=cadvisor   google/cadvisor:latest

# affichage des adresses TCP-IP des conteneurs
for conteneur in $(docker ps -aq)
do
	echo $(docker inspect -f '{{.Name}} - {{.NetworkSettings.IPAddress }}' $conteneur | cut -c2-)
done
