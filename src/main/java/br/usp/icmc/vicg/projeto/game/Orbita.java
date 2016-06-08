package br.usp.icmc.vicg.projeto.game;

import br.usp.icmc.vicg.projeto.engine.core.Objeto;

public class Orbita extends Objeto{
    private float w;
    /**
     * Órbita, sem nenhum gráfico.
     * @param w velocidade de rotacao ao redor do sol em deg/frame
     */
    public Orbita(float w) {
        super();
        this.w = w;
    }
    
    @Override
    public void update() {
        super.update(); 
        rotate(0, 1, 0, w);
    }
}
