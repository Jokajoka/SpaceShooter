/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.Jokajoka.spaceshooter.logiikka;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author kahonjon
 */
public class Movement implements KeyListener {
    
    //keyListener superluokan metodit.
    
    // Lisätään toiminta painettaessa.
    @Override
    public void keyPressed(KeyEvent a){
        
        key(a);
      
    }
    
    //Lisätään otiminta vapautettaessa. Toistaiseksi sama, mutta tulee muuttumaan myöhemmin.
    @Override
    public void keyReleased(KeyEvent a){
       
        key(a);
        
    }
    //Ei tarvita pelissä, mutta täytyi löytyä aliluokalta.
    @Override
    public void keyTyped(KeyEvent a){}
    
    // Itse näppäimen kuuntelu   
    public void key(KeyEvent a){
        switch (a.getKeyCode()){
            
            case KeyEvent.VK_W:
                System.out.println("W");
                break;
            
            case KeyEvent.VK_S:
                System.out.println("S");
                break;
            
            case KeyEvent.VK_A:
                System.out.println("A");
                break;
            
            case KeyEvent.VK_D:
                System.out.println("D");
                break;
                
            case KeyEvent.VK_SPACE:
                System.out.println("FIRE");
                break;
        }
    }
    
}
