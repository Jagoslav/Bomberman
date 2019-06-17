/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;

/**
 * obiekt reprezentujący ogień eksplozji
 * horizontal - informacja o tym, czy wybuch idzie w kierunku poziomym
 * part - część płomienia
 * minusFromCenter - informacja o położeniu obiektu względem miejsca ekplozji
 * timer - odlicza czas do zniknięcia płomieni
 * vaporize - flaga informująca o zniknięciu płomienia
 * @author Jakub
 */
public class FireObject extends GameObject{
    boolean horizontal;
    int part;//część wybuchu (środek, prosta lub koniec), + kierunek wybuchu0 poziomo, 1 pionowo
    boolean minusFromCenter; //na lewo/góra od środka =true
    SleepingThread timer;
    private boolean vaporize=false;

    /**
     *konstruktor
     * @param x współrzędna x obiektu
     * @param y współrzędna y obiektu
     * @param horizontal informacja o kierunku płomienia
     * @param part informacja o części płomienia (centrum eksplozji, końcówka lub środek)
     * @param MinusFromCenter informacja o kierunku końcówki płomienia względem środka 
     */
    public FireObject(int x, int y,boolean horizontal,int part,boolean MinusFromCenter) {
        super(x, y);
        this.type=ObjectType.fire;
        this.horizontal=horizontal;
        this.part=part;
        this.minusFromCenter=MinusFromCenter;
        timer=new SleepingThread(250);
        timer.start();
    }

    /**
     * geter informacji o tym, czy ogień ma zniknąć
     * @return
     */
    public boolean getVaporize(){return vaporize;}

    /**
     * aktualizaca stanu obiektu, czy ma już zniknąć czy nie
     */
    public void update() {
        try{
            timer.join();
            if(!timer.thread.isAlive()){
                vaporize=true;
            }
        }catch(Exception all){
            System.err.println("FireObject.update() error");
        }
    }

    /**
     * wyświetlanie odpowiedniego obrazka ognia na ekran
     * @param graphic referencja do obiektu na którym ma zostać narysowany obrazek
     * @param image referencja do obrazka
     */
    @Override
    public void render(Graphics graphic, Images image) {
        if(part==0) //środek
            graphic.drawImage(image.explosionCenter,getX(),getY(),null);
        else if(part==1){ //środek
            if(horizontal)
                graphic.drawImage(image.explosionMiddleHorizontal,getX(),getY(),null);
            else graphic.drawImage(image.explosionMiddleVertical,getX(),getY(),null);
        }
        else { //końcówka
            if(horizontal){
                if(minusFromCenter)
                    graphic.drawImage(image.explosionEndLeft,getX(),getY(),null);
                else 
                    graphic.drawImage(image.explosionEndRight,getX(),getY(),null);
            }
            else{
                if(minusFromCenter)
                    graphic.drawImage(image.explosionEndUp,getX(),getY(),null);
                else
                    graphic.drawImage(image.explosionEndDown,getX(),getY(),null);
            }
        }   
    }
}
