/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.units;

import fi.jokajoka.spaceshooter.gui.Game;
import fi.jokajoka.spaceshooter.gui.SS;
import fi.jokajoka.spaceshooter.logiikka.Buff;
import fi.jokajoka.spaceshooter.logiikka.Movement;
import fi.jokajoka.spaceshooter.logiikka.Projectile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author kahonjon
 */
public class Player extends Unit {

    private boolean alive;
    private BufferedImage player;
    private Movement movement;
    private int chargeShoot;
    private Game instance;
    private ArrayList<Projectile> ammo = new ArrayList<>();
    private ArrayList<Projectile> removeAmmo = new ArrayList<>();
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
        this.setPlayable(true);
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
        this.setPlayable(true);
        //asettaa pelaajan "henkiin".
        this.alive = true;
        this.instance = instance;
        SS ss = new SS(instance.getSheet());
        player = ss.crop(1, 1, 60, 60);
        this.movement = new Movement(this);
        this.chargeShoot = 10;

    }

    public boolean getAlive() {
        return this.alive;
    }

    /**
     * Metodi, joka suoritetaan, kun pelaaja kerää kentältä buff olion.
     * Tarkoituksena muuttaa pelaajan oliomuuttujien arvoa.
     *
     * @param buff Buff olio, joita on useita tyyppejä.
     */
    public void getBuff(Buff buff) {
        if (buff.getType().equals("a")) {
            this.setDamage(this.getDamage() * 2);
        }
    }

    public ArrayList<Projectile> getAmmo() {
        return this.ammo;
    }
    
    public void setAmmo(ArrayList<Projectile> newAmmo){
        this.ammo = newAmmo;
    }

    public void shoot() {
        if (this.chargeShoot == 30) {
            this.ammo.add(new Projectile(this.instance, this));
            this.chargeShoot = 0;
        }
    }

    public int killed() {
        return this.killed;
    }

    public void enemyDestroyed() {
        this.killed++;
    }

    public void kill() {
        this.alive = false;
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
        if(this.getFire() == true){
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

    public void moveRight() {
        this.setSpeedX(5);
    }

    public void moveUp() {
        this.setSpeedY(-5);
    }

    public void moveDown() {
        this.setSpeedY(5);
    }

    public void stop() {
        this.setSpeedX(0);
        this.setSpeedY(0);
    }

    public void fire() {
        this.setFire(true);
    }

    public void stopFire() {
        this.setFire(false);
    }

}
