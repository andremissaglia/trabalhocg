package br.usp.icmc.vicg.projeto.game;

import br.usp.icmc.vicg.projeto.engine.math.Vector3;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import br.usp.icmc.vicg.projeto.game.components.Mesh;
import javax.media.opengl.GL3;


public class Bola extends Objeto{

    public Bola(float x, float y, float z) {
        super();
        addComponent(new Mesh(this, "./data/meteor/meteor.obj"));
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
