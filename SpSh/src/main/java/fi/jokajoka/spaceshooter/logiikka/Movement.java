/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.logiikka;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author kahonjon
 */
public class Movement implements KeyListener {
    
    
    private Player player;
    
    public Movement(Player x) {
        this.player = x;
    }
    //keyListener superluokan metodit.
    // Lisätään toiminta painettaessa.
    @Override
    public void keyPressed(KeyEvent a) {
        
        switch (a.getKeyCode()) {
            
            case KeyEvent.VK_W:
                this.player.moveUp();
                break;
            
            case KeyEvent.VK_S:
                this.player.moveDown();
                break;
            
            case KeyEvent.VK_A:
                this.player.moveLeft();
                break;
            
            case KeyEvent.VK_D:
                this.player.moveRight();
                break;
                
            case KeyEvent.VK_SPACE:
                this.player.fire();
                break;
        }
      
    }
    
    //Lisätään otiminta vapautettaessa. Toistaiseksi sama, mutta tulee muuttumaan myöhemmin.
    @Override
    public void keyReleased(KeyEvent a) {
       
        switch (a.getKeyCode()) {
            
            case KeyEvent.VK_W:
                this.player.stop();
                break;
            
            case KeyEvent.VK_S:
                this.player.stop();
                break;
            
            case KeyEvent.VK_A:
                this.player.stop();
                break;
            
            case KeyEvent.VK_D:
                this.player.stop();
                break;
                
            case KeyEvent.VK_SPACE:
                this.player.stopFire();
                break;
        }
        
    }
    //Ei tarvita pelissä, mutta täytyi löytyä aliluokalta.
    @Override
    public void keyTyped(KeyEvent a){}
        
}
