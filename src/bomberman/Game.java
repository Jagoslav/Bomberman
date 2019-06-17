/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 * Główna pętla gry
 * audioManager - odtwarzacz muzyki
 * gameState - główna flaga statusu gry
 * subMenuState - dodatkowa flaga statusu gry
 * width,height - wymiary okna aplikacji
 * thread - wątek w którym wykonuje się gra
 * running - flaga postępu gry
 * objectHandler - nadzorca obiektów w grze
 * gameEnds - flaga stanu końca gry
 * @author Jakub
 */
public class Game extends Canvas implements Runnable{
    public AudioManager audioManager;
    public GameState gameState;
    public GameState subMenuState;
    public static int width=19*40, height=16*40;
    private Thread thread;
    private boolean running=false;
    public ObjectHandler objectHandler;
    public EndGameState gameEnds;

    /**
     *Konstruktor
     */
    public Game(){
        gameState=GameState.none;
        subMenuState=GameState.none;
        audioManager=new AudioManager(this);
        gameEnds=EndGameState.none;
        objectHandler=new ObjectHandler();
        this.addKeyListener(new KeyboardAdapter(this));
        this.addMouseListener(new CursorAdapter(this));
        this.addMouseMotionListener(new CursorAdapter(this));
        Window window = new Window(width+5,height,"BomberMan",this);
    }
    /**
     * aktualizacja gry polega na aktualizacji listy obiektów oraz kontrolowaniu
     * menadżera dźwięków.
     */
    public void update(){
        audioManager.play();
        if(gameState!=GameState.exit)objectHandler.updateList(); //aktualizacja obiektów (współrzędne itd)
    }
    
    public void render(){
        BufferStrategy bufferStrategy=this.getBufferStrategy();
        if(bufferStrategy==null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics;
        Images image=new Images();
        graphics=bufferStrategy.getDrawGraphics(); 
        
        graphics.drawImage(image.backgroundImage,getX(),getY(),null); //rysowanie tła
        objectHandler.renderList(graphics,image); //rysowanie dotychczasowej zawartości listy
        
        graphics.setColor(Color.WHITE);
        switch(gameState){
            case menu: //menu główne
                if(subMenuState==GameState.none){
                    graphics.setFont(new Font("arial",1,50));
                    graphics.drawImage(image.logo,15*10,40,null); //15*10===3.75*40, podane jako iloraz by łatwiej operować współrzędnymi
                    graphics.drawImage(image.button,6*40,5*40,null);
                    graphics.drawImage(image.button,6*40,8*40,null);
                    graphics.drawImage(image.button,6*40,11*40,null);
                    graphics.drawString("Nowa Gra", 13*20, 13*20);
                    graphics.drawString("Instrukcje", 13*20, 19*20);
                    graphics.drawString("Zakończ", 14*20, 25*20);
                }
                else if(subMenuState==GameState.newGame){ //okno wyboru planszy
                    graphics.setFont(new Font("arial",1,50));
                    graphics.drawImage(image.button,6*40,2*40,null);
                    graphics.drawImage(image.button,6*40,5*40,null);
                    graphics.drawImage(image.button,6*40,8*40,null);
                    graphics.drawImage(image.button,6*40,11*40,null);
                    graphics.drawString("mapa 1", 15*20, 7*20);
                    graphics.drawString("mapa 2", 15*20, 13*20);
                    graphics.drawString("mapa 3", 15*20, 19*20);
                    graphics.drawString("Wróć", 16*20, 25*20);
                }
                else if(subMenuState==GameState.instructions){ //instrukcje 
                    graphics.setFont(new Font("arial",1,32));
                    graphics.drawString("Sterowanie:", width/2-2*40, 4*20);
                    graphics.setFont(new Font("arial",1,24));
                    graphics.drawString("Gracz 1: W.A.S.D", 7*20+5, 6*20);
                    graphics.drawString("Gracz 2: Strzałki", 21*20+5, 6*20);
                    graphics.setFont(new Font("arial",1,32));
                    graphics.drawString("postaw bombę:", width/2-5*20, 8*20);
                    graphics.setFont(new Font("arial",1,24));
                    graphics.drawString("Gracz 1: spacja", 4*40+5, 19*10);
                    graphics.drawString("Gracz 2: enter", 21*20+5, 19*10);
                    graphics.drawImage(image.bombNumberUp,11*20+5,5*40+5,null);
                    graphics.drawString("ilość bomb +1", 13*20,6*40-10);
                    graphics.drawImage(image.explosionSizeUp,11*20+5,6*40+5,null);
                    graphics.drawString("Zasięg wybuchu +1", 13*20,27*10);
                    graphics.drawImage(image.movementSpeedUp,11*20+5,7*40+5,null);
                    graphics.drawString("prędkość poruszania +1", 13*20,15*20+10);
                    graphics.setFont(new Font("arial",1,16));
                    graphics.drawString("porada: stawianie bomb jedna na drugiej jest możliwe. Wykorzystuj to,", 5*20,9*40);
                    graphics.drawString("by zaskoczyć przeciwnika,zniszczyć kilka blokad w linii jednocześnie,",5*20,19*20);
                    graphics.drawString("lub kiedy taki masz kaprys. ",5*20,10*40);
                    graphics.drawImage(image.button,6*40,11*40,null);
                    graphics.setFont(new Font("arial",1,50));
                    graphics.drawString("Wróć", 16*20, 25*20);
                }
                break;
            case exit:
                System.exit(0);
                break;
            case run:
                graphics.drawImage(image.hud,0,height-40,null);
                if(objectHandler.players[0]!=null){
                    String tempString;
                    graphics.setFont(new Font("arial",1,32));
                    graphics.setColor(Color.BLACK);
                    graphics.drawImage(image.bombNumberUp,2*20+5,height-35,null);
                    tempString=objectHandler.players[0].getMaxNumberOfBombs()+"/5";
                    graphics.drawString(tempString,4*20+5,height-10);
                    graphics.drawImage(image.explosionSizeUp,7*20+5,height-35,null);
                    tempString=objectHandler.players[0].getBombExplosionSize()+"/7";
                    graphics.drawString(tempString,9*20+5,height-10);
                    graphics.drawImage(image.movementSpeedUp,6*40+5,height-35,null);
                    tempString=objectHandler.players[0].getMovementSpeedBonus()+"/2";
                    graphics.drawString(tempString,7*40+5,height-10);
                    graphics.drawImage(image.playerImage,17*20+7,height-35,null);
                }
                if(objectHandler.players[1]!=null){
                    String tempString;
                    graphics.setFont(new Font("arial",1,32));
                    graphics.setColor(Color.BLACK);
                    graphics.drawImage(image.player2Image,19*20+7,height-35,null);
                    graphics.drawImage(image.bombNumberUp,21*20+5,height-35,null);
                    tempString=objectHandler.players[1].getMaxNumberOfBombs()+"/5";
                    graphics.drawString(tempString,23*20+5,height-10);
                    graphics.drawImage(image.explosionSizeUp,26*20+5,height-35,null);
                    tempString=objectHandler.players[1].getBombExplosionSize()+"/7";
                    graphics.drawString(tempString,28*20+5,height-10);
                    graphics.drawImage(image.movementSpeedUp,31*20+5,height-35,null);
                    tempString=objectHandler.players[1].getMovementSpeedBonus()+"/2";
                    graphics.drawString(tempString,33*20+5,height-10);
                }
                lastManStanding();
                if(gameEnds!=EndGameState.none){
                    objectHandler.bombList.removeAll(objectHandler.bombList);
                    graphics.setColor(Color.WHITE);
                    graphics.setFont(new Font("arial",1,50));
                    graphics.drawImage(image.exitField,5*40,height/2-3*40,null);
                    if(gameEnds==EndGameState.remis) graphics.drawString("remis",31*10+5,height/2-6*10);
                    else if(gameEnds==EndGameState.przegrana)graphics.drawString("przegrana",13*20,height/2-6*10);
                    else if(gameEnds==EndGameState.wygrana){
                            graphics.setFont(new Font("arial",1,32));
                        if(objectHandler.players[0]==null)
                            graphics.drawString("wygrana gracz 2",13*20,height/2-7*10);
                        else graphics.drawString("wygrana gracz 1",13*20,height/2-7*10);
                    }
                    graphics.setFont(new Font("arial",1,50));
                    graphics.drawImage(image.button,6*40,height/2-1*40,null);
                    graphics.drawString("wyjdź",31*10,height/2+15);
                }
                break;
            default: //GameState.none
                break;
            
        }
        graphics.dispose();
        bufferStrategy.show();
      
    }

    /**
     *metoda wykonywana automatycznie po użyciu konstruktora
     */
    public synchronized void start(){
        thread=new Thread(this);
        thread.start();
        running=true;
        gameState=GameState.menu;
    }

    /**
     * metoda wykonująca się aż zostanie przerwana (tutaj: w nieskończoność)
     * polega na prostym schemacie "aktualizuj, wyświetl
     */
    @Override
    public  void run(){
        long lastTime=System.nanoTime();
        double ammountOfTicks=60.0;
        double nanoSeconds=1000000000/ammountOfTicks;
        double delta=0;
        long timer=System.currentTimeMillis();
        //powyższe zmienne pozwalają na opóźnienie aktualizacji, by nie odbywała się ona za czesto
        while(running){
            long now=System.nanoTime();
            delta+=(now-lastTime)/nanoSeconds;
            lastTime=now;
            while(delta>=1){
                update();
                delta--;
            }
            render();
            if(System.currentTimeMillis()-timer>1000)
                timer+=1000;
        }
    };

    /**
     * przerywa działanie pętli run, ale nie jest nigdy używane
     * (ze względu na użycie System.exit(0))
     */
    public synchronized void stop(){
        try{
            thread.join();
            running=false;
        }catch(Exception all){
            System.err.println("Game.stop() error");
        }
    }

    /**
     * ustawia flagę końca gry na podstawie ilości pozostałych na mapie graczy
     * oraz obecności botów
     */
    public void lastManStanding(){
        int playersLeft=2;
        for(int i=0;i<2;i++)
            if(objectHandler.players[i]==null)
                playersLeft--;
        if(objectHandler.botList.isEmpty()){
            if(playersLeft==1)
                gameEnds=EndGameState.wygrana;
            else if(playersLeft==0)
                gameEnds=EndGameState.remis;
        }
        else if(playersLeft==0)
            gameEnds=EndGameState.przegrana;
        else gameEnds=EndGameState.none; 
    }
}
