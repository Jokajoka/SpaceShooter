/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.gui;

import java.awt.image.BufferedImage;

/**
 * @author Jonde
 */
public class SS {

    private BufferedImage image;

    /**
     * Konstruktori, jolle annetaan parametriksi Sprite sheet kuva.
     *
     * @param sheet kuvakooste
     */
    public SS(BufferedImage sheet) {
        this.image = sheet;
    }

    /**
     * Tällä metodilla pystytään valitsemaan Sprite sheet kuvasta haluttu
     * pikkukuva. Colum ja row parametrit toimivat kertoimena valittaessa
     * haluttujen pikselien rajaama alue alkuperäisestä kuvasta. Width ja Height
     * ovat valittavan pikkukuvan koko.
     *
     * @param column sarake
     * @param row rivi
     * @param width leveys
     * @param height korkeus
     * @return BufferedImage cropped
     */
    public BufferedImage crop(int column, int row, int width, int height) {

        BufferedImage cropped = image.getSubimage((column * 60) - 60, (row * 60) - 60, width, height);
        return cropped;

    }

}
