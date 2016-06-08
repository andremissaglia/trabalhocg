package br.usp.icmc.vicg.projeto.game.components;

import br.usp.icmc.vicg.projeto.engine.core.Component;
import br.usp.icmc.vicg.gl.jwavefront.JWavefrontObject;
import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.engine.graphics.MeshFactory;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import javax.media.opengl.GL3;

public class Mesh extends Component{
    private JWavefrontObject model;
    private final String modelPath;
    public Mesh(Objeto objeto, String path) {
        super(objeto);
        modelPath = path;
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        model = MeshFactory.getInstance().getMesh(modelPath);
    }

    @Override
    public void draw(GL3 gl, Matrix4 transform) {
        transform.bind();
        model.draw();
    }
    
    
    
}
