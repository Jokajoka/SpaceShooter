/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceshooter.spsh;

/**
 *
 * @author kahonjon
 */
public class Unit {
    
    private int health;
    
    public Unit(int health){
        
        this.health = health;
        
    }
    
    public void reduce(int x){
        
        this.health = this.health - x;
        
    }
    
    public int getHealth() {
        return this.health;
    }
    
}
