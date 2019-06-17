/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * adapter myszki
 * game - referencja do obiektu gry, która się wykonuje
 * 
 * @author Jakub
 */
public class CursorAdapter extends MouseAdapter{
    Game game; 

    /**
     *konstruktor
     * @param game referencja do obiektu typu Game, od którego wartości pól zależy działanie klasy
     */
    public CursorAdapter(Game game){
        this.game=game;
    }
    /**
     * zwraca informację o tym, czy kliknięcie myszki znalazło się w danym obszarze
     * @param x współrzędna x lewego górnego wierzchołka obszaru
     * @param y współrzędna y lewego górnego wierzchołka obszaru
     * @param mX współrzędna x myszki
     * @param mY współrzędna y myszki
     * @param width szerokość obszaru
     * @param height wysokość obszaru
     * @return 
     */
    private Boolean mouseOver(int x,int y, int mX, int mY, int width, int height){
        return mX>x && mX<x+width && mY>y && mY<y+height; //true same prawdy, false jeśli coś nie
    }

    /**
     * jeśli kliknięto myszką, to podejmowana jest akcja zależna od stanu gry
     * oraz położenia kursora
     * @param event zdarzenie myszki
     */
    @Override
    public void mousePressed(MouseEvent event){
        int my=event.getY();
        int mx=event.getX();
        switch(game.gameState){
            case menu:
                if(game.subMenuState==GameState.none){
                    if(mouseOver(6*40,5*40,mx,my,7*40,2*40)){
                        game.subMenuState=GameState.newGame;
                    }
                    else if(mouseOver(6*40,8*40,mx,my,7*40,2*40)){
                        game.subMenuState=GameState.instructions;
                    }
                    else if(mouseOver(6*40,11*40,mx,my,7*40,2*40)){
                        game.gameState=GameState.exit;
                    }
                }
                else if(game.subMenuState==GameState.newGame){
                    CreateMap map;
                    int[][] tempMap=null;
                    if(mouseOver(6*40,2*40,mx,my,7*40,2*40)){
                        game.objectHandler.removeAll();
                        map=new CreateMap(0);
                        tempMap=map.getNewMap();
                        game.objectHandler.players[0]=new PlayerObject(1*40+7,1*40+7,0);
                        game.objectHandler.players[1]=new PlayerObject(game.width-2*40+7,game.height-3*40+7,1);
                        game.subMenuState=GameState.map1;
                        game.gameEnds=EndGameState.none;
                    }
                    else if(mouseOver(6*40,5*40,mx,my,7*40,2*40)){
                        game.objectHandler.removeAll();
                        map=new CreateMap(1);
                        tempMap=map.getNewMap();
                        game.objectHandler.players[0]=new PlayerObject(1*40+7,1*40+7,0);
                        game.objectHandler.players[1]=new PlayerObject(game.width-2*40+7,game.height-3*40+7,1);
                        game.subMenuState=GameState.map2;
                        game.gameEnds=EndGameState.none;
                    }
                    else if(mouseOver(6*40,8*40,mx,my,7*40,2*40)){
                        game.objectHandler.removeAll();
                        map=new CreateMap(2);
                        tempMap=map.getNewMap();
                        game.objectHandler.players[0]=new PlayerObject(6*40+7,4*40+7,0);
                        game.objectHandler.players[1]=new PlayerObject(game.width-7*40+7,game.height-6*40+7,1);
                        game.subMenuState=GameState.map3;
                        game.gameEnds=EndGameState.none;
                    }
                    else if(mouseOver(6*40,11*40,mx,my,7*40,2*40)){
                        game.gameState=GameState.menu;
                        game.subMenuState=GameState.none;
                    }
                    if(tempMap!=null){
                        for(int x=0;x<19;x++){
                            for(int y=0;y<15;y++){
                                if(tempMap[y][x]==1)
                                    game.objectHandler.addObject(new WallObject(x*40,y*40)); 
                                else if(tempMap[y][x]==2)
                                    game.objectHandler.addObject(new CrateObject(x*40,y*40)); 
                                else if(tempMap[y][x]==4)
                                    game.objectHandler.addBot(new BotObject(x*40+7,y*40+7));
                            }
                        }
                        game.gameState=GameState.run;
                    }
                }
                else if(game.subMenuState==GameState.instructions){
                    if(mouseOver(6*40,11*40,mx,my,7*40,2*40)){
                        game.gameState=GameState.menu;
                        game.subMenuState=GameState.none;
                    }
                }
                break;
            case run:
                if(game.gameEnds!=EndGameState.none){
                    if(mouseOver(6*40,game.height/2-1*40,mx,my,7*40,2*40)){
                        game.objectHandler.removeAll();
                        game.gameEnds=EndGameState.none;
                        game.gameState=GameState.menu;
                        game.subMenuState=GameState.none;
                    }
                }
                break;
            case exit:
                break;
            default: //GameState.none
                break;
        }
    }
}
