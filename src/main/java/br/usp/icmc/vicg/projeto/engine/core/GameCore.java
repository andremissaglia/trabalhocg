package br.usp.icmc.vicg.projeto.engine.core;

import br.usp.icmc.vicg.projeto.engine.input.InputManager;
import br.usp.icmc.vicg.projeto.engine.graphics.InterfaceManager;
import java.util.ArrayList;
import java.util.Random;

public class GameCore {

    private static GameCore coreObj;
    private final InputManager input;
    private final InterfaceManager ui;
    private final EventManager events;
    private Scene scene;
    private final int UPDATE_RATE = 30;
    public Random random;
    private ArrayList<Service> services;
    public static GameCore getGame() {
        return coreObj;
    }

    public GameCore() {
        this.input = new InputManager();
        this.ui = new InterfaceManager();
        this.events = new EventManager();
        this.random = new Random();
        this.services = new ArrayList<>();
        coreObj = this;

    }

    public InputManager getInput() {
        return input;
    }
    public EventManager getEventManager(){
        return events;
    }
    public void addService(Service s){
        this.services.add(s);
    }
    public void pushScene(Scene scene) {
        scene.buildScene();
        this.scene = scene;
        ui.setScene(scene);
    }

    public Scene getCurrentScene() {
        return scene;
    }

    public void start() {
        ui.init();
    }

    public void runGameLoop() {
        GameLoop gl = new GameLoop();
        gl.start();
    }

    class GameLoop extends Thread {

        @Override
        public void run() {
            long before, after = System.nanoTime();
            long updateTime = (long) (1e9 / UPDATE_RATE);
            long counter = updateTime;
            long nextPrint = after + 1000000000;
            float fps;
            int i = 0;
            while (true) {
                before = after;
                input.processEvents();
                while (counter >= updateTime) {
                    scene.update();
                    for(Service s : services){
                        s.update();
                    }
                    counter -= updateTime;
                }
                ui.draw();
                after = System.nanoTime();
                counter += after - before;
                fps = 1000000000.0f / (after - before);
                if (after >= nextPrint) {
                    System.out.println(fps);
                    nextPrint = after + 1000000000;
                }
            }
        }

    }
}
