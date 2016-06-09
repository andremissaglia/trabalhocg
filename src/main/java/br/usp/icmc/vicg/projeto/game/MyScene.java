package br.usp.icmc.vicg.projeto.game;

import br.usp.icmc.vicg.gl.core.Light;
import br.usp.icmc.vicg.projeto.engine.core.GameCore;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import br.usp.icmc.vicg.projeto.engine.core.Scene;
import br.usp.icmc.vicg.projeto.engine.graphics.Camera;
import br.usp.icmc.vicg.projeto.game.components.CameraFollow;
import br.usp.icmc.vicg.projeto.game.components.FollowObject;

public class MyScene extends Scene{
    public MyScene() {
        super();
        Light light = new Light();
        light.setPosition(new float[]{0, 0, 0, 1.0f});
        light.setAmbientColor(new float[]{0.1f, 0.1f, 0.1f, 1.0f});
        light.setDiffuseColor(new float[]{0.9f, 0.9f, 0.9f, 1.0f});
        light.setSpecularColor(new float[]{0.2f, 0.2f, 0.2f, 1.0f});
        setLight(light);
        GameCore.getGame().addService(new CollisionManager());
    }

    @Override
    public void buildScene() {
        //criar objs
        Camera camera = getCamera();
        Skybox skybox = new Skybox();
        camera.addChild(skybox);
        Nave nave = new Nave();
        camera.addChild(nave);
        Objeto sistemaSolar = new Objeto();
        camera.addChild(sistemaSolar);
        Planeta sol = new Planeta("./data/sun/sun.obj", 150, 0, 0, 0);
        sistemaSolar.addChild(sol);
        Orbita o1 = new Orbita(0.5f, 3.38f);
        Orbita o2 = new Orbita(0.17f, 3.86f);
        Orbita o3 = new Orbita(0.1f, 7.16f);
        Orbita o4 = new Orbita(0.053f, 5.65f);
        sistemaSolar.addChild(o1);
        sistemaSolar.addChild(o2);
        sistemaSolar.addChild(o3);
        sistemaSolar.addChild(o4);
        Planeta p1 = new Planeta("./data/mercurio/mercurio.obj", 10, 250, 2f, 0);
        Planeta p2 = new Planeta("./data/venus/venus.obj", 20, 350, 2f, 120);        
        Planeta p3 = new Planeta("./data/terra/terra.obj", 25, 650, 2f, 240);
        Planeta p4 = new Planeta("./data/marte/marte.obj", 28, 800, 2f, 60);
        o1.addChild(p1);
        o2.addChild(p2);
        o3.addChild(p3);
        o4.addChild(p4);
        Cinturao c = new Cinturao(0.1f, 900, 1000, 1000, 0);
        sistemaSolar.addChild(c);
        //configurar objs
        nave.addComponent(new CameraFollow(nave, camera, getLight()));
        skybox.addComponent(new FollowObject(skybox, nave));
        CollisionManager.obj.setCollidables(c);
    }
    
}
