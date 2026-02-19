/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeon_raiders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Abulele
 */
class Enemies implements Serializable {
    int level;
    
    Enemies(int level){
        this.level = level;
    }
    ArrayList<Enemy> enemies = new ArrayList<>(Arrays.asList(
            new Enemy("Ogre", level),
            new Enemy("Crocotta", level),
            new Enemy("Bicorn", level),
            new Enemy("Yale", level),
            new Enemy("Wyvern", level)));
    
}
