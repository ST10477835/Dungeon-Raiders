/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonraiders;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Abulele
 */
class Entity implements Cloneable {
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    Scanner scanner = new Scanner(System.in);
    
    public String name;
    public double health;
    public double attack;
    public double luck = 1;
    public Weapon currentWeapon;
    public int coins = 20;
    public ArrayList<Weapon> inventory = new ArrayList<>();
    public boolean isDead = false; // used for checking health conflicts
    public boolean canEscape = true;

    public Entity(String name, double health, double attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    public void printHealth() {
        System.out.print(name + ":[" + "#".repeat((int) health) + "-".repeat(20 - (int) health) + "]\n");
    }

    public void printStatistics() {
        boolean inPrintStatistics = true;
        while (inPrintStatistics) {
            System.out.println("=".repeat(60) + "\nStatistics\n" + "=".repeat(60) + "\n" + name + "\nHealth: " + health + "\nAttack: " + attack + "\nLuck: " + luck + "\n" + "-".repeat(60) + "\nCoins: " + coins);
            System.out.println("=".repeat(60) + "\n1. Back");
            while (inPrintStatistics) {
                System.out.print(">>>");
                int ans = scanner.nextInt();
                if (ans == 1) {
                    inPrintStatistics = false;
                } else {
                    System.out.println("Invaild Input.");
                }
            }
        }
    }

    public void printInventory() {
        boolean inPrintInventory = true;
        while (inPrintInventory) {
            System.out.println("=".repeat(60) + "\nInventory\n" + "=".repeat(60) + "\nCurrent Weapon: " + currentWeapon.name + "\n" + "-".repeat(60));
            int count = 1;
            if (inventory.isEmpty()) {
                System.out.println("--Inventory is empty.");
            } else {
                for (Weapon weapon : inventory) {
                    System.out.println("- " + weapon.name);
                    count++;
                }
            }
            System.out.println("=".repeat(60) + "\n1. Back");
            while (inPrintInventory) {
                System.out.print(">>>");
                int ans = scanner.nextInt();
                if (ans == 1) {
                    inPrintInventory = false;
                } else {
                    System.out.println("Invaild Input.");
                }
            }
        }
    }
    
}
