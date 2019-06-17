/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

/**
 * klasa pełniąca rolę dozorcy, nadzorująca wszystkie obiekty i wykonująca na
 * nich różne operacje (rysowanie, aktualiacja wartości)
 * objectList - przetrzymuje obiekty stałe (ściany, skrzynie i powerUpy)
 * bombList - trzyma czasowe obiekty reprezentujące bomby
 * explosionList - trzyma czasowe obiekty reprezentujace wybuchy
 * botList - lista botów w grze
 * players - referencje na graczy, którzy sa wciąż grze
 * @author Jakub
 */
public class ObjectHandler {
    LinkedList<GameObject> objectList;
    LinkedList<BombObject> bombList;
    LinkedList<FireObject> explosionList;
    LinkedList<BotObject> botList;
    PlayerObject[] players;
    
    /**
     *konstruktor
     */
    public ObjectHandler(){
        players=new PlayerObject[2];
        objectList=new LinkedList<GameObject>();
        bombList=new LinkedList<BombObject>();
        explosionList=new LinkedList<FireObject>();
        botList=new LinkedList<BotObject>();
    }

    /**
     * metoda odpowiedzialna za aktualizację wszystkich list obiektów
     * -ruchu botów/graczy
     * -wybuchu bomb
     * -zaniku ognia po eksplozji
     */
    public void updateList(){
        for(int i=0;i<bombList.size();i++){
            BombObject tempObject;
            tempObject=bombList.get(i);
            if(tempObject!=null){
                if(tempObject.getExplode()){
                    if(players[tempObject.getPlayerID()]!=null)players[tempObject.getPlayerID()].addBomb();
                    Rectangle fire = new Rectangle(tempObject.getX(),tempObject.getY(),40,40); //hitbox płomienia w miejscu eksplozji
                    fireIntersectsWith(fire,false,0,false);
                    for(int range=1;range<=tempObject.getExplosionSize();range++){//w prawo
                        fire = new Rectangle(tempObject.getX()+range*40,tempObject.getY(),40,40); //hitbox płomienia
                        if(fireIntersectsWith(fire,true,(range==tempObject.getExplosionSize())?2:1,false))break;
                    }
                    for(int range=1;range<=tempObject.getExplosionSize();range++){//w lewo
                        fire = new Rectangle(tempObject.getX()-range*40,tempObject.getY(),40,40); //hitbox płomienia
                        if(fireIntersectsWith(fire,true,(range==tempObject.getExplosionSize())?2:1,true))break;
                    }
                    for(int range=1;range<=tempObject.getExplosionSize();range++){//w dół
                        fire = new Rectangle(tempObject.getX(),tempObject.getY()+range*40,40,40); //hitbox płomienia
                        if(fireIntersectsWith(fire,false,(range==tempObject.getExplosionSize())?2:1,false))break;
                    }
                    for(int range=1;range<=tempObject.getExplosionSize();range++){//w góre
                        fire = new Rectangle(tempObject.getX(),tempObject.getY()-range*40,40,40); //hitbox płomienia
                        if(fireIntersectsWith(fire,false,(range==tempObject.getExplosionSize())?2:1,true))break;
                    }
                    bombList.remove(tempObject);
                }
                else tempObject.update();
            }
        }
        for(int i=0;i<explosionList.size();i++){
            FireObject tempObject=explosionList.get(i);
            if(tempObject.getVaporize()==true)
                explosionList.remove(tempObject);
            else tempObject.update();
        }
        for(int i=0;i<botList.size();i++){
            BotObject tempObject=botList.get(i);
            moveBot(tempObject);
            Rectangle b=new Rectangle(tempObject.getX(),tempObject.getY(),26,26);
            if(players[0]!=null){
                Rectangle p1=new Rectangle(players[0].getX(),players[0].getY(),26,26);
                if(b.intersects(p1))players[0]=null;
            }
            if(players[1]!=null){
                Rectangle p2=new Rectangle(players[1].getX(),players[1].getY(),26,26);
                if(b.intersects(p2))players[1]=null;
            }
        }
        for(int i=0;i<2;i++)
            if(players[i]!=null) movePlayer(i);
    }

    /**
     * Metoda odpowiedzialna za rysowanie obiektów zawartych we wszystkich listach klasy
     * @param graphic referencja do obiektu na którym ma zostać narysowany obrazek
     * @param image referencja do obrazka
     */
    public void renderList(Graphics graphic,Images image){
        for(int i=0;i<objectList.size();i++){
            GameObject tempObject;
            tempObject=objectList.get(i);
            if(tempObject!=null)
                tempObject.render(graphic,image);
        }
        for(int i=0;i<bombList.size();i++){
            BombObject tempObject;
            tempObject=bombList.get(i);
            if(tempObject!=null)
                tempObject.render(graphic,image);
        }
        for(int i=0;i<explosionList.size();i++){
            FireObject tempObject;
            tempObject=explosionList.get(i);
            if(tempObject!=null)
                tempObject.render(graphic,image);
        }
        for(int i=0;i<botList.size();i++){
            BotObject tempObject;
            tempObject=botList.get(i);
            tempObject.render(graphic,image);
        }
        for(int i=0;i<2;i++)
            if(players[i]!=null)
                players[i].render(graphic,image);
    }

    /**
     * metoda dodająca do listy obiektów statycznych nowy obiekt
     * @param object obiekt dodawany na listę
     */
    public void addObject(GameObject object){
        this.objectList.add(object);
    }

    /**
     * dodaje nowy obiekt do listy tymczasowych obiektów statycznych reprezentujących ogień
     * @param object obiekt reprezentujący ogień po wybuchu
     */
    public void addFire(FireObject object){
        this.explosionList.add(object);
    }

    /**
     * dodaje do listy bomb nowy obiekt tymczasowy
     * @param object obiekt reprezentujący bombę
     */
    public void addBomb(BombObject object){
        this.bombList.add(object);
    }

    /**
     * do listy botów dodaje nowego bota
     * @param object obiekt reprezentujący bota
     */
    public void addBot(BotObject object){
            this.botList.add(object);
    }

    /**
     * usuwa dany obiekt z listy obiektów statycznych(ściana, skrzynia, powerUp)
     * @param object obiekt do usunięcia 
     */
    public void removeObject(GameObject object){
        this.objectList.remove(object);
    }

    /**
     * usuwa dany obiekt z listy botów
     * @param object obiekt do usunięcia 
     */
    public void removeBot(BotObject object){
        this.botList.remove(object);
    }

    /**
     * metoda czyszcząca zawartość wszystkich list naraz
     */
    public void removeAll(){
        this.objectList.removeAll(objectList);
        this.bombList.removeAll(bombList);
        this.explosionList.removeAll(explosionList);
        this.botList.removeAll(botList);
        for(int i=0;i<2;i++)players[i]=null;
    }

    /**
     * metoda odpowiedzialna za poruszanie się botów na mapie
     * @param tempObject bot którego współrzędne zostaną zmodyfikowane
     */
    public void moveBot(BotObject tempObject){
        if(tempObject!=null){
            Rectangle currentPos=new Rectangle(tempObject.getX()-7,tempObject.getY()-7,26,26);
            Rectangle bx = new Rectangle(tempObject.getX()+tempObject.getMoveX()-7,tempObject.getY()-7,40,40);
            Rectangle by = new Rectangle(tempObject.getX()-7,tempObject.getY()+tempObject.getMoveY()-7,40,40);
            //sprawdzane kolizje ruchu o 1 dalej,by mieć pewność z
            boolean collideX=false;
            boolean collideY=false;
            for(int i=0;i<objectList.size();i++){
                GameObject tempCheck=objectList.get(i);
                if(tempCheck.getType()==ObjectType.powerUp)continue;
                Rectangle o = new Rectangle(tempCheck.getX(),tempCheck.getY(),40,40);
                if(o.intersects(bx))
                    collideX=true;
                if(o.intersects(by))
                    collideY=true;
            }
            for(int i=0;i<bombList.size();i++){
                BombObject tempCheck=bombList.get(i);
                Rectangle o = new Rectangle(tempCheck.getX(),tempCheck.getY(),40,40);
                if(o.intersects(bx) && !o.intersects(currentPos))
                    collideX=true;
                if(o.intersects(by) && !o.intersects(currentPos))
                    collideY=true;
            }
            for(int i=0;i<botList.size();i++){
                BotObject tempCheck=botList.get(i);
                Rectangle o = new Rectangle(tempCheck.getX(),tempCheck.getY(),26,26);
                if(o.intersects(bx) && !o.intersects(currentPos))
                    collideX=true;
                if(o.intersects(by) && !o.intersects(currentPos))
                    collideY=true;
            }
            tempObject.update(!collideX, !collideY); //jeśli gdzieś występuje kolizja (true) przekazana wartość informuje o niemozliwości ruchu
        }
    }

    /**
     * metoda odpowiedzialna za poruszanie się gracza
     * @param id id gracza który ma się poruszyć
     */
    public void movePlayer(int id){
        if(players[id]==null)return;
        boolean collideX=false;
        boolean collideY=false;
        Rectangle px = new Rectangle(players[id].getX()+players[id].getMoveX(),players[id].getY(),26,26); 
        Rectangle py = new Rectangle(players[id].getX(),players[id].getY()+players[id].getMoveY(),26,26);
        //hitbox gracza po potencjalnym ruchu w pionie lub poziomie
        
        for(int i=0;i<objectList.size();i++){
            GameObject tempObject=objectList.get(i);
            if(tempObject.type==ObjectType.powerUp){ //zbieramy powerUpy
                Rectangle o = new Rectangle(tempObject.getX()+10,tempObject.getY()+10,10,10); //powerupy mają mniejszy hitbox, by trzeba było na nie "wejść"
                if(o.intersects(px)||o.intersects(py)){
                    PowerUpObject tempCheck=(PowerUpObject)tempObject;
                    if(tempCheck.getPowerUpType()==ObjectType.bombNumberUp)players[id].increaseMaxBombsNumber();
                    else if(tempCheck.getPowerUpType()==ObjectType.explosionSizeUp)players[id].increaseExplosionSize();
                    else players[id].increaseMovementSpeed();
                    removeObject(tempObject);
                }
                continue;
            }
            Rectangle o = new Rectangle(tempObject.getX(),tempObject.getY(),40,40); //hitbox ścian i skrzynek
            if(o.intersects(px))
                collideX=true;
            if(o.intersects(py))
                collideY=true;
        }
        
        for(int i=0;i<bombList.size();i++){
            BombObject tempObject=bombList.get(i);
            Rectangle o = new Rectangle(tempObject.getX(),tempObject.getY(),40,40); //hitbox bomby
            Rectangle currentPos = new Rectangle(players[id].getX(),players[id].getY(),26,26); 
            if(o.intersects(px) && !o.intersects(currentPos))
                collideX=true;
            if(o.intersects(py) && !o.intersects(currentPos))
                collideY=true;
        }
        players[id].update(!collideX,!collideY); //jeśli flagi przeciwne informują o tym, czy można się przesunąć w tym kierunku
    }

    /**
     * metoda sprawdzająca kolizję płomienia i na jej podstawie usuwa odpowiednie
     * obiekty z list lub wymusza eksplozje i dodaje nowy obiekt reprezentujący ogień 
     * @param r kwadrat kolizji płomienia
     * @param horizontal kierunek płomienia (pion/poziom)
     * @param part //element wybuchu (środek, końcówka, środek płomienia)
     * @param MinusFromCenter //współrzędne względem środka
     * @return
     */
    public boolean fireIntersectsWith(Rectangle r, boolean horizontal,int part,boolean MinusFromCenter){ //płomień bomby dosięgnie cokolwieky
        for(int j=0;j<bombList.size();j++){ //sprawdzenie, czy inna bomba też nie powinna wybuchnąć
            BombObject tempCheck=bombList.get(j);
            Rectangle c = new Rectangle(tempCheck.getX(),tempCheck.getY(),40,40);
            if(c.intersects(r))
                tempCheck.makeItExplode();
        }
        for(int i=0;i<2;i++){ //kasowanie graczy
            if(players[i]!=null){
                Rectangle c = new Rectangle(players[i].getX(),players[i].getY(),26,26);
                if(c.intersects(r)) players[i]=null;
            }
        }
        for(int i=0;i<botList.size();i++){
            BotObject tempObject=botList.get(i);
            Rectangle b = new Rectangle(tempObject.getX(),tempObject.getY(),26,26);
            if(b.intersects(r))removeBot(tempObject);
        }
        for(int j=0;j<objectList.size();j++){ //usuwanie elementów w zasięgu wybuchu
            GameObject tempCheck=objectList.get(j);
            Rectangle c = new Rectangle(tempCheck.getX(),tempCheck.getY(),40,40);
            if(c.intersects(r)){
                if(tempCheck.type==ObjectType.powerUp){
                    addFire(new FireObject(r.x,r.y,horizontal,part,MinusFromCenter)); //obowiązkowo koniec
                    removeObject(tempCheck);
                    return false; //powerup się niszczy, ale nie blokuje ognia
                }
                else if(tempCheck.type==ObjectType.crate){
                    addFire(new FireObject(r.x,r.y,horizontal,2,MinusFromCenter)); //obowiązkowo koniec
                    Random generatePowerUp=new Random();
                    if(generatePowerUp.nextInt(5)==0)
                        addObject(new PowerUpObject(r.x,r.y));
                    removeObject(tempCheck);
                    return true;//niszczy, ale blokuje ogień
                }
                else if(tempCheck.type==ObjectType.wall)
                    return true;//nie niszczy, blokuje ogień
            }
        }
        addFire(new FireObject(r.x,r.y,horizontal, part,MinusFromCenter)); //nic nie ma, obrazek ognia
        return false;
    }
}
