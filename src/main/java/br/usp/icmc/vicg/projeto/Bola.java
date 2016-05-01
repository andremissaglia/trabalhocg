package br.usp.icmc.vicg.projeto;

import br.usp.icmc.vicg.gl.model.Sphere;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.components.SimpleModelMesh;
import javax.media.opengl.GL3;


public class Bola extends Objeto{

    public Bola(float x, float y, float z) {
        super();
        addComponent(new SimpleModelMesh(this, "Sphere"));
        this.scale = new Vector3(0.1f, 0.1f, 0.1f);
        this.position = new Vector3(x, y, z);
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
