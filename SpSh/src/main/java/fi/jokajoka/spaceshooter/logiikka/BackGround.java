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

/**
 * Metodilla luodaan uusi taustakuva-olio. 
 *
 * @param	syote	x-koordinaatti alussa, y-koordinaatti alussa
 *
 * @return BackGround-olio
 */

    public BackGround(int posX, int posY) {

        this.posX = posX;
        this.posY = posY;
    }

/**
 * Metodilla määritetään käytettävä taustakuva olio-muuttujaan. 
 *
 * @param	syote	kuvatiedosto
 *
 * @return void
 */

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

/**
 * 2 metodia, joilla muutetaan olion sijaintia pystysuunnassa.
 *
 * @param	syote	siirrettävä määrä kokonaislukuna
 *
 * @return void
 */

    public void moveDown(int y) {
        this.posY = this.posY + y;
    }

    public void moveUp(int y) {
        this.posY = this.posY - y;
    }

}
