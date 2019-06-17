/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;

/**
 * obiekt reprezentujący ściany na mapie
 * @author Jakub
 */
public class WallObject extends GameObject{
    
    /**
     * konstruktor
     * @param x współrzędna x obiektu
     * @param y współrzędna y obiektu
     */
    public WallObject(int x, int y) {
        super(x, y);
        this.type=ObjectType.wall;
    }
    /**
     * wyświetla obraz ściany na ekran
     * @param graphic referencja do obiektu na którym ma zostać narysowany obrazek
     * @param image referencja do obrazka
     */
    @Override
    public void render(Graphics graphic,Images image) {
        graphic.drawImage(image.wallImage,getX(),getY(),null);
    }
    
}
