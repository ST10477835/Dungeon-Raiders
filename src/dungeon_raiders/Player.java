/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeon_raiders;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Abulele
 */
class Player implements Serializable {
    
    String name;
    //xp dictates player level
    int level = 0;
    //used in levelling up player stats
    int tokens = 0;
    boolean canEscape = true;
    boolean canMoveToNextFloor = false;
    boolean canMoveToPreviousFloor = false;
    Role role;
    ArrayList<Weapon> playerWeaponInventory = new ArrayList<>();
    ArrayList<Potion> playerPotionInventory = new ArrayList<>();
    ArrayList<BeastLog> beastiary = new ArrayList<>();
    int VIT = 1;
    int DEF = 1;
    int STR = 1;
    int INT = 1;
    int LUC = 1;
    int maxHealth = 0;
    int currentHealth = 0; //hpGain()
    int currentLevel = 0;
    int currentXp = 0;
    int currentDefense = 0;
    int currentPhysicalAttack = 0; //strGain()
    int currentMagicalAttack = 0; //intGain()
    int currentLuck = 0; //luckGain()
    Weapon currentWeapon = new Weapon("Fists", 0, 1, 1);
    int expGauge = 0;
    int hp = 10;
    int coins = 20;

    public Player(String name) {
        this.name = name;
    }

    public double totalPhysicalDamage() {
        return currentPhysicalAttack + currentWeapon.currentPhysicalAttack;
    }

    public double totalMagicalDamage() {
        return currentMagicalAttack + currentWeapon.currentMagicalAttack;
    }
    
}
