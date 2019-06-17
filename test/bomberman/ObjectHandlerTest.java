/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;
import java.lang.reflect.Method;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jakub
 */
public class ObjectHandlerTest {
    public ObjectHandlerTest(){
    }
    
    @Test
    public void ObjectHandlerIstnieje(){
        try {
            Class.forName("bomberman.ObjectHandler");
        } catch (ClassNotFoundException e) {
            fail("Nie utworzono odpowiedniej klasy");
        }
    }
    @Test
     public void renderIstnieje() throws ClassNotFoundException {
        try {
            Class c = Class.forName("bomberman.ObjectHandler");
            c.getMethod("renderList",Graphics.class,Images.class);
        } catch (NoSuchMethodException e) {
            fail("Nie zaimplementowano metody renderList()");
        } catch (IllegalArgumentException ex) {
            fail("Zły typ argumentu metody renderList()");
        }
    }
    @Test
    public void updateIstnieje(){
        try{
            Class c=Class.forName("bomberman.ObjectHandler");
            Method update = c.getMethod("updateList");
        }catch(ClassNotFoundException e){
            fail("klasa nie istnieje");
        }catch(NoSuchMethodException e){
            fail("metoda nie istnieje:");
        }
    }
}
