/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.units;

import fi.jokajoka.spaceshooter.gui.Game;
import fi.jokajoka.spaceshooter.gui.SS;
import fi.jokajoka.spaceshooter.units.Unit;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author kahonjon
 */
public class Enemy extends Unit {

    private BufferedImage image;
    private Random random = new Random();

    /**
     * Metodilla luodaan uusi Enemy-olio.
     *
     * @param health Terveys alussa.
     * @param posX X-koordinaatti alussa.
     * @param posY Y-koordinaatti alussa.
     * @param damage Vahingon määrä.
     */
    public Enemy(int health, int posX, int posY, double damage) {
        super(health, posX, posY);
        this.setDamage(damage);
        this.setPlayable(false);
    }

    /**
     * Vaihtoehtoinen konstruktori.
     *
     * @param health Terveys alussa.
     * @param posX X-koordinaatti alussa.
     * @param posY Y-koordinaatti alussa.
     * @param damage vahinko alussa.
     * @param instance Game-olio, johon Enemy-olio liittyy.
     */
    public Enemy(int health, int posX, int posY, double damage, Game instance) {
        super(health, posX, posY);
        this.setDamage(damage);
        this.setPlayable(false);
        SS ss = new SS(instance.getSheet());
        image = ss.crop(2, 1, 60, 60);
        this.setSpeedY(3);
    }

    /**
     * Tällä metodilla päivitetään Enemy olion tila.
     */
    public void update() {
        int ran = random.nextInt(2);
        if (ran == 0) {
            this.setSpeedX(3);
        } else if (ran == 1) {
            this.setSpeedX(-3);
        } else if (ran == 2) {
            this.setSpeedX(0);
        }

        this.setPosY();
        this.setPosX();

    }

    /**
     * Tällä metodilla piirretään Enemy-olioon asetettu kuva sen X- ja
     * Y-koordinaatteihin.
     *
     * @param g piirto-olio
     */
    public void paint(Graphics g) {
        g.drawImage(image, this.getPosX(), this.getPosY(), null);
    }
}
