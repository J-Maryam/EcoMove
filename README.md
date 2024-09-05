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
Cette application aide les administrateurs d'EcoMove à gérer :
- **Partenaires** : Gérer les partenaires commerciaux associés aux contrats et aux promotions.
- **Promotions** : Gérer les offres promotionnelles liées aux contrats, comme les réductions.
- **Tickets** : Suivre et gérer les tickets de transport.
- **Contrats** : Gérer les accords entre les partenaires et EcoMove.

## Fonctionnalités
- Ajouter, afficher, mettre à jour et supprimer des **Partenaires**.
- Ajouter, afficher, mettre à jour et supprimer des **Promotions**.
- Ajouter, afficher, mettre à jour et supprimer des **Tickets**.
- Ajouter, afficher, mettre à jour et supprimer des **Contrats**.
- Stocker et récupérer les données dans une base de données **PostgreSQL**.
- Interface utilisateur en ligne de commande pour une navigation simple à travers les entités.

## Technologies Utilisées
- **Java** : Langage de programmation principal.
- **PostgreSQL** : Base de données pour stocker et gérer les informations des entités.
- **JDBC** : Pour la communication entre l'application et la base de données.
- **Modèle DAO** : Pour gérer les opérations de base de données pour chaque entité.
- **Modèle Singleton** : Pour une gestion efficace de la connexion à la base de données.

## Structure du Projet
Le projet est organisé en plusieurs packages :

src/ │ ├── config/ │ └── DatabaseConfig.java # Configuration de la connexion à la BD (Singleton) │ ├── dao/ │ ├── PartnerDao.java # DAO pour les partenaires │ ├── PromotionDao.java # DAO pour les promotions │ ├── TicketDao.java # DAO pour les tickets │ └── ContractDao.java # DAO pour les contrats │ ├── models/ │ ├── Partner.java # Entité partenaire │ ├── Promotion.java # Entité promotion │ ├── Ticket.java # Entité ticket │ ├── Contract.java # Entité contrat │ └── enums/ # Enums pour les statuts des contrats, types de réductions, etc. │ ├── console/ │ ├── MainMenu.java # Menu principal pour la navigation │ ├── PartnerMenu.java # Opérations pour les partenaires │ ├── PromotionMenu.java # Opérations pour les promotions │ ├── TicketMenu.java # Opérations pour les tickets │ └── ContractMenu.java # Opérations pour les contrats │ └── main/ └── Main.java # Point d'entrée de l'application



## Installation

### Prérequis
1. **Java** : Assurez-vous que le JDK Java (version 8 ou supérieure) est installé.
2. **PostgreSQL** : Installez PostgreSQL et créez une base de données pour l'application.

## UML Diagrammes
Voici le diagramme UML des classes utilisées dans le projet. Vous pouvez y accéder en cliquant sur le lien ci-dessous :

- [Diagramme UML des classes](https://lucid.app/lucidchart/1a44254d-c877-4fed-beaa-734e27290630/edit?viewport_loc=-239%2C179%2C2415%2C1051%2CHWEp-vi-RSFO&invitationId=inv_a2069a07-a55d-40db-a341-6b937da2b99f)

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

