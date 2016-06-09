package br.usp.icmc.vicg.projeto.game.components;

import br.usp.icmc.vicg.projeto.engine.core.Component;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.game.Bullet;
import br.usp.icmc.vicg.projeto.game.Nave;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import br.usp.icmc.vicg.projeto.engine.core.GameCore;
import java.awt.event.KeyEvent;
import javax.media.opengl.GL3;

public class Bullets extends Component {
    private GL3 gl;
    private Shader shader;
    private final int MAX_IGNORES = 4;
    private int ignores = 0;
    public Bullets(Objeto objeto) {
        super(objeto);
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        super.init(gl, shader);
        this.gl = gl;
        this.shader = shader;
    }

    @Override
    public void update() {
        if(ignores++ < MAX_IGNORES){
            return;
        }
        if(GameCore.getGame().getInput().isPressed(KeyEvent.VK_SPACE)){
            ignores = 0;
            Bullet b = new Bullet(objeto.position, ((Nave) objeto).direcao));
            b.init(gl, shader);
            objeto.getFather().addChild(b);
        }
    }    
}
