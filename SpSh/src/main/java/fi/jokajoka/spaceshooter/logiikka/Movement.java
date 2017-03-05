/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.logiikka;

import fi.jokajoka.spaceshooter.gui.SS;
import fi.jokajoka.spaceshooter.units.Player;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Vastaa pelin näppäineten kuuntelusta.
 *
 * @author kahonjon
 */
public class Movement implements KeyListener {

    private Player player;

    /**
     * Näppäimistön kuuntelija. Kuuntelijalle annetaan parametriksi Player-olio.
     *
     * @param x Player-olio
     */
    public Movement(Player x) {
        this.player = x;
    }

    /**
     * Tämä metodi vastaa näppäinten painalluksesta.
     *
     * @param a
     */
    @Override
    public void keyPressed(KeyEvent a) {

        switch (a.getKeyCode()) {

            case KeyEvent.VK_W:
                this.player.moveUp();
                break;

            case KeyEvent.VK_S:
                this.player.moveDown();
                break;

            case KeyEvent.VK_A:
                this.player.moveLeft();
                this.player.setImage(new SS(this.player.getGame().getSheet()).crop(1, 2, 60, 60));
                break;

            case KeyEvent.VK_D:
                this.player.moveRight();
                this.player.setImage(new SS(this.player.getGame().getSheet()).crop(1, 3, 60, 60));
                break;

            case KeyEvent.VK_SPACE:
                this.player.fire();
                break;
        }

    }

    /**
     * Tämä metodi vastaa näppäimen vapautuksesta.
     *
     * @param a
     */
    @Override
    public void keyReleased(KeyEvent a) {

        switch (a.getKeyCode()) {

            case KeyEvent.VK_W:
                this.player.setSpeedY(0);
                break;

            case KeyEvent.VK_S:
                this.player.setSpeedY(0);
                break;

            case KeyEvent.VK_A:
                this.player.setSpeedX(0);
                this.player.setImage(new SS(this.player.getGame().getSheet()).crop(1, 1, 60, 60));
                break;

            case KeyEvent.VK_D:
                this.player.setSpeedX(0);
                this.player.setImage(new SS(this.player.getGame().getSheet()).crop(1, 1, 60, 60));
                break;

            case KeyEvent.VK_SPACE:
                this.player.stopFire();
                break;
        }

    }

    /**
     * Tällä metodilla ei ole ohjelmassa käyttöä.
     *
     * @param a
     */
    @Override
    public void keyTyped(KeyEvent a) {
    }

}
