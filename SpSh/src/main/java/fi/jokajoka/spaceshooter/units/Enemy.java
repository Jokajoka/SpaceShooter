/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.units;

import fi.jokajoka.spaceshooter.gui.Game;
import fi.jokajoka.spaceshooter.gui.SS;
import fi.jokajoka.spaceshooter.logiikka.Projectile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Luo pelin vihollis-oliot.
 *
 * @author kahonjon
 */
public class Enemy extends Unit {

    private BufferedImage image;
    private Random random = new Random();
    private boolean alive;
    private Game instance;
    private int killTimer;

    /**
     * Metodilla luodaan uusi Enemy-olio.
     *
     * @param health Terveys alussa.
     * @param posX X-koordinaatti alussa.
     * @param posY Y-koordinaatti alussa.
     * @param damage Vahingon mÃ¤Ã¤rÃ¤.
     */
    public Enemy(int health, int posX, int posY, double damage) {
        super(health, posX, posY);
        this.setDamage(damage);
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
        SS ss = new SS(instance.getSheet());
        image = ss.crop(2, 1, 60, 60);
        this.setSpeedY(1);
        this.alive = true;
        this.instance = instance;
        this.killTimer = 5;
    }

    /**
     * Tapa olio.
     */
    public void kill() {
        this.alive = false;
    }

    /**
     * Getteri onko hengissä.
     *
     * @return boolean alive
     */
    public boolean getAlive() {
        return this.alive;
    }

    /**
     * Getteri killtimerille.
     *
     * @return Integer killTimer
     */
    public int getKillTimer() {
        return this.killTimer;
    }

    /**
     * Tämä metodi tarkkailee, törmääkö jokin vihollis aluksista pelaajaan ja
     * muokkaa näiden tilaa sen mukaan.
     *
     * @param player
     */
    public void checkCollision(Player player) {
        if ((player.getPosY() - this.getPosY() <= 60)
                && (player.getPosY() - this.getPosY() >= 0)
                && (player.getPosX() - this.getPosX() >= -60)
                && ((player.getPosX()) - this.getPosX() <= 60)
                && this.getAlive() == true) {
            player.reduce(50);
            this.kill();
            if (player.getHealth() == 0) {
                player.kill();
            }
            this.instance.getPlayer().enemyDestroyed();
        }
    }

    /**
     * TÃ¤llÃ¤ metodilla pÃ¤ivitetÃ¤Ã¤n Enemy olion tila.
     */
    public void update() {
        if (this.getAlive() == true) {
            int ran = random.nextInt(2);
            if (random.nextInt(10) == 0) {
                if (ran == 0) {
                    this.setSpeedX(1);
                } else if (ran == 1) {
                    this.setSpeedX(-1);
                } else if (ran == 2) {
                    this.setSpeedX(0);
                }
            }
            if (getSpeedY() == 1) {
                setSpeedY(0);
            } else {
                setSpeedY(1);
            }

            this.setPosY();
            this.setPosX();

            for (Projectile projectile : this.instance.getPlayer().getAmmo()) {
                projectile.checkCollision(this);
            }
            this.checkCollision(this.instance.getPlayer());
        } else {
            if (this.killTimer == 5) {
                this.image = new SS(this.instance.getSheet()).crop(2, 2, 60, 60);
            }
            this.killTimer--;
        }

    }

    /**
     * TÃ¤llÃ¤ metodilla piirretÃ¤Ã¤n Enemy-olioon asetettu kuva sen X- ja
     * Y-koordinaatteihin.
     *
     * @param g piirto-olio
     */
    public void paint(Graphics g) {
        if (this.getAlive() == true || this.getKillTimer() > 0) {
            g.drawImage(image, this.getPosX(), this.getPosY(), null);
        }

    }
}
