/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.Jokajoka.spaceshooter.logiikka;

/**
 *
 * @author kahonjon
 */
public class Player extends Unit{
    
    private boolean alive;
    
    
    public Player(int health, int posX, int posY) {
        
            //asettaa pelaajan alkusijainnin.
        super(health, posX, posY);
            //asettaa pelaajan tekemän vahingon määrän.
        this.setDamage(1.5);
            //asettaa pelaaja olion pelattavaan tilaan.
        this.setPlayable(true);
            //asettaa pelaajan "henkiin".
        this.alive = true;
        
    }
    
    public void update(){
        
            //liikuttaa pelaajaa x suunnassa.
        this.setPosX();
            //sama y suunnassa.
        this.setPosY();
        
    }
    
}
