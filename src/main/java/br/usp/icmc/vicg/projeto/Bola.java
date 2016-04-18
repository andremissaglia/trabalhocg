package br.usp.icmc.vicg.projeto;

import br.usp.icmc.vicg.gl.model.Sphere;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.components.SimpleModelMesh;
import javax.media.opengl.GL3;


public class Bola extends Objeto{

    public Bola() {
        addComponent(new SimpleModelMesh(this, new Sphere()));
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        super.init(gl, shader); 
        tz = -3;
        ty = 0.5f;
        tx = -0.5f;
    }
    
    @Override
    public void update() {
        super.update();
        sx = sy = sz= 0.3f;
    }
    
}
