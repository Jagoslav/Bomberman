/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;
import java.util.Random;

/**
 * obiekt reprezentujący zbieralne bonusy dla graczy
 * powerUpType - flaga informująca o rodzaju bonusu dawanego przez ten obiekt
 * @author Jakub
 */
public class PowerUpObject extends GameObject{
    private final ObjectType powerUpType;

    /**
     * konstruktor
     * @param x współrzędna x obiektu
     * @param y współrzędna y obiektu
     */
    public PowerUpObject(int x, int y) {
        super(x, y);
        this.type=ObjectType.powerUp;
        Random r = new Random();
        int i=r.nextInt(3);
        if(i==0)
            this.powerUpType=ObjectType.bombNumberUp;
        else if(i==1) 
            this.powerUpType=ObjectType.explosionSizeUp;
        else this.powerUpType=ObjectType.movementSpeedUp;
    }
    /**
     * wyświetla obraz bonusu na ekran
     * @param graphic referencja do obiektu na którym ma zostać narysowany obrazek
     * @param image referencja do obrazka
     */
    @Override
    public void render(Graphics graphic, Images image) {
        if(powerUpType==ObjectType.bombNumberUp)graphic.drawImage(image.bombNumberUp,getX()+5,getY()+5,null);
        else if(powerUpType==ObjectType.explosionSizeUp)graphic.drawImage(image.explosionSizeUp,getX()+5,getY()+5,null);
        else graphic.drawImage(image.movementSpeedUp,getX()+5,getY()+5,null);
    }

    /**
     * geter rodzaju bonusu
     * @return
     */
    public ObjectType getPowerUpType(){return powerUpType;}
}
