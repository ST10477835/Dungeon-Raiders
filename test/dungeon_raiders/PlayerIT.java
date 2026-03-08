/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dungeon_raiders;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Abulele
 */
public class PlayerIT {
    Player player = new Player("Abu", new Role("mage"));
    Enemy enemy = new Enemy("Ogre", 1);
    public PlayerIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calculateDamage method, of class Player.
     */
    @Test
    public void testCalculateDamage() {
        System.out.println("calculateDamage");
        int index = 0;
        Enemy enemy = this.enemy;
        Player instance = this.player;
        double expResult = 250.0;
        double result = instance.calculateDamage(index, enemy);
        assertEquals(expResult, result, 0);
    }
    
}
