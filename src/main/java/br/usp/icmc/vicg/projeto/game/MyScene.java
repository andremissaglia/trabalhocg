package br.usp.icmc.vicg.projeto.game;

import br.usp.icmc.vicg.gl.core.Light;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import br.usp.icmc.vicg.projeto.engine.core.Scene;
import br.usp.icmc.vicg.projeto.engine.graphics.Camera;
import br.usp.icmc.vicg.projeto.game.components.CameraFollow;

public class MyScene extends Scene{

    public MyScene() {
        super();
        Light light = new Light();
        light.setPosition(new float[]{0, 0, 0, 1.0f});
        light.setAmbientColor(new float[]{0.1f, 0.1f, 0.1f, 1.0f});
        light.setDiffuseColor(new float[]{0.9f, 0.9f, 0.9f, 1.0f});
        light.setSpecularColor(new float[]{0.2f, 0.2f, 0.2f, 1.0f});
        setLight(light);
    }

    @Override
    public void buildScene() {
        Camera camera = getCamera();
        Nave nave = new Nave();
        nave.addComponent(new CameraFollow(nave, camera, getLight()));
        camera.addChild(nave);
        Objeto sistemaSolar = new Objeto();
        camera.addChild(sistemaSolar);
        Planeta sol = new Planeta("./data/sun/sun.obj", 50, 0, 0, 0);
        sistemaSolar.addChild(sol);
        Orbita o = new Orbita(0.5f);
        sistemaSolar.addChild(o);
        Planeta p1 = new Planeta("./data/terra/terra.obj", 5, 150, 0.5f, 0);
        Planeta p2 = new Planeta("./data/terra/terra.obj", 5, 300, 0.05f, 120);        
        Planeta p3 = new Planeta("./data/terra/terra.obj", 5, 450, 0.01f, 240);
        o.addChild(p1);
        o.addChild(p2);
        o.addChild(p3);
    }
    
}
