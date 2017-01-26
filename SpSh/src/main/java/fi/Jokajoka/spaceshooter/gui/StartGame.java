/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.Jokajoka.spaceshooter.gui;

import fi.Jokajoka.spaceshooter.logiikka.Movement;
import fi.Jokajoka.spaceshooter.logiikka.Player;
import java.applet.Applet;
import java.awt.*;
import fi.Jokajoka.spaceshooter.logiikka.Player;
import java.net.URL;



/**
 *
 * @author kahonjon
 */
public class StartGame extends Applet implements Runnable {
    
    private Image image;
    private Image character;
    private Graphics graphics;
    private Player player;
    private URL root;

    //Applet superluokan metodeja
    @Override
    public void init() {
        
        setSize(400, 400);
        setBackground(Color.BLACK);
        setFocusable(true);
        
        Frame frame = (Frame)this.getParent().getParent();
        frame.setTitle("SpaceShooter");
        
        Movement listener = new Movement();

    }

    @Override
    public void start() {
        Thread thread = new Thread(this); // LUodaan thread, johon määritellään päivitettävät asiat.
        thread.start();
        
        player = new Player (100, 200, 350);
    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void run() {
        while (true) {
            //Myöhemmin repaint();
            try {
                Thread.sleep(17);
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    }
    
    public void update(Graphics a){
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            graphics = image.getGraphics();
        }
        
        graphics.setColor(getBackground());
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(getForeground());
        paint(graphics);
        
        a.drawImage(image, 0, 0, this);
        
    }
    
    public void paint(Graphics a){
        a.drawImage(character, player.getPosX(), player.getPosY(), this);
    }

}
