/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.units;

/**
 * Toimii superluokkana sekä Player,- että Enemy-olioille.
 *
 * @author kahonjon
 */
public class Unit {

    private int health;
    private double damage;
    private int posX;
    private int posY;
    private int speedX;
    private int speedY;
    private boolean fire = false;

    /**
     * Unit luokka toimii superluokkana sekä pelaajalle, että pleissä
     * esiintyville vihollisille. Kaikille uniteille on ominaista sijainti x ja
     * y suunnassa, sekä terveyden määrä.
     *
     * @param health terveys
     * @param posX x-koordinaatti
     * @param posY y-koordinaatti
     */
    public Unit(int health, int posX, int posY) {
        this.health = health;
        this.speedX = 0;
        this.speedY = 0;
        this.posX = posX;
        this.posY = posY;

    }

    /**
     * Metodi parantaa kyseisen olion terveyden määrää annetun määrän
     * ylittämättä arvoa 100.
     *
     * @param amount
     */
    public void heal(int amount) {
        if (this.health + amount < 100) {
            this.health = this.health + amount;
        } else {
            this.health = 100;
        }
    }

    /**
     * Metodi vähentää kyseisen olion terveyden määrää annetun määrän
     * alittamatta arvoa 0.
     *
     * @param amount
     */
    public void reduce(int amount) {
        if (this.health > amount) {
            this.health = this.health - amount;
        } else {
            this.health = 0;
        }
    }

    /**
     * Getteri terveydelle.
     *
     * @return Integer health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Getteri vahingolle.
     *
     * @return double damage
     */
    public double getDamage() {
        return this.damage;
    }

    /**
     * Setteri vahingolle.
     *
     * @param damage
     */
    public void setDamage(double damage) {
        this.damage = damage;
    }

    /**
     * Getteri x-koordinaatille.
     *
     * @return Integer posX
     */
    public int getPosX() {
        return this.posX;
    }

    /**
     * Getteri y-koordinaatille.
     *
     * @return Integer posY
     */
    public int getPosY() {
        return this.posY;
    }

    /**
     * Setteri x-suuntaiselle nopeudelle.
     *
     * @param speed
     */
    public void setSpeedX(int speed) {
        this.speedX = speed;
    }

    /**
     * Setteri y-suuntaiselle nopeudelle.
     *
     * @param speed
     */
    public void setSpeedY(int speed) {
        this.speedY = speed;
    }

    /**
     * Getteri x-suuntaiselle nopeudelle.
     *
     * @return Integer speedX
     */
    public int getSpeedX() {
        return this.speedX;
    }

    /**
     * Getteri y-suuntaiselle nopeudelle.
     *
     * @return Integer speedY
     */
    public int getSpeedY() {
        return this.speedY;
    }

    /**
     * Setteri x-koordinaatille.
     */
    public void setPosX() {

        if (this.speedX > 0) {

            if (this.posX != 740) {

                if (this.posX + this.speedX <= 740) {
                    this.posX = this.posX + this.speedX;
                } else {
                    this.posX = 740;
                }
            } else {
                this.posX = 740;
            }

        } else if (this.speedX < 0) {

            if (this.posX != 0) {

                if (this.posX + this.speedX >= 0) {
                    this.posX = this.posX + this.speedX;
                } else {
                    this.posX = 0;
                }
            } else {
                this.posX = 0;
            }
        }
    }

    /**
     * Setteri y-koordinaatille.
     */
    public void setPosY() {

        if (this.speedY > 0) {

            if (this.posY != 740) {

                if (this.posY + this.speedY <= 740) {
                    this.posY = this.posY + this.speedY;
                } else {
                    this.posY = 740;
                }
            } else {
                this.posY = 740;
            }

        } else if (this.speedY < 0) {

            if (this.posY != 0) {

                if (this.posY + this.speedY >= 0) {
                    this.posY = this.posY + this.speedY;
                } else {
                    this.posY = 0;
                }
            } else {
                this.posY = 0;
            }
        }
    }

    /**
     * Setteri tulitukselle.
     *
     * @param value
     */
    public void setFire(boolean value) {
        this.fire = value;
    }

    /**
     * Getteri tulitukselle.
     *
     * @return boolean fire
     */
    public boolean getFire() {
        return this.fire;
    }

}
