package br.usp.icmc.vicg.projeto.engine.core;

import br.usp.icmc.vicg.gl.core.Light;
import br.usp.icmc.vicg.projeto.engine.graphics.Camera;

public abstract class Scene {
    private Light light;
    private final Camera camera;

    public abstract void buildScene();
    
    public Scene() {
        this.camera = new Camera();
    }
    protected final void setLight(Light light){
        this.light = light;
    }
    public final Light getLight(){
        return light;
    }
    public final Camera getCamera(){
        return camera;
    }
    public void update(){
        this.camera.update();
    }
}
