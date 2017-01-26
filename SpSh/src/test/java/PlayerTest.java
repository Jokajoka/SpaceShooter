/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.Jokajoka.spaceshooter.logiikka.Player;
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
public class PlayerTest {
    
    public PlayerTest() {
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
    public void damage() {
        Player player = new Player(100, 200, 350);
        player.reduce(15);
        int vastaus = player.getHealth();
        assertEquals(85, vastaus);
    }
    
    @Test
    public void playability() {
        Player player = new Player(100, 200, 350);
        assertEquals(true, player.getPlayable());
    }
}
