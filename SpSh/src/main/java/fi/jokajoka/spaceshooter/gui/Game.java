/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.gui;

import fi.jokajoka.spaceshooter.units.Player;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author kahonjon
 */
public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;
    public static int height = 400;
    public static int width = 400;
    public String title = "SpaceShooter";
    public ArrayList<Integer> state = new ArrayList<>();
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    public static void main(String[] args) {
        // TODO code application logic here
        Game instance = new Game();

        Dimension d = new Dimension(width * 2, height * 2);
        instance.setPreferredSize(d);

        JFrame frame = new JFrame(instance.title);
        frame.add(instance);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                tick();
                updates++;
                delta--;
            }
            //update();
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

    public synchronized void start() {
        if (running == true) {
            return;
        } else {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

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

    public void init() {

    }

    public void tick() {

    }

    public void repaint() {
        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = buffer.getDrawGraphics();
        g.drawImage(image, 0, 0, getHeight(), getWidth(), this);

        g.dispose();
        buffer.show();
    }

}
