/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.icmc.vicg.projeto;

import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.gl.util.ShaderFactory;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

/**
 *
 * @author andre
 */
public class Scene extends KeyAdapter implements GLEventListener{
    private final Objeto root;
    private final Matrix4 modelMatrix;
    private final Matrix4 projectionMatrix;
    private final Matrix4 viewMatrix;
    private final Shader shader;
    
    private float pz;
    private float px;

    public Scene(){
        shader = ShaderFactory.getInstance(ShaderFactory.ShaderType.VIEW_MODEL_PROJECTION_MATRIX_SHADER);
        modelMatrix = new Matrix4();
        root = new Bola();
        projectionMatrix = new Matrix4();
        viewMatrix = new Matrix4();
        pz = 0.2f;
        px = 0.0f;
    }
    
    @Override
    public void init(GLAutoDrawable glad) {
        GL3 gl = glad.getGL().getGL3();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        //Inicializa os Shaders
        shader.init(gl);
        
        //ativa os Shaders
        shader.bind();
        
        //Inicialiaza a matrix Model , View and Projection
        modelMatrix.init(gl, shader.getUniformLocation("u_modelMatrix"));
        projectionMatrix.init(gl, shader.getUniformLocation("u_projectionMatrix"));
        viewMatrix.init(gl, shader.getUniformLocation("u_viewMatrix"));
        
        //Inicializa o sistema de coordenadas
        projectionMatrix.loadIdentity();
        projectionMatrix.perspective(45.0f, 1.0f, 0.1f, 10.0f);
        projectionMatrix.bind();
        
        viewMatrix.loadIdentity();
        viewMatrix.lookAt( px, 0.25f, pz,  //P0
                           0.0f, 0.0f, 0.0f,    //Pref
                           0.0f, 1.0f, 0.0f);   //Vup
        viewMatrix.bind();
        
        //Inicializa os objetos da cena
        root.init(gl, shader);
        
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
    }

    @Override
    public void display(GLAutoDrawable glad) {
        GL3 gl = glad.getGL().getGL3();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        root.update();
        
        root.draw(gl, modelMatrix);
        
        viewMatrix.loadIdentity();
        viewMatrix.lookAt( 0.0f, 0.0f, pz,  //P0
                           px, 0.0f, 0.0f,    //Pref
                           0.0f, 1.0f, 0.0f);   //Vup
        viewMatrix.bind();
        
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                if(pz > 0.6f)
                    pz -= 0.5;
                break;
            case KeyEvent.VK_DOWN:
                if(pz < 9.4f)
                    pz += 0.5;
                break;
            case KeyEvent.VK_LEFT:
                px -= 0.5;
                break;
            case KeyEvent.VK_RIGHT:
                px += 0.5;
                break;
        }
    }
}
