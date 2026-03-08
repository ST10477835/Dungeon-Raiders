/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dungeon_raiders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.SwingUtilities;

/**
 *
 * @author Abulele
 */
public class Dungeon_Raiders {

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

class Game {

    Entity player = new Entity("Player", 10.0, 2, 1);
    Entity enemy = new Entity("Enemy", 10.0, 2, 1);

    ArrayList<Weapon> shop = new ArrayList<>(Arrays.asList(new Weapon("Sword")));

    Scanner scanner = new Scanner(System.in);
    boolean isRunning = true;

    public Game() {
        System.out.println("Game Created.");
        new Thread(() -> {
            while (isRunning) {
                System.out.println("Running...");
                buyScreen();
            }
        }).start();
    }

    public void startScreen() {
        System.out.println("""
                           1. Go to shop
                           2. Fight enemy
                           >>>""");
        int ans = scanner.nextInt();
        switch (ans) {
            case 1 -> {
                buyScreen();
            }
            case 2 -> {
                fightScreen();
            }
            default -> {
                System.out.println("Invaild Input.");
            }
        }
    }

    public void buyScreen() {
        boolean inBuyScreen = true;
        while (inBuyScreen) {
            System.out.println("What would you like to purchase?"
                    + "=".repeat(60)
            );
            int count = 0;
            for (Weapon weapon : shop) {
                System.out.println((count++)+"[" + shop + "]");
            }
            System.out.print((count++)+"Back"+"=".repeat(60) + "\n>>>");
            int ans = scanner.nextInt();
            if (ans > 0 && ans <= shop.size()) {
                Weapon weapon = shop.get(ans - 1);
                System.out.println("You have purchase " + weapon.name);
            }
        }
    }

    public void fightScreen() {
        System.out.println("What would you like to do?\n"
                + "=".repeat(60)
                + "\n1. Weapon Skills"
                + "\n2. Switch Weapon\n"
                + "=".repeat(60)
        );
        int ans = scanner.nextInt();
        switch (ans) {
            case 1 -> {
                weaponSkills();
            }
            case 2 -> {
                switchWeapon();
            }
            default -> {
                System.out.println("Invalid Input");
            }
        }
    }

    public void weaponSkills() {

    }

    public void switchWeapon() {

    }
}

class Entity {

    public String name;
    public double health;
    public double attack;
    public double luck;

    public Entity(String name, double health, double attack, double luck) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.luck = luck;
    }
}

class Weapon {

    public String name;
    public String lore;
    public String description;
    public ArrayList<Skill> skillset = new ArrayList<>();

    public Weapon(String name) {
        this.name = name;
    }

    public Weapon(String name, String lore, String description) {
        this.name = name;
        this.lore = lore;
        this.description = description;
    }
}

class Skill {

    public String name;
    public double attack;
    public double effect;//-= for debuff or += for buff 

    public Skill(String name, double attack, double effect) {
        this.name = name;
        this.attack = attack;
        this.effect = effect;
    }
}
