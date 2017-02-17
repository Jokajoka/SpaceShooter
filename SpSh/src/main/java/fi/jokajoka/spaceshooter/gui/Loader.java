/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jokajoka.spaceshooter.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Jonde
 */
/**
 * Tätä luokkaa käytetään kuvien lataamiseen.
 *
 */
public class Loader {

    private BufferedImage image;

    /**
     * Tällä metodilla ladataan kuva ohjelman käyttöön kovalevyltä.
     * path-parametrin mukaisesta polusta.
     *
     * @param path Polku
     * @return BufferedImage image
     * @throws IOException Kuvaongelma
     */
    public BufferedImage load(String path) throws IOException {

        image = ImageIO.read(getClass().getResource(path));

        return this.image;

    }

}
