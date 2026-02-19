package dungeon_raiders;


import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{
    String name;
    Role role;
    
    //setup cross data type indexing
    /*ArrayList<Object> playerInventory = new ArrayList<>();*/
    
    ArrayList<Weapon> playerWeaponInventory = new ArrayList<>();
    ArrayList<Potion> playerPotionInventory = new ArrayList<>();
    
    int coins = 20;
    
    int level;//Level Max = 100
    double experiencePoints;
    
    /**
     * Main stats work as follows:
     * - Main stat level will be within a range of (0-100)
     * - Max stat follows which will indicate what a players base statistic is without any stats buffs or de-buffs
     * - Current stat then shows the current state of a players stat.
     * note: current stat will always point to max stat for every refresh
     */
    
    //Stat Max = 100
    int VIT;
    double health;
    double currentHealth;
    
    int STR;
    double strength;
    double currentStrength;
    
    int INT;
    double intelligence;
    double currentIntelligence;
    
    int DEF;
    int LUC;
    
    Player(String name, Role role){
        this.name = name;
        
        //role differentiation affects individual stats 
        this.VIT = role.VIT;
        this.STR = role.STR;
        this.INT = role.INT;
        this.DEF = role.DEF;
        this.LUC = role.LUC;
    }
    
    public void calculation(){
        
    }
    
    
}