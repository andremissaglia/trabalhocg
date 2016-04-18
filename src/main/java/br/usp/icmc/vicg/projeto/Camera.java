package br.usp.icmc.vicg.projeto;

import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.util.Shader;
import javax.media.opengl.GL3;

public class Camera {
    private Matrix4 modelMatrix;
    private Matrix4 projectionMatrix;
    private Matrix4 viewMatrix;
    private Quaternion rotation;
    private float x;
    private float y;
    private float z;
    public Camera() {
        modelMatrix = new Matrix4();
        projectionMatrix = new Matrix4();
        viewMatrix = new Matrix4();
        rotation = new Quaternion(0, 0, 0, 1);
        z=-2f;
    }
    
    public void init(GL3 gl, Shader shader){
        
        modelMatrix.init(gl, shader.getUniformLocation("u_modelMatrix"));
        projectionMatrix.init(gl, shader.getUniformLocation("u_projectionMatrix"));
        viewMatrix.init(gl, shader.getUniformLocation("u_viewMatrix"));
        
        //Inicializa o sistema de coordenadas
        projectionMatrix.loadIdentity();
        projectionMatrix.perspective(45.0f, 1.0f, 0.1f, 10.0f);
        projectionMatrix.bind();
        
        viewMatrix.loadIdentity();
        viewMatrix.translate(x, y, z);
        viewMatrix.bind();
    }
    public void draw(GL3 gl, Objeto root){
//        viewMatrix.loadIdentity();
//        viewMatrix.multiply(this.rotation.getMatrix());
//        viewMatrix.translate(x, y, z);
//        viewMatrix.bind();
        root.draw(gl, modelMatrix);
    }
    public void setViewMatrix(Matrix4 myViewMatrix){
        viewMatrix.loadIdentity();
        viewMatrix.multiply(myViewMatrix);
        //viewMatrix.translate(x, y, z);
        viewMatrix.bind();
    }
    public void rollup(){
        Quaternion r = Quaternion.getRotation(1, 0, 0, -0.1f);
        this.rotation = this.rotation.multiply(r);
        this.rotation.normalize();
    }
    public void rolldown(){
        Quaternion r = Quaternion.getRotation(1, 0, 0, 0.1f);
        this.rotation = this.rotation.multiply(r);
        this.rotation.normalize();
    }
    public void rollleft(){
        Quaternion r = Quaternion.getRotation(0, 0, 1, -0.1f);
        this.rotation = this.rotation.multiply(r);
        this.rotation.normalize();
    }
    public void rollright(){
        Quaternion r = Quaternion.getRotation(0, 0, 1, 0.1f);
        this.rotation = this.rotation.multiply(r);
        this.rotation.normalize();
    }
    public void translate(float x, float y, float z){
        this.x += x;
        this.y += y;
        this.z += z;
    }
}
