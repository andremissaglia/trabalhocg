package br.usp.icmc.vicg.projeto.game;

import br.usp.icmc.vicg.projeto.engine.math.Quaternion;
import br.usp.icmc.vicg.projeto.engine.math.Vector3;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.engine.core.GameCore;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import br.usp.icmc.vicg.projeto.game.components.Bullets;
import br.usp.icmc.vicg.projeto.game.components.Mesh;
import br.usp.icmc.vicg.projeto.game.components.MovimentacaoNave;
import javax.media.opengl.GL3;

public class Nave extends Objeto {

    public Vector3 direcao;
    public Vector3 direita;
    private float velocidade;
    private float acceleration = 0;
    private static final float MAX_VELOCITY = 10;

    public Nave() {
        super();
        addComponent(new Mesh(this, "./data/xwing/xwing.obj"));
        addComponent(new MovimentacaoNave(this));
        addComponent(new Bullets(this));
        this.position = new Vector3(0, 0, -1500);
        direcao = new Vector3(0, 0, 1);
        direita = new Vector3(-1, 0, 0);
        velocidade = 0;
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        super.init(gl, shader);
        rotate(0, 1, 0, 180);
    }

    public void accelerate(float amount) {
        if (amount < -MAX_VELOCITY || amount > MAX_VELOCITY) {
            return;
        }
        this.velocidade += amount;
        if (this.velocidade < 0) {
            velocidade = 0;
        }
    }

    @Override
    public void update() {
        super.update();
        moveFront(velocidade);

    }

    public void moveFront(float vel) {
        Vector3 velocidade = direcao.multiply(vel);
        this.position.add(velocidade);
        GameCore.getGame().getEventManager().emit("moved");
    }

    public void moveRight(float vel) {
        Vector3 velocidade = direita.multiply(vel);
        this.position.add(velocidade);
    }

    @Override
    public void rotate(float x, float y, float z, float theta) {
        super.rotate(x, y, z, theta);
        this.direcao = Quaternion.rotate(new Vector3(0, 0, -1), rotation);
        this.direcao.normalize();
        this.direita = Quaternion.rotate(new Vector3(1, 0, 0), rotation);
        this.direita.normalize();
    }
}
