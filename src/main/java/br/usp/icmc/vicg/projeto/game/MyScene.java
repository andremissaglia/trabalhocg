package br.usp.icmc.vicg.projeto.game;

import br.usp.icmc.vicg.gl.core.Light;
import br.usp.icmc.vicg.projeto.engine.core.Scene;

public class MyScene extends Scene{

    public MyScene() {
        Light light = new Light();
        light.setPosition(new float[]{10, 10, 50, 1.0f});
        light.setAmbientColor(new float[]{0.1f, 0.1f, 0.1f, 1.0f});
        light.setDiffuseColor(new float[]{0.75f, 0.75f, 0.75f, 1.0f});
        light.setSpecularColor(new float[]{0.7f, 0.7f, 0.7f, 1.0f});
        setLight(light);
    }

    @Override
    public void buildScene() {
        getCamera().addChild(new Nave());
    }
    
}
