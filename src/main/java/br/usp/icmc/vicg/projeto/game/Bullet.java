package br.usp.icmc.vicg.projeto.game;

import br.usp.icmc.vicg.projeto.engine.math.Vector3;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import br.usp.icmc.vicg.projeto.game.components.SimpleModelMesh;

public class Bullet extends Objeto{
    private final Vector3 velocidade;
    private int nupdates = 600;
    public Bullet(Vector3 direcao) {
        super();
        this.velocidade = direcao.multiply(10f);
        addComponent(new SimpleModelMesh(this, "Sphere"));
        this.scale = new Vector3(0.1f, 0.1f, 0.1f);
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
