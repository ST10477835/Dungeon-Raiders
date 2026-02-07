/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dungeon_raiders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.SwingUtilities;

/**
 *
 * @author abule
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

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    Scanner scanner = new Scanner(System.in);
    SavePlayerData playerData = new SavePlayerData();
    SaveEnemyData enemyData = new SaveEnemyData();
    String playerFileName = "game_save.bin";

    String folderPath = "C:\\Users\\abule\\OneDrive\\Documents\\NetBeansProjects\\Dungeon_Raiders";
    File folder = new File(folderPath);
    File[] uniFiles = folder.listFiles();
    ArrayList<File> files = new ArrayList<>();

    boolean isRunning = true;
    boolean canEscape = true;
    boolean isFighting = false;
    boolean canMoveNextFloor = false;
    boolean isInStore = false;
    boolean isInWeaponStore = false;
    boolean isInPotionStore = false;
    boolean inAllocateTokens = false;
    boolean isInDungeon = false;
    boolean isInMenu = false;
    boolean inInventory = false;
    boolean isInWeaponInventory = false;
    boolean isInPotionInventory = false;
    boolean buyingPotion = false;
    boolean buyingWeapon = false;
    boolean isInBeastiary = false;
    boolean inLevelUp = false;

    double physicalMultiplier;
    double magicMultiplier;

    Player player = new Player("Abu");
    Enemy enemy;
    Dungeon dungeon;

    ArrayList<Weapon> shopWeaponInventory = new ArrayList<>(Arrays.asList(new Weapon("World Ender", 10, 2), new Weapon("Steel Nail", 10, 2), new Weapon("Mage's Staff", 10, 2)));
    ArrayList<Potion> shopPotionInventory = new ArrayList<>(Arrays.asList(new Potion("Regeneration", 1, 5, "heal"), new Potion("Attack", 1, 5, "atkBuff"), new Potion("Defense", 1, 5, "defBuff")));

    ArrayList<Enemy> enemies = loadEnemyData();
    int enemyFloorPity = 0;

    public Game() {

        for (File file : uniFiles) {
            if (file.isFile() && file.getName().endsWith(".bin")) {
                files.add(file);
                System.out.println(file.getName() + " added");
            }
        }
        
        System.out.println("works");
        for(Enemy enemy :enemies){
            System.out.println(enemy.name);
        }

        /*new Thread(() -> {
            while (isRunning) {
                startScreen();
            }
        }).start();*/
    }

    public void startScreen() {
        textBox("""
                Welcome to Dungeon Raiders
                1. New Game
                2. Load Game
                3. Exit Game""");
        System.out.print(">>>");
        int ans = scanner.nextInt();
        delay(800);
        switch (ans) {
            case 1 -> {
                newGame();
            }
            case 2 -> {
                loadSave();
            }
            case 3 -> {
                isRunning = false;
            }
            default -> {
                System.out.println("Invalid Input.");
            }
        }

    }

    public void newGame() {
        createPlayer();
        isInMenu = true;
        menu();
    }

    public void loadSave() {
        if (files != null) {
            System.out.println("Which save would you like to open: \n" + "=".repeat(60));
            int count = 0;

            for (File file : files) {//Prints out .bin files in game directory
                if (file.isFile() && file.getName().endsWith(".bin")) {
                    count++;
                    System.out.println(count + ". " + file.getName());
                }
            }
            count++;
            System.out.println(count + ". <-- Back");

            System.out.print("=".repeat(60) + "\n" + ">>>");
            int ans = scanner.nextInt();
            delay(800);

            if (ans > 0 && ans <= files.size()) {
                System.out.println("You have opened " + files.get(ans - 1).getName());
                player = loadPlayer(files.get(ans - 1).getName());

                System.out.println("Welcome back " + player.name + ". Here are your current stats:");
                displayStats();
                delay(800);

                isInMenu = true;
                menu();
            }

        } else {
            System.out.println("Directory does not exist or is not a folder.");
        }

    }

    public void createEnemy() {
        enemy = enemies.get((int) Math.floor(Math.random() * enemies.size()));
    }

    public void createDungeon() {
        dungeon = new Dungeon();
        System.out.println("New dungeon created.");
    }

    public void showWeaponStats(Weapon weapon) {
        textBox("Name: " + weapon.name
                + "\nDurability: " + weapon.durability
                + "\nDamage: " + weapon.dmg
                + "\nCurrent Price: " + weapon.price);
    }

    public void showPotionStats(Potion potion) {
        textBox("Name: " + potion.name
                + "\nEffect: " + potion.effect
                + "\nLevel: " + potion.level
                + "\nPotency: " + potion.statIncrease
                + "\nPrice: " + potion.price);
    }

    public void textBox(String text) {
        System.out.println("=".repeat(60));
        System.out.println(text);
        System.out.println("=".repeat(60));
    }

    public void menu() {
        while (isInMenu) {
            System.out.println("What would you like to do?");
            textBox("""
                1. Enter Store
                2. Enter Dungeon
                3. Check Inventory
                4. Use Tokens
                5. Check Beastiary
                6. Leave Game""");
            System.out.print(">>>");
            int ans = scanner.nextInt();
            delay(800);
            switch (ans) {
                case 1 -> {
                    isInStore = true;
                    shopChoices();
                }
                case 2 -> {
                    isInDungeon = true;
                    dungeonChoices();
                }
                case 3 -> {
                    inInventory = true;
                    checkInventory();
                }
                case 4 -> {
                    inAllocateTokens = true;
                    allocateTokens();
                }
                case 5 -> {
                    isInBeastiary = true;
                    inBeastiary();
                }
                case 6 -> {
                    saveGame();
                    isInMenu = false;
                    isRunning = false;
                }
            }
        }
    }

    public void saveGame() {
        System.out.println("Would you like to save the game? (y/n)");
        System.out.print(">>>");
        String choice = scanner.next();
        delay(800);
        switch (choice) {
            case "y" -> {

                if (files != null) {
                    System.out.println("Which file would you like to save into: \n" + "=".repeat(60));
                    int count = 0;
                    System.out.println("=".repeat(60));
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".bin")) {
                            count++;
                            System.out.println(count + ". " + file.getName());
                        }
                    }
                    count++;
                    System.out.println(count + ". Create new save");
                    System.out.print(">>>");
                    int ans = scanner.nextInt();
                    delay(800);
                    if (ans == count) {
                        System.out.println("Type in new file save name.");
                        System.out.print(">>>");
                        String fileName = scanner.next();
                        if (!fileName.endsWith(".bin")) {
                            fileName += ".bin";
                        }
                        playerData.player = player;
                        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName))) {
                            os.writeObject(playerData);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (ans > 0 && ans <= files.size()) {
                        System.out.println("running");
                        playerData.player = player;
                        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(files.get(ans - 1).getName()))) {
                            os.writeObject(playerData);
                            System.out.println("Data saved to " + files.get(ans - 1).getName());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
            case "n" -> {
                System.out.println("Leaving Game...");
            }
            default -> {
                System.out.println("Incorrect input.");
            }
        }
        delay(800);
    }

    public Player loadPlayer(String fileName) {
        try (ObjectInputStream is
                = new ObjectInputStream(new FileInputStream(fileName))) {

            SavePlayerData data = (SavePlayerData) is.readObject();
            return data.player;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void checkPotionInventory() {
        while (isInPotionInventory) {
            if (!player.playerPotionInventory.isEmpty()) {
                System.out.println("What potion would you like to checkout");
                int count = 0;
                System.out.print("=".repeat(60));
                for (Potion potion : player.playerPotionInventory) {
                    count++;
                    System.out.print("\n" + count + ". " + potion.name + "");
                }
                System.out.println("\n" + "=".repeat(60));
                count++;
                System.out.println(count + ". <--Back");
                System.out.print(">>>");
                int ans = scanner.nextInt();
                delay(800);
                if (ans == count) {
                    isInPotionInventory = false;
                } else if (ans <= player.playerPotionInventory.size() || ans > 0) {
                    Potion potion = player.playerPotionInventory.get(ans - 1);
                    showPotionStats(potion);
                    delay(800);
                    System.out.println("1. <--Back");
                    System.out.print(">>>");
                    int answer = scanner.nextInt();
                }
            } else {
                textBox("No potions found.");
                isInPotionInventory = false;
                delay(800);
            }
        }
    }

    public void checkWeaponInventory() {
        while (isInWeaponInventory) {
            if (!player.playerWeaponInventory.isEmpty()) {
                System.out.println("What weapon would you like to checkout");
                int count = 0;
                System.out.print("=".repeat(60));
                for (Weapon weapon : player.playerWeaponInventory) {
                    count++;
                    System.out.print("\n" + count + ". " + weapon.name + "");
                }
                System.out.println("\n" + "=".repeat(60));
                count++;
                System.out.println(count + ". <--Back");
                System.out.print(">>>");
                int ans = scanner.nextInt();
                delay(800);
                if (ans == count) {
                    isInWeaponInventory = false;
                } else if (ans <= player.playerWeaponInventory.size() || ans > 0) {
                    Weapon weapon = player.playerWeaponInventory.get(ans - 1);
                    showWeaponStats(weapon);
                    delay(800);
                    System.out.println("1. Equip\n2. <--Back");
                    System.out.print(">>>");
                    int answer = scanner.nextInt();
                    switch (answer) {
                        case 1 -> {
                            player.playerWeaponInventory.remove(ans - 1);
                            System.out.println("weapon removed from inventory.");
                            delay(800);
                            if (player.currentWeapon != null) {
                                player.playerWeaponInventory.add(player.currentWeapon);
                                System.out.println("current weapon added to inventory");
                                delay(800);
                            }
                            player.currentWeapon = weapon;
                            System.out.println("equipped chosen weapon");
                        }
                        case 2 -> {

                        }
                    }
                }
            } else {
                textBox("No weapons found.");
                isInWeaponInventory = false;
                delay(800);
            }
        }
    }

    public void checkInventory() {
        while (inInventory) {
            System.out.println("What would you like to checkout?");
            textBox("1. Weapons \n2. Potions \n3. <--Back");
            System.out.print(">>>");
            scanner.nextLine();
            int ans = scanner.nextInt();
            switch (ans) {
                case 1 -> {
                    isInWeaponInventory = true;
                    checkWeaponInventory();
                }
                case 2 -> {
                    isInPotionInventory = true;
                    checkPotionInventory();
                }
                case 3 -> {
                    inInventory = false;
                }
                default -> {

                }
            }
        }
    }

    public void dungeonChoices() {
        while (isInDungeon) {
            if (dungeon == null) {
                createDungeon();
                delay(800);
            }
            textBox((!dungeon.conquered ? "1. Explore Floor" : "1. Move to new Dungeon") + "\n2. Move to previous Floor \n3. Move to next floor \n4. Display Stats \n5. Check Inventory \n6. Use Potion \n7. Check Beastiary \n8. Leave Dungeon \n9. Leave Game");
            System.out.print(">>>");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    if (dungeon.conquered == true) {
                        createDungeon();
                        System.out.println(dungeon.conquered);
                    }
                    exploreFloor();
                }
                case 2 -> {
                    if (dungeon.current > 1) {
                        dungeon.current--;
                        System.out.println("Moving to previous floor...");
                    } else {
                        System.out.println("You are unable to move to a previous floor.");
                    }
                }
                case 3 -> {
                    if (canMoveNextFloor) {
                        dungeon.current++;
                        System.out.println("Moving to next floor...");
                        canMoveNextFloor = false;
                    } else {
                        System.out.println("You are unable to move to the next floor.");
                    }
                }
                case 4 -> {
                    displayStats();
                }
                case 5 -> {
                    inInventory = true;
                    checkInventory();
                }
                case 6 -> {

                    if (!player.playerPotionInventory.isEmpty()) {
                        System.out.println("What potion would you like to use?\n" + "=".repeat(60));
                        int count = 0;
                        for (Potion potion : player.playerPotionInventory) {
                            count++;
                            System.out.println(count + ". " + potion.name + ", lvl: " + potion.level);
                        }
                        System.out.println("=".repeat(60));
                        System.out.print(">>>");
                        int ans = scanner.nextInt();
                        if (ans <= player.playerPotionInventory.size() && ans > 0 && !player.playerPotionInventory.isEmpty()) {
                            Potion potion = player.playerPotionInventory.get(ans - 1);
                            usePotion(ans);
                            switch (potion.effect) {
                                case "heal" -> {
                                    player.hp += potion.effect();
                                }
                                case "atkBuff" -> {
                                    player.buffedAtk += potion.effect();
                                }
                                case "defBuff" -> {
                                    player.buffedDef += potion.effect();
                                }
                            }
                            delay(800);
                        }
                    } else {
                        System.out.println("=".repeat(60) + "\n*No potions in inventory.");
                        delay(800);
                    }
                }
                case 7 -> {
                    isInBeastiary = true;
                    inBeastiary();
                }
                case 8 -> {
                    isInDungeon = false;
                    System.out.println("Leaving Dungeon");
                    delay(800);
                }
                case 9 -> {
                    saveGame();
                    System.out.println("Leaving Game...");
                    isInDungeon = false;
                    isInMenu = false;
                    isRunning = false;
                }
            }
            delay(800);
        }
    }

    public void weaponShop() {
        while (isInWeaponStore) {
            textBox("coins: " + player.coins + "\n" + "-".repeat(60) + "\n" + """
                1. Buy Weapon
                2. Sell Weapon
                3. <--Back""");
            System.out.print(">>>");
            int ans = scanner.nextInt();
            delay(800);
            switch (ans) {
                case 1 -> {
                    buyingWeapon = true;
                    while (buyingWeapon) {
                        int count = 0;
                        textBox("Which weapon would you like to purchase?");
                        for (dungeon_raiders.Weapon weapon : shopWeaponInventory) {
                            count++;
                            System.out.println(count + ". " + weapon.name + ", dmg: " + weapon.dmg + ", price: " + weapon.price);
                        }
                        count++;
                        System.out.println(count + ". <--Back");
                        System.out.print(">>>");
                        ans = scanner.nextInt();
                        if (ans == count) {
                            buyingWeapon = false;
                            break;
                        }
                        dungeon_raiders.Weapon item = shopWeaponInventory.get(ans - 1);
                        if (item.price <= player.coins) {
                            if (ans <= shopWeaponInventory.size() && ans > 0) {
                                player.coins -= shopWeaponInventory.get(ans - 1).price;
                                player.playerWeaponInventory.add(item);
                                System.out.println(item.name + " added to inventory");
                                delay(800);
                                System.out.println(item.name + " removed from shop inventory");
                                delay(800);
                                System.out.println("You have purchased: " + item.name + "\n-" + item.price + " coins");
                                shopWeaponInventory.remove(ans - 1);
                                if (player.currentWeapon == null) {
                                    player.currentWeapon = player.playerWeaponInventory.get(0);
                                    player.playerWeaponInventory.remove(0);
                                    System.out.println(item.name + " removed from inventory.");
                                    delay(800);
                                    System.out.println(item.name + " automatically equiped.");
                                }
                            } else {
                                System.out.println("Invalid value");
                            }
                        } else {
                            System.out.println("You have insufficient funds.");
                        }
                        delay(800);
                    }

                }
                case 2 -> {
                    if (!player.playerWeaponInventory.isEmpty()) {
                        int count = 0;
                        textBox("Which weapon would you like to sell?");
                        for (dungeon_raiders.Weapon weapon : player.playerWeaponInventory) {
                            count++;
                            System.out.println(count + ". " + weapon.name + ",dmg: " + weapon.dmg + ", current price: " + weapon.price);
                        }
                        System.out.print(">>>");
                        ans = scanner.nextInt();
                        if (ans <= player.playerWeaponInventory.size() && ans > 0) {
                            System.out.println(player.playerWeaponInventory.get(ans - 1).name + " was sold");
                            player.playerWeaponInventory.remove(ans - 1);
                            delay(800);
                        } else {
                            System.out.println("Invalid value");
                        }
                    } else {
                        System.out.println("You currently have nothing to sell.");
                    }
                }
                case 3 -> {
                    isInWeaponStore = false;
                }
                default -> {

                }
            }
        }
    }

    public void potionShop() {
        while (isInPotionStore) {
            textBox("coins: " + player.coins + "\n" + "-".repeat(60) + "\n" + """
                1. Buy Potion
                2. Sell Potion
                3. <--Back""");
            System.out.print(">>>");
            int ans = scanner.nextInt();
            delay(800);
            switch (ans) {
                case 1 -> {
                    buyingPotion = true;
                    while (buyingPotion) {
                        int count = 0;
                        textBox("Which potion would you like to purchase?");
                        for (Potion potion : shopPotionInventory) {
                            count++;
                            System.out.println(count + ". " + potion.name + ", lvl: " + potion.level + ", price: " + potion.price);
                        }
                        count++;
                        System.out.println(count + ". <--Back");
                        System.out.print(">>>");
                        ans = scanner.nextInt();
                        if (ans == count) {
                            buyingPotion = false;
                            break;
                        }
                        Potion potion = shopPotionInventory.get(ans - 1);
                        if (potion.price <= player.coins) {
                            if (ans <= shopPotionInventory.size() && ans > 0) {
                                player.coins -= shopPotionInventory.get(ans - 1).price;
                                player.playerPotionInventory.add(potion);
                                System.out.println(potion.name + " added to inventory");
                                delay(800);
                                System.out.println(potion.name + " removed from shop inventory");
                                delay(800);
                                System.out.println("You have purchased: " + potion.name + "\n-" + potion.price + " coins");
                                shopPotionInventory.remove(ans - 1);
                            } else {
                                System.out.println("Invalid value");
                            }
                        } else {
                            System.out.println("You have insufficient funds.");
                            delay(800);
                        }
                    }
                }
                case 2 -> {
                    if (!player.playerPotionInventory.isEmpty()) {
                        int count = 0;
                        textBox("Which potion would you like to sell?");
                        for (Potion potion : player.playerPotionInventory) {
                            count++;
                            System.out.println(count + ". " + potion.name + ",lvl: " + potion.level + ", current price: " + potion.price);
                        }
                        System.out.print(">>>");
                        ans = scanner.nextInt();
                        if (ans <= player.playerPotionInventory.size() && ans > 0) {
                            System.out.println(player.playerPotionInventory.get(ans - 1).name + " was sold");
                            player.playerPotionInventory.remove(ans - 1);
                            delay(800);
                        } else {
                            System.out.println("Invalid value");
                        }
                    } else {
                        System.out.println("You currently have nothing to sell.");
                    }
                }
                case 3 -> {
                    isInPotionStore = false;
                }
                default -> {

                }
            }
        }
    }

    public void shopChoices() {
        while (isInStore) {
            System.out.println("What would you like to purchase?");
            textBox("1. Weapons \n2. Potions \n3. <--Back");
            System.out.print(">>>");
            int ans = scanner.nextInt();
            delay(800);
            switch (ans) {
                case 1 -> {
                    isInWeaponStore = true;
                    weaponShop();
                }
                case 2 -> {
                    isInPotionStore = true;
                    potionShop();
                }
                case 3 -> {
                    System.out.println("Leaving Store...");
                    isInStore = false;
                }
                default -> {

                }
            }

        }
    }

    public void exploreFloor() {
        System.out.println("You are currently on floor " + dungeon.current + ".");
        if (Math.random() > 0.5) {//50/50 odds for either trying for enemy encounter or finding next floor stairs
            if (encounterEnemy() || enemyFloorPity == 3) {

                fightSequence();
                enemyFloorPity = 0;
            } else {
                enemyFloorPity++;
            }
        } else {
            if ((findFloorStairs() || enemyFloorPity == 3) && !canMoveNextFloor) {
                System.out.println("Would you like to move to the next floor? (y/n)");
                canMoveNextFloor = true;
                System.out.print(">>>");
                String ans = scanner.next();
                delay(800);
                switch (ans) {
                    case "y" -> {
                        dungeon.current++;
                        if (dungeon.current == dungeon.floors) {
                            dungeon.conquered = true;
                        }
                        System.out.println("Moved to floor " + dungeon.current + ".");
                    }
                    case "n" -> {
                        dungeonChoices();
                    }
                }
                enemyFloorPity = 0;// reset floor finding pity
            } else {
                enemyFloorPity++;
            }
        }

    }

    public void inBeastiary() {
        while (isInBeastiary) {
            if (!player.beastiary.isEmpty()) {
                System.out.println("Which enemy would you like to checkout.\n" + "=".repeat(60));
                int count = 0;
                for (BeastLog beastLog : player.beastiary) {
                    count++;
                    System.out.println(count + ". " + beastLog.enemy.name);
                }
                System.out.print("=".repeat(60) + "\n>>>");
                int ans = scanner.nextInt();
                if (ans > 0 && ans <= player.beastiary.size()) {
                    Enemy beast = player.beastiary.get(ans - 1).enemy;
                    String description = player.beastiary.get(ans - 1).description;
                    textBox("name: " + beast.name
                            + "\ndescription: " + description);
                }
                System.out.println("=".repeat(60));
                System.out.println("1. <--Back");
                System.out.print(">>>");
                int back = scanner.nextInt();
            } else {
                System.out.println("Beastiary is currently empty.");
                isInBeastiary = false;
            }
            delay(800);
        }
    }

    public void fightSequence() {
        System.out.println("You encountered an enemy.");
        isFighting = true;
        createEnemy();

        if (!player.beastiary.contains(new BeastLog(enemy))) {
            player.beastiary.add(new BeastLog(enemy));
            System.out.println("New Beastiary Log made.");
            delay(800);
        }

        while (isFighting) {
            textBox(player.name + ": " + "[" + "=".repeat(player.hp) + " ".repeat(10 - player.hp) + "]" + " ".repeat(10)
                    + enemy.name + ": " + "[" + "=".repeat(enemy.hp) + " ".repeat(20 - enemy.hp) + "]"
                    + "\nLevel: " + player.currentLevel + " ".repeat(19) + "Level: " + enemy.lvl
                    + "\n" + "-".repeat(60)
                    + "\n1. Attack \n2. Use Potion \n3. Escape\n4. Display Stats \n5. Check Beastiary");
            System.out.print(">>>");
            int ans = scanner.nextInt();
            switch (ans) {
                case 1 -> {//attack sequence
                    attackEnemy();
                    if (enemy.hp <= 0) {
                        System.out.println("You have successfully defeated the enemy.");
                        enemyDrops();
                        levelUp();
                        isFighting = false;
                    }
                    delay(800);

                    if (isFighting) {
                        enemyAttacks();
                    }

                    if (player.hp <= 0 && isFighting) {
                        System.out.println("You have died to " + enemy.name);
                        System.exit(0);
                    }
                    delay(800);
                }
                case 2 -> {
                    if (!player.playerPotionInventory.isEmpty()) {
                        System.out.println("What potion would you like to use?\n" + "=".repeat(60));
                        int count = 0;
                        for (Potion potion : player.playerPotionInventory) {
                            count++;
                            System.out.println(count + ". " + potion.name + ", lvl: " + potion.level);
                        }
                        System.out.println("=".repeat(60));
                        System.out.print(">>>");
                        ans = scanner.nextInt();
                        if (ans <= player.playerPotionInventory.size() && ans > 0 && !player.playerPotionInventory.isEmpty()) {
                            Potion potion = player.playerPotionInventory.get(ans - 1);
                            usePotion(ans);
                            switch (potion.effect) {
                                case "heal" -> {
                                    player.hp += potion.effect();
                                }
                                case "atkBuff" -> {
                                    player.buffedAtk += potion.effect();
                                }
                                case "defBuff" -> {
                                    player.buffedDef += potion.effect();
                                }
                            }
                            delay(800);
                        }

                        if (isFighting) {
                            enemyAttacks();
                        }

                        if (player.hp <= 0 && isFighting) {
                            System.out.println("You have died to " + enemy.name);
                            System.exit(0);
                        }
                        delay(800);
                    } else {
                        System.out.println("=".repeat(60) + "\n*No potions in inventory.");
                        delay(800);
                    }
                }
                case 3 -> {
                    if (canEscape) {
                        if (escapeEnemy()) {
                            System.out.println("You have successfully escaped from the enemy.");
                            isFighting = false;
                        } else {
                            System.out.println("You have failed to escape.");
                            canEscape = false;
                        }
                    } else {
                        System.out.println("You can no longer escape!");
                    }
                    delay(800);

                }
                case 4 -> {
                    displayStats();
                    delay(800);
                }
                case 5 -> {
                    isInBeastiary = true;
                    inBeastiary();
                }
                default -> {
                }
            }
        }

        player.buffedAtk = 0;
        player.buffedDef = 0;
        canEscape = true; //canEscape reset after entire encounter
        enemy = null; //enemy reset
    }

    public void usePotion(int ans) {
        Potion potion = player.playerPotionInventory.get(ans - 1);
        System.out.println("You have seleted " + potion.name);
        delay(800);
        player.playerPotionInventory.remove(ans - 1);
        System.out.println("Potion removed from player inventory.");
        delay(800);
    }

    public void attackEnemy() {
        int damage = (player.role.equals("mage") ? player.kno + player.buffedAtk : player.atk + player.buffedAtk) + (player.currentWeapon != null ? player.currentWeapon.dmg : 0) - (int) Math.floor(Math.random() * enemy.def + 1);
        if (hitEvasion("hit")) {
            System.out.println("You have attacked the enemy. -" + damage + "hp");
            enemy.hp -= damage;
            if (Math.random() < 0.4 - (0.1 * player.luc) && player.currentWeapon != null) {
                player.currentWeapon.durability--;
                System.out.println("-1 durability");
                delay(800);
            }
        } else {
            System.out.println("You have missed your attack.");
        }
        if (player.currentWeapon != null) {
            player.currentWeapon.price = (int) Math.floor(player.currentWeapon.durability / 20);
        }
    }

    public void enemyAttacks() {
        int damage = enemy.atk - (int) Math.floor(Math.random() * player.def + player.buffedDef + 1);
        if (damage <= 0) {
            damage = 1;
        }
        if (!hitEvasion("evasion")) {
            System.out.println("Enemy has attacked you. -" + damage + "hp");
            player.hp -= damage;
        } else {
            System.out.println("Enemy has missed its attack.");
        }
    }

    public void enemyDrops() {
        /*enemy has a chance to drop 3 items
            1 item is 100% guaranteed
            2 drops down to 25% + players luck*/
        double probability = Math.random();
        System.out.println("The enemy has dropped: ");
        int coinDrop = (int) Math.floor(Math.random() * 10 + 1);
        player.coins += coinDrop;
        System.out.println("- " + coinDrop + " coins");
        if (probability < 0.15 + (0.1 * player.luc)) {
            Weapon weapon = new Weapon("Random tool", 10, 1);
            player.playerWeaponInventory.add(weapon);
            System.out.println("- " + weapon.name);
        }
        if (probability < 0.15 + (0.1 * player.luc)) {
            Potion potion = new Potion("Random elixir", 1, 5, "atkBuff");
            player.playerPotionInventory.add(potion);
            System.out.println("- " + potion.name);
        }
        System.out.println("=".repeat(60));
        delay(800);
        int xp = (int) (Math.floor(Math.random() * 10 + 1)) * 10;
        player.expGauge += xp;
        System.out.println("exp gained: " + xp);
        delay(800);
    }

    public boolean hitEvasion(String hitEvasion) {
        if (hitEvasion.equals("hit")) {
            return Math.random() > 0.2 - (0.1 * player.luc);
        } else {
            return Math.random() < 0.2;
        }
    }

    public void delay(int mill) {
        try {
            Thread.sleep(mill);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public boolean findFloorStairs() {
        return Math.random() > 0.8;//20% chance to find next floor stairs
    }

    public boolean encounterEnemy() {
        return Math.random() > 0.5;//50% chance to encounter an enemy
    }

    public boolean escapeEnemy() {
        return Math.random() > 0.8 - (0.1 * player.luc);//30% base chance to escape enemy
    }

    //---------------------------------------SHOP BASED METHODS-----------------------------------------------------------
    //---------------------------------------ENEMY BASED METHODS-----------------------------------------------------------
    public void fetchEnemyList() {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("enemies.bin"))) {
            os.writeObject(enemyData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Enemy> loadEnemyData() {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("enemies.bin"))) {
            SaveEnemyData data = (SaveEnemyData) is.readObject();
            System.out.println("Enemy List found.");
            return data.enemies;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Enemy List not found.");
            e.printStackTrace();
            return null;
        }
    }

    //---------------------------------------PLAYER BASED METHODS----------------------------------------------------------
    public void createPlayer() {
        System.out.print("What is your name: ");
        String name = scanner.next();
        System.out.println("What is your role: ");
        textBox("1. Mage \n2. Gish \n3. Knight \n4. Barbarian");
        System.out.print(">>>");
        int role = scanner.nextInt();
        delay(800);
        switch (role) {
            case 1 -> {
                player = new Player(name);
                player.role = new Role("mage");
                System.out.println("Mage character created.");
            }
            case 2 -> {
                player = new Player(name);
                player.role = new Role("gish");
                System.out.println("Gish character created.");
            }
            case 3 -> {
                player = new Player(name);
                player.role = new Role("knight");
                System.out.println("Knight character created.");
            }
            case 4 -> {
                player = new Player(name);
                player.role = new Role("barbarian");
                System.out.println("Barbarian character created.");
            }
            default -> {
                System.out.println("Invalid role.");
            }
        }

        delay(800);
    }

    //directly affected by the players stat level
    //used to calculate increase in atk, int, vit, and luc after each level up
    public static double setGainDecrease(int stat) {//could be atk, int, vit or luc
        //f(x) = a . b ^ x;
        double maxGain = 50.0;//a
        double decayFactor = 0.95499258602144;//b

        return maxGain * Math.pow(decayFactor, stat);
    }

    public static double statGainIncrease(int stat) {
        double maxGain = 50.0;
        double decayFactor = 0.95499258602144;

        double increment = maxGain * Math.pow(decayFactor, stat);
        return maxGain - increment;
    }

    public void allocateTokens() {
        if (player.tokens <= 0) {
            System.out.println("You have insufficient tokens to level up your attributes.");
            inAllocateTokens = false;
            return;
        }

        while (inAllocateTokens && player.tokens > 0) {
            System.out.println("Which stat would you like to upgrade.\n" + "=".repeat(60) + "\nTokens: " + player.tokens);
            delay(800);
            textBox("""
                    1. VIT
                    2. STR
                    3. INT
                    4. LUC
                    5. <--Back""");
            System.out.print(">>>");
            int ans = scanner.nextInt();
            delay(800);
            switch (ans) {
                case 1 -> {
                    player.currentHealth += setGainDecrease(player.VIT);
                    System.out.println("Player vitality has increased.");
                    player.tokens--;
                }
                case 2 -> {
                    switch (player.role.type.toLowerCase()) {
                        case "mage" ->
                            physicalMultiplier = 0.25;
                        case "gish" ->
                            physicalMultiplier = 0.50;
                        case "knight" ->
                            physicalMultiplier = 0.75;
                        case "barbarian" ->
                            physicalMultiplier = 1.0;
                    }
                    player.currentPhysicalAttack += (setGainDecrease(player.STR) * physicalMultiplier);
                    System.out.println("Player attack has increased.");
                    player.tokens--;
                }
                case 3 -> {
                    switch (player.role.type.toLowerCase()) {
                        case "mage" ->
                            magicMultiplier = 1.0;
                        case "gish" ->
                            magicMultiplier = 0.75;
                        case "knight" ->
                            magicMultiplier = 0.50;
                        case "barbarian" ->
                            magicMultiplier = 0.25;
                    }
                    player.currentPhysicalAttack += (setGainDecrease(player.INT) * magicMultiplier);
                    System.out.println("Player intelligence has increased.");
                    player.tokens--;
                }
                case 4 -> {
                    player.currentLuck += setGainDecrease(player.LUC);
                    System.out.println("Player luck has increased.");
                    player.tokens--;
                }
                case 5 -> {
                    inAllocateTokens = false;
                }

            }
        }
    }

    public void displayStats() {
        textBox(player.name + ": " + "[" + "=".repeat(player.hp) + " ".repeat(10 - player.hp) + "]"
                + "\nLevel: " + player.currentLevel
                + "\nTokens: " + player.tokens
                + "\nRole: " + player.role
                + "\n" + "-".repeat(60)
                + "\nVIT: " + player.VIT
                + "\nSTR: " + player.STR
                + "\nINT: " + player.INT
                + "\nLUC: " + player.LUC
                + "\n" + "-".repeat(60)
                + "\nCurrent Weapon: " + player.currentWeapon.name
        );
    }

    public void displayStatDetails() {
        textBox("Base Health: " + player.currentHealth
                + "\nBase Physical Attack: " + player.currentPhysicalAttack
                + "\nBase Magical Attack: " + player.currentMagicalAttack
                + "\nCurrent Weapon Physical Attack: " + player.currentWeapon.currentPhysicalAttack
                + "\nCurrent Weapon Magical Attack: " + player.currentWeapon.currentMagicalAttack
                + "\n" + "-".repeat(60)
                + "\nTotal Physical Attack: " + player.totalPhysicalDamage()
                + "\nTotal Magical Attack: " + player.totalMagicalDamage());
    }

    public void setStats() {
        for (int i = 1; i <= player.currentLevel; i++) {
            player.currentXp += setGainDecrease(i);
            System.out.println(i + ", " + player.currentXp);
        }
        for (int i = 1; i <= player.STR; i++) {
            player.currentPhysicalAttack += setGainDecrease(i);
            System.out.println(i + ", " + player.currentPhysicalAttack);
        }
        for (int i = 1; i <= player.INT; i++) {
            player.currentMagicalAttack += setGainDecrease(i);
            System.out.println(i + ", " + player.currentMagicalAttack);
        }
        for (int i = 1; i <= player.VIT; i++) {
            player.currentHealth += setGainDecrease(i);
            System.out.println(i + ", " + player.currentHealth);
        }
        for (int i = 1; i <= player.LUC; i++) {
            player.currentLuck += setGainDecrease(i);
            System.out.println(i + ", " + player.currentLuck);
        }
    }

    public void levelUp() {
        while (inLevelUp) {
            int curr = 0;
            for (int i = 1; i <= player.currentLevel + 1; i++) {
                curr += statGainIncrease(i);
            }
            System.out.println(curr);

            if (curr <= player.currentXp) {
                player.currentLevel++;
                System.out.println("You have leveled up.\nNew level: " + player.currentLevel);

                //token allocation -- player gets between 1 and 5 tokens to use on stats after each level up
                int tokens = (int) Math.floor(Math.random() * 5 + 1);
                player.tokens += tokens;
                System.out.println("You have received " + tokens + " level up token" + (tokens > 1 ? "s." : ".")/*This is purely for grammaticaly correctness*/);
            } else {
                System.out.println((curr - player.currentXp) + " xp left until next level up.");
                inLevelUp = false;
            }
        }
    }
}

class Player implements Serializable {

    String name;
    //xp dictates player level
    int level = 0;

    //used in levelling up player stats
    int tokens = 0;

    Role role;

    ArrayList<Weapon> playerWeaponInventory = new ArrayList<>();
    ArrayList<Potion> playerPotionInventory = new ArrayList<>();
    ArrayList<BeastLog> beastiary = new ArrayList<>();

    int VIT = 1;
    int STR = 1;
    int INT = 1;
    int LUC = 1;

    int maxHealth = 0;
    int currentHealth = 0;//hpGain()
    int currentLevel = 0;
    int currentXp = 0;
    int currentPhysicalAttack = 0;//strGain()
    int currentMagicalAttack = 0;//intGain()
    int currentLuck = 0;//luckGain()

    Weapon currentWeapon = new Weapon("Fists", 0, 1);

    int expGauge = 0;
    int hp = 10;
    int coins = 20;
    int atk;
    int buffedAtk = 0;
    int def;
    int buffedDef = 0;
    int kno;
    int luc;

    public Player(String name) {
        this.name = name;
    }

    public double totalPhysicalDamage() {
        return currentPhysicalAttack + currentWeapon.currentPhysicalAttack;
    }

    public double totalMagicalDamage() {
        return currentMagicalAttack + currentWeapon.currentMagicalAttack;
    }
}

class Role {

    String type;

    int VIT = 1;
    int STR = 1;
    int INT = 1;
    int LUC = 1;

    int atk = 1;
    int def = 1;
    int kno = 1;
    int luc = 1;

    public Role(String type) {
        this.type = type;
    }
}

class Enemy implements Serializable {

    String name;
    String description;
    int lvl;
    int hp;
    int atk;
    int def;

    public Enemy(String name, String description, int lvl, int hp, int atk, int def) {
        this.name = name;
        this.lvl = lvl;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
    }
}

class Dungeon {

    int floors = 5;
    int current = 1;
    boolean conquered = false;

    public Dungeon() {

    }
}

class Potion implements Serializable {

    String name;
    int statIncrease = 3;//hp increase effect for now
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

class Weapon implements Serializable {

    String name;
    int durability = 10;
    int price;
    int dmg;

    int currentPhysicalAttack;
    int currentMagicalAttack;

    //the less durability an item has the less its market value would be
    public Weapon(String name, int price, int dmg) {
        this.name = name;
        this.price = price;
        this.dmg = dmg;
    }
}

class SavePlayerData implements Serializable {

    Player player;
}

class SaveEnemyData implements Serializable {

    ArrayList<Enemy> enemies = new ArrayList<>(Arrays.asList(
            new Enemy(
                    "Ogre",
                    "\"The hulking brutes of the wild.\"\n"
                    + "Ogres are muscular, human-like giants standing 9 to 10 feet tall and weighing over 600 pounds. "
                    + "They have hideous, disproportioned features, including large noses, tusks, warts, and often possess dull, "
                    + "earth-toned or sometimes greenish-grey skin. They have notoriously quick tempers, often resorting to violent "
                    + "tantrums when frustrated.",
                    1, 10, 3, 2
            ),
            new Enemy(
                    "Crocotta",
                    "\"The Cackling Predators of the Badlands.\"\n"
                    + "Crocottas are lean, hyena-like monstrosities roughly the size of a large lion, with powerful forequarters, "
                    + "sloped backs, and long, bone-crushing jaws filled with jagged teeth. Their coarse fur ranges from dusty brown "
                    + "to ash-grey, often mottled to blend into rocky plains and scrublands. Most unsettling is their intelligence "
                    + "and cruel cunning—crocottas are known to mimic human voices and familiar sounds to lure prey into ambushes. "
                    + "They delight in panic and pursuit, hunting not just to feed, but to terrorize.",
                    1, 10, 2, 3
            ),
            new Enemy(
                    "Bicorn",
                    "\"The Two-Horned Wardens of the Wilds.\"\n"
                    + "Bicorns are massive, bull-like beasts with thick, corded muscle and a low, powerful stance built for "
                    + "unstoppable charges. They are distinguished by their pair of forward-curving horns, polished smooth from "
                    + "constant combat and territorial clashes. Their hides are dense and leathery, often scarred from battles. "
                    + "When provoked, a bicorn’s charge can shatter shields and uproot trees.",
                    1, 15, 2, 2
            ),
            new Enemy(
                    "Yale",
                    "\"The Shadow-Maned Hunters of the High Slopes.\"\n"
                    + "Yales are powerful, goat- or antelope-like beasts with compact, muscular bodies and dense coats suited for "
                    + "cold, mountainous terrain. Their most striking feature is a pair of large, swiveling horns capable of rotating "
                    + "independently, making ambush nearly impossible. Cunning and relentless, they pursue intruders across cliffs "
                    + "and narrow ledges.",
                    1, 10, 1, 3
            ),
            new Enemy(
                    "Wyvern",
                    "\"The Venom-Winged Terrors of the Sky.\"\n"
                    + "Wyverns are ferocious, two-legged drakes with vast, leathery wings that double as forelimbs. Their lean but "
                    + "powerful bodies are built for speed and aerial dominance, with long, barbed tails often ending in a venomous "
                    + "stinger. Though lacking the cunning of true dragons, their raw aggression makes them a nightmare for travelers.",
                    1, 20, 4, 4
            )
    ));

}

class BeastLog implements Serializable {

    Enemy enemy;
    String description;

    public BeastLog(Enemy enemy) {
        this.enemy = enemy;
        switch (enemy.name.toLowerCase()) {
            case "ogre" -> {
                this.description = "";
            }
            case "crocotta" -> {
                this.description = "";
            }
            case "bicorn" -> {
                this.description = "";
            }
            case "yale" -> {
                this.description = "";
            }
            case "wyvern" -> {
                this.description = "";
            }
        }
    }
}
