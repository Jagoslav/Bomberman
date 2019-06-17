/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 * menadżer dźwięków. zapętla utwory lub podmienia je, w zależności od argumentu
 * wywołania
 * game - referencja do obiektu gry
 * menu,map1-3 - uchwyty do plików muzycznych
 * playingClip - obecnie grany utwór
 * loopAfter - timer odliczający czas do zakończenia utworu
 * isPlaying- informacja o tym, czy w danym momencie grany jest jakiś utwór
 * lastCheckedGameState - informacja o uprzednio sprawdzonym stanie gry, pozwala
 *                        zmienić utwór w trakcie trwania poprzedniego
 * @author Jakub
 */
public class AudioManager {
    static Game game;
    private final File menu;
    private final File map1;
    private final File map2;
    private final File map3;
    private Clip playingClip;
    static SleepingThread loopAfter;
    private boolean isPlaying;
    private GameState lastCheckedGameState;

    /**
     *konstruktor
     * @param game referencja do obiektu typu Game, od którego pól zależy działanie tej klasy
     */
    public AudioManager(Game game){
        this.game=game;
        menu=new File("src\\audio\\menu.wav");
        map1=new File("src\\audio\\map1.wav");
        map2=new File("src\\audio\\map2.wav");
        map3=new File("src\\audio\\map3.wav");
        isPlaying=false;
        lastCheckedGameState=game.gameState;
    }

    /**
     *metoda odtwarza utwór odpowiedni do obecnego stanu gry
     */
    public void play(){
        
        if(game.gameState==lastCheckedGameState){
            if(isPlaying==false){
                if(game.gameState==GameState.menu && menu!=null)
                    playSound(menu);
                if(game.gameState==GameState.run)
                    if(game.subMenuState==GameState.map1 && map1!=null)
                        playSound(map1);
                    if(game.subMenuState==GameState.map2 && map2!=null)
                        playSound(map2);
                    if(game.subMenuState==GameState.map3 && map3!=null)
                        playSound(map3);
            }
            checkLoop(); //jeśli nie zmienił się stan gry, to utwór mógł dobiec końca
        }
        else{
            if(playingClip!=null){
                isPlaying=false;
                playingClip.stop();
            }
            lastCheckedGameState=game.gameState;
        }
    }

    /**
     * metoda odtwarza wskazaną ścieżkę dźwiękową
     * @param sound odtwarzany plik dźwiekowy
     */
    public void playSound(File sound){
        try{
            playingClip=AudioSystem.getClip();
            playingClip.open(AudioSystem.getAudioInputStream(sound));
            playingClip.start();
            loopAfter=new SleepingThread((int)(playingClip.getMicrosecondLength()/1000));
            loopAfter.start();
            
            isPlaying=true;
        }catch(Exception e){
            System.err.println("Problem z uruchomieniem pliku.wav");
        }
    }

    /**
     * metoda sprawdza, czy odtwarzany utwór dobiegł końca
     */
    public void checkLoop(){
        try{
            loopAfter.join();
            if(!loopAfter.thread.isAlive()){
                isPlaying=false;
            }
        }catch(Exception all){
            System.err.println("checkLoop() error");
        }
    }
}
