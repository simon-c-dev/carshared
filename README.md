# CarShared

# Projet de session : Systeme de covoiturage

## Description
Une application de covoiturage avec un emphase sur le matchmaking des utilisateurs basé sur leur intérêt en commun.

## Équipe
Pour voir les informations de l'équipe et les exigences, voir la [charte de l'équipe](charte-equipe.md).

## Technologies
- [Springboot](https://spring.io/projects/spring-boot) : Framework backend Java permettant de créer des application web et l'architecture microservice.
- [Sqlite3](https://www.sqlite.org/) : Type de base de données sql légère.

## Dépendance
- [Java](https://www.oracle.com/ca-fr/java/technologies/downloads/)
- [Maven](https://maven.apache.org/download.cgi)
- [NPM](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm)

## Fonctionnement


Il faut premièrement récupérer le dépôt sur Github. 
```
$ git clone https://github.com/simon-c-dev/CarShared.git

```
### Backend

Dans un terminal, pour lancer le serveur Eureka, il suffit de se déplacer dans le répertoire `backend` et dans lancer le script `runAllMicroservices.sh`
```
$ cd <chemin-au-projet>/backend
$ ./runAllMicroservices.bat
```

Il est possible de consulter les microservices qui roulent a cette adresse. : http://localhost:8761
