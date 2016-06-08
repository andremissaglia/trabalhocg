package br.usp.icmc.vicg.projeto.engine.core;

import br.usp.icmc.vicg.projeto.engine.input.InputManager;
import br.usp.icmc.vicg.projeto.engine.graphics.InterfaceManager;

public class GameCore {

    private static GameCore coreObj;
    private final InputManager input;
    private final InterfaceManager ui;
    private Scene scene;
    private final int FPS = 30;
    private final int UPDATE_RATE = 30;

    public static GameCore getGame() {
        return coreObj;
    }

    public GameCore() {
        this.input = new InputManager();
        this.ui = new InterfaceManager();
        coreObj = this;

    }

    public InputManager getInput() {
        return input;
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
