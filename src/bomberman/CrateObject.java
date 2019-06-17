/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;

/**
 * klasa reprezentująca skrzynki
 * @author Jakub
 */
public class CrateObject extends GameObject{

    /**
     * konstruktor
     * @param x współrzędna x obiektu
     * @param y współrzędna y obiektu
     */
    public CrateObject(int x, int y) {
        super(x, y);
        this.type=ObjectType.crate;
    }

    /**
     * wyświetla obraz skrzynki na gracza
     * @param graphic referencja do obiektu na którym ma zostać narysowany obrazek
     * @param image referencja do obrazka
     */
    @Override
    public void render(Graphics graphic,Images image) {
        graphic.drawImage(image.crateImage,getX(),getY(),null);
    }
}
