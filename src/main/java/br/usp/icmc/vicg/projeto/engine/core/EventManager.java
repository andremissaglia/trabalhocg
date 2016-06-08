package br.usp.icmc.vicg.projeto.engine.core;

import java.util.ArrayList;
import java.util.HashMap;

public class EventManager {
    private final HashMap<String,ArrayList<Runnable>> listeners;

    public EventManager() {
        this.listeners = new HashMap<>();
    }
    public void addOnEvent(String event, Runnable action){
        if(!listeners.containsKey(event)){
            listeners.put(event, new ArrayList<Runnable>());
        }
        listeners.get(event).add(action);
    }
    public void emit(String event){
        if(!listeners.containsKey(event)){
            return;
        }
        for(Runnable r : listeners.get(event)){
            r.run();
        }
    }
}
