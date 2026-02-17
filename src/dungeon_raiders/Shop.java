/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeon_raiders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Abulele
 */
class Shop implements Serializable {
    
    ArrayList<Weapon> weaponInventory = new ArrayList<>(Arrays.asList(new Weapon("World Ender", 10, 1, 1), new Weapon("Steel Nail", 10, 1, 1), new Weapon("Mage's Staff", 10, 1, 1)));
    ArrayList<Potion> potionInventory = new ArrayList<>(Arrays.asList(new Potion("Regeneration", 1, 5, "heal"), new Potion("Attack", 1, 5, "atkBuff"), new Potion("Defense", 1, 5, "defBuff")));
    
}
