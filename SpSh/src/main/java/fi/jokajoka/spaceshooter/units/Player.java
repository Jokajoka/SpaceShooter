/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.units;

import fi.jokajoka.spaceshooter.gui.Game;
import fi.jokajoka.spaceshooter.gui.SS;
import fi.jokajoka.spaceshooter.logiikka.Movement;
import fi.jokajoka.spaceshooter.logiikka.Projectile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Luo pelin pleaajan.
 *
 * @author kahonjon
 */
public class Player extends Unit {

    private boolean alive;
    private BufferedImage player;
    private Movement movement;
    private int chargeShoot;
    private Game instance;
    private ArrayList<Projectile> ammo = new ArrayList<>();
    private int killed = 0;

    /**
     * Unit superluokan perivä luokka, jolla luodaan itse pelaaja peliin.
     *
     * @param health Pelaajan terveyden määrä alussa.
     * @param posY Pelaajan Y-koordinaatti alussa.
     * @param posX Pelaajan X-koordinaatti alussa.
     */
    public Player(int health, int posX, int posY) {

        //asettaa pelaajan alkusijainnin.
        super(health, posX, posY);
        //asettaa pelaajan tekemän vahingon määrän.
        this.setDamage(1.5);
        //asettaa pelaaja olion pelattavaan tilaan.
        //asettaa pelaajan "henkiin".
        this.alive = true;

    }

    /**
     * Vaihtoehtoinen konstruktori.
     *
     * @param health Pelaajan terveyden määrä alussa.
     * @param posX Pelaajan Y-koordinaatti alussa.
     * @param posY Pelaajan X-koordinaatti alussa.
     * @param instance Itse peli, johon pelaaja luodaan.
     */
    public Player(int health, int posX, int posY, Game instance) {

        //asettaa pelaajan alkusijainnin.
        super(health, posX, posY);
        //asettaa pelaajan tekemän vahingon määrän.
        this.setDamage(5.0);
        //asettaa pelaaja olion pelattavaan tilaan.
        //asettaa pelaajan "henkiin".
        this.alive = true;
        this.instance = instance;
        SS ss = new SS(instance.getSheet());
        player = ss.crop(1, 1, 60, 60);
        this.movement = new Movement(this);
        this.chargeShoot = 10;

    }

    /**
     * Getteri onko elävä vai kuollut.
     *
     * @return boolean alive
     */
    public boolean getAlive() {
        return this.alive;
    }

    /**
     * Getteri mukana olevat ammukset.
     *
     * @return ArrayList ammo
     */
    public ArrayList<Projectile> getAmmo() {
        return this.ammo;
    }

    /**
     * Muuttaa mukana olevien ammusten listan sisältöä.
     *
     * @param newAmmo
     */
    public void setAmmo(ArrayList<Projectile> newAmmo) {
        this.ammo = newAmmo;
    }

    /**
     * Getteri Game-olioon.
     *
     * @return Game instance
     */
    public Game getGame() {
        return this.instance;
    }

    /**
     * Säätelee tulituksen tiheyttä.
     */
    public void shoot() {
        if (this.chargeShoot == 30) {
            this.ammo.add(new Projectile(this.instance, this));
            this.chargeShoot = 0;
        }
    }

    /**
     * Palauttaa tuhottujen vihollisten määrän.
     *
     * @return Integer killed
     */
    public int killed() {
        return this.killed;
    }

    /**
     * Lisää tuhottujen vihollisten määrää.
     */
    public void enemyDestroyed() {
        this.killed++;
    }

    /**
     * Tappaa pelaajan.
     */
    public void kill() {
        this.alive = false;
    }

    /**
     * Muuttaa pelaajan kuvaketta.
     *
     * @param image
     */
    public void setImage(BufferedImage image) {
        this.player = image;
    }

    /**
     * Metodi, joka suoritetaan jokaisessa game-loopin vaiheessa. Tarkoituksena
     * päivittää pelaajan sijainti.
     *
     *
     */
    public void update() {

        //liikuttaa pelaajaa x suunnassa.
        this.setPosX();
        //sama y suunnassa.
        this.setPosY();

        //ampumistiheyden rajoitus
        if (this.getFire() == true) {
            shoot();
        }

        if (this.chargeShoot < 30) {
            this.chargeShoot++;
        }

        for (Projectile projectile : this.ammo) {
            if (projectile.getPosY() >= -100) {
                projectile.update();
            }
        }
    }

    /**
     * Metodi, jolla piirretään pelaajalle asetettu kuva sen x- ja
     * y-koordinaatteihin.
     *
     * @param g piirto-olio
     */
    public void paint(Graphics g) {
        g.drawImage(this.player, this.getPosX(), this.getPosY(), null);
    }

    /**
     * Useita metodeja, joita on tarkoitus kutsua näppäinten painalluksien
     * yhteydessä Tarkoituksena muuttaa pelaajan nopeutta eri suuntiin, sekä
     * ohjata tulitusta.
     *
     */
    public void moveLeft() {
        this.setSpeedX(-5);
    }

    /**
     * Muuttaa pelaajan x-koordinaattia.
     */
    public void moveRight() {
        this.setSpeedX(5);
    }

    /**
     * Muuttaa pelaajan y-koordinaattia.
     */
    public void moveUp() {
        this.setSpeedY(-5);
    }

    /**
     * Muuttaa pelaajan y-koordinaattia.
     */
    public void moveDown() {
        this.setSpeedY(5);
    }

    /**
     * Pysäyttää pelaajan liikkeen.
     */
    public void stop() {
        this.setSpeedX(0);
        this.setSpeedY(0);
    }

    /**
     * Aloittaa tulituksen.
     */
    public void fire() {
        this.setFire(true);
    }

    /**
     * Keskeyttää tulituksen.
     */
    public void stopFire() {
        this.setFire(false);
    }

}
