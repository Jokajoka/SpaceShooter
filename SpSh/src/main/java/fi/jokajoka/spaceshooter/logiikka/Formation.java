/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.logiikka;

import fi.jokajoka.spaceshooter.gui.Game;
import fi.jokajoka.spaceshooter.units.Enemy;
import java.util.ArrayList;
import java.util.Random;

/**
 * Luo pelin vihollis muodostelman.
 *
 * @author Jonde
 */
public class Formation {

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Game instance;
    private Random random = new Random();

    /**
     * Konstruktori, jolla luodaan uusi muodostelma. Parametrina annetaan viite
     * Game-olioon.
     *
     * @param instance
     */
    public Formation(Game instance) {
        this.instance = instance;
    }

    /**
     * Tämä metodi luo muodostelmaan kuuluvat Enemy-oliot sopivalle etäisyydelle
     * toisistaan.
     */
    public void set() {
        int a = 0;
        while (a < 3) {
            int b = 0;
            while (b < 5) {
                this.enemies.add(new Enemy((10 + (1 * this.random.nextInt(5))), (80 + (b * 160)), (-100 - (a * 100)), 0.5, this.instance));
                b++;
            }
            a++;
            b = 0;
        }
    }

    /**
     * Tämä metodi päivittää Formation-olioon tallennettua listaa, joka sisältää
     * Enemy-olioita.
     */
    public void setNew() {
        ArrayList<Enemy> newEnemies = new ArrayList<>();
        for (Enemy enemy : this.enemies) {
            if (enemy.getAlive() == true || enemy.getKillTimer() > 0) {
                newEnemies.add(enemy);
            }
        }
        this.enemies = newEnemies;
    }

    /**
     * Tämä getteri palauttaa listan muodostelmaan talletetuista Enemy-olioista.
     *
     * @return ArrayList enemies
     */
    public ArrayList<Enemy> getFormation() {
        return this.enemies;
    }

    /**
     * Tämä metodi vastaa sen sisäisen Enemy-listan ja sen olioiden
     * päivityksestä.
     */
    public void update() {
        setNew();
        for (Enemy enemy : this.enemies) {
            enemy.update();
        }
    }

}
