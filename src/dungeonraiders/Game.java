/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonraiders;

import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Abulele
 */
class Game {
    Entity player;
    Entity enemy;
    Dungeon dungeon;
    Shop shop;
    
    
    Scanner scanner = new Scanner(System.in);
    boolean isRunning = true;

    public Game() {
        new Thread(() -> {
                startScreen();
        }).start();
    }
    public void incorrectFormat(){
        divider();
        System.out.println("Incorrect format");
        divider();
    }
    public void incorrectOption(){
        divider();
        System.out.println("That isn't an option.");
        divider();
    }
    
    public void divider(){
        System.out.println("=".repeat(60));
    }
    
    public int collectUserInt(){
        System.out.print(">>>");
        String text = scanner.nextLine();
        if(text.trim().isEmpty()) return collectUserInt();

        int ans = 0;
        try{
            ans = Integer.parseInt(text.trim());
        }catch(NumberFormatException  e){
            incorrectFormat();
            return collectUserInt();
        }
        return ans;
    }
    public String collectUserString(){
        System.out.print(">>>");
        String text = scanner.nextLine();
        if(text.trim().isEmpty()) return collectUserString();

        return text;
    }
    
    public void homeScreen() {
        System.out.println("=".repeat(60)
                    +"\nWelcome To Dungeon Raiders\n"
                    + "=".repeat(60)
                    + "\nWhat would you like to do?\n"
                    + "=".repeat(60)
                    + "\n1. New Game"
                    + "\n2. Load Game"
                    + "\n3. Leave Game\n"
                    + "=".repeat(60)
            );
        while (isRunning) {
            int ans = collectUserInt();
            
            switch (ans) {
                case 1 -> {
                    //new character maker
                    createCharacter();
                }
                case 2 -> {
                    //load via sql or json
                    loadCharacter();
                }
                case 3 -> {
                    System.out.println("Leaving Game...");
                    isRunning = false;
                }
                default -> {
                    incorrectOption();
                    homeScreen();
                }
            }
        }
    }
    
    public void createCharacter(){
        divider();
        System.out.println("What is your name: ");
        divider();
        
        String name = collectUserString();
        player = new Entity(name, 20.0, 3);
        startScreen();
    }
    public void loadCharacter(){
        System.out.println("Under construction.");
    }
    
    public void createDungeon(){
        dungeon = new Dungeon("Dungeon Ruin", 3);
    }
    
    public void startScreen() {
        boolean inStartScreen = true;
        System.out.println(
                "What would you like to do?\n" 
                + "=".repeat(60) 
                + "\n1. Go to shop" 
                + "\n2. Enter Dungeon" 
                + "\n3. Player Menu" 
                + "\n4. Go to Home Page\n" 
                + "=".repeat(60) 
        );
        while (inStartScreen) {
            int ans = collectUserInt();
            
            switch (ans) {
                case 1 -> {
                    shopScreen();
                }
                case 2 -> {
                    dungeonScreen();
                }
                case 3 -> {
                    playerMenuScreen();
                }
                case 4 -> {
                    inStartScreen = false;
                }
                default -> {
                    incorrectOption();
                }
            }
        }
    }
    public void shopScreen(){
        boolean inShopScreen = true;
        System.out.println("What would you like to look at?\n"
                +"=".repeat(60)
                +"\n1. Weapons\n2. Items\n3. Back\n"
                +"=".repeat(60)
        );
        while(inShopScreen){
            int ans = scanner.nextInt();
            switch(ans){
                case 1 ->{
                    weaponShopScreen();
                }
                case 2 ->{
                    itemShopScreen();
                }
                case 3 ->{
                    inShopScreen = false;
                }
                default->{
                    System.out.println("Invalid Input.");
                }
            }
        }
    }
    
    public void weaponShopScreen() {
        boolean inShopScreen = true;
        while (inShopScreen && !shop.weapons.isEmpty()) {
            System.out.println("What would you like to purchase?\n" + "=".repeat(60) + "\nCoins: " + player.coins);
            int count = 0;
            for (Weapon weapon : shop.weapons) {
                count++;
                System.out.printf("%d. [%s] --%d coins\n", count, weapon.name, weapon.price);
            }
            count++;
            System.out.print(count + ". [Back]\n" + "=".repeat(60) + "\n>>>");
            int ans = scanner.nextInt();
            if (ans > 0 && ans <= shop.weapons.size()) {
                Weapon weapon = shop.weapons.get(ans - 1);
                //printing weapon info before dealing with purchase logic
                weapon.weaponInformation();
                purchaseWeapon(weapon);
            } else if (ans == count) {
                inShopScreen = false;
                System.out.println("Leaving shop...");
            } else {
                System.out.println("Invalid Input.");
            }
        }
        if (shop.weapons.isEmpty()) {
            System.out.println("Shop is currently empty.");
        }
    }
    public void itemShopScreen(){
        boolean inItemShopScreen = true;
        while (inItemShopScreen && !shop.items.isEmpty()) {
            System.out.println("What would you like to purchase?\n" + "=".repeat(60) + "\nCoins: " + player.coins);
            int count = 0;
            for (Item item : shop.items) {
                count++;
                System.out.printf("%d. [%s] --%d coins\n", count, item.name, item.price);
            }
            count++;
            System.out.print(count + ". [Back]\n" + "=".repeat(60) + "\n>>>");
            int ans = scanner.nextInt();
            if (ans > 0 && ans <= shop.items.size()) {
                Item item = shop.items.get(ans - 1);
                //printing weapon info before dealing with purchase logic
                item.itemInformation();
                purchaseItem(item);
            } else if (ans == count) {
                inItemShopScreen = false;
                System.out.println("Leaving shop...");
            } else {
                System.out.println("Invalid Input.");
            }
        }
        if (shop.weapons.isEmpty()) {
            System.out.println("Shop is currently empty.");
        }
    }

    public void purchaseWeapon(Weapon weapon) {
        boolean inPurchaseWeapon = true;
        while (inPurchaseWeapon) {
            System.out.print("Would you like to purchase this weapon? (y/n)\n>>>");
            String ans = scanner.next();
            switch (ans) {
                case "y" -> {
                    if (player.coins >= weapon.price) {
                        // money leaves account -> put weapon into player inventory -> remove weapon from shop inventory
                        player.coins -= weapon.price;
                        System.out.println("You have purchased \"" + weapon.name + "\"");
                        shop.weapons.remove(weapon);
                        if (player.currentWeapon == null) {
                            player.currentWeapon = weapon;
                        } else {
                            player.inventory.add(weapon);
                        }
                        inPurchaseWeapon = false;
                    } else {
                        System.out.println("You have insufficient coins.");
                    }
                }
                case "n" -> {
                    inPurchaseWeapon = false;
                }
                default -> {
                    System.out.println("Invalid Input.");
                }
            }
        }
    }
    public void purchaseItem(Item item){
        boolean inPurchaseItem = true;
        while (inPurchaseItem) {
            System.out.print("Would you like to purchase this item? (y/n)\n>>>");
            String ans = scanner.next();
            switch (ans) {
                case "y" -> {
                    if (player.coins >= item.price) {
                        // money leaves account -> put item into player inventory -> remove item from shop inventory
                        player.coins -= item.price;
                        System.out.println("You have purchased \"" + item.name + "\"");
                        shop.items.remove(item);
                        inPurchaseItem = false;
                    } else {
                        System.out.println("You have insufficient coins.");
                    }
                }
                case "n" -> {
                    inPurchaseItem = false;
                }
                default -> {
                    System.out.println("Invalid Input.");
                }
            }
        }
    }

    public void dungeonScreen() {
        //new dungeon created when 
        if (dungeon == null||dungeon.isConquered) createDungeon();
        
        boolean inDungeonScreen = true;
        System.out.println("You have entered a \"" + dungeon.name + "\"");
        while (inDungeonScreen) {
            System.out.println("What would you like to do?\n" + "=".repeat(60) + "\nCurrent Floor: " + dungeon.currentFloor + "\n" + "-".repeat(60) + "\n1. Explore Current Floor");
            int count = 1;
            if (dungeon.canMoveToNextFloor) {
                count++;
                System.out.println(count + ". Move to Next Floor.");
            }
            if (dungeon.canMoveToPrevFloor) {
                count++;
                System.out.println(count + ". Move to Previous Floor.");
            }
            count++;
            System.out.print(count + ". Leave Dungeon" + "\n>>>");
            int ans = scanner.nextInt();
            if (ans == 1) {
                double prob = Math.random();
                if ( /*prob < 0.33*/prob < 0) {
                    //remove later
                    double coins = Math.floor(Math.random() * 50) + 1;
                    System.out.println("You have found loot\n- " + coins + " coins");
                    player.coins += coins;
                } else if ( /*prob < 0.66*/true) {
                    System.out.println("Fight triggered");
                    if (player.currentWeapon != null) {
                        System.out.println("isnt null");
                        fightScreen();
                    } else {
                        System.out.println("You have no weapon equipped.");
                    }
                } else {
                    System.out.println("Nothing found");
                }
            } else if (ans < count && ans > 1) {
                if (dungeon.canMoveToNextFloor && dungeon.canMoveToPrevFloor) {
                    if (ans == 2) {
                        //move to next floor
                        System.out.println("Moving to next floor...");
                        dungeon.currentFloor++;
                        floorConfiguration();
                    } else if (ans == 3) {
                        //move to previous floor
                        System.out.println("Moving to previous floor...");
                        dungeon.currentFloor--;
                        floorConfiguration();
                    }
                } else if (dungeon.canMoveToNextFloor) {
                    System.out.println("Moving to next floor...");
                    dungeon.currentFloor++;
                    floorConfiguration();
                } else if (dungeon.canMoveToPrevFloor) {
                    System.out.println("Moving to previous floor...");
                    dungeon.currentFloor--;
                    floorConfiguration();
                }
            } else if (ans == count) {
                System.out.println("Leaving Dungeon...");
                inDungeonScreen = false;
            } else {
                System.out.println("Invalid Input.");
            }
        }
    }

    public void fightScreen() {
        //look out for starting a fight with no weapons
        boolean inFightScreen = true;
        double prob = Math.random();
        boolean floorCrystal = false;
        if (prob > 0.50 || dungeon.pity == 2) {
            if (dungeon.atLeastOne) {
                try {
                    int index = (int) Math.floor(Math.random() * dungeon.enemies.size());
                    enemy = dungeon.currentFloor != dungeon.floors ? (Entity) dungeon.floorMasters.get(index).clone() : (Entity) dungeon.dungeonMaster.clone();
                    System.out.println(index);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                floorCrystal = true;
            } else {
                try {
                    enemy = (Entity) dungeon.enemies.get(0).clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                int index = (int) Math.floor(Math.random() * dungeon.enemies.size());
                enemy = (Entity) dungeon.enemies.get(index).clone();
                System.out.println(index);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            dungeon.atLeastOne = true;
        }
        System.out.println("You have encountered \"" + enemy.name + "\"");
        while (inFightScreen) {
            System.out.println("=".repeat(60));
            player.printHealth();
            System.out.print("----");
            enemy.printHealth();
            System.out.println();
            System.out.print("=".repeat(60) + "\nWhat would you like to do?\n" + "=".repeat(60) + "\n1. Weapon Skills" + "\n2. Switch Weapon" + (player.canEscape ? "\n3. Escape\n" : "\n") + "=".repeat(60) + "\n>>>");
            int ans = scanner.nextInt();
            switch (ans) {
                case 1 -> {
                    weaponSkills();
                    inFightScreen = (checkEnemyHealth() == false);
                }
                case 2 -> {
                    switchWeapon();
                }
                case 3 -> {
                    if (Math.random() > 0.5) {
                        System.out.println("You have successfully escaped");
                        enemy.isDead = true;
                        inFightScreen = false;
                    } else {
                        System.out.println("You have failed to escape.");
                        player.canEscape = false;
                    }
                }
                default -> {
                    System.out.println("Invalid Input");
                }
            }
            if (!enemy.isDead) {
                Skill enemySkill = enemy.currentWeapon.skillset.get(0);
                System.out.println(enemy.name + " has used \"" + enemySkill.name + "\" -" + enemySkill.attack + "HP");
                player.health -= enemySkill.attack;
                inFightScreen = (checkPlayerHealth() == false);
            }
        }
        boolean floorSwitchConfig = true;
        while (floorSwitchConfig) {
            if (floorCrystal && dungeon.currentFloor != dungeon.floors) {
                dungeon.currentLowestFloor++;
                System.out.println("Would you like to move to the next floor? (y/n)");
                String ans = scanner.next();
                switch (ans) {
                    case "y" -> {
                        System.out.println("Moving to next floor...");
                        dungeon.currentFloor++;
                        floorConfiguration();
                        floorSwitchConfig = false;
                    }
                    case "n" -> {
                        System.out.println("Staying on current floor...");
                        if (dungeon.currentFloor < dungeon.currentLowestFloor) {
                            dungeon.canMoveToNextFloor = true;
                        }
                        floorSwitchConfig = false;
                    }
                    default -> {
                        System.out.println("Invalid Input.");
                    }
                }
            } else {
                if (dungeon.currentFloor == dungeon.floors && enemy.name.equals("Dungeon Master")) {
                    System.out.println("Congratulations, you have conquered the Dungeon!");
                    dungeon.isConquered = true;
                }
                floorSwitchConfig = false;
            }
        }
    }

    public void floorConfiguration() {
        if (dungeon.currentFloor == dungeon.floors) {
            dungeon.canMoveToNextFloor = false;
        } else if (dungeon.currentFloor == dungeon.currentLowestFloor) {
            dungeon.canMoveToNextFloor = false;
        } else if (dungeon.currentFloor < dungeon.currentLowestFloor) {
            dungeon.canMoveToNextFloor = true;
        }
        dungeon.canMoveToPrevFloor = dungeon.currentFloor > 1;
        dungeon.atLeastOne = false; //resets after every floor change meaning youd have to face atleast one enemy every floor switch before facing FM or DM
        dungeon.pity = 0; //resets floor pity after every floor change
    }

    public boolean checkEnemyHealth() {
        if (enemy.health <= 0) {
            dungeon.pity++;
            System.out.println(enemy.name + " has died!");
            System.out.println(enemy.name + " has dropped:");
            boolean coinDrop = Math.random() > 0.5;
            boolean weaponDrop = Math.random() > 0.5;
            if (coinDrop) {
                System.out.println("- " + enemy.coins + " coins");
                player.coins += enemy.coins;
            }
            if (weaponDrop) {
                System.out.println("- " + enemy.currentWeapon.name);
                player.inventory.add(enemy.currentWeapon);
            }
            enemy.isDead = true;
            player.canEscape = true;
            return true;
        }
        return false;
    }

    public boolean checkPlayerHealth() {
        if (player.health <= 0) {
            System.out.println("You have died!");
            player.isDead = true;
            return true;
        }
        return false;
    }

    public boolean weaponSkills() {
        Weapon weapon = player.currentWeapon;
        boolean inWeaponSkills = true;
        while (inWeaponSkills) {
            System.out.println("\"" + weapon.name + "\" Skills" + "\n" + "=".repeat(60));
            int count = 1;
            for (Skill skill : weapon.skillset) {
                System.out.println(count + ". [" + skill.name + "] --" + skill.attack + " ATK");
                count++;
            }
            System.out.print(count + ". [Back]\n" + "=".repeat(60) + "\n>>>");
            int ans = scanner.nextInt();
            if (ans > 0 && ans <= weapon.skillset.size()) {
                Skill skill = weapon.skillset.get(ans - 1);
                skill.skillInformation();
                inWeaponSkills = (useSkill(skill) == true);
            } else if (ans == count) {
                return false;
            } else {
                System.out.println("Invalid Input.");
            }
        }
        return true;
    }

    public boolean useSkill(Skill skill) {
        while (true) {
            System.out.print("Would you like to use this skill? (y/n)\n>>>");
            String ans = scanner.next();
            switch (ans) {
                case "y" -> {
                    //Skill effect application on both enemy and player stats
                    if (skill.potency < 0) {
                        System.out.println(skill.name + " debuff applied to " + enemy.name);
                        enemy.health -= (skill.attack + player.attack) - skill.potency;
                    } else {
                        System.out.println(skill.name + " buff applied to " + player.name);
                        player.attack += skill.potency;
                        enemy.health -= (skill.attack + player.attack);
                    }
                    System.out.println("You have used \"" + skill.name + "\"");
                    return false;
                }
                case "n" -> {
                    return true;
                }
                default -> {
                    System.out.println("Invalid Input.");
                }
            }
        }
    }

    public boolean switchWeapon() {
        if (player.inventory.isEmpty()) {
            System.out.println("No weapon to switch to.");
            return false;
        }
        boolean inSwitchWeapon = true;
        while (inSwitchWeapon) {
            System.out.println("What weapon would you like to switch to?\n" + "=".repeat(60));
            int count = 1;
            for (Weapon weapon : player.inventory) {
                System.out.println(count + ". [" + weapon.name + "]");
                count++;
            }
            System.out.print(count + ". [Back]\n>>>");
            int ans = scanner.nextInt();
            if (ans > 0 && ans <= player.inventory.size()) {
                Weapon weapon = player.inventory.get(ans - 1);
                weapon.weaponInformation();
                inSwitchWeapon = (useWeapon(weapon) == true);
            } else if (ans == count) {
                return false;
            } else {
                System.out.println("Invalid Input.");
            }
        }
        return true;
    }

    public boolean useWeapon(Weapon weapon) {
        while (true) {
            System.out.print("Would you like to use this weapon? (y/n)\n>>>");
            String ans = scanner.next();
            switch (ans) {
                case "y" -> {
                    player.inventory.remove(weapon);
                    player.inventory.add(player.currentWeapon);
                    player.currentWeapon = weapon;
                    System.out.println("You have switched to  \"" + weapon.name + "\"");
                    return false;
                }
                case "n" -> {
                    return true;
                }
                default -> {
                    System.out.println("Invalid Input.");
                }
            }
        }
    }

    public void playerMenuScreen() {
        boolean inPlayerMenuScreen = true;
        while (inPlayerMenuScreen) {
            System.out.print("=".repeat(60) + "\nWhat would you like to look at?\n" + "=".repeat(60) + "\n1. Statistics" + "\n2. Inventory" + "\n3. Back\n" + "=".repeat(60) + "\n>>>");
            int ans = scanner.nextInt();
            switch (ans) {
                case 1 -> {
                    player.printStatistics();
                }
                case 2 -> {
                    player.printInventory();
                }
                case 3 -> {
                    inPlayerMenuScreen = false;
                }
                default -> {
                    System.out.println("Invalid Input.");
                }
            }
        }
    }

}
