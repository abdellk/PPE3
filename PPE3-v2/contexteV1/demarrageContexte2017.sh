#!/bin/bash
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker run -d --name derby lecoz/derbysiolapie
docker run -d --name mongodb mongo:latest
docker run -d --hostname mon-rabbit --name rabbitmq rabbitmq:3-management
docker run -d --name tomcatauthentificateur --link rabbitmq:rabbitmq --link derby:derby lecoz/tomcatsshsiolapie 
docker run -d --name tomcatjournalisateur --link rabbitmq:rabbitmq --link mongodb:mongodb lecoz/tomcatsshsiolapie 
docker run -d --name tomcatgui --link tomcatauthentificateur:tomcatauthentificateur lecoz/tomcatsshsiolapie 
for conteneur in $(docker ps -aq)
do
	echo $(docker inspect -f '{{.Name}} - {{.NetworkSettings.IPAddress }}' $conteneur | cut -c2-)
done
