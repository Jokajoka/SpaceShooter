/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.logiikka;

/**
 *
 * @author kahonjon
 */
public class Buff {

    private String type;

/**
 * Metodilla luodaan uusi Buff-olio. 
 *
 * @param	syote	olion tyyppi merkkijonona
 *
 * @return Buff-olio
 */

    public Buff(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
