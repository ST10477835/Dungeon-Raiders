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
class Weapon implements Serializable {
    //Serializable lets weapon information get stored on game save binary files

    String name;

    int level = 1;//set bounds at level 10
    int experiencePoints = 0;

    int durability = 100;
    double price;

    double damage; //flat damage with no boosts or enhancements
    double critRate;//dictates how frequently crit attacks hit
    double critDamage;// damage multiplier when crit lands

    ArrayList<Skill> skillSlots = new ArrayList<>();//min = 2 and max = 6

    Weapon(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public void display(){
        System.out.println("=".repeat(60)
                +"\nWeapon: "+name
                +"\nLevel: "+level
                +"\nDamage: "+damage
                +"\n"+"=".repeat(60));
    }
    
    

}

