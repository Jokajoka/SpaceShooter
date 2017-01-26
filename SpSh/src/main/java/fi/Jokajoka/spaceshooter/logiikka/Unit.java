/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.Jokajoka.spaceshooter.logiikka;

/**
 *
 * @author kahonjon
 */
public class Unit {

    private int health;
    private double damage;
    private boolean playable;
    private int posX;
    private int posY;
    private int speedX;
    private int speedY;

    public Unit(int health, int posX, int posY) {
        this.health = health;
        this.speedX = 0;
        this.speedY = 0;
    }

    public void heal(int amount) {
        if (this.health + amount > 100) {
            this.health = this.health + amount;
        } else {
            this.health = 100;
        }
    }

    public void reduce(int amount) {
        if (this.health > amount) {
            this.health = this.health - amount;
        } else {
            this.health = 0;
        }
    }

    public int getHealth() {
        return this.health;
    }

    public double getDamage() {
        return this.damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
    
    public int getPosX(){
        return this.posX;
    }
    
    public int getPosY(){
        return this.posY;
    }

    public void setPlayable(boolean set) {
        if (set == true) {
            this.playable = true;
        } else if (set = false) {
            this.playable = false;
        } else {
            System.out.println("Set either true of false");
        }
    }

    public boolean getPlayable() {
        return this.playable;
    }

    public void setPosX() {
            //tarkistetaan että liikkuu
        if (this.speedX > 0) {
                //tarkistetaan, ettei olla reunalla
            if (this.posX != 400) {
                    //tarkistetaan, ettei mennä yli reunasta
                if (this.posX + this.speedX <= 400) {
                    this.posX = this.posX + this.speedX;
                } else {
                    this.posX = 400;
                }
            } else {
                this.posX = 400;
            }
            //tarkistetaan että liikkuu
        } else if (this.speedX < 0) {
                //tarkistetaan, ettei olla reunalla
            if (this.posX != 0) {
                    //tarkistetaan, ettei mennä yli reunasta
                if (this.posX - this.speedX >= 0) {
                    this.posX = this.posX - this.speedX;
                } else {
                    this.posX = 0;
                }
            } else {
                this.posX = 0;
            }
        }
    }

    public void setPosY() {
        //tarkistetaan että liikkuu
        if (this.speedY > 0) {
                //tarkistetaan, ettei olla reunalla
            if (this.posY != 400) {
                    //tarkistetaan, ettei mennä yli reunasta
                if (this.posY + this.speedY <= 400) {
                    this.posY = this.posY + this.speedY;
                } else {
                    this.posY = 400;
                }
            } else {
                this.posY = 400;
            }
            //tarkistetaan että liikkuu
        } else if (this.speedY < 0) {
                //tarkistetaan, ettei olla reunalla
            if (this.posY != 0) {
                    //tarkistetaan, ettei mennä yli reunasta
                if (this.posY - this.speedY >= 0) {
                    this.posY = this.posY - this.speedY;
                } else {
                    this.posY = 0;
                }
            } else {
                this.posY = 0;
            }
        }
    }

}
