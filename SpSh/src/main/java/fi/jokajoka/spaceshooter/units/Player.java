/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.units;

import fi.jokajoka.spaceshooter.logiikka.Buff;
import fi.jokajoka.spaceshooter.units.Unit;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author kahonjon
 */
public class Player extends Unit {

    private boolean alive;

/**
 * Unit superluokan perivä luokka, jolla luodaan itse pelaaja peliin.
 *
 * @param	syote	terveys alussa, x-koordinaatti alussa, y-koordinaatti alussa
 *
 * @return Player-olio
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
 * Metodi, joka suoritetaan, kun pelaaja kerää kentältä buff olion. 
 * Tarkoituksena muuttaa pelaajan oliomuuttujien arvoa.
 *
 * @param	syote	Buff-olio
 *
 * @return void
 */

    public void getBuff(Buff buff) {
        if (buff.getType().equals("a")) {
            this.setDamage(this.getDamage() * 2);
        }
    }

/**
 * Metodi, joka suoritetaan jokaisessa game-loopin vaiheessa.
 * Tarkoituksena päivittää pelaajan sijainti.
 *
 * @param	syote
 *
 * @return void
 */

    public void update() {

        //liikuttaa pelaajaa x suunnassa.
        this.setPosX();
        //sama y suunnassa.
        this.setPosY();

    }


/**
 * Useita metodeja, joita on tarkoitus kutsua näppäinten painalluksien yhteydessä 
 * Tarkoituksena muuttaa pelaajan nopeutta eri suuntiin, sekä ohjata tulitusta.
 *
 * @param	syote	
 *
 * @return void
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
