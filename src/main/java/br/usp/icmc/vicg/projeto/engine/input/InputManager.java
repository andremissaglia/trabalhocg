package br.usp.icmc.vicg.projeto.engine.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

public class InputManager extends KeyAdapter{
    private final ArrayList<KeyEventListener> keyboardListeners;
    private final TreeSet<Integer> keys;
    private final LinkedList<KeyEvent> eventQueue;
    public InputManager() {
        super();
        this.keyboardListeners = new ArrayList<>();
        keys = new TreeSet<>();
        eventQueue = new LinkedList<>();
    }
    
    public void addKeyboardListener(KeyEventListener eventListener){
        keyboardListeners.add(eventListener);
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        if(isPressed(e.getKeyCode())) return;
        keys.add(e.getKeyCode());
        for(KeyEventListener kel : keyboardListeners){
            kel.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys.remove(e.getKeyCode());
        for(KeyEventListener kel : keyboardListeners){
            kel.keyReleased(e);
        }
    }
    public void processKeyPressed(KeyEvent e){
        if(isPressed(e.getKeyCode())) return;
        keys.add(e.getKeyCode());
        for(KeyEventListener kel : keyboardListeners){
            kel.keyPressed(e);
        }
    }

    public void processKeyReleased(KeyEvent e) {
        keys.remove(e.getKeyCode());
        for(KeyEventListener kel : keyboardListeners){
            kel.keyReleased(e);
        }
    }
    public boolean isPressed(int key){
        return keys.contains(key);
    }
    public synchronized void insertEvent(KeyEvent e){
        eventQueue.add(e);
    }
    public synchronized void processEvents(){
        for(KeyEvent e : eventQueue){
            if(e.getID() == KeyEvent.KEY_PRESSED){
                processKeyPressed(e);
            } else if(e.getID() == KeyEvent.KEY_RELEASED){
                processKeyReleased(e);
            }
        }
        eventQueue.clear();
    }
}
