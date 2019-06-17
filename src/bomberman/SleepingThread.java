/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

/**
 * klasa używana jako timer dla bomb i ognia, sprawdzająca czy od powstania
 * obiektu minęła odpowiednia ilość czasu
 * thread - usypiany wątek
 * delay - informacja o odcinku czasu, który wątek ma spać
 * @author Jakub
 */
public class SleepingThread extends Thread{
    Thread thread;
    private final int delay;

    /**
     * konstruktor
     * @param delay wartość czasu, jaką ma przeczekać obiekt
     */
    public SleepingThread(int delay){
        this.delay=delay;
    }

    /**
     * metoda uruchamiana zaraz po konstruktorze
     */
    @Override
    public synchronized void start(){
        thread=new Thread(this);
        thread.start();
    }

    /**
     * pętla wątku. Metoda śpi określoną ilość czasu a następnie przerywa 
     * działanie wątku
     */
    @Override
    public void run(){
        try{
            Thread.sleep(delay);
            this.interrupt();
        }catch(Exception all){
            System.err.println("SleepingThread.run() error");
        }
    }
}