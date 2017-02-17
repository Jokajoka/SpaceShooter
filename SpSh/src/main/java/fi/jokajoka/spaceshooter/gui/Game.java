/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.gui;

import fi.jokajoka.spaceshooter.logiikka.BackGround;
import fi.jokajoka.spaceshooter.logiikka.Movement;
import fi.jokajoka.spaceshooter.units.Enemy;
import fi.jokajoka.spaceshooter.units.Player;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
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
    private Random random = new Random(2);

    /**
     * Päämetodi, jossa luodaan ilmentymä itse pelistä. Samassa luodaan myös
     * ikkuna otsikkoineen, sekä ulottovuuksineen ja lopulta ajetaan
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
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    /**
     * Käynnistetään thread. Mikäli jostain syystä sattuisi tilanne, että start
     * metodi ajettaisiin toisen kerran, ohjelma hyppää pois metodista.
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
     * Luonnollisesti pysäytetään thread. Mikäli metodi ajetaan syystä tai
     * toisesta vielä pysähtymisen jälkeen, ohjelma hyppää sieltä suoraan pois.
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
     * Kun peli käynnistyy, init metodi suoritetaan ensimmäisenä. Tässä luodaan
     * ilmentymät kuvan lataajasta, pelaajasta, taustakuvasta sekä näppäimistön
     * kuuntelijasta.
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

        addKeyListener(new Movement(this.player));

    }

    /**
     * Tämä metodi ajetaan jokaisella pelin sydämmen sykähdyksellä.
     * Tarkoituksena on päivittää objektien tila.
     */
    public void update() {
        bg.update();
        player.update();
        for (Enemy enemy : enemies) {
            enemy.update();
            if (enemy.getPosY() == 740) {
                enemies.remove(enemy);
            }
        }
    }

    @Override
    public void repaint() {
        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = buffer.getDrawGraphics();

        g.drawImage(image, 0, 0, 800, 800, this);
        bg.paint(g);
        player.paint(g);
        for (Enemy enemy : enemies) {
            enemy.paint(g);
        }

        g.dispose();
        buffer.show();
    }

    /**
     * Tällä metodilla päästään itse pelin ilmentymään tallennettuun SpriteSheet
     * kuvaan käsiksi muista luokista.
     *
     * @return BufferedImage ss
     */
    public BufferedImage getSheet() {
        return ss;
    }

}
