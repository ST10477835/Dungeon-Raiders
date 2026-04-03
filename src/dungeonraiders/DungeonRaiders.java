/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dungeonraiders;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.SwingUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class DungeonRaiders {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        if (args.length > 0 && args[0].equals("child")) {
            // Normal program execution
            runProgram();
        } else {
            // Only the FIRST run should launch CMD
            openCmd();
        }
    }

    public static void runProgram() {
        SwingUtilities.invokeLater(() -> {
            new Game();
        });
    }

    public static void openCmd() {
    try {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "start", "cmd.exe", "/k",
                "java -cp . dungeonraiders.DungeonRaiders child"
        );

        builder.directory(new File(
            "C:\\Users\\abule\\OneDrive\\Documents\\NetBeansProjects\\DungeonRaiders\\build\\classes"
        ));

        builder.start();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}

enum Status {
    HEAL,
    BUFF,
    DEBUFF
}

class Item {

    public String name;
    public int price;
    public Status status;
    public double potency;

    public Item(String name, int price, Status status, double potency) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.potency = potency;
    }

    public void itemInformation() {
        System.out.println("=".repeat(60) + "\n[" + name + "] -- " + price + " coins" + "\n" + "=".repeat(60) + "\nStatus: " + status);
    }

}
