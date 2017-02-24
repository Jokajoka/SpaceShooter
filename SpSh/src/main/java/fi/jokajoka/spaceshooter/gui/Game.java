/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.gui;

import fi.jokajoka.spaceshooter.logiikka.BackGround;
import fi.jokajoka.spaceshooter.logiikka.Formation;
import fi.jokajoka.spaceshooter.logiikka.Movement;
import fi.jokajoka.spaceshooter.logiikka.Projectile;
import fi.jokajoka.spaceshooter.units.Enemy;
import fi.jokajoka.spaceshooter.units.Player;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * @author kahonjon
 */
public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;
    private static int height = 400;
    private static int width = 400;
    private final String title = "SpaceShooter";
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private BufferedImage ss = null;

    private Player player;
    private BackGround bg;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Formation formation;
    private boolean victory = false;

    /**
     * PÃ¤Ã¤metodi, jossa luodaan ilmentymÃ¤ itse pelistÃ¤. Samassa luodaan
     * myÃ¶s ikkuna otsikkoineen, sekÃ¤ ulottovuuksineen ja lopulta ajetaan
     * start-metodi.
     *
     * @param args Kuvaus
     */
    public static void main(String[] args) {

        Game instance = new Game();

        Dimension d = new Dimension(width * 2, height * 2);
        instance.setPreferredSize(d);

        JFrame frame = new JFrame(instance.title);
        frame.add(instance);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        instance.start();

    }

    @Override
    public void run() {
        init();
        long previousT = System.nanoTime();
        final double ticks = 60.0;
        double nanoseconds = 1000000000 / ticks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            delta += (now - previousT) / nanoseconds;
            previousT = now;
            if (delta >= 1) {
                update();

                updates++;
                delta--;
            }

            repaint();

            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " Ticks, Fps " + frames);
                // LisÃ¤Ã¤ toimintaa
                System.out.println(this.formation.getFormation());
                System.out.println(this.player.getAmmo());

                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    /**
     * KÃ¤ynnistetÃ¤Ã¤n thread. MikÃ¤li jostain syystÃ¤ sattuisi tilanne, ettÃ¤
     * start metodi ajettaisiin toisen kerran, ohjelma hyppÃ¤Ã¤ pois metodista.
     */
    public synchronized void start() {
        if (running == true) {
            return;
        } else {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Luonnollisesti pysÃ¤ytetÃ¤Ã¤n thread. MikÃ¤li metodi ajetaan syystÃ¤ tai
     * toisesta vielÃ¤ pysÃ¤htymisen jÃ¤lkeen, ohjelma hyppÃ¤Ã¤ sieltÃ¤ suoraan
     * pois.
     */
    public synchronized void stop() {
        if (running == false) {
            return;
        } else {
            running = false;
            try {
                thread.join();
            } catch (Exception e) {
                System.out.println("Couldn't join threads");
            }
            System.exit(1);
        }
    }

    /**
     * Kun peli kÃ¤ynnistyy, init metodi suoritetaan ensimmÃ¤isenÃ¤. TÃ¤ssÃ¤
     * luodaan ilmentymÃ¤t kuvan lataajasta, pelaajasta, taustakuvasta sekÃ¤
     * nÃ¤ppÃ¤imistÃ¶n kuuntelijasta.
     */
    public void init() {
        Loader loader = new Loader();
        try {
            ss = loader.load("/playerSS.png");
        } catch (IOException e) {
            System.out.println("Fail");
        }
        player = new Player(100, 370, 670, this);
        bg = new BackGround(0, -800);
        this.formation = new Formation(this);
        formation.set();

        addKeyListener(new Movement(this.player));
    }

    /**
     * TÃ¤mÃ¤ metodi ajetaan jokaisella pelin sydÃ¤mmen sykÃ¤hdyksellÃ¤.
     * Tarkoituksena on pÃ¤ivittÃ¤Ã¤ objektien tila.
     */
    public void update() {

        bg.update();
        player.update();
        this.formation.update();

        if (player.getAlive() == false || this.victory == true) {
            repaint();
            stop();
        }
        if (player.killed() == 15) {
            this.victory = true;
        }
    }

    @Override
    public void repaint() {
        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null) {
            createBufferStrategy(2);
            return;
        }
        Graphics g = buffer.getDrawGraphics();

        g.drawImage(image, 0, 0, 800, 800, this);
        bg.paint(g);
        for (Enemy enemy : this.formation.getFormation()) {
            if (enemy.getAlive() == true) {
                enemy.paint(g);
            }
        }
        for (Projectile projectile : this.player.getAmmo()) {
            if (projectile.getPosY() >= -60 && (projectile.getUsable() == true || projectile.getBlowTimer() > 0)) {
                projectile.paint(g);
            }
        }
        player.paint(g);

        g.clearRect(20, 700, 100, 80);
        g.drawString("Health: " + this.player.getHealth(), 40, 720);
        g.drawString("Killed: " + this.player.killed(), 40, 760);
        if (player.getAlive() == false) {
            try {
                BufferedImage death = new Loader().load("/death.png");
                g.drawImage(death, 0, 0, 800, 800, this);
            } catch (IOException e) {
                System.out.println("Couldn't load image");
            }
        }
        if (this.victory == true) {
            try {
                BufferedImage victory = new Loader().load("/victory.png");
                g.drawImage(victory, 0, 0, 800, 800, this);
            } catch (IOException e) {
                System.out.println("Couldn't load image");
            }
        }

        g.dispose();
        buffer.show();
    }

    /**
     * TÃ¤llÃ¤ metodilla pÃ¤Ã¤stÃ¤Ã¤n itse pelin ilmentymÃ¤Ã¤n tallennettuun
     * SpriteSheet kuvaan kÃ¤siksi muista luokista.
     *
     * @return BufferedImage ss
     */
    public BufferedImage getSheet() {
        return ss;
    }

    public Player getPlayer() {
        return this.player;
    }

}
