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
public class Skill implements Serializable {
    
    String name;
    
    /**
     * rating is linked to damage multipliers: low = 1.5 dm medium = 2.0 dm high
     * = 2.5 dm
     */
    String rating;
    double dmgLow = 1.5;
    double dmgMed = 2.0;
    double dmgHig = 2.5;
    String type; // physical/ elemental type(fire, air, water, etc.)
    /**
     * Each skill can apply the following effects: - wither (periodically
     * damages the enemy, low amounts) -- 3 turns - stat buff (temporarily
     * boosts the player's stats) -- 3 turns - healing (heals the player, in
     * exchange for a moderate to high amount of mana) - stat de-buff
     * (temporarily drops the enemy's stats) -- 3 turns
     */
    String effect;
    int effectTracker = 0;

    Skill(String name, String rating, String type, String effect) {
        this.name = name;
        this.rating = rating; // e.g. HIGH, MEDIUM, LOW
        this.type = type; //attribute type
        this.effect =  effect; //additional status effect
    }

    public double damage(double playerBaseDamage, double weaponBaseDamage, String enemyWeakness) {
        double additionalDamage = enemyWeakness.equals(type) ? 1.5 : 0; //damage multiplier if enemy weakness hit
        double primaryDamage = playerBaseDamage + weaponBaseDamage;
        switch (rating) {
            case "low" -> {
                primaryDamage *= dmgLow;
            }
            case "medium" -> {
                primaryDamage *= dmgMed;
            }
            case "high" -> {
                primaryDamage *= dmgHig;
            }
            default -> {
                System.out.println("Skill damage issue encountered.");
                return 0;
            }
        }
        return primaryDamage + additionalDamage;
    }

    public void display() {
        System.out.println("=".repeat(60) + "\nSkill: " + name + "\nType: " + type + "\nRating: " + rating + "\nEffect: " + effect + "\n" + "=".repeat(60));
    }
    
}
