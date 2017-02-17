/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.logiikka;

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
public class BackGroundTest {

    public BackGroundTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Test
    public void posXTest(){
        BackGround bg = new BackGround(10, 12);
        assertEquals(10, bg.getPosX());
        assertEquals(12, bg.getPosY());
    }
    
    @Test public void moveTest(){
        BackGround bg = new BackGround(0, 0);
        bg.moveDown(10);
        assertEquals(10, bg.getPosY());
        bg.moveUp(5);
        assertEquals(5, bg.getPosY());
    }
}
