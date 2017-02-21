/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.logiikka;

import fi.jokajoka.spaceshooter.gui.Game;
import fi.jokajoka.spaceshooter.gui.SS;
import fi.jokajoka.spaceshooter.units.Enemy;
import fi.jokajoka.spaceshooter.units.Unit;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
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

    public Projectile(Game instance, Unit unit) {
        this.instance = instance;
        SS ss = new SS(this.instance.getSheet());
        projectile = ss.crop(3, 1, 60, 60);
        this.posY = unit.getPosY() - 61;
        this.posX = unit.getPosX();
    }

    public void checkCollision(Enemy enemy) {
        if (((enemy.getPosY() + 60) - this.getPosY() >= 0)
                && ((enemy.getPosY() + 60) - this.getPosY() <= 60)
                && (enemy.getPosX() - (this.getPosX() - 30) >= 0)
                && ((enemy.getPosX()) - this.getPosX() <= 30)
                && enemy.getAlive() == true
                && this.usable == true) {
            enemy.reduce((int) this.instance.getPlayer().getDamage());
            if (enemy.getHealth() == 0) {
                enemy.kill();
                this.instance.getPlayer().enemyDestroyed();
            }
            this.usable = false;
        }
    }

    public boolean getUsable() {
        return this.usable;
    }

    public void update() {
        if (this.usable == true) {
            this.posY -= speedY;
        }

    }

    public void paint(Graphics g) {
        g.drawImage(this.projectile, this.posX, this.posY, null);
    }

    public int getPosY() {
        return this.posY;
    }

    public int getPosX() {
        return this.posX;
    }

}
