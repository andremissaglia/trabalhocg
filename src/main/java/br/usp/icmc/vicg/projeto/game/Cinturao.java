package br.usp.icmc.vicg.projeto.game;

public class Cinturao extends Orbita{

    public Cinturao(float w, float raioMin, float raioMax, int n, float theta) {
        super(w, theta);
        for(int i = 0; i < n; i++){
            addChild(new Meteoro(raioMin, raioMax));
        }
    }

}
