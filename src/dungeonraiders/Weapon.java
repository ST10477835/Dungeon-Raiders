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
class Weapon {
    
    public String name;
    public int price;
    public String lore;
    public String description;
    public ArrayList<Skill> skillset = new ArrayList<>();

    public Weapon(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Weapon(String name, int price, String lore, String description) {
        this.name = name;
        this.price = price;
        this.lore = lore;
        this.description = description;
    }

    public void weaponInformation() {
        Game.divider();
        System.out.println("[" + name + "] -- " + price + " coins");
        Game.divider();
        System.out.println("Lore:\n" + (lore == null ? "--lore" : lore) + "\nDescription:\n" + (description == null ? "--description" : description));
        Game.divider();
        System.out.println("Skills");
        Game.divider();
        
        if(skillset.isEmpty()){
            System.out.println("-- No skills found.");
            return;
        }
        
        for (Skill skill : skillset) {
            System.out.printf("- %s\n", skill.name);
        }
    }
    
}
