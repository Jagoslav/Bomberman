/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

/**
 * typ wyliczeniowy reprezentujący stan gry
 * informuje o tym, czy "uruchomione" jest menu gry, jego podmenu czy sama gra
 * @author Jakub
 */
public enum GameState {
    menu, //menu gry 
    run, //gra
    instructions, //podmenu instrukcje
    newGame, //podmenu wyboru mapy
    map1,map2,map3, //id poszczególnych map, bo mają różne soundtracki
    exit, //wyjście
    none; //odpowiednik null, nie jest nullem, ale przyjęcie tej wartości nie poskutkuje niczym
}
