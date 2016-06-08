package br.usp.icmc.vicg.projeto.game;

import br.usp.icmc.vicg.projeto.engine.core.GameCore;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import br.usp.icmc.vicg.projeto.engine.math.Vector3;
import br.usp.icmc.vicg.projeto.game.components.Mesh;
import java.util.Random;

public class Meteoro extends Objeto{
    private final Vector3 eixo;
    private final float w;
    public Meteoro(float raioOrbitaMin, float raioOrbitaMax) {
        super();
        addComponent(new Mesh(this, "./data/meteorHuge/meteorHuge.obj"));
        Random r = GameCore.getGame().random;
        float raioOrbita = raioOrbitaMin + r.nextFloat()*(raioOrbitaMax-raioOrbitaMin);
        float initialPos = (float) (r.nextFloat()*Math.PI*2);
        float raio = (float) 3+r.nextFloat()*10;
        float altura = -7 + r.nextFloat()*14;
        this.position = new Vector3(raioOrbita * ((float) Math.cos(initialPos)), altura, raioOrbita * ((float) Math.sin(initialPos)));
        this.scale=new Vector3(raio,raio,raio);
        this.boundingSphere=raio;
        this.eixo = new Vector3(r.nextFloat(), r.nextFloat(), r.nextFloat());
        this.eixo.normalize();
        this.w = r.nextFloat()*3;
    }
    @Override
    public void update() {
        super.update();
        rotate(eixo.x, eixo.y, eixo.z, w);
    }

}
