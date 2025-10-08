# eHospital

# Tests et Structure des Pages JSP

## Tests

### JUnit 5 pour les tests unitaires

- Tests des méthodes métier (services)
- Tests des régles de gestion
- Tests de validation des entités

### Mockito pour les tests avec mocks

- Simuler les dépendances (repositories)
- Tester les services de maniére isolée
- Vérifier les interactions entre composants

### Couverture de code

**Objectif minimum : 50% pour les couches Service et Repository**

---

## Structure des pages JSP recommandÃ©es

### Pages communes

- `login.jsp` : authentification (patients, docteurs, admin)
- `header.jsp` / `footer.jsp` : fragments réutilisables avec JSTL

### Pages patients

- `patient-dashboard.jsp` : tableau de bord patient
- `docteur-list.jsp` : liste des docteurs avec filtrage par dÃ©partement
- `reservation-form.jsp` : formulaire de réservation
- `mes-consultations.jsp` : historique des consultations

### Pages docteurs

- `docteur-dashboard.jsp` : tableau de bord docteur
- `planning.jsp` : affichage du planning avec les consultations
- `consultation-detail.jsp` : détails et saisie du compte rendu

### Pages admin

- `admin-dashboard.jsp` : tableau de bord admin avec statistiques
- `departement-management.jsp` : CRUD départements
- `docteur-management.jsp` : CRUD docteurs
- `salle-management.jsp` : CRUD salles avec visualisation des créneaux
- `consultations-supervision.jsp` : liste de toutes les consultations
