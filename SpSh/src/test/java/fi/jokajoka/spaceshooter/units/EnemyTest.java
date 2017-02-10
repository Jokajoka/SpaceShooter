package fi.jokajoka.spaceshooter.units;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.jokajoka.spaceshooter.units.Enemy;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kahonjon
 */
public class EnemyTest {
    
    public EnemyTest() {
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
   
    @Test
    public void playability() {
        Enemy enemy = new Enemy(100, 200, -100, 1.0);
        assertEquals(false, enemy.getPlayable());
    }
    
    @Test
    public void takeDamage() {
        Enemy enemy = new Enemy(100, 200, -100, 1.0);
        enemy.reduce(15);
        int vastaus = enemy.getHealth();
        assertEquals(85, vastaus);
    }
}