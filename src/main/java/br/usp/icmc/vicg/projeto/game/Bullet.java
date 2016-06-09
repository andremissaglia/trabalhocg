package br.usp.icmc.vicg.projeto.game;

import br.usp.icmc.vicg.projeto.engine.math.Vector3;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import br.usp.icmc.vicg.projeto.game.components.Mesh;

public class Bullet extends Objeto{
    private final Vector3 velocidade;
    private int nupdates = 100;
    public Bullet(Vector3 posicao, Vector3 direcao) {
        super();
        this.velocidade = direcao.multiply(10f);
        addComponent(new Mesh(this, "./data/bullet/bullet.obj"));
        this.scale = new Vector3(0.1f, 0.1f, 0.1f);
        this.position = posicao.copy();
        this.position.add(this.velocidade);
    }

    @Override
    public void update() {
        super.update();
        this.position.add(velocidade);
        if(nupdates-- == 0){
            this.getFather().removeChild(this);
        }
    }
    
}
