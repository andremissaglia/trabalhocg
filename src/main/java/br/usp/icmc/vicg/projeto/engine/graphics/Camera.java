package br.usp.icmc.vicg.projeto.engine.graphics;

import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import br.usp.icmc.vicg.projeto.engine.math.Vector3;
import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import javax.media.opengl.GL3;

public class Camera extends Objeto {
    private final Matrix4 modelMatrix;
    private final Matrix4 projectionMatrix;
    private final Matrix4 viewMatrix;
    private final FrustumCulling frustumCulling;
    public Camera() {
        super();
        modelMatrix = new Matrix4();
        projectionMatrix = new Matrix4();
        viewMatrix = new Matrix4();
        frustumCulling = new FrustumCulling();
    }
    
    @Override
    public void init(GL3 gl, Shader shader){
        modelMatrix.init(gl, shader.getUniformLocation("u_modelMatrix"));
        projectionMatrix.init(gl, shader.getUniformLocation("u_projectionMatrix"));
        viewMatrix.init(gl, shader.getUniformLocation("u_viewMatrix"));
        
        modelMatrix.loadIdentity();
        
        viewMatrix.loadIdentity();
        viewMatrix.bind();
        frustumCulling.extractFrustum(projectionMatrix, viewMatrix);
        super.init(gl, shader);
    }
    public void draw(GL3 gl){
        viewMatrix.bind();
        super.draw(gl, modelMatrix, this);
    }
    public void setViewMatrix(Matrix4 myViewMatrix){
        viewMatrix.loadIdentity();
        viewMatrix.multiply(myViewMatrix);
        frustumCulling.extractFrustum(projectionMatrix, viewMatrix);
    }
    public boolean isVisible(Vector3 position, float radius){
        return frustumCulling.isSphereInFrustum(position.x, position.y, position.z, radius);
    }
    public void reshape(int width, int height){
        float aspect = (float)width/(float)height;
        projectionMatrix.loadIdentity();
        projectionMatrix.perspective(60.0f, aspect, 0.1f, 20000.0f);
        projectionMatrix.bind();
    }
}
