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
class Dungeon {
    
    public String name;
    public ArrayList<Entity> enemies = new ArrayList<>();
    public ArrayList<Entity> floorMasters = new ArrayList<>();
    public Entity dungeonMaster;
    public int floors;
    public int pity = 0;
    public int currentFloor = 1;
    public int currentLowestFloor = currentFloor;
    public boolean atLeastOne = false; //this is required for facing floor masters and dungeon masters
    public boolean isConquered = false;
    public boolean canMoveToNextFloor = false;
    public boolean canMoveToPrevFloor = false;

    public Dungeon(String name) {
        this.name = name;
        loadEnemies();
    }

    public Dungeon(String name, int floors) {
        this.name = name;
        this.floors = floors;
        loadEnemies();
    }

    public void loadEnemies() {
        //enemies
        Entity enemy1 = new Entity("Slime", 20.0, 1);
        enemy1.currentWeapon = new Weapon("Slime Residue", 1);
        enemy1.currentWeapon.skillset.add(new Skill("Spew", 1, 0));
        Entity enemy2 = new Entity("Goblin", 20.0, 2);
        enemy2.currentWeapon = new Weapon("Club", 2);
        enemy2.currentWeapon.skillset.add(new Skill("Bash", 2, 0));
        Entity enemy3 = new Entity("Skeleton", 20.0, 2);
        enemy3.currentWeapon = new Weapon("Shattered Dagger", 3);
        enemy3.currentWeapon.skillset.add(new Skill("Blunt Cut", 2, 0));
        //floor masters
        Entity floorMaster1 = new Entity("Large Slime", 20.0, 3);
        floorMaster1.currentWeapon = new Weapon("Slime blobs", 2);
        floorMaster1.currentWeapon.skillset.add(new Skill("Mucus Shot", 3, 0));
        Entity floorMaster2 = new Entity("Goblin Chief", 20.0, 3);
        floorMaster2.currentWeapon = new Weapon("Big Club", 4);
        floorMaster2.currentWeapon.skillset.add(new Skill("Skull Crusher", 3, 0));
        Entity floorMaster3 = new Entity("Skeleton Lord", 20.0, 3);
        floorMaster3.currentWeapon = new Weapon("Large Axe", 8);
        floorMaster3.currentWeapon.skillset.add(new Skill("Lethal Shot", 3, 0));
        //dungeon masters
        dungeonMaster = new Entity("Dungeon Master", 20.0, 5);
        dungeonMaster.currentWeapon = new Weapon("BroadSword", 15);
        dungeonMaster.currentWeapon.skillset.add(new Skill("Zwerchau", 5, 0));
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);
        floorMasters.add(floorMaster1);
        floorMasters.add(floorMaster2);
        floorMasters.add(floorMaster3);
    }
    
}
