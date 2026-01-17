# 🧹 Application de Nettoyage Android

## 👩‍💻 Développée par
**Sara Saadi**
**Rim Laasri**

## 📅 Date de fin du projet
**18/01/2026**

---

## 📱 Description
Application Android développée en **Kotlin** permettant le **nettoyage manuel et automatique des fichiers temporaires** afin d’optimiser l’espace de stockage et améliorer les performances du téléphone.  
L’application intègre la **gestion des permissions**, les **notifications système** et l’exécution des tâches en **arrière-plan**.

---

## ⚙️ Technologies utilisées
- Kotlin
- Android SDK (**API 24+**)
- WorkManager (tâches en arrière-plan)
- Notifications Android
- EasyPermissions
- Espresso (tests UI)

---

## ✨ Fonctionnalités principales
- 🔘 Nettoyage manuel des fichiers temporaires
- 🔁 Nettoyage automatique (Auto Clean)
- 🔔 Notifications de nettoyage (début / progression / fin)
- 📊 Affichage de la progression via ProgressBar
- 🧪 Tests d’interface utilisateur avec Espresso

---

## 🧪 Tests
Les tests **Espresso** permettent de vérifier :
- Les clics sur les boutons (**Clean / Auto Clean**)
- Le changement d’état du nettoyage automatique
- L’affichage correct des messages
- Le fonctionnement de la **ProgressBar**

---

## 🖼️ Captures d’écran

<table>
  <tr>
    <td align="center">
      <img width="213" height="369" alt="Autorisation d’accès aux notifications" src="https://github.com/user-attachments/assets/b06f9948-9430-43b2-b513-10918a187a1e" /><br/>
      <small>Autorisation d’accès aux notifications</small>
    </td>
    <td align="center">
      <img width="214" height="370" alt="Accueil de l’application" src="https://github.com/user-attachments/assets/fac00622-3ae4-49e7-a680-3c1ff71265a6" /><br/>
      <small>Accueil de l’application</small>
    </td>
    <td align="center">
      <img width="215" height="371" alt="Manuelle Cleaning Activity" src="https://github.com/user-attachments/assets/4220ff2b-3bc9-46d6-87c1-db32814d649b" /><br/>
      <small>Manuelle Cleaning Activity</small>
    </td>
    <td align="center">
      <img width="215" height="377" alt="Notification de nettoyage" src="https://github.com/user-attachments/assets/a7a00c5f-fe37-4644-91e0-8f5787e95a44" /><br/>
      <small>Notification de nettoyage</small>
    </td>
  </tr>

  <tr>
    <td align="center">
      <img width="212" height="368" alt="Démarrage automatique" src="https://github.com/user-attachments/assets/da59052e-dcf1-43e1-8ca6-5cd2afbd5e60" /><br/>
      <small>Démarrage automatique</small>
    </td>
    <td align="center">
      <img width="214" height="371" alt="Notification de Démarrage automatique" src="https://github.com/user-attachments/assets/72f695b2-bd0d-41c5-9034-42f30f25df6d" /><br/>
      <small>Notification de Démarrage automatique</small>
    </td>
    <td align="center">
      <img width="215" height="365" alt="Arrete de Démarrage automatique" src="https://github.com/user-attachments/assets/33c817bd-6246-4a41-983e-360a1c5c6c0e" /><br/>
      <small>Arrete de Démarrage automatique</small>
    </td>
    </tr>
    <tr>
    <td align="center">
      <img width="215" height="376" alt="Notification de Arrete de Démarrage automatique" src="https://github.com/user-attachments/assets/724d2e4d-8694-47eb-a236-8a79f8c647b6" /><br/>
      <small>Notification de Arrete de Démarrage automatique</small>
    </td>
    <td align="center">
      <img width="487" height="227" alt="Test de progress" src="https://github.com/user-attachments/assets/31848061-e62c-4203-989b-799693e7a144" /><br/>
      <small>Test de progress</small>
    </td>
    <td align="center">
      <img width="480" height="225" alt="Test de storage utils" src="https://github.com/user-attachments/assets/0b140d9e-4a53-450a-9f4a-a67ad33827d8" /><br/>
      <small>Test de storage utils</small>
    </td>
  </tr>
</table>

---

## 🚀 Objectif du projet
Ce projet a été réalisé dans un **cadre pédagogique**, afin de mettre en pratique :
- La gestion des permissions Android
- Les tâches automatiques en arrière-plan
- Les notifications système
- Les tests UI avec Espresso  
