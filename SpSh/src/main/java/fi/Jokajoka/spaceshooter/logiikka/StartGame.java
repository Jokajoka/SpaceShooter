/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.Jokajoka.spaceshooter.logiikka;

import java.applet.Applet;
import java.awt.*;



/**
 *
 * @author kahonjon
 */
public class StartGame extends Applet implements Runnable {

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

}
