package br.usp.icmc.vicg.projeto.components;

import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.KeyEventListener;
import br.usp.icmc.vicg.projeto.Nave;
import br.usp.icmc.vicg.projeto.Objeto;
import br.usp.icmc.vicg.projeto.Scene;
import java.awt.event.KeyEvent;
import javax.media.opengl.GL3;

public class MovimentacaoNave extends Component implements KeyEventListener{
    private static int UP = 0;
    private static int RIGHT = 1;
    private static int DOWN = 2;
    private static int LEFT = 3;
    private boolean keyStates[];
    public MovimentacaoNave(Objeto objeto) {
        super(objeto);
        keyStates = new boolean[4];
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        super.init(gl, shader); 
        Scene.getScene().addKeyboardListener(this);
    }

    @Override
    public void update() {
        Nave nave = (Nave) objeto;
        if(keyStates[UP]){
            //inverter o controle parece ser mais fácil: tecla pra cima *mergulha* a nave
            nave.rotate(nave.direita.x, nave.direita.y, nave.direita.z, -2f);
        }
        if(keyStates[DOWN]){
            nave.rotate(nave.direita.x, nave.direita.y, nave.direita.z, 2f);
        }
        if(keyStates[RIGHT]){
            nave.rotate(nave.direcao.x, nave.direcao.y, nave.direcao.z, 2f);
        }
        if(keyStates[LEFT]){
            nave.rotate(nave.direcao.x, nave.direcao.y, nave.direcao.z, -2f);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                keyStates[UP] = true;
                break;
            case KeyEvent.VK_DOWN:
                keyStates[DOWN] = true;
                break;
            case KeyEvent.VK_LEFT:
                keyStates[LEFT] = true;
                break;
            case KeyEvent.VK_RIGHT:
                keyStates[RIGHT] = true;
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
    
    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                keyStates[UP] = false;
                break;
            case KeyEvent.VK_DOWN:
                keyStates[DOWN] = false;
                break;
            case KeyEvent.VK_LEFT:
                keyStates[LEFT] = false;
                break;
            case KeyEvent.VK_RIGHT:
                keyStates[RIGHT] = false;
                break;
        }
    }
    
}
