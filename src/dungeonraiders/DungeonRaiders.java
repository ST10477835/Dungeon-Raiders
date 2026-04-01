/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dungeonraiders;

import javax.swing.SwingUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class DungeonRaiders {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            new Game();
        });
    }

}
enum Status{
    HEAL,
    BUFF,
    DEBUFF
}
class Item{
    public String name;
    public int price;
    public Status status;
    public double potency;
    
    public Item(String name, int price, Status status, double potency){
        this.name = name;
        this.price = price;
        this.status = status;
        this.potency = potency;
    }
    
    public void itemInformation(){
        System.out.println("=".repeat(60) + "\n[" + name + "] -- " + price + " coins" + "\n" + "=".repeat(60)+"\nStatus: "+status);
    }
    
}
class Shop{
    public ArrayList<Weapon> weapons;
    public ArrayList<Item> items;
    
    public Shop(){
        System.out.println("Shop created.(No weapons or items.)");
    }
    public Shop(ArrayList<Weapon> weapons, ArrayList<Item> items){
        this.weapons = weapons;
        this.items = items;
        System.out.println("Shop created.");
    }
    
    public void setup(){
        //weapon-skill list configuration
        Weapon weapon = new Weapon("Sword", 10);
        Skill skill = new Skill("Slash", 4, -1);
        weapon.skillset.add(skill);
        weapons.add(weapon);
        
        //item list configuration
        Item item = new Item("New Item.", 0,Status.HEAL, 0);
        items.add(item);
    }
}
