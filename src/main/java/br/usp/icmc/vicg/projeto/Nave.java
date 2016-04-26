package br.usp.icmc.vicg.projeto;

import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.projeto.components.SimpleModelMesh;
import br.usp.icmc.vicg.gl.model.Cube;
import br.usp.icmc.vicg.gl.model.Sphere;
import br.usp.icmc.vicg.gl.model.WiredCube;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.components.CameraFollow;
import br.usp.icmc.vicg.projeto.components.Mesh;
import br.usp.icmc.vicg.projeto.components.MovimentacaoNave;
import javax.media.opengl.GL3;


public class Nave extends Objeto{
    public Vector3 direcao;
    public Vector3 direita;
    public Nave() {
        addComponent(new Mesh(this, "./data/f-16.obj"));
        addComponent(new MovimentacaoNave(this));
        addChild(new CameraObj());
        direcao = new Vector3(0,0,1);
        direita = new Vector3(-1,0,0);
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        super.init(gl, shader);
        //rotate(1, 0, 0, 180);
        //rotate(0, 0, 1, 180);
    }
    
    @Override
    public void update() {
        super.update();
        moveFront(0.01f);
        
    }
    public void moveFront(float vel){
        Vector3 velocidade = direcao.multiply(vel);
        tx +=velocidade.x;
        ty +=velocidade.y;
        tz +=velocidade.z;     
    }
    public void moveRight(float vel){
        Vector3 velocidade = direita.multiply(vel);
        tx +=velocidade.x;
        ty +=velocidade.y;
        tz +=velocidade.z;     
    }

    @Override
    public void rotate(float x, float y, float z, float theta) {
        super.rotate(x, y, z, theta); 
        this.direcao = Quaternion.rotate(new Vector3(0, 0, 1), rotation);
        this.direcao.normalize();
        this.direita = Quaternion.rotate(new Vector3(-1, 0, 0), rotation);
        this.direita.normalize();
    }
    
    private class CameraObj extends Objeto{

        public CameraObj() {
            addComponent(new CameraFollow(this));
        }

        @Override
        public void init(GL3 gl, Shader shader) {
            super.init(gl, shader);
            rotate(0, 1, 0, 180);
            rotate(1, 0, 0, 5);
            this.tz=-2;
            this.ty=0.15f;
        }
        
        
    }
}
