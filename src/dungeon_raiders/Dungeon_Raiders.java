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
        
        double answer;
        
        answer = 250 * Math.pow(0.8, 1);
        System.out.println(answer);
        
        answer = Math.round(200 * (1-Math.pow(0.8, 2))/(1-0.8));
        System.out.println(answer);
        /*SwingUtilities.invokeLater(() -> {
            new Game();
        });*/
    }

}

