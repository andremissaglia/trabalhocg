package br.usp.icmc.vicg.projeto;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

public class InputManager {
    private final ArrayList<KeyEventListener> keyboardListeners;
    private final TreeSet<Integer> keys;
    private LinkedList<KeyEvent> eventQueue;
    public InputManager() {
            this.keyboardListeners = new ArrayList<>();
            keys = new TreeSet<>();
            eventQueue = new LinkedList<>();
    }
    
    public void addKeyboardListener(KeyEventListener eventListener){
        keyboardListeners.add(eventListener);
    }
    
    public void keyPressed(KeyEvent e){
        if(isPressed(e.getKeyCode())) return;
        keys.add(e.getKeyCode());
        for(KeyEventListener kel : keyboardListeners){
            kel.keyPressed(e);
        }
    }

    public void keyReleased(KeyEvent e) {
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
                keyPressed(e);
            } else if(e.getID() == KeyEvent.KEY_RELEASED){
                keyReleased(e);
            }
        }
        eventQueue.clear();
    }
}
