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

    public BackGround(int posX, int posY) {

        this.posX = posX;
        this.posY = posY;
    }

    public void addImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void moveDown(int y) {
        this.posY = this.posY + y;
    }

    public void moveUp(int y) {
        this.posY = this.posY - y;
    }

}
