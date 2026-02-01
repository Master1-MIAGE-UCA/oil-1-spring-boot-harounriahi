# player-service

Microservice Spring Boot pour la gestion des joueurs.

## Environnement
- Java : 21
- Spring Boot : 4.0
- Build tool : Maven
- Base de données : H2 (en mémoire)
- OS : macOS

## Fonctionnalités réalisées
- Création de l’entité Player (id, pseudo, score)
- Repository JPA
- Service PlayerService
- Controller REST
- Endpoints :
    - GET /api/players
    - GET /api/players/{id}
    - POST /api/players
- Chargement de données initiales via CommandLineRunner

## Lancement
Lancer l’application avec :
- mvn spring-boot:run
  ou
- PlayerServiceApplication (IDE)
