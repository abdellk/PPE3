#!/bin/bash

# arrêt et suppression des conteneurs passés
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)

# démarrage des conteneurs de production

# les conteneurs de bases de données [MODELE persistant]
docker run -d --name derby lecoz/derbysiolapie
docker run -d --name mongodb mongo:latest

# le conteneur de messages asynchrones
docker run -d --hostname mon-rabbit --name rabbitmq rabbitmq:3-management

# les conteneurs de services WEB de type RESTful [CONTRÔLEURS]
docker run -d --name tomcatauthentificateur --link rabbitmq:rabbitmq --link derby:derby lecoz/tomcatsshsiolapie
docker run -d --name tomcatcreateurutilisateur --link derby:derby lecoz/tomcatsshsiolapie 
docker run -d --name tomcatjournalisateur --link rabbitmq:rabbitmq --link mongodb:mongodb lecoz/tomcatsshsiolapie

# le serveur d'applications WEB (interface Homme-Machine) [VUE]  
docker run -d --name tomcatgui --link tomcatauthentificateur:tomcatauthentificateur --link tomcatcreateurutilisateur:tomcatcreateurutilisateur lecoz/tomcatsshsiolapie 

# conteneur de supervision (cadvisor) des conteneurs Docker (interface de supervision http://172.17.0.x:8080) 
docker run   --volume=/:/rootfs:ro   --volume=/var/run:/var/run:rw   --volume=/sys:/sys:ro   --volume=/var/lib/docker/:/var/lib/docker:ro  --privileged=true  --publish=8080:8080   --detach=true   --name=cadvisor   google/cadvisor:latest

# peuplement initial de la base de données relationnelle (actions, rôles, utilisateurs)
/opt/librairies/jdk1.8.0_112/bin/java -jar ./peuplement.jar > /dev/null 2>1&


# affichage des adresses TCP-IP des conteneurs
for conteneur in $(docker ps -aq)
do
	echo $(docker inspect -f '{{.Name}} - {{.NetworkSettings.IPAddress }}' $conteneur | cut -c2-)
done
