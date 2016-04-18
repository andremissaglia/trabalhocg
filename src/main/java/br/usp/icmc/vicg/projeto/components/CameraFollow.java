package br.usp.icmc.vicg.projeto.components;

import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.Camera;
import br.usp.icmc.vicg.projeto.Objeto;
import br.usp.icmc.vicg.projeto.Scene;
import javax.media.opengl.GL3;

public class CameraFollow extends Component{
    private Camera camera;
    public CameraFollow(Objeto objeto) {
        super(objeto);
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        this.camera = Scene.getScene().getCamera();
    }
    
    @Override
    public void draw(GL3 gl, Matrix4 transform) {
        transform.invert();
        this.camera.setViewMatrix(transform);
    }
}
