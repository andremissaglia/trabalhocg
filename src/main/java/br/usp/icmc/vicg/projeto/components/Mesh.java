package br.usp.icmc.vicg.projeto.components;

import br.usp.icmc.vicg.gl.jwavefront.JWavefrontObject;
import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.Objeto;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL3;

public class Mesh extends Component{
    private JWavefrontObject model;
    public Mesh(Objeto objeto, String path) {
        super(objeto);
        model = new JWavefrontObject(new File(path));
        
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        try {
            model.init(gl, shader);
            model.unitize();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void draw(GL3 gl, Matrix4 transform) {
        transform.bind();
        model.draw();
    }
    
    
    
}
