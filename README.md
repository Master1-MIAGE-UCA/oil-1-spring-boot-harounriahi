# OIL — Architecture Microservices

**Auteur : Haroun Riahi**
**MIAGE1 — Groupe 2**

Ce projet implémente une architecture **microservices** développée avec **Spring Boot**.
Les services communiquent entre eux via **REST APIs** et utilisent un **service de découverte (Discovery Service)** pour localiser dynamiquement les instances disponibles.

---

# 1. Player Service

Ce service gère les joueurs de l'application.

## Description

Player Service expose une API REST permettant d'effectuer des opérations CRUD sur les joueurs.
Les données sont stockées dans une **base H2 en mémoire**.

## Technologies

* Java 21
* Spring Boot 4.0
* Spring Web MVC
* Spring Data JPA
* H2 Database
* Lombok

## API Endpoints

| Méthode | Endpoint                  | Description                   |
| ------- | ------------------------- | ----------------------------- |
| GET     | `/api/players`            | Récupère tous les joueurs     |
| GET     | `/api/players/{id}`       | Récupère un joueur par ID     |
| POST    | `/api/players`            | Crée un nouveau joueur        |
| PATCH   | `/api/players/{id}/score` | Met à jour le score du joueur |

---

# 2. Question Catalog Service

Ce service gère le **catalogue de questions** utilisées dans le jeu.

## Description

Question Catalog Service expose une API REST permettant de gérer les questions.

## Technologies

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* H2 Database

## API Endpoints

| Méthode | Endpoint              | Description                |
| ------- | --------------------- | -------------------------- |
| GET     | `/api/questions`      | Liste toutes les questions |
| GET     | `/api/questions/{id}` | Récupère une question      |
| POST    | `/api/questions`      | Crée une question          |
| PUT     | `/api/questions/{id}` | Remplace une question      |
| PATCH   | `/api/questions/{id}` | Met à jour partiellement   |
| DELETE  | `/api/questions/{id}` | Supprime une question      |

## Gestion des erreurs

Le service utilise une exception personnalisée :

* `ResourceNotFoundException` → retourne **HTTP 404**

---

# 3. Score Service

Ce service stocke **l'historique des parties jouées**.

## Description

Score Service enregistre les scores obtenus par les joueurs à la fin d'une partie.

## Technologies

* Java
* Spring Boot
* Spring Data JPA
* H2 Database

## Endpoint

| Méthode | Endpoint      | Description         |
| ------- | ------------- | ------------------- |
| POST    | `/api/scores` | Enregistre un score |

---

# 4. Game Engine Service

Ce microservice agit comme **orchestrateur** entre les autres services.

## Description

Game Engine Service coordonne les interactions entre :

* Player Service
* Question Catalog Service
* Score Service

Il récupère les informations d'un joueur et les questions nécessaires pour créer une session de jeu.

## Technologies

* Java 21
* Spring Boot
* Spring Web
* RestClient
* Lombok

## API Endpoint

### Démarrer une partie

POST `/api/games/start/{playerId}?nb=3`

Cette opération :

1. récupère le joueur depuis **player-service**
2. récupère des questions depuis **question-catalog-service**
3. crée une session de jeu
4. retourne les données de la partie.

### Terminer une partie

POST `/api/games/end`

Permet d'enregistrer le score d'une partie dans **score-service**.

---

# 5. Discovery Service (TD7)

Le **Discovery Service** agit comme un **annuaire de services** dans l'architecture.

Il permet aux microservices de **se découvrir dynamiquement** sans utiliser d'URL codées en dur.

## Port

```
8761
```

## Fonctionnement

Chaque microservice s'enregistre automatiquement au démarrage via :

POST `/api/registry`

Exemple :

```json
{
  "serviceName": "question-catalog-service",
  "url": "http://localhost:8082"
}
```

Pour découvrir un service :

GET `/api/discovery/{serviceName}`

Exemple :

```
GET /api/discovery/question-catalog-service
```

Réponse :

```json
[
  "http://localhost:8082",
  "http://localhost:8092"
]
```

---

# 6. Test Multi-Instance (Load Balancing)

Deux instances du **question-catalog-service** ont été lancées :

```
http://localhost:8082
http://localhost:8092
```

Le **game-engine-service** interroge le discovery service et sélectionne **aléatoirement une instance disponible**.

Cela permet de répartir les requêtes entre plusieurs instances du service.

---

# Architecture globale

```
Client
   |
   v
Game Engine Service
   |
   v
Discovery Service
   |
   |---- Player Service
   |
   |---- Score Service
   |
   |---- Question Catalog Service (8082)
   |
   |---- Question Catalog Service (8092)
```

---

# Lancer le projet

Lancer les services dans cet ordre :

1. discovery-service
2. player-service
3. score-service
4. question-catalog-service (8082)
5. question-catalog-service (8092)
6. game-engine-service

---

# Exemple de test

Découvrir les services :

```
curl http://localhost:8761/api/discovery/question-catalog-service
```

Démarrer une partie :

```
curl -X POST "http://localhost:8080/api/games/start/1?nb=3"
```

---
## TD8 - WebSockets (Rooms / Private / Chat)

### Dashboard
- URL : http://localhost:8080/game.html

### WebSocket endpoints
- STOMP endpoint : /ws
- Room topic : /topic/game/{gameId}
- Private topic : /topic/player/{playerId}
- Chat send : /app/chat/{gameId}

### Demo attendue
- Client A : gameId=partie-1, playerId=10
- Client B : gameId=partie-1, playerId=20
- Client C : gameId=partie-2, playerId=30
- Test privé : POST /api/games/bonus/10 → seul A reçoit

# Auteur

**Haroun Riahi**
MIAGE1 — Groupe 2
