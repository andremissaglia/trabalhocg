package br.usp.icmc.vicg.projeto;

import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.projeto.components.SimpleModelMesh;
import br.usp.icmc.vicg.gl.model.Cube;
import br.usp.icmc.vicg.gl.model.Sphere;
import br.usp.icmc.vicg.gl.model.WiredCube;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.components.CameraFollow;
import br.usp.icmc.vicg.projeto.components.Mesh;
import javax.media.opengl.GL3;


public class Nave extends Objeto{

    public Nave() {
        addComponent(new Mesh(this, "./data/f-16.obj"));
        addChild(new CameraObj());
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        super.init(gl, shader);
        rotate(1, 0, 0, 180);
        rotate(0, 0, 1, 180);
    }
    
    @Override
    public void update() {
        super.update();
        //sx = sy = sz= 0.1f;
        tz-=0.01f;
    }
    private class CameraObj extends Objeto{

        public CameraObj() {
            addComponent(new CameraFollow(this));
        }

        @Override
        public void init(GL3 gl, Shader shader) {
            super.init(gl, shader);
            rotate(0, 1, 0, 180);
            rotate(1, 0, 0, 10);
            this.tz=-2;
            this.ty=0.3f;
        }
        
        
    }
}
