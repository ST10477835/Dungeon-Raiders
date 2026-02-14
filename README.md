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


You have encountered an enemy.
New Beast Log Made.
============================================================
BigSmokes: [==========]          Yale: [==========]
Level: 1                         Level: 1
============================================================
1. Attack
2. Use Potion
3. Escape
4. Display Stats
5. Check Beastiary
============================================================
>>>

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

