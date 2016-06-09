package br.usp.icmc.vicg.projeto.game;

import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import br.usp.icmc.vicg.projeto.engine.math.Vector3;
import br.usp.icmc.vicg.projeto.game.components.Mesh;
import javax.media.opengl.GL3;

public class Skybox extends Objeto{
    public Skybox() {
        super();
        addComponent(new Mesh(this,"./data/skybox/skybox.obj"));
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        super.init(gl, shader);
        this.scale=new Vector3(10000, 10000, 10000);
        this.boundingSphere = 15000;
    }

}
