package br.usp.icmc.vicg.projeto;

import br.usp.icmc.vicg.gl.model.Sphere;


public class Bola extends Objeto{

    public Bola() {
        setModel(new Sphere());
    }
    @Override
    public void update() {
        super.update();
        sx = sy = sz= 0.01f;
        tx +=0.01f;
    }
    
}
