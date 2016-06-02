package br.usp.icmc.vicg.projeto;

import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.util.Shader;
import javax.media.opengl.GL3;

public class Camera extends Objeto {
    private final Matrix4 modelMatrix;
    private final Matrix4 projectionMatrix;
    private final Matrix4 viewMatrix;
    public Camera() {
        modelMatrix = new Matrix4();
        projectionMatrix = new Matrix4();
        viewMatrix = new Matrix4();
    }
    
    @Override
    public void init(GL3 gl, Shader shader){
        modelMatrix.init(gl, shader.getUniformLocation("u_modelMatrix"));
        projectionMatrix.init(gl, shader.getUniformLocation("u_projectionMatrix"));
        viewMatrix.init(gl, shader.getUniformLocation("u_viewMatrix"));
        
        modelMatrix.loadIdentity();
        
        viewMatrix.loadIdentity();
        viewMatrix.bind();
        super.init(gl, shader);
    }
    public void draw(GL3 gl){
        viewMatrix.bind();
        super.draw(gl, modelMatrix);
    }
    public void setViewMatrix(Matrix4 myViewMatrix){
        viewMatrix.loadIdentity();
        viewMatrix.multiply(myViewMatrix);
    }
    public void reshape(int width, int height){
        float aspect = (float)width/(float)height;
        projectionMatrix.loadIdentity();
        projectionMatrix.perspective(45.0f, aspect, 0.1f, 10.0f);
        projectionMatrix.bind();
    }
}
