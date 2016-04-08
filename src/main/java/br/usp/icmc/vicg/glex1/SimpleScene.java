package br.usp.icmc.vicg.glex1;

import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.model.SimpleModel;
import br.usp.icmc.vicg.gl.model.Sphere;
import br.usp.icmc.vicg.gl.model.WiredCube;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.gl.util.ShaderFactory;
import javax.media.opengl.GL;
import javax.media.opengl.GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

/**
 *
 * @author andre
 */
public class SimpleScene implements GLEventListener {
    private Shader shader;
    private SimpleModel cube;
    private SimpleModel sphere;
    private Matrix4 modelMatrix;
    private float delta;
    private float inc;
    public SimpleScene(){
        shader = ShaderFactory.getInstance(ShaderFactory.ShaderType.MODEL_MATRIX_SHADER);
        cube = new WiredCube();
        sphere = new Sphere();;
        modelMatrix = new Matrix4();
        delta = 0.8f;
        inc = 0.00f;
    }
    @Override
    public void init(GLAutoDrawable glad) {
        GL3 gl = glad.getGL().getGL3();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        
        shader.init(gl);
        shader.bind();
        
        cube.init(gl, shader);
        sphere.init(gl, shader);
        modelMatrix.init(gl, shader.getUniformLocation("u_modelMatrix"));
    }

    @Override
    public void display(GLAutoDrawable glad) {
        GL3 gl = glad.getGL().getGL3();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        modelMatrix.loadIdentity();
        modelMatrix.translate(0.5f, 0.5f, 0.0f);
        modelMatrix.rotate(33, 1, 1, 0);
        modelMatrix.scale(0.25f, 0.25f, 0.25f);
        modelMatrix.bind();
        
        cube.bind();
        cube.draw();
        
        //obj2
        modelMatrix.loadIdentity();
        modelMatrix.translate(0.5f, 0.5f, 0.0f);
        modelMatrix.rotate(33, 1, 1, 0);
        modelMatrix.scale(0.125f, 0.125f, 0.125f);
        modelMatrix.bind();
        
        sphere.bind();
        sphere.draw();
        
        delta += inc;
        
        if(delta < 0){
            inc *=-0.95;
        } else {
            inc -= 0.005f;
        }
        //obj3
        modelMatrix.loadIdentity();
        modelMatrix.translate(0.0f, delta, 0.0f);
        modelMatrix.translate(0.5f, -0.5f, 0.0f);
        modelMatrix.rotate(33, 1, 1, 0);
        modelMatrix.scale(0.125f, 0.125f, 0.125f);
        modelMatrix.bind();
        
        sphere.bind();
        sphere.draw();
    }
    
    @Override
    public void dispose(GLAutoDrawable glad) {

    }
    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
    }

}
