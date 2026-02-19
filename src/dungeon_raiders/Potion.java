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
public class Potion implements Serializable {
    
    String name;
    int statIncrease = 3; //hp increase effect for now
    int level;
    int price;
    String effect;

    public Potion(String name, int level, int price, String effect) {
        this.name = name;
        this.level = level;
        this.price = price;
        this.effect = effect;
    }

    public int effect() {
        switch (effect) {
            case "heal" -> {
                System.out.println("You have successfully healed. " + statIncrease + "hp");
            }
            case "atkBuff" -> {
                System.out.println("You have successfully increased attack. " + statIncrease + "atk");
            }
            case "defBuff" -> {
                System.out.println("You have successfully increased defense. " + statIncrease + "def");
            }
        }
        return statIncrease;
    }
    
}
