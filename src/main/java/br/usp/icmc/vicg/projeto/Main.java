package br.usp.icmc.vicg.projeto;
import br.usp.icmc.vicg.projeto.engine.core.GameCore;
import br.usp.icmc.vicg.projeto.game.MyScene;

public class Main {
    
    public static void main(String[] args) {
        GameCore game = new GameCore();
        MyScene scene = new MyScene();
        game.pushScene(scene);
        game.start();
    }
}
