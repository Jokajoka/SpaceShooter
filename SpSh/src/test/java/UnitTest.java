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
import fi.Jokajoka.spaceshooter.logiikka.Unit;

/**
 *
 * @author kahonjon
 */
public class UnitTest {
    
    public UnitTest() {
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
    public void damage(){
        Unit player = new Unit(100, 200, 350);
        player.reduce(33);
        if(player.getHealth() == 67){
            System.out.println("True");;
        } else {
            System.out.println("False");;
        }
        
    }
    
    @Test
    public void heal(){
        Unit player = new Unit(50, 200, 350);
        player.heal(15);
        if(player.getHealth() == 65){
            System.out.println("True");;
        } else {
            System.out.println("False");;
        }
    }
}
