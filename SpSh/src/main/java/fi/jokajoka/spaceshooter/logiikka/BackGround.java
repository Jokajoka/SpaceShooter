/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.logiikka;

import java.awt.Image;
import java.net.URL;

/**
 *
 * @author kahonjon
 */
public class BackGround {
    
    private int posX;
    private int posY;
    private Image image;
    private URL base;
    
    public BackGround(Image bg, int posX, int posY) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
    }
    
    public void moveDown(int y) {
        this.posY = this.posY + y;
    }
    
    public void moveUp(int y) {
        this.posY = this.posY - y;
    }
    
}
