# Player Service

Ce projet est un service simple pour gérer des joueurs, développé avec Spring Boot.

## Description

Player Service offre une API RESTful pour effectuer des opérations CRUD (Créer, Lire, Mettre à jour, Supprimer) sur des joueurs. Il utilise une base de données en mémoire H2 pour la persistance des données.

## Technologies

* **Java 21**
* **Spring Boot 4.0.0**
* **Spring Web MVC**: Pour créer des applications web et des API RESTful.
* **Spring Data JPA**: Pour simplifier l'accès aux données.
* **H2 Database**: Une base de données en mémoire.
* **Lombok**: Pour réduire le code répétitif.

## API Endpoints

Le service expose les endpoints suivants:

* **`GET /api/players`**: Récupère la liste de tous les joueurs.
* **`GET /api/players/{id}`**: Récupère un joueur par son ID.
* **`POST /api/players`**: Crée un nouveau joueur.

## Comment démarrer

1.  **Clonez le projet**:
    ```bash
    git clone https://github.com/Master1-MIAGE-UCA/oil-1-spring-boot-YoussefZogh.git
    ```
2.  **Lancez l'application**:
    Vous pouvez lancer l'application en utilisant votre IDE ou via la ligne de commande:
    ```bash
    ./gradlew bootRun
    ```
3.  **Accédez à la console H2**:
    L'application utilise une base de données en mémoire H2. Vous pouvez y accéder via votre navigateur:
    [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

    Utilisez les identifiants suivants:
    *   **JDBC URL**: `jdbc:h2:mem:testdb`
    *   **User Name**: `sa`
    *   **Password**: (laissez vide)

## Exemples d'utilisation avec Postman

Les requêtes peuvent être exécutées avec un outil comme [Postman](https://www.postman.com/).

### Récupérer tous les joueurs
* **Méthode**: `GET`
* **URL**: `http://localhost:8080/api/players`

### Récupérer un joueur par ID
* **Méthode**: `GET`
* **URL**: `http://localhost:8080/api/players/1`

### Créer un nouveau joueur
* **Méthode**: `POST`
* **URL**: `http://localhost:8080/api/players`
* **Body** (raw, JSON):
  ```json
  {
      "name": "Nouveau Joueur",
      "level": 10
  }
  ```

## Auteur

*   **Réalisé par**: Youssef Zoghlami
*   **Groupe**: 1
*   **Formation**: M1 MIAGE
*   **Date d'envoi**: 17/12/2025