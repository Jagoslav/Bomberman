/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;

/**
 * klasa reprezentujaca gracza
 * moveX,moveY - informacje o przesunięciu obiektu podczas następnego ruchu
 * bombCountr - licznik obecnie posiadanych bomb
 * maxNumberOfBombs - licznik maksymalnej obecnej posiadanej ilości bomb
 * movementSpeedBonus - bonus do prędkości poruszania się gracza
 * bombExplosionSize - rozmiar wybuchu bomb gracza
 * playerId - id gracza
 * buttonPressed - flaga informująca o ciągłym trzymaniu przycisku, blokuje nieswiadome
 *                 dublowanie stawianych bomb w tym samym miejscu
 * @author Jakub
 */
public class PlayerObject extends GameObject{ 
    private int moveX,moveY;
    private int bombCounter;
    private int maxNumberOfBombs;
    private int movementSpeedBonus;
    private int bombExplosionSize;
    private final int playerID;
    public boolean buttonPressed;

    /**
     * konstruktor
     * @param x współrzędna x obiektu
     * @param y współrzędna y obiektu
     * @param playerID id gracza
     */
    public PlayerObject(int x, int y,int playerID) {
        super(x,y);
        this.type=ObjectType.player;
        this.playerID=playerID;
        bombCounter=1;
        maxNumberOfBombs=bombCounter;
        bombExplosionSize=1;
        movementSpeedBonus=0;
        buttonPressed=false; 
    }

    /**
     * geter informacji o przesunięciu względem starej pozycji x
     * @return
     */
    public int getMoveX(){return moveX+movementSpeedBonus*moveX/2;}

    /**
     * geter informacji o przesunięciu względem starej pozycji y
     * @return
     */
    public int getMoveY(){return moveY+movementSpeedBonus*moveY/2;}

    /**
     * seter informacji o przesunięciu względem starej pozycji x
     * @param moveX
     */
    public void setMoveX(int moveX){this.moveX=moveX;}

    /**
     * seter informacji o przesunięciu względem starej pozycji y
     * @param moveY
     */
    public void setMoveY(int moveY){this.moveY=moveY;}

    /**
     *geter informacji o maksymalnej dostępnej ilości bomb gracza
     * @return
     */
    public int getMaxNumberOfBombs(){return maxNumberOfBombs;}

    /**
     * geter informacji o bonusie do poruszania się gracza
     * @return
     */
    public int getMovementSpeedBonus(){return movementSpeedBonus;}

    /**
     * geter informacji o rozmiarze eksplozji bomb gracza
     * @return
     */
    public int getBombExplosionSize(){return bombExplosionSize;}

    /**
     * metoda służąca do stawiania bomb na mapie
     * @param objectHandler obiekt posiadający listy wszystkich obiektów
     */
    public void placeBomb(ObjectHandler objectHandler){
        if(bombCounter>0){
            objectHandler.addBomb(new BombObject(((getX()+13)/40)*40,((getY()+13)/40)*40,bombExplosionSize,playerID));
            //stawia na polu, na którym znajduje się środek gracza
            bombCounter--;
        }
    }

    /**
     * aktualizacja współrzędnych gracza
     * @param x informacja o możliwości przesunięcia względem osi x
     * @param y informacja o możliwości przesunięcia względem osi y
     */
    public void update(boolean x,boolean y) { //ruch w osi x/y dozwolony lub nie
        if(x)this.setX(getX()+moveX+movementSpeedBonus*moveX/2); //moveX=-2/0/2, a bonus ma być +1 co poziom
        if(y)this.setY(getY()+moveY+movementSpeedBonus*moveY/2); 
    }
    /**
     * wyświetla obraz gracza na ekran
     * @param graphic referencja do obiektu na którym ma zostać narysowany obrazek
     * @param image referencja do obrazka
     */
    @Override
    public void render(Graphics graphic,Images image) {
        if(playerID==0)
            graphic.drawImage(image.playerImage,getX(),getY(),null);
        else graphic.drawImage(image.player2Image,getX(),getY(),null);
    }

    /**
     * inkrementuje ilość dostępnych bomb gracza
     */
    public void addBomb(){
        if(bombCounter<maxNumberOfBombs)bombCounter++;
    }

    /**
     * inkrementuje górny limit ilości bomb gracza
     */
    public void increaseMaxBombsNumber(){
        if(maxNumberOfBombs<5){
            maxNumberOfBombs++;
            bombCounter++;
        }
    }

    /**
     * inkrementuje rozmiar eksplozji
     */
    public void increaseExplosionSize(){
        if(bombExplosionSize<7)
            bombExplosionSize++;
    }

    /**
     * metoda inkrementująca bonus do szybkości poruszania się
     */
    public void increaseMovementSpeed(){
        if(movementSpeedBonus<2)movementSpeedBonus++;
    }
}
