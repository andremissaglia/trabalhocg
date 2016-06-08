package br.usp.icmc.vicg.projeto.engine.core;

import br.usp.icmc.vicg.projeto.engine.input.InputManager;
import br.usp.icmc.vicg.projeto.engine.graphics.Camera;
import br.usp.icmc.vicg.projeto.engine.graphics.InterfaceManager;

public class GameCore{
    
    private static GameCore coreObj;
    private final InputManager input;
    private final InterfaceManager ui;
    private Scene scene;
    public static GameCore getGame(){
        return coreObj;
    }
    
    public GameCore() {
        this.input = new InputManager();
        this.ui = new InterfaceManager();
        coreObj = this;
        
    }
    public InputManager getInput(){
        return input;
    }
    public void pushScene(Scene scene){
        scene.buildScene();
        this.scene = scene;
        ui.setScene(scene);
    }
    public Scene getCurrentScene(){
        return scene;
    }
    public void run(){
        ui.init();
        scene.update();
        ui.draw();
    }
}
