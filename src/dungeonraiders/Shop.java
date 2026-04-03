/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonraiders;

import java.util.ArrayList;

/**
 *
 * @author Abulele
 */
class Shop {
    
    public ArrayList<Weapon> weapons = new ArrayList<>();
    public ArrayList<Item> items = new ArrayList<>();

    public Shop() {
        System.out.println("Shop created.(No weapons or items.)");
    }

    public Shop(ArrayList<Weapon> weapons, ArrayList<Item> items) {
        this.weapons = weapons;
        this.items = items;
        System.out.println("Shop created.");
    }

    public void setup() {
        //weapon-skill list configuration
        Weapon weapon = new Weapon("Sword", 10);
        Skill skill = new Skill("Slash", 4, -1);
        weapon.skillset.add(skill);
        weapons.add(weapon);
        //item list configuration
        Item item = new Item("New Item.", 0, Status.HEAL, 0);
        items.add(item);
    }
    
}
