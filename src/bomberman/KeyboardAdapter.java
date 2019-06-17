/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * adapter klawiatury
 * game - referencja do obiektu gry
 * @author Jakub
 */
public class KeyboardAdapter extends KeyAdapter{
    Game game;

    /**
     * konstruktor
     * @param game referencja do obiektu typu Game, od którego pól zależy działanie tej klasy
     */
    public KeyboardAdapter(Game game){
        this.game=game;
    }

    /**
     * metoda sprawdzająca który klawisz został naciśnięty i w zależności od
     * stanu gry podejmująca odpowiednia akcję
     * @param event zdarzenie klawiatury
     */
    @Override
    public void keyPressed(KeyEvent event){
        int key=event.getKeyCode();
        switch(game.gameState){
            case run:
                if(game.gameEnds==EndGameState.none){
                    if(game.objectHandler.players[0]!=null){
                        if(key==KeyEvent.VK_SPACE && game.objectHandler.players[0].buttonPressed==false){
                            game.objectHandler.players[0].buttonPressed=true;
                            game.objectHandler.players[0].placeBomb(game.objectHandler);
                        }
                        if(key==KeyEvent.VK_W){
                            game.objectHandler.players[0].setMoveY(-2);
                        }
                        if(key==KeyEvent.VK_S){
                            game.objectHandler.players[0].setMoveY(2);
                        }
                        if(key==KeyEvent.VK_A){
                            game.objectHandler.players[0].setMoveX(-2);
                        }
                        if(key==KeyEvent.VK_D){
                            game.objectHandler.players[0].setMoveX(2);
                        }
                    }
                    if(game.objectHandler.players[1]!=null){
                        if(key==KeyEvent.VK_ENTER && game.objectHandler.players[1].buttonPressed==false){
                            game.objectHandler.players[1].buttonPressed=true;
                            game.objectHandler.players[1].placeBomb(game.objectHandler);
                        }
                        if(key==KeyEvent.VK_UP){
                            game.objectHandler.players[1].setMoveY(-2);
                        }
                        if(key==KeyEvent.VK_DOWN){
                            game.objectHandler.players[1].setMoveY(2);
                        }
                        if(key==KeyEvent.VK_LEFT){
                            game.objectHandler.players[1].setMoveX(-2);
                        }
                        if(key==KeyEvent.VK_RIGHT){
                            game.objectHandler.players[1].setMoveX(2);
                        }
                    }
                }
                break;
            case menu:
                if(key==KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }
                break;
            case newGame:
                break;
            case instructions:
                break;
            case exit:
                break;
            default: //GameState.none
                break;
        }
    }

    /**
     * metoda kontrolująca puszczanie klawiszy, reagująca na te zdarzenia
     * w odpowiedni sposób
     * @param event zdarzenie klawiatury
     */
    @Override
    public void keyReleased(KeyEvent event){
        int key=event.getKeyCode();
        switch(game.gameState){
            case run:
                if(game.objectHandler.players[0]!=null){
                    if(key==KeyEvent.VK_SPACE){
                        game.objectHandler.players[0].buttonPressed=false;
                    }
                    if(key==KeyEvent.VK_W){
                        game.objectHandler.players[0].setMoveY(0);
                    }
                    if(key==KeyEvent.VK_S){
                        game.objectHandler.players[0].setMoveY(0);
                    }
                    if(key==KeyEvent.VK_A){
                        game.objectHandler.players[0].setMoveX(0);
                    }
                    if(key==KeyEvent.VK_D){
                        game.objectHandler.players[0].setMoveX(0);
                    }
                }
                if(game.objectHandler.players[1]!=null){
                    if(key==KeyEvent.VK_ENTER){
                        game.objectHandler.players[1].buttonPressed=false;
                    }
                    if(key==KeyEvent.VK_UP){
                        game.objectHandler.players[1].setMoveY(0);
                    }
                    if(key==KeyEvent.VK_DOWN){
                        game.objectHandler.players[1].setMoveY(0);
                    }
                    if(key==KeyEvent.VK_LEFT){
                        game.objectHandler.players[1].setMoveX(0);
                    }
                    if(key==KeyEvent.VK_RIGHT){
                        game.objectHandler.players[1].setMoveX(0);
                    }
                }
                break;
            default:
                break;
        }
    }
}
