package fi.jokajoka.spaceshooter.logiikka;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.jokajoka.spaceshooter.logiikka.Player;
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
    public void getTest(){
        Player player = new Player(100, 200, 350);
        assertEquals(200, player.getPosX());
        assertEquals(350, player.getPosY());
        assertEquals(100, player.getHealth());
        assertEquals(1.5, player.getDamage(), 0.0);
        assertEquals(0, player.getSpeedX());
        assertEquals(0, player.getSpeedY());
        assertEquals(false, player.getFire());
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
    
    @Test
    public void move(){
        Player player = new Player(100, 200, 250);
        player.moveRight();
        assertEquals(5, player.getSpeedX());
        player.stop();
        assertEquals(0, player.getSpeedX());
        player.moveLeft();
        assertEquals(-5, player.getSpeedX());
        player.stop();
        player.moveUp();
        assertEquals(-5, player.getSpeedY());
        player.stop();
        player.moveDown();
        assertEquals(5, player.getSpeedY());
        
    }
    
    @Test
    public void heal(){
        Player player = new Player(50, 200, 350);
        player.heal(15);
        assertEquals(65, player.getHealth());
    }
    
    @Test
    public void positionX(){
        Player player = new Player(100, 390, 350);
        player.setSpeedX(5);
        player.setPosX();
        assertEquals(395, player.getPosX());
        player.setSpeedX(6);
        player.setPosX();
        assertEquals(400, player.getPosX());
        player.setPosX();
        assertEquals(400, player.getPosX());
        player.setSpeedX(-401);
        player.setPosX();
        assertEquals(0, player.getPosX());
    }
    
    @Test
    public void positionY(){
        Player player = new Player(100, 200, 390);
        player.setSpeedY(5);
        player.setPosY();
        assertEquals(395, player.getPosY());
        player.setSpeedY(6);
        player.setPosY();
        assertEquals(400, player.getPosY());
        player.setPosY();
        assertEquals(400, player.getPosY());
        player.setSpeedY(-401);
        player.setPosY();
        assertEquals(0, player.getPosY());
    }
    
    @Test
    public void update(){
        Player player = new Player(100, 200, 350);
        player.setSpeedX(5);
        player.setSpeedY(5);
        player.update();
        assertEquals(205, player.getPosX());
        assertEquals(355, player.getPosY());
    }
    
    @Test
    public void fire(){
        Player player = new Player(100, 250, 350);
        player.setFire(true);
        assertEquals(true, player.getFire());
        player.setFire(false);
        assertEquals(false, player.getFire());
    }
    
    
}
