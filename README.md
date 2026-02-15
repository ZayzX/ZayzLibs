# ZayzLibs

[![Minecraft](https://img.shields.io/badge/Minecraft-1.7.10-green.svg)](https://minecraft.net)
[![Forge](https://img.shields.io/badge/Forge-10.13.4.1614-blue.svg)](https://files.minecraftforge.net)
[![Java](https://img.shields.io/badge/Java-8-orange.svg)](https://www.java.com)

> Une bibliothèque d'utilitaires complète pour simplifier le développement de mods Minecraft Forge 1.7.10

## 📖 À propos

**ZayzLibs** est une bibliothèque Java conçue pour faciliter le développement de mods Minecraft. Elle offre un ensemble complet de classes utilitaires qui encapsulent les opérations courantes du modding, permettant aux développeurs de se concentrer sur la logique de leur mod plutôt que sur l'implémentation des fonctionnalités de base.

### 🎯 Objectif du projet

L'objectif de ZayzLibs est de fournir une API simple et intuitive pour manipuler tous les aspects du jeu Minecraft, de la gestion des joueurs aux calculs mathématiques avancés, en passant par les systèmes de messagerie et les effets visuels.

## ✨ Fonctionnalités principales

ZayzLibs propose **13 modules spécialisés** couvrant l'ensemble des besoins du modding :

### 🎮 Gestion des joueurs
- **PlayerAction** : Santé, nourriture, XP, inventaire, téléportation, modes de jeu, effets de potion, armure, etc.

### 💬 Communication
- **ChatAction** : Messages colorés, composants interactifs (cliquables et hover), broadcast, formatage avancé

### 🔢 Mathématiques
- **MathAction** : Vecteurs, distances, rotation, interpolation, collisions, courbes de Bézier, random avancé

### 🌍 Monde et environnement
- **WorldAction** : Manipulation du monde et de l'environnement
- **BlockAction** : Actions sur les blocs
- **EntityAction** : Gestion des entités

### 🎒 Items et inventaires
- **ItemAction** : Manipulation des items
- **InventoryAction** : Gestion des inventaires

### 💾 Données et réseau
- **NBTAction** : Manipulation des données NBT
- **PacketAction** : Gestion des paquets réseau

### 🎨 Effets et interface
- **ParticleAction** : Création et gestion des particules
- **SoundAction** : Gestion des sons
- **GuiAction** : Utilitaires pour les interfaces graphiques

## 🚀 Démarrage rapide

### Prérequis

- Java 8 ou supérieur
- Minecraft Forge 1.7.10 (version 10.13.4.1614 recommandée)
- Gradle (pour la compilation)

### Installation

#### En tant que dépendance dans votre mod

1. Téléchargez la dernière version depuis les [releases](https://github.com/ZayzX/ZayzLibs/releases)
2. Placez le fichier JAR dans le dossier `libs` de votre projet
3. Ajoutez la dépendance dans votre `build.gradle` :

```gradle
dependencies {
    compile files('libs/ZayzLibs-1.0.0.jar')
}
```

#### En tant que mod standalone

Placez simplement le fichier JAR dans le dossier `mods` de votre installation Minecraft.

### Utilisation de base

```java
import fr.zayzx.zayzlibs.utils.*;

// Exemple : Téléporter un joueur et lui envoyer un message
PlayerAction.teleport(player, x, y, z);
ChatAction.sendSuccess(player, "Téléportation réussie !");
```

## 📚 Documentation

La documentation complète avec exemples d'utilisation, référence API et guides sera disponible prochainement dans le [Wiki](https://github.com/ZayzX/ZayzLibs/wiki).

## 🔧 Compilation

Pour compiler le projet depuis les sources :

```bash
# Cloner le repository
git clone https://github.com/ZayzX/ZayzLibs.git
cd ZayzLibs

# Compiler avec Gradle
./gradlew build

# Le fichier JAR sera généré dans build/libs/
```

## 🤝 Contribution

Les contributions sont les bienvenues ! N'hésitez pas à :

- Signaler des bugs via les [Issues](https://github.com/ZayzX/ZayzLibs/issues)
- Proposer de nouvelles fonctionnalités
- Soumettre des Pull Requests
- Améliorer la documentation

## 📝 Licence

Ce projet est sous licence MIT.

## 👤 Auteur

**ZayzX**

## 🙏 Remerciements

- L'équipe Forge et FML pour leur travail sur Minecraft Forge
- La communauté Minecraft modding pour leur soutien et leurs retours

---

<div align="center">
  <sub>Développé avec ❤️ pour la communauté Minecraft modding</sub>
</div>
