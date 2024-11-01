### Présentation/Démo Rapide de la Fonctionnalité Backend

Pour une présentation rapide de la fonctionnalité backend, concentrez-vous sur les principaux points suivants :

1. **Aperçu de l'Architecture**
    - **Microservices** : Séparation des taches avec différents services, comme AuthService et UserService.
    - **Spring Boot** : Utilise Spring Boot pour créer des applications Spring autonomes et prêtes pour la production.

2. **Authentification et Autorisation**
    - **Jetons JWT** : Les jetons JWT sont utilisés pour sécuriser les points d'accès.
    - **Configuration de la Sécurité** : Configuration Spring Security protège les points d'accès.

3. **Couche de Service**
    - **AuthService** : Gère les opérations liées à l'authentification, telles que l'enregistrement des utilisateurs, la génération et la validation de jetons.
    - **UserService** : Gère les opérations CRUD liées aux utilisateurs.

4. **Couche Contrôleur**
    - **UserController** : Expose des points d'accès RESTful pour les opérations liées aux utilisateurs.
    - **Points d'accès** : `/api/users` pour ajouter, mettre à jour, supprimer et récupérer des utilisateurs.

5. **Configuration du Gateway**
    - **API Gateway** : Redirige les requêtes vers les microservices appropriés.
    - **Filtres de Sécurité** : Assure que seules les requêtes authentifiées sont transmises.

6. **Gestion des Erreurs**
    - **Codes d'état HTTP** : Les codes d'état (par exemple, 403, 404) sont utilisés pour indiquer diverses conditions d'erreur.

7. **Démo**
    - **Requêtes HTTP** : Montrez des exemples de requêtes HTTP pour tester les points d'accès de UserController.
    - **Gestion des Réponses** : Démontrez comment le backend gère les différentes réponses.
    - **Exemples de Code** : Présentez quelques extraits de code pour illustrer le fonctionnement.