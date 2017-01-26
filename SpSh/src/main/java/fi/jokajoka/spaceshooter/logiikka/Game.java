/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.logiikka;

import java.awt.Canvas;

/**
 *
 * @author kahonjon
 */
public class Game implements Runnable {

    private boolean running;
    
    public static void main(String[] args) {
        // TODO code application logic here
        Game game = new Game();
    }
    @Override
    public void run() {
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
            if (delta <= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
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
    
    public void tick(){
        
    }
    
    public void render() {
        
    }
    
    public void stop(){
        
    }
    
}
