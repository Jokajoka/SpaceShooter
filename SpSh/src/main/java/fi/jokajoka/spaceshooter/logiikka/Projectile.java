/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.logiikka;

import fi.jokajoka.spaceshooter.gui.Game;
import fi.jokajoka.spaceshooter.gui.SS;
import fi.jokajoka.spaceshooter.units.Enemy;
import fi.jokajoka.spaceshooter.units.Player;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Tämä luokka toteuttaa pelaajan ampumat ammukset.
 *
 * @author Jonde
 */
public class Projectile {

    private BufferedImage projectile;
    private Game instance;
    private int posY;
    private int posX;
    private int speedY = 10;
    private boolean usable = true;
    private int blowTimer = 0;
    private Player player;
    private Random random;

    /**
     * Konsturktori Pelaaja-olion luontiin.
     *
     * @param instance
     * @param player
     */
    public Projectile(Game instance, Player player) {
        this.instance = instance;
        SS ss = new SS(this.instance.getSheet());
        projectile = ss.crop(3, 1, 60, 60);
        this.player = player;
        this.posY = player.getPosY();
        this.posX = player.getPosX();
        this.random = new Random();
    }

    /**
     * Tarkistaa, mikäli kyseinen olio osuu johonkin Enemy-olioon ja muuttaa
     * tämän tilaa tarvittaessa.
     *
     * @param enemy
     */
    public void checkCollision(Enemy enemy) {
        if (((enemy.getPosY() + 60) - this.getPosY() >= 0)
                && ((enemy.getPosY() + 60) - this.getPosY() <= 60)
                && (enemy.getPosX() - (this.getPosX() - 30) >= 0)
                && ((enemy.getPosX()) - this.getPosX() <= 30)
                && enemy.getAlive() == true
                && this.usable == true) {
            enemy.reduce((int) this.instance.getPlayer().getDamage());

            randomPicture();

            this.blowTimer = 3;
            if (enemy.getHealth() == 0) {
                enemy.kill();
                this.instance.getPlayer().enemyDestroyed();
            }
            this.usable = false;
        }
    }

    /**
     * Tämä metodi muuttaa ammuksen kuvakkeen räjähdykseksi osuman sattuessa.
     */
    public void randomPicture() {
        if (this.random.nextInt(1) == 1) {
            this.projectile = new SS(this.instance.getSheet()).crop(4, 1, 60, 60);
        } else {
            this.projectile = new SS(this.instance.getSheet()).crop(4, 2, 60, 60);
        }
    }

    /**
     * Tällä metodilla tarkisetaan, onko ammus vielä käyttettävissä pelin
     * sisäisissä toiminnoissa.
     *
     * @return boolean usable
     */
    public boolean getUsable() {
        return this.usable;
    }

    /**
     * Tällä metodilla tarkkaillaan ammuksen osumahetkestä kulunutta aikaa.
     *
     * @return Integer aika
     */
    public int getBlowTimer() {
        return this.blowTimer;
    }

    /**
     * Tätä metodia käytetään ammusen päivittämiseen.
     */
    public void update() {
        ArrayList<Projectile> newProjectiles = new ArrayList<>();

        for (Projectile projectile : this.player.getAmmo()) {
            if (projectile.getUsable() == true) {
                newProjectiles.add(projectile);
            }
        }
        this.player.setAmmo(newProjectiles);

        if (this.usable == true) {
            this.posY -= speedY;
        }
        if (this.getPosY() <= -60) {
            this.usable = false;
        }

        if (this.blowTimer > 0) {
            this.blowTimer--;
        }

    }

    /**
     * Tätä metodia käytetään ammusten piirtämiseen.
     *
     * @param g
     */
    public void paint(Graphics g) {
        g.drawImage(this.projectile, this.posX, this.posY, null);
    }

    /**
     * Tällä metodilla päästään käsiksi ammuksen y-koordinaattiin.
     *
     * @return Integer posY
     */
    public int getPosY() {
        return this.posY;
    }

    /**
     * Tällä metodilla päästään käsiksi ammuksen x-koordinaattiin.
     *
     * @return Integer posX
     */
    public int getPosX() {
        return this.posX;
    }

}
