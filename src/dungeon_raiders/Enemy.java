/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeon_raiders;

import java.io.Serializable;

/**
 *
 * @author Abulele
 */
class Enemy implements Serializable {
    
    String name;
    String description;
    String weakness; //correlates to the attacking player skill damage type -> phys/elmnt
    int level = 0;
    int VIT = 1;
    int DEF = 1;
    int STR = 1;
    int INT = 1;
    int LUC = 1;
    int maxHealth = 0;
    int currentHealth = maxHealth;
    int currentDefense = 0;
    int currentPhysicalAttack = 0; //strGain()
    int currentMagicalAttack = 0; //intGain()
    int currentLuck = 0; //luckGain()
    int lvl;
    int hp = 10;

    public Enemy(String name, String description, int level, int VIT, int DEF, int STR, int INT, int LUC) {
        this.name = name;
        this.description = description;
        this.level = level;
        this.VIT = VIT;
        this.DEF = DEF;
        this.STR = STR;
        this.INT = INT;
        this.LUC = LUC;
    }
    
}
