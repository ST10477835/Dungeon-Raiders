/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dungeonraiders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Abulele
 */
class Connect {
    
    private final static String URL = "jdbc:mysql://localhost:3306/DungeonRaidersDb";
    private final static String USER = "root";
    private final static String PASSWORD = "pass123";

    public static Connection getConnection() {
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to MySQL successfully!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return null;
        }
    }
    
}
