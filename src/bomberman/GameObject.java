/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;
/**
 * klasa abstrakcyjna reprezentująca obiekty występujące w grze
 * x,y - współrzędne obiektu
 * type - typ obiektu
 * @author Jakub
 */
public abstract class GameObject {
    protected int x,y;
    protected ObjectType type;
    public int getX(){ return x; }
    public int getY(){ return y; }
    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }
    public ObjectType getType(){ return type; }
    public void setType(ObjectType type){ this.type = type; }
    
    /**
     *konstruktor
     * @param x współrzędna x obiektu
     * @param y współrzędna y obiektu
     */
    public GameObject(int x,int y){
        this.x=x;
        this.y=y;
    }

    /**
     * abstrakcyjna metoda odpowiadająca za wyświetlanie obrazu na ekranie
     * @param graphic referencja do obiektu na którym ma zostać narysowany obrazek
     * @param image referencja do obrazka
     */
    public abstract void render(Graphics graphic,Images image);
}
