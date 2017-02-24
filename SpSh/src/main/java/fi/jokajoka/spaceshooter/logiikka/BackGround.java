/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.logiikka;

import fi.jokajoka.spaceshooter.gui.Loader;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author kahonjon
 */
public class BackGround {

    private int posX;
    private int posY;
    private BufferedImage bg;

    /**
     * Metodilla luodaan uusi taustakuva-olio.
     *
     * @param posX x-koordinaatti
     * @param posY y-koordinaatti
     */
    public BackGround(int posX, int posY) {

        this.posX = posX;
        this.posY = posY;
        try {
            bg = new Loader().load("/bg1.png");
        } catch (IOException e) {
            System.out.println("Fail");
        }

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
     * @param y muutos
     */
    public void moveDown(int y) {
        this.posY = this.posY + y;
    }

    /**
     * @param y muutos
     */
    public void moveUp(int y) {
        this.posY = this.posY - y;
    }

    /**
     * @param g piirto-olio
     */
    public void paint(Graphics g) {
        g.drawImage(bg, this.getPosX(), this.getPosY(), null);
    }

    /**
     * Tällä päivitetään taustakuvan tila.
     */
    public void update() {
        this.moveDown(1);
        if (this.getPosY() == -1) {
            this.moveUp(799);
        }
    }

}
