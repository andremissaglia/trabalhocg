package br.usp.icmc.vicg.projeto.game.components;

import br.usp.icmc.vicg.projeto.engine.core.Component;
import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.engine.graphics.Camera;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import br.usp.icmc.vicg.projeto.engine.core.GameCore;
import br.usp.icmc.vicg.projeto.engine.math.Quaternion;
import br.usp.icmc.vicg.projeto.engine.math.Vector3;
import javax.media.opengl.GL3;

public class CameraFollow extends Component{
    private Camera camera;
    private Vector3 position;
    Matrix4 mat;
    public CameraFollow(Objeto objeto) {
        super(objeto);
        position = new Vector3(0, 0.3f, 2.5f);
        mat = new Matrix4();
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        this.camera = GameCore.getGame().getCurrentScene().getCamera();
    }

    @Override
    public void update() {
//        mat.loadIdentity();
//        mat.translate(objeto.position.x, objeto.position.y, objeto.position.z);
//        mat.multiply(objeto.rotation.getMatrix());
//        mat.translate(position.x, position.y, position.z);
//        mat.invert();
//        camera.setViewMatrix(mat);
    }
    
}
