package fi.jokajoka.spaceshooter.units;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public void getTest() {
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
    public void setTest() {
        Player player = new Player(100, 200, 350);
        player.setDamage(1.8);
        player.setFire(true);
        player.setSpeedX(9);
        player.setSpeedY(9);
        player.setPosX();
        player.setPosY();
        assertEquals(1.8, player.getDamage(), 0);
        assertEquals(true, player.getFire());
        assertEquals(9, player.getSpeedX());
        assertEquals(9, player.getSpeedY());
        assertEquals(209, player.getPosX());
        assertEquals(359, player.getPosY());
    }

    @Test
    public void takeDamage() {
        Player player = new Player(100, 200, 350);
        player.reduce(15);
        int vastaus = player.getHealth();
        assertEquals(85, vastaus);
    }

    @Test
    public void move() {
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
    public void heal() {
        Player player = new Player(50, 200, 350);
        player.heal(15);
        assertEquals(65, player.getHealth());
    }

    @Test
    public void positionX() {
        Player player = new Player(100, 390, 350);
        player.setSpeedX(5);
        player.setPosX();
        assertEquals(395, player.getPosX());
        player.setSpeedX(406);
        player.setPosX();
        assertEquals(740, player.getPosX());
        player.setPosX();
        assertEquals(740, player.getPosX());
        player.setSpeedX(-801);
        player.setPosX();
        assertEquals(0, player.getPosX());
    }

    @Test
    public void positionY() {
        Player player = new Player(100, 200, 390);
        player.setSpeedY(5);
        player.setPosY();
        assertEquals(395, player.getPosY());
        player.setSpeedY(406);
        player.setPosY();
        assertEquals(740, player.getPosY());
        player.setPosY();
        assertEquals(740, player.getPosY());
        player.setSpeedY(-801);
        player.setPosY();
        assertEquals(0, player.getPosY());
    }

    @Test
    public void update() {
        Player player = new Player(100, 200, 350);
        player.setSpeedX(5);
        player.setSpeedY(5);
        player.update();
        assertEquals(205, player.getPosX());
        assertEquals(355, player.getPosY());
    }

    @Test
    public void fire() {
        Player player = new Player(100, 250, 350);
        player.setFire(true);
        assertEquals(true, player.getFire());
        player.setFire(false);
        assertEquals(false, player.getFire());
    }

}
