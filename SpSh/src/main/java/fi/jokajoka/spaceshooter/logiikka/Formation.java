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
 *
 * @author Jonde
 */
public class Formation {

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Game instance;
    private Random random = new Random();

    public Formation(Game instance) {
        this.instance = instance;
    }

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

    public ArrayList<Enemy> getFormation() {
        return this.enemies;
    }

    public void remove(Enemy enemy) {
        this.enemies.remove(enemy);
    }

    public void update() {
        for (Enemy toUpdate : this.enemies) {
            if (toUpdate.getAlive() == true) {
                toUpdate.update();
            }
        }
    }

}
