package br.usp.icmc.vicg.projeto;

import br.usp.icmc.vicg.gl.model.Cube;
import br.usp.icmc.vicg.gl.model.Sphere;
import br.usp.icmc.vicg.gl.model.WiredCube;


public class Bola extends Objeto{

    public Bola() {
        setModel(new WiredCube());
    }
    @Override
    public void update() {
        super.update();
        sx = sy = sz= 0.1f;
    }
    
}
