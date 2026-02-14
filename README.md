![License](https://img.shields.io/badge/License-GPLv325-orange)

# Dungeon Raiders

Dungeon Raiders is a text based rpg adventure game that requires players to play strategically and tactifully face every enemy encounter. It makes use of a turn based fighting system in which both the enemy and the player make their turns one after the other. This game was a challenge to recreate the feeling of older retro text based games whilst only using java as my medium. Currently the game allows players to by equipment and upgrade their overall stats but in future I want to implement more weapon and armour choice as well as add a system for local and online game saves.

---

# Gameplay

The game makes use of layered probability mechanics in controlling the outcomes of countless actions. Will you encounter an enemy? Will the chest you decide to open be a mimic? By adding this layer of unpredictability, this makes each playthrough unique.

---

## Probability rates

| Action | Probability % |
| --- | --- |
| Finding Chest | **10 %** (Affected by player luck stat) |
| Loot being hidden mimic | **35 %** |
| Encountering an Enemy | **30 %** |
| Finding next floor stairs | **25 %** (100% after reaching max pity)|

## Example Combat

### **Main Menu**

```
What would you like to do?
============================================================
1. Enter Shop
2. Enter Dungeon
3. Enter Player Options
4. Leave Game
============================================================
>>>
```
### **Battle Encounter**

```
You have encountered an enemy.
New Beast Log Made.
============================================================
BigSmokes: [==========]          Yale: [==========]
Level: 0                         Level: 0
============================================================
1. Attack
2. Use Potion
3. Escape
4. Display Stats
5. Check Beastiary
============================================================
>>>
```

### **Inside Shop**

```
Which weapon would you like to checkout?
============================================================
1. World Ender
2. Steel Nail
3. Mage's Staff
4. <--Back
============================================================
>>>
```
---

```
Which potion would you like to purchase?
============================================================
1. Regeneration
2. Attack
3. Defense
4. <--Back
============================================================
>>>
```

---

## Prerequisites

- Java 17+ (works with most modern JDKs/IDEs)
- Optional: NetBeans / IntelliJ IDEA / Eclipse

## Compile and Run

From the project’s src root (so the dungeon_raiders folder is visible):

```bash
# Compile
javac rpg_game/RPG_GAME.java

# Run (note the package-qualified name)
java rpg_game.RPG_GAME
```
### Run in NetBeans (or any IDE)

1. Create/open a Java project and ensure the **package** is `rpg_game`.  
2. Place `RPG_GAME.java` under `src/rpg_game/`.  
3. Set `RPG_GAME` as the **Main Class**.  
4. Click **Run**.

Project Structure

> This project is a single-file implementation containing multiple top-level classes in one source file.
```sql
src/
└── rpg_game/
    └── RPG_GAME.java   # Contains: RPG_GAME (main), Character (abstract), Enemy, Player
```
