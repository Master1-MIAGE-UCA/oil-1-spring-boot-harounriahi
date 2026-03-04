# OIL Haroun

Ce projet contient les microservices suivants :

## 1. Player Service

Ce service est une application Spring Boot simple pour gérer des joueurs.

### Description

Player Service offre une API RESTful pour effectuer des opérations CRUD (Créer, Lire, Mettre à jour, Supprimer) sur des joueurs. Il utilise une base de données en mémoire H2 pour la persistance des données.

### Technologies

* **Java 21**
* **Spring Boot 4.0.0**
* **Spring Web MVC**
* **Spring Data JPA**
* **H2 Database**
* **Lombok**

### API Endpoints

* **`GET /api/players`**: Récupère la liste de tous les joueurs.
* **`GET /api/players/{id}`**: Récupère un joueur par son ID.
* **`POST /api/players`**: Crée un nouveau joueur.

---

## 2. Question Catalog Service

Ce service gère le catalogue de questions pour l'application.

### Description

Question Catalog Service expose une API RESTful permettant de gérer les questions (récupération, création, mise à jour, suppression).

### Technologies

* **Java**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**

### API Endpoints

Le contrôleur `QuestionController` expose les endpoints suivants sous `/api/questions` :

* **`GET /api/questions`** : Récupère la liste de toutes les questions.
* **`GET /api/questions/{id}`** : Récupère une question spécifique par son ID.
* **`POST /api/questions`** : Crée une nouvelle question.
* **`PUT /api/questions/{id}`** : Met à jour une question existante (remplacement complet).
* **`PATCH /api/questions/{id}`** : Met à jour partiellement une question.
* **`DELETE /api/questions/{id}`** : Supprime une question par son ID.

### Gestion des Erreurs

Le service utilise une gestion d'exception personnalisée pour retourner des codes d'état HTTP appropriés.

* **`ResourceNotFoundException`** : Cette exception est levée lorsqu'une ressource demandée (comme une question par ID) n'est pas trouvée. Elle renvoie un statut HTTP **404 Not Found**.

---

## 3. Game Engine Service

Ce microservice agit comme un orchestrateur entre les autres services.

### Description

Game Engine Service permet de démarrer une session de jeu en communiquant avec les microservices **Player Service** et **Question Catalog Service**.
Il récupère les informations d'un joueur et une liste de questions afin de construire une session de jeu.

### Technologies

* **Java 21**
* **Spring Boot**
* **Spring Web**
* **RestClient**
* **Lombok**

### API Endpoint

* **`POST /api/games/start/{playerId}?nb=3`**

Cet endpoint :

1. récupère un joueur depuis **player-service**
2. récupère des questions depuis **question-catalog-service**
3. crée une session de jeu
4. retourne un JSON contenant le joueur et les questions sélectionnées.

---

## Auteur

**Haroun Riahi**
MIAGE1 GRP2
