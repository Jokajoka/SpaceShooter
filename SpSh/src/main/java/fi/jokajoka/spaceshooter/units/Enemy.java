/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.units;

import fi.jokajoka.spaceshooter.units.Unit;

/**
 *
 * @author kahonjon
 */
public class Enemy extends Unit {
    
    public Enemy(int health, int posX, int posY, double damage) {
        super(health, posX, posY);
        this.setDamage(damage);
        this.setPlayable(false);
    }
}
