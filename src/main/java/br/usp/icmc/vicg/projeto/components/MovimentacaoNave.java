package br.usp.icmc.vicg.projeto.components;

import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.KeyEventListener;
import br.usp.icmc.vicg.projeto.Nave;
import br.usp.icmc.vicg.projeto.Objeto;
import br.usp.icmc.vicg.projeto.Scene;
import java.awt.event.KeyEvent;
import javax.media.opengl.GL3;

public class MovimentacaoNave extends Component implements KeyEventListener{

    public MovimentacaoNave(Objeto objeto) {
        super(objeto);
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        super.init(gl, shader); 
        Scene.getScene().addKeyboardListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Nave nave = (Nave) objeto;
        
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                //inverter o controle parece ser mais fácil: tecla pra cima *mergulha* a nave
                nave.rotate(nave.direita.x, nave.direita.y, nave.direita.z, -2f);
                break;
            case KeyEvent.VK_DOWN:
                nave.rotate(nave.direita.x, nave.direita.y, nave.direita.z, 2f);
                break;
            case KeyEvent.VK_LEFT:
                nave.rotate(nave.direcao.x, nave.direcao.y, nave.direcao.z, -2f);
                break;
            case KeyEvent.VK_RIGHT:
                nave.rotate(nave.direcao.x, nave.direcao.y, nave.direcao.z, 2f);
                break;
//            Para Debug
//            case KeyEvent.VK_W:
//                nave.moveFront(0.1f);
//                break;
//            case KeyEvent.VK_D:
//                nave.moveRight(0.1f);
//                break;
//            case KeyEvent.VK_S:
//                nave.moveFront(-0.1f);
//                break;
//            case KeyEvent.VK_A:
//                nave.moveRight(-0.1f);
//                break;
        }
    }
    
}
