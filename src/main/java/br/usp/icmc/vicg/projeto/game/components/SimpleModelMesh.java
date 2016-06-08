package br.usp.icmc.vicg.projeto.game.components;

import br.usp.icmc.vicg.projeto.engine.core.Component;
import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.model.SimpleModel;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.engine.graphics.MeshFactory;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import javax.media.opengl.GL3;

public class SimpleModelMesh extends Component{
    private String modelName;
    private SimpleModel model;
    public SimpleModelMesh(Objeto objeto, String modelName) {
        super(objeto);
        this.modelName = modelName;
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        this.model = MeshFactory.getInstance().getSimpleModel(modelName);
    }
    
    @Override
    public void draw(GL3 gl, Matrix4 transform) {
        transform.bind();
        model.bind();
        model.draw();
    }
}
