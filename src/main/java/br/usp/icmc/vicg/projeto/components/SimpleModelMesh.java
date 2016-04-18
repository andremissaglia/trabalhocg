package br.usp.icmc.vicg.projeto.components;

import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.model.SimpleModel;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.Objeto;
import javax.media.opengl.GL3;

public class SimpleModelMesh extends Component{
    private SimpleModel model;
    public SimpleModelMesh(Objeto objeto, SimpleModel model) {
        super(objeto);
        this.model = model;
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        model.init(gl, shader);
    }
    
    @Override
    public void draw(GL3 gl, Matrix4 transform) {
        transform.bind();
        model.bind();
        model.draw();
    }
}
