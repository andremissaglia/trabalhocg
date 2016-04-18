package br.usp.icmc.vicg.projeto.components;

import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.Objeto;
import javax.media.opengl.GL3;

public abstract class Component {
    protected Objeto objeto;
    public Component(Objeto objeto){
        this.objeto = objeto;
    }
    public void init(GL3 gl, Shader shader){
        
    }
    public void update(){
        
    }
    public void draw(GL3 gl, Matrix4 transform){
        
    }
}
