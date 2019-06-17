/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;

/**
 * klasa reprezentująca bomby
 * explosionSize - rozmiar wybuchu
 * explode - flaga informująca o konieczności wybuchu
 * timer - odlicza czas do eksplozji
 * playerID - informacja o tym, do którego gracza należy bomba
 * @author Jakub
 */
public class BombObject extends GameObject{
    private final int explosionSize;
    private boolean explode=false;
    private final SleepingThread timer;
    private final int playerID;

    /**
     * konstruktor
     * @param x współrzędna x obiektu
     * @param y współrzędna y obiektu
     * @param explosionSize zasięg wybuchu
     * @param playerID id gracza, który postawił bombę
     */
    public BombObject(int x, int y,int explosionSize,int playerID) {
        super(x, y);
        this.type=ObjectType.bomb;
        this.explosionSize=explosionSize;
        this.explode=false;
        this.playerID=playerID;
        timer=new SleepingThread(2500);
        timer.start();
    }

    /**
     * geter id gracza, do którego bomba należy
     * @return
     */
    public int getPlayerID(){return playerID;}

    /**
     * geter rozmiaru eksplozji
     * @return
     */
    public int getExplosionSize(){return explosionSize;}

    /**
     * geter informacji o tym, czy bomba ma wybuchnąć
     * @return
     */
    public boolean getExplode(){return explode;}

    /**
     * zmuszenie bomby do wybuchu, jeśli ma wybuchnąć przed czasem
     */
    public void makeItExplode(){explode=true;} //zmuszanie do wybuchu, bo w zasięgu innego wybuchu

    /**
     * aktualizacja stanu bomby, czy ma wybuchnąć, czy jeszcze nie
     */
    public void update() {
        try{
            timer.join();
            if(!timer.thread.isAlive()){
                explode=true;
            }
        }catch(Exception all){
            System.err.println("BombObject.update() error");
        }
    }

    /**
     * wyświetlanie obrazku bomby na ekran
     * @param graphic referencja do obiektu na którym ma zostać narysowany obrazek
     * @param image referencja do obrazka
     */
    @Override
    public void render(Graphics graphic, Images image) {
        graphic.drawImage(image.bombImage,getX(),getY(),null);
    }
}
