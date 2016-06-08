package br.usp.icmc.vicg.projeto.game;

import br.usp.icmc.vicg.projeto.engine.math.Vector3;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import br.usp.icmc.vicg.projeto.game.components.Mesh;
import javax.media.opengl.GL3;


public class Planeta extends Objeto{
    private float w;
    /**
     * 
     * @param mesh
     * @param raio
     * @param raioOrbita
     * @param w velocidade de rotação ao redor do próprio eixo, em deg/frame
     * @param initialPos posição inicial ao redor do sol, em deg
     */
    public Planeta(String mesh, float raio, float raioOrbita, float w, float initialPos) {
        super();
        addComponent(new Mesh(this, mesh));
//        addComponent(new Mesh(this, "./data/meteorHuge/meteorHuge.obj"));
        this.position = new Vector3(raioOrbita * ((float) Math.cos(Math.toRadians(initialPos))), 0, raioOrbita * ((float) Math.sin(Math.toRadians(initialPos))));
        this.scale=new Vector3(raio, raio, raio);
        this.boundingSphere=raio;
        this.w = w;
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        super.init(gl, shader); 
    }
    
    @Override
    public void update() {
        super.update();
        rotate(0, 1, 0, w);
    }
    
}
