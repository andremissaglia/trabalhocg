package br.usp.icmc.vicg.projeto.game;

import br.usp.icmc.vicg.gl.core.Light;
import br.usp.icmc.vicg.projeto.engine.core.Scene;
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
        Nave nave = new Nave();
        nave.addComponent(new CameraFollow(nave, getCamera(), getLight()));
        getCamera().addChild(nave);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                for(int k = 0; k < 4; k++){
                    getCamera().addChild(new Bola(i, j, k));
                }
            }
        }
    }
    
}
