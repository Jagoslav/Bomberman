/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

/**
 * klasa reprezentująca początkowy stan mapy
 * typesToDraw - liczbowa reprezentacja mapy
 * @author Jakub
 */
public class CreateMap{ //pozwala wybrać rodzaj mapy, którą chcemy zagrać
    private final int [][] typesToDraw;

    /**
     * konstruktor
     * @param mapaID tablica wartości int, informująca o wstępnym położeniu obiektów względem siebie
     */
    public CreateMap(int mapaID){
        if(mapaID==0){ //ściany co 2 pola
            int[][] tempInt={ //wall(1), crate(2), player(3), player 4 bot
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,3,0,2,2,2,0,4,0,2,0,4,0,2,2,2,0,4,1},
                {1,0,1,2,1,2,1,0,1,2,1,0,1,2,1,2,1,0,1},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
                {1,2,1,0,1,2,1,2,1,2,1,2,1,2,1,0,1,2,1},
                {1,2,0,4,0,2,2,2,2,2,2,2,2,2,0,4,0,2,1},
                {1,2,1,0,1,2,1,0,1,2,1,0,1,2,1,0,1,2,1},
                {1,2,2,2,2,2,0,4,0,2,0,4,0,2,2,2,2,2,1},
                {1,2,1,0,1,2,1,0,1,2,1,0,1,2,1,0,1,2,1},
                {1,2,0,4,0,2,2,2,2,2,2,2,2,2,0,4,0,2,1},
                {1,2,1,0,1,2,1,2,1,2,1,2,1,2,1,0,1,2,1},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
                {1,0,1,2,1,2,1,0,1,2,1,0,1,2,1,2,1,0,1},
                {1,4,0,2,2,2,0,4,0,2,0,4,0,2,2,2,0,3,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            };
            typesToDraw=tempInt;
        }
        else if(mapaID==1){//wzorek 1
            int[][] tempInt={ //wall(1), crate(2), player(3), player 4 bot
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,3,0,2,1,4,0,2,2,2,1,4,0,2,2,2,0,4,1},
                {1,0,1,2,1,0,1,1,1,2,1,0,1,2,1,1,1,0,1},
                {1,2,1,2,1,2,2,2,1,2,1,1,1,2,2,2,2,2,1},
                {1,2,1,2,1,1,1,2,1,0,1,2,2,2,1,1,1,1,1},
                {1,2,1,0,4,0,1,2,0,4,1,2,1,2,2,2,0,4,1},
                {1,2,2,2,1,1,1,2,1,0,1,2,1,1,2,1,1,0,1},
                {1,2,1,2,2,0,4,0,1,2,1,0,4,0,2,2,1,2,1},
                {1,0,1,1,2,1,1,2,1,0,1,2,1,1,1,2,2,2,1},
                {1,4,0,2,2,2,1,2,1,4,0,2,1,0,4,0,1,2,1},
                {1,1,1,1,1,2,2,2,1,0,1,2,1,1,1,2,1,2,1},
                {1,2,2,2,2,2,1,1,1,2,1,2,2,2,1,2,1,2,1},
                {1,0,1,1,1,2,1,0,1,2,1,1,1,0,1,2,1,0,1},
                {1,4,0,2,2,2,0,4,1,2,2,2,0,4,1,2,0,3,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            };
            typesToDraw=tempInt;
        }
        else {//wzorek 2
            int[][] tempInt={ //wall(1), crate(2), player(3), player 4 bot
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,4,0,2,1,2,2,2,0,4,0,2,2,2,1,2,0,4,1},
                {1,0,1,2,2,2,1,2,1,1,1,2,1,2,2,2,1,0,1},
                {1,2,2,2,1,1,0,2,2,2,2,2,0,1,1,2,2,2,1},
                {1,1,2,1,1,0,3,1,1,2,1,1,4,0,1,1,2,1,1},
                {1,2,2,2,1,2,1,1,0,0,0,1,1,2,1,2,2,2,1},
                {1,0,1,2,2,2,1,0,4,1,4,0,1,2,2,2,1,0,1},
                {1,4,1,1,1,2,2,0,1,1,1,0,2,2,1,1,1,4,1},
                {1,0,1,2,2,2,1,0,4,1,4,0,1,2,2,2,1,0,1},
                {1,2,2,2,1,2,1,1,0,0,0,1,1,2,1,2,2,2,1},
                {1,1,2,1,1,0,4,1,1,2,1,1,3,0,1,1,2,1,1},
                {1,2,2,2,1,1,0,2,2,2,2,2,0,1,1,2,2,2,1},
                {1,0,1,2,2,2,1,2,1,1,1,2,1,2,2,2,1,0,1},
                {1,4,0,2,1,2,2,2,0,4,0,2,2,2,1,2,0,4,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            };
            typesToDraw=tempInt;
        }
    }
    /**
     * geter mapy, którą utworzono
     * @return
     */
    int[][] getNewMap(){return typesToDraw;}
}
