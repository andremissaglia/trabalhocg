package br.usp.icmc.vicg.projeto.game.components;

import br.usp.icmc.vicg.projeto.engine.core.Component;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.engine.input.InputManager;
import br.usp.icmc.vicg.projeto.game.Nave;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import br.usp.icmc.vicg.projeto.engine.core.GameCore;
import java.awt.event.KeyEvent;
import javax.media.opengl.GL3;

public class MovimentacaoNave extends Component {
    public MovimentacaoNave(Objeto objeto) {
        super(objeto);
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        super.init(gl, shader); 
    }

    @Override
    public void update() {
        Nave nave = (Nave) objeto;
        InputManager input = GameCore.getGame().getInput();
        if(input.isPressed(KeyEvent.VK_UP)){
            //inverter o controle parece ser mais fácil: tecla pra cima *mergulha* a nave
            nave.rotate(nave.direita.x, nave.direita.y, nave.direita.z, -2f);
        }
        if(input.isPressed(KeyEvent.VK_DOWN)){
            nave.rotate(nave.direita.x, nave.direita.y, nave.direita.z, 2f);
        }
        if(input.isPressed(KeyEvent.VK_RIGHT)){
            nave.rotate(nave.direcao.x, nave.direcao.y, nave.direcao.z, 5f);
        }
        if(input.isPressed(KeyEvent.VK_LEFT)){
            nave.rotate(nave.direcao.x, nave.direcao.y, nave.direcao.z, -5f);
        }
        if(input.isPressed(KeyEvent.VK_Z)){
            nave.accelerate(0.1f);
        }
        if(input.isPressed(KeyEvent.VK_X)){
            nave.accelerate(-0.1f);
        }
    }
}
