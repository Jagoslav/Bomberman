/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;
import java.util.Random;

/**
 * Klasa reprezentująca boty 
 * moveX,moveY - informacje o poruszaniu się bota na mapie
 * @author Jakub
 */
public class BotObject extends GameObject{
    private int moveX,moveY;
    public BotObject(int x, int y) {
        super(x, y);
        this.type=ObjectType.bot;
        moveX=0;
        moveY=0;
        Random r=new Random();
        int i=r.nextInt(4);
        switch (i) {
            case 0:
                moveX=1;
                break;
            case 1:
                moveX=-1;
                break;
            case 2:
                moveY=1;
                break;
            default:
                moveY=-1;
                break;
        }
        
    }

    /**
     * geter informacji o przesunięciu względem starej pozycji x
     * @return
     */
    public int getMoveX(){return moveX;}

    /**
     * geter informacji o przesunięciu względem starej pozycji y
     * @return
     */
    public int getMoveY(){return moveY;}

    /**
     * seter informacji o przesunięciu względem starej pozycji x
     * @param moveX przesunięcie względem osi x
     */
    public void setMoveX(int moveX){this.moveX=moveX;}

    /**
     * seter informacji o przesunięciu względem starej pozycji y
     * @param moveY przesunięcie względem osi y
     */
    public void setMoveY(int moveY){this.moveY=moveY;}

    /**
     * wyświetla obraz bota na ekran
     * @param graphic referencja do obiektu na którym ma zostać narysowany obrazek
     * @param image referencja do obrazka
     */
    @Override
    public void render(Graphics graphic, Images image) {
        graphic.drawImage(image.botImage,getX(),getY(),null);
    }

    /**
     * aktualizacja współrzędnych bota i ewentualna zmiana kierunku poruszania się
     * @param x informacja o możliwości przesunięcia względem osi x
     * @param y informacja o możliwości przesunięcia względem osi y
     */
    public void update(boolean x,boolean y) { //ruch w osi x/y dozwolony lub nie
        if(x)setX(getX()+moveX); 
        if(y)setY(getY()+moveY);
        if(!x){
            if(new Random().nextInt(2)==1)
                setMoveY(1);
            else setMoveY(-1);
            setMoveX(0);
        }
        if(!y){
            if(new Random().nextInt(2)==1)
                setMoveX(-1);
            else setMoveX(1);
            setMoveY(0);
        }
    }
}
