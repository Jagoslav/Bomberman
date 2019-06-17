/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.lang.reflect.Method;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jakub
 */
public class GameTest {
    public GameTest(){
    }
    
    @Test
    public void GameIstnieje(){
        try {
            Class.forName("bomberman.Game");
        } catch (ClassNotFoundException e) {
            fail("Nie utworzono odpowiedniej klasy");
        }
    }
    
    @Test
    public void renderIstnieje() throws ClassNotFoundException {
        try {
            Class c = Class.forName("bomberman.Game");
            c.getMethod("render");
        } catch (NoSuchMethodException e) {
            fail("Nie zaimplementowano metody render");
        }
    }
     
    @Test
    public void updateIstnieje() throws ClassNotFoundException {
        try {
            Class c = Class.forName("bomberman.Game");
            c.getMethod("update");
        } catch (NoSuchMethodException e) {
            fail("Nie zaimplementowano metody update()");
        }
    }
     
    @Test
    public void LastManStandingTest() throws ClassNotFoundException{
        try{
            Class c=Class.forName("bomberman.Game");
            c.getMethod("lastManStanding");
        }catch(NoSuchMethodException e){
            fail("Nie zaimplementowano metody lastManStanding()");
        }
    }
}
