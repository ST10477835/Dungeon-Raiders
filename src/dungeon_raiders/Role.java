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
public class Role implements Serializable {

    String type;
    int VIT;
    int STR;
    int INT;
    int DEF;
    int LUC;

    public Role(String type) {
        this.type = type;

        this.VIT = 2;
        this.DEF = 1;
        this.LUC = 1;
        switch (type) {
            case "mage" -> {
                this.STR = 1;
                this.INT = 3;
            }
            case "gish" -> {
                this.STR = 2;
                this.INT = 2;
            }
            case "knight" -> {
                this.STR = 2;
                this.INT = 2;
            }
            case "barbarian" -> {
                this.STR = 2;
                this.INT = 2;
            }
        }
    }

}
