# Système de Gestion EcoMove

Le système de gestion EcoMove est une application console en Java conçue pour gérer diverses entités telles que les partenaires, les promotions, les tickets et les contrats. Le projet utilise PostgreSQL comme base de données et suit le modèle DAO (Data Access Object) pour l'accès aux données. Ce système offre une interface utilisateur basée sur des menus pour permettre aux administrateurs de gérer ces entités de manière efficace.


## Table des Matières
- [Aperçu du Projet](#aperçu-du-projet)
- [Fonctionnalités](#fonctionnalités)
- [Technologies Utilisées](#technologies-utilisées)
- [Structure du Projet](#structure-du-projet)
- [Installation](#installation)
- [Utilisation](#utilisation)
- [Améliorations Futures](#améliorations-futures)

## Aperçu du Projet
EcoMove est une application console permettant la gestion des partenaires, promotions, tickets et contrats, avec la nouvelle extension pour la gestion des clients et des réservations. La plateforme permet aux utilisateurs de :
- **S'inscrire et mettre à jour** leurs informations.
- **Rechercher** des billets de transport disponibles.
- **Réserver**  des billets de transport.
- **Gérer et annuler** leurs réservations.

## Fonctionnalités
### -Gestion des Clients
**Enregistrement des clients :** Les utilisateurs peuvent s'inscrire en fournissant des informations telles que leur nom, prénom, email et numéro de téléphone.
**Mise à jour des informations clients :** Les clients peuvent mettre à jour leurs informations personnelles après enregistrement.
**Recherche de clients :** Le système vérifie l'existence d'un client avant toute opération de recherche ou de réservation.

### Recherche et Réservation de Billets
**Recherche de billets :** Les utilisateurs peuvent rechercher des billets disponibles en entrant des critères comme la ville de départ, la ville de destination et la date de départ.
**Affichage des résultats :** Les résultats incluent les détails des billets tels que le transporteur, l'horaire, la durée et le prix.
**Réservation de billets :** Une fois les billets trouvés, les utilisateurs peuvent les réserver.

### Gestion des Réservations
**Consultation des réservations :** Les utilisateurs peuvent consulter leurs réservations à tout moment.
**Annulation des réservations :** Les réservations peuvent être annulées par les utilisateurs si nécessaire.

## Technologies Utilisées
- **Java** : Langage de programmation principal.
- **PostgreSQL** : Base de données pour stocker et gérer les informations des entités.
- **JDBC** : Pour la communication entre l'application et la base de données.
- **Modèle DAO** : Pour gérer les opérations de base de données pour chaque entité.
- **Modèle Singleton** : Pour une gestion efficace de la connexion à la base de données.

## Structure du Projet
Le projet est organisé en plusieurs packages :

src/
├── config/
│   └── DatabaseConfig.java          # Singleton pour la connexion à la BD
├── dao/
│   ├── ClientDao.java               # DAO pour les clients
│   ├── TicketDao.java               # DAO pour les billets
│   └── ReservationDao.java          # DAO pour les réservations
├── models/
│   ├── Client.java                  # Entité Client
│   ├── Ticket.java                  # Entité Ticket
│   └── Reservation.java             # Entité Reservation
├── console/
│   ├── ClientMenu.java              # Interface pour la gestion des clients
│   ├── TicketMenu.java              # Interface pour la recherche de billets
│   ├── ReservationMenu.java         # Interface pour la gestion des réservations
│   └── MainMenu.java                # Menu principal
└── main/
    └── Main.java                    # Point d'entrée de l'application



## Installation

### Prérequis
1. **Java** : Assurez-vous que le JDK Java (version 8 ou supérieure) est installé.
2. **PostgreSQL** : Installez PostgreSQL et créez une base de données pour l'application.

## UML Diagrammes
Voici le diagramme UML des classes utilisées dans le projet. Vous pouvez y accéder en cliquant sur le lien ci-dessous :

- [Diagramme UML des classes][(https://lucid.app/lucidchart/ed201a01-c606-4501-ac07-c17682a65394/edit?viewport_loc=1212%2C911%2C2862%2C1245%2CHWEp-vi-RSFO&invitationId=inv_7147e738-746e-48e8-995d-95f8b97aa2dd)]

## Gestion de Projet - Jira
Les issues du projet sont gérées via Jira. Vous pouvez accéder aux tickets en suivant ce lien :

- [Accès Jira](https://maryamjammar1509-1724855071586.atlassian.net/jira/software/projects/EM/boards/1?sprintStarted=true&atlOrigin=eyJpIjoiMWRmMzQ3YzY3ZDBjNDUzNzkxMTI2MjMzMjdlZTA3MjMiLCJwIjoiaiJ9)

### Configuration de la Base de Données
1. Créez une base de données PostgreSQL :
   ```bash
   createdb ecomove

  -- Table des partenaires
CREATE TABLE partenaires (
    id UUID PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    adresse VARCHAR(255),
    contactInfo VARCHAR(255)
);

-- Table des contrats
CREATE TABLE contrats (
    id UUID PRIMARY KEY,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    specialRate FLOAT,
    agreementConditions TEXT,
    renewable BOOLEAN,
    contractStatus VARCHAR(50),
    partnerId UUID REFERENCES partenaires(id) ON DELETE CASCADE
);

-- Table des promotions
CREATE TABLE promotions (
    id UUID PRIMARY KEY,
    discountType VARCHAR(50) NOT NULL,
    discountValue FLOAT NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    contractId UUID REFERENCES contrats(id) ON DELETE CASCADE
);

-- Table des tickets
CREATE TABLE tickets (
    id UUID PRIMARY KEY,
    transportType VARCHAR(50) NOT NULL,
    purchasePrice FLOAT NOT NULL,
    salePrice FLOAT NOT NULL,
    saleDate DATE NOT NULL,
    ticketStatus VARCHAR(50) NOT NULL,
    contractId UUID REFERENCES contrats(id) ON DELETE CASCADE
);


### Configuration du Projet

1.Clonez le projet depuis le repository :
   [git clone https://github.com/username/ecomove.git](https://github.com/J-Maryam/EcoMove.git)
   
2.Mettez à jour le fichier DatabaseConfig.java dans le package config avec vos identifiants PostgreSQL :
public class DatabaseConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/ecomove";
    private static final String USER = "votre_utilisateur";
    private static final String PASSWORD = "votre_mot_de_passe";
    // Code pour le Singleton...
}

## Compilation et Exécution

   1. Ouvrez le projet dans votre IDE (par exemple, IntelliJ, Eclipse).
   2. Compilez le projet.
   3. Exécutez l'application via la classe Main.java, qui lancera l'interface en ligne de commande.

## Utilisation

   1. Lancez l'application.
   2. Utilisez le menu principal pour gérer les partenaires, promotions, tickets ou contrats.
   3. Suivez les instructions pour ajouter, afficher, mettre à jour ou supprimer des enregistrements.
   4. Quittez l'application en sélectionnant l'option Quitter.

## Licence
Ce projet est sous licence MIT. Vous êtes libre de l'utiliser, de le modifier et de le distribuer.


### Explication des sections :
- **Aperçu du Projet** : Introduction générale de l'application.
- **Fonctionnalités** : Fonctionnalités principales de l'application.
- **Technologies Utilisées** : Liste des outils technologiques employés.
- **Structure du Projet** : Détails de l'architecture du projet.
- **Installation** : Étapes pour installer et configurer le projet localement.
- **Utilisation** : Guide pour utiliser l'application.

