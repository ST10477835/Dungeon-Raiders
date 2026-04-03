/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonraiders;

import java.util.Scanner;

/**
 *
 * @author Abulele
 */
class Game {

    Entity player = new Entity("Player", 20, 20);
    Entity enemy;
    Dungeon dungeon;
    Shop shop = new Shop();

    Scanner scanner = new Scanner(System.in);
    boolean isRunning = true;

    public Game() {
        new Thread(() -> {
            homeScreen();
        }).start();
    }

    public double generateProbability() {
        return Math.random();
    }

    public void incorrectFormat() {
        divider();
        System.out.println("Incorrect format");
        divider();
    }

    public void incorrectOption() {
        divider();
        System.out.println("That isn't an option.");
        divider();
    }

    public void nothingFound() {
        divider();
        System.out.println("Nothing Found.");
        divider();
    }

    public static void divider() {
        System.out.println("=".repeat(60));
    }

    public int collectUserInt() {
        System.out.print(">>>");
        String text = scanner.nextLine();
        if (text.trim().isEmpty()) {
            return collectUserInt();
        }

        int ans = 0;
        try {
            ans = Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            incorrectFormat();
            return collectUserInt();
        }
        return ans;
    }

    public String collectUserString() {
        System.out.print(">>>");
        String text = scanner.nextLine();
        if (text.trim().isEmpty()) {
            return collectUserString();
        }

        return text;
    }

    public void homeScreen() {
        System.out.println("=".repeat(60)
                + "\nWelcome To Dungeon Raiders\n"
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

    public void createCharacter() {
        divider();
        System.out.println("What is your name: ");
        divider();

        String name = collectUserString();
        player = new Entity(name, 20.0, 3);
        startScreen();
    }

    public void loadCharacter() {
        System.out.println("Under construction.");
    }

    public void createDungeon() {
        if (dungeon == null) {
            dungeon = new Dungeon("Dungeon Ruin", 3);
        }
    }

    public void createShop() {
        shop = new Shop();
        shop.setup();
    }

    public void createEnemy() {
        try {
            double range = generateProbability() * dungeon.enemies.size();
            int index = (int) Math.floor(range);

            enemy = (Entity) dungeon.enemies.get(index).clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Enemy failed to initialize.");
            e.printStackTrace();
        }
    }

    public void startScreen() {
        if (shop == null) {
            createShop();
        }

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
                    continue;
                }
            }
            if (inStartScreen) {
                startScreen();
            }
        }
    }

    public void shopScreen() {
        boolean inShopScreen = true;

        System.out.println("What would you like to look at?\n"
                + "=".repeat(60)
                + "\n1. Weapons"
                + "\n2. Items"
                + "\n3. Back\n"
                + "=".repeat(60)
        );

        while (inShopScreen) {
            int ans = collectUserInt();

            switch (ans) {
                case 1 -> {
                    weaponShopScreen();
                }
                case 2 -> {
                    itemShopScreen();
                }
                case 3 -> {
                    inShopScreen = false;
                }
                default -> {
                    incorrectOption();
                    continue;
                }
            }
            if (inShopScreen) {
                shopScreen();
            }
        }
    }

    public void weaponShopScreen() {

        if (shop.weapons.isEmpty()) {
            System.out.println("Shop is currently empty.");
            return;
        }

        boolean inWeaponShopScreen = true;

        System.out.println("What would you like to purchase?");
        divider();
        System.out.println("Coins: " + player.coins);
        divider();

        int count = 0;
        for (Weapon weapon : shop.weapons) {
            count++;
            System.out.printf("%d. [%s] --%d coins\n", count, weapon.name, weapon.price);
        }

        count++;
        System.out.println(count + ". [Back]");
        divider();

        while (inWeaponShopScreen) {
            int ans = collectUserInt();

            if (ans > 0 && ans <= shop.weapons.size()) {
                Weapon weapon = shop.weapons.get(ans - 1);
                checkout(weapon);
                if (shop.weapons.isEmpty()) {
                    return;
                }
            } else if (ans == count) {
                System.out.println("Leaving shop...");
                return;
            } else {
                incorrectOption();
                continue;
            }
            weaponShopScreen();
        }
    }

    public void itemShopScreen() {

        if (shop.items.isEmpty()) {
            System.out.println("Shop is currently empty.");
            return;
        }

        boolean inItemShopScreen = true;

        System.out.println("What would you like to purchase?");
        divider();
        System.out.println("Coins: " + player.coins);
        divider();

        int count = 0;
        for (Item item : shop.items) {
            count++;
            System.out.printf("%d. [%s] --%d coins\n", count, item.name, item.price);
        }

        count++;
        System.out.println(count + ". [Back]");
        divider();

        while (inItemShopScreen) {
            int ans = collectUserInt();

            if (ans > 0 && ans <= shop.items.size()) {
                Item item = shop.items.get(ans - 1);
                checkout(item);
                if (shop.items.isEmpty()) {
                    return;
                }
            } else if (ans == count) {
                System.out.println("Leaving shop...");
                return;
            } else {
                incorrectOption();
                continue;
            }
            itemShopScreen();
        }
    }

    public void checkout(Weapon weapon) {
        boolean inCheckout = true;

        //printing weapon info before dealing with purchase logic
        weapon.weaponInformation();

        System.out.println("Would you like to purchase this weapon? (y/n)");

        while (inCheckout) {
            String ans = collectUserString();

            switch (ans) {
                case "y" -> {
                    purchase(weapon);
                }
                case "n" -> {
                    System.out.println("Cancelling purchase");
                }
                default -> {
                    incorrectOption();
                    continue;
                }
            }
            inCheckout = false;
        }
    }

    public void checkout(Item item) {
        boolean inCheckout = true;

        //printing weapon info before dealing with purchase logic
        item.itemInformation();

        System.out.println("Would you like to purchase this item? (y/n)");

        while (inCheckout) {
            String ans = collectUserString();

            switch (ans) {
                case "y" -> {
                    purchase(item);
                }
                case "n" -> {
                    System.out.println("Cancelling purchase");
                }
                default -> {
                    incorrectOption();
                    continue;
                }
            }
            inCheckout = false;
        }
    }

    public void purchase(Weapon weapon) {
        if (player.coins >= weapon.price) {// money leaves account -> put weapon into player inventory -> remove weapon from shop inventory
            player.coins -= weapon.price;
            divider();
            System.out.println("You have purchased \"" + weapon.name + "\"");
            divider();

            shop.weapons.remove(weapon);

            if (player.currentWeapon == null) {
                player.currentWeapon = weapon;
            } else {
                player.inventory.add(weapon);
            }
        } else {
            System.out.println("You have an insufficient amount of coins.");
        }
    }

    public void purchase(Item item) {
        if (player.coins >= item.price) {// money leaves account -> put item into player inventory -> remove item from shop inventory
            player.coins -= item.price;
            divider();
            System.out.println("You have purchased \"" + item.name + "\"");
            divider();

            shop.items.remove(item);
        } else {
            System.out.println("You have an insufficient amount of coins.");
        }
    }

    public void dungeonScreen() {
        createDungeon();

        boolean inDungeonScreen = true;
        System.out.println("You have entered a \"" + dungeon.name + "\"");
        System.out.println("What would you like to do?");
        divider();
        System.out.println("Current Floor: " + dungeon.currentFloor);
        divider();
        System.out.println("1. Explore Current Floor");
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
        System.out.println(count + ". Leave Dungeon");
        divider();

        while (inDungeonScreen) {

            int ans = collectUserInt();

            if (ans == 1) {
                exploreFloor();
            } else if (ans < count && ans > 1) {
                if (dungeon.canMoveToNextFloor && dungeon.canMoveToPrevFloor) {
                    if (ans == 2) {
                        //move to next floor
                        System.out.println("Moving to next floor...");
                        dungeon.currentFloor++;
                        floorConfiguration();

                        dungeonScreen();
                    } else if (ans == 3) {
                        //move to previous floor
                        System.out.println("Moving to previous floor...");
                        dungeon.currentFloor--;
                        floorConfiguration();

                        dungeonScreen();
                    }
                } else if (dungeon.canMoveToNextFloor) {
                    System.out.println("Moving to next floor...");
                    dungeon.currentFloor++;
                    floorConfiguration();

                    dungeonScreen();
                } else if (dungeon.canMoveToPrevFloor) {
                    System.out.println("Moving to previous floor...");
                    dungeon.currentFloor--;
                    floorConfiguration();

                    dungeonScreen();
                }
            } else if (ans == count) {
                System.out.println("Leaving Dungeon...");
                inDungeonScreen = false;
            } else {
                incorrectOption();
            }
        }
    }

    public void exploreFloor() {
        double prob = generateProbability();
        if (prob < 0.33) {
            generateLoot();
        } else if (prob < 0.66) {
            fightScreen();
        } else {
            nothingFound();
        }
    }

    public void generateLoot() {//add mimics
        System.out.println("You have found loot: ");
        divider();
        //coins
        int loot = (int) Math.floor(generateProbability() * 10) + 1;
        System.out.println("- " + loot + "coins");
        divider();
    }

    public boolean escape() {
        if (generateProbability() > 0.5) {
            System.out.println("You have successfully escaped");
            return true;
        } else {
            System.out.println("You have failed to escape.");
            player.canEscape = false;
            return false;
        }
    }

    public void fightScreen() {
        System.out.println("Fight triggered");

        if (player.currentWeapon == null) {//look out for starting a fight with no weapons
            divider();
            System.out.println("You have no weapon equipped.");
            divider();
            return;
        }

        createEnemy();

        boolean inFightScreen = true;

        System.out.println("You have encountered \"" + enemy.name + "\"");
        divider();
        player.printHealth();
        System.out.print("----");
        enemy.printHealth();
        divider();
        System.out.println("What would you like to do?");
        divider();
        System.out.println("""
                1. Weapon Skills
                2. Switch Weapon"""
                + (player.canEscape ? "\n3. Escape" : ""));
        divider();

        while (inFightScreen) {//while loop maintains the same enemy
            int ans = collectUserInt();

            switch (ans) {
                case 1 -> {
                    weaponSkills();
                    inFightScreen = !checkEnemyHealth();//enemy alive == false
                }
                case 2 -> {
                    switchWeapon();
                }
                case 3 -> {
                    if (player.canEscape) {
                        if (escape()) {
                            return;
                        }
                        System.out.println("ran");
                    } else {
                        incorrectOption();
                    }
                }
                default -> {
                    incorrectOption();
                }
            }

            if (inFightScreen && !player.inventory.isEmpty()) {//would have been switched by weapon skill
                Skill skill = enemy.currentWeapon.skillset.get(0);
                enemyUseSkill(skill);

                inFightScreen = checkPlayerHealth();
            }
        }

        handleFloors();
        dungeonScreen();
    }

    public void handleFloors() {
        if (!checkPlayerHealth()) {
            System.out.println("Would you like to move to the next floor? (y/n)");
            divider();
            dungeon.currentLowestFloor++;

            String ans = collectUserString();

            switch (ans) {
                case "y" -> {
                    moveToNextFloor();
                }
                case "n" -> {
                    stayOnCurrentFloor();
                }
                default -> {
                    incorrectOption();
                }
            }
        }
    }

    //come back and optimize
    public void moveToNextFloor() {
        System.out.println("Moving to next floor...");
        System.out.println("ran");
        dungeon.currentFloor++;
        floorConfiguration();
    }

    public void stayOnCurrentFloor() {
        System.out.println("Staying on current floor...");
        if (dungeon.currentFloor < dungeon.currentLowestFloor) {
            dungeon.canMoveToNextFloor = true;
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
    }

    public boolean checkEnemyHealth() {
        if (enemy.health <= 0) {
            System.out.println(enemy.name + " has died!");
            divider();
            System.out.println(enemy.name + " has dropped:");
            generateLoot();

            player.canEscape = true;
            enemy = null;
            return true;
        }
        return false;
    }

    public boolean checkPlayerHealth() {
        if (player.health <= 0) {
            System.out.println("You have died!");
            enemy = null;
            return true;
        }
        return false;
    }

    public void playerReset() {
        player.health = 20;
    }

    public boolean weaponSkills() {
        Weapon weapon = player.currentWeapon;

        System.out.println("\"" + weapon.name + "\" Skills");
        divider();

        int count = 0;
        for (Skill skill : weapon.skillset) {
            count++;
            System.out.println(count + ". [" + skill.name + "] --" + skill.attack + " ATK");
        }
        System.out.println(count + ". [Back]");
        divider();

        int ans = collectUserInt();

        if (ans > 0 && ans <= weapon.skillset.size()) {
            Skill skill = weapon.skillset.get(ans - 1);

            skill.skillInformation();
            return useSkill(skill);
        } else if (ans == count) {
            return false;
        } else {
            incorrectOption();
            return weaponSkills();
        }
    }

    public boolean useSkill(Skill skill) {
        System.out.println("Would you like to use this skill? (y/n)");
        String ans = collectUserString();

        switch (ans) {
            case "y" -> {
                enemy.health -= (skill.attack + player.attack);
                System.out.println("You have used \"" + skill.name + "\"");
                return false;
            }
            case "n" -> {
                return true;
            }
            default -> {
                incorrectOption();
                return useSkill(skill);
            }
        }
    }

    public void enemyUseSkill(Skill skill) {
        System.out.println(enemy.name + " has used \"" + skill.name + "\" -" + skill.attack + "HP");
        player.health -= skill.attack;
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
                incorrectOption();
            }
        }
        return true;
    }

    public boolean useWeapon(Weapon weapon) {
        boolean inUseWeapon = true;

        System.out.print("Would you like to use this weapon? (y/n)");
        while (inUseWeapon) {
            String ans = collectUserString();

            switch (ans) {
                case "y" -> {
                    player.inventory.remove(weapon);
                    player.inventory.add(player.currentWeapon);
                    player.currentWeapon = weapon;
                    System.out.println("You have switched to  \"" + weapon.name + "\"");
                }
                case "n" -> {
                    return false;
                }
                default -> {
                    incorrectOption();
                }
            }
        }
        return true;
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
