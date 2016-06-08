package br.usp.icmc.vicg.projeto.game.components;

import br.usp.icmc.vicg.gl.core.Light;
import br.usp.icmc.vicg.projeto.engine.core.Component;
import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.engine.core.GameCore;
import br.usp.icmc.vicg.projeto.engine.graphics.Camera;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import br.usp.icmc.vicg.projeto.engine.math.Vector3;
import javax.media.opengl.GL3;

public class CameraFollow extends Component implements Runnable{
    private final Camera camera;
    private final Light light;
    private final Vector3 position;
    Matrix4 mat;
    public CameraFollow(Objeto objeto, Camera camera, Light light) {
        super(objeto);
        position = new Vector3(0, 0.3f, 2.5f);
        mat = new Matrix4();
        this.camera = camera;
        this.light = light;
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        super.init(gl, shader); 
        GameCore.getGame().getEventManager().addOnEvent("moved", this);
    }
    
    @Override
    public void run() {
        mat.loadIdentity();
        mat.translate(objeto.position.x, objeto.position.y, objeto.position.z);
        mat.multiply(objeto.rotation.getMatrix());
        mat.translate(position.x, position.y, position.z);
        float vals[] = mat.getVals();
        light.setPosition(new float[]{vals[12], vals[13], vals[14], 1.0f});
        mat.invert();
        camera.setViewMatrix(mat);
    }
    
}
