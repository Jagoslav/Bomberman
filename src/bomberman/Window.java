/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
/**
 * klasa reprezentująca okno aplikacji
 * width,height - wymiary okna
 * title - tytuł okna aplikacji
 * game - referencja do obiektu gry;
 * @author Jakub
 */
public class Window extends Canvas{
    JFrame frame;

    /**
     * konstruktor
     * @param width szerokość okna
     * @param height wysokość okna
     * @param title tytuł aplikacji
     * @param game referencja do klasy głównej gry, którą będzie wyświetlało okno
     */
    public Window(int width,int height,String title, Game game){
        frame=new JFrame(title);
        frame.setPreferredSize(new Dimension(width,height+28));
        frame.setMinimumSize(new Dimension(width,height+28));
        frame.setMaximumSize(new Dimension(width,height+28));
        frame.setResizable(false);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
        
    }
}
