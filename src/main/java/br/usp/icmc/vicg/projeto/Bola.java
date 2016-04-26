package br.usp.icmc.vicg.projeto;

import br.usp.icmc.vicg.gl.model.Sphere;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.components.SimpleModelMesh;
import javax.media.opengl.GL3;


public class Bola extends Objeto{

    public Bola(float x, float y, float z) {
        addComponent(new SimpleModelMesh(this, new Sphere()));
        sx = sy = sz= 0.1f;
        tx = x;
        ty = y;
        tz = z;
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        super.init(gl, shader); 
    }
    
    @Override
    public void update() {
        super.update();
    }
    
}
