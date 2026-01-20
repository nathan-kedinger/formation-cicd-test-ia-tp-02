# AI_NOTES.md - Traçabilité de l'assistance IA

## 1. Outil IA utilisé
*   GitHub Copilot (via l'extension IDE JetBrains)

## 2. Prompts utilisés
*   *Prompt initial* : "génère les tests en repectant SPEC_USER.md. Crée les fichiers si nécessaire"
*   *Prompt Partie 2* : "Applique la partie 2 maintenant" (génération des tests unitaires et fonctionnels dans les bons packages)
*   *Prompt Bonus* : "Réalise la même chose pour SPER_ORDER.md" (génération des tests pour les commandes)
*   *Prompt Partie 3* : "On peut passer à la partie 3" (implémentation du code métier pour faire passer les tests)

## 3. Analyse comparative (Recherche humaine vs IA)
*   **Analyse humaine** : Identification des règles de validation de format d'email (exactement un @, au moins un point après), des contraintes de mot de passe fort, et des calculs de remise et frais de priorité pour les commandes.
*   **Suggestions de l'IA** : L'IA a proposé des tests paramétrés (`@ParameterizedTest`) pour couvrir plusieurs variantes d'emails invalides et de formats de prix, ce qui est plus efficace que des tests manuels unitaires individuels.

## 4. Cas de tests proposés par l’IA
### Gestion Utilisateur (SPEC_USER.md)
*   **Nominal** : Création d'un utilisateur valide, accès admin pour rôle ADMIN.
*   **Limite** : Email avec espaces avant/après (vérification du trim).
*   **Erreur** : Emails sans @, avec plusieurs @, sans point dans le domaine. Mots de passe faibles. Rôle null.

### Gestion Commande (SPEC_ORDER.md)
*   **Nominal** : Calcul du total simple, calcul avec remise de 5%, calcul avec priorité.
*   **Limite** : Seuil exact de remise (100€). Arrondi monétaire à 2 décimales (ex: .123 vs .127).
*   **Erreur** : Quantité à 0 ou négative, prix unitaire à 0 ou négatif.

## 5. Analyse critique
*   **Tests conservés** : Tous les tests de validation de format et de logique de calcul.
*   **Tests rejetés** : Aucun test n'a été rejeté, mais j'ai dû corriger la structure des packages suggérée initialement pour qu'elle corresponde exactement aux répertoires source Java (`com.devops.cicd.user` au lieu de `user`).
*   **Décisions humaines** : J'ai dû implémenter les champs et le constructeur de la classe `Order` manuellement au préalable pour que les tests générés puissent compiler, l'IA ayant généré des tests appelant un constructeur qui n'existait pas encore dans le squelette.
