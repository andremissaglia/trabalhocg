package br.usp.icmc.vicg.projeto.game;

import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import br.usp.icmc.vicg.projeto.engine.math.Quaternion;
import br.usp.icmc.vicg.projeto.engine.math.Vector3;

public class Orbita extends Objeto{
    private float w;
    private Vector3 eixo;
    /**
     * Órbita, sem nenhum gráfico.
     * @param w velocidade de rotacao ao redor do sol em deg/frame
     * @param theta inclinação da órbita
     */
    public Orbita(float w, float theta) {
        super();
        this.w = w;
        rotate(1,0,0,theta);
        eixo = Quaternion.rotate(new Vector3(0, 1, 0), rotation);
        
    }
    
    @Override
    public void update() {
        super.update(); 
        rotate(eixo.x, eixo.y, eixo.z, w);
    }
}
