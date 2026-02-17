/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeon_raiders;

import java.io.Serializable;

/**
 *
 * @author Abulele
 */
class BeastLog implements Serializable {
    
    Enemy enemy;

    public BeastLog(Enemy enemy) {
        this.enemy = enemy;
    }
    
}
