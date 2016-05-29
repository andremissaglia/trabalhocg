package br.usp.icmc.vicg.projeto.components;

import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.Bullet;
import br.usp.icmc.vicg.projeto.KeyEventListener;
import br.usp.icmc.vicg.projeto.Nave;
import br.usp.icmc.vicg.projeto.Objeto;
import br.usp.icmc.vicg.projeto.GameCore;
import java.awt.event.KeyEvent;
import javax.media.opengl.GL3;

public class Bullets extends Component implements KeyEventListener{
    private GL3 gl;
    private Shader shader;
    public Bullets(Objeto objeto) {
        super(objeto);
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        super.init(gl, shader);
        GameCore.getScene().getInput().addKeyboardListener(this);
        this.gl = gl;
        this.shader = shader;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            Bullet b = new Bullet(((Nave) objeto).direcao.copy());
            b.position = objeto.position.copy();
            b.init(gl, shader);
            objeto.getFather().addChild(b);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
