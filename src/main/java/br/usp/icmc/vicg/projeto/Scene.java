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
    private final Shader shader;
    private Camera camera;
    private static Scene scene;
    public Scene(){
        shader = ShaderFactory.getInstance(ShaderFactory.ShaderType.VIEW_MODEL_PROJECTION_MATRIX_SHADER);
        root = new Objeto();
        root.addChild(new Nave());
        root.addChild(new Bola());
        this.camera = new Camera();
        
    }
    public static Scene getScene(){
        return scene;
    }
    public Camera getCamera(){
        return this.camera;
    }
    @Override
    public void init(GLAutoDrawable glad) {
        GL3 gl = glad.getGL().getGL3();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        Scene.scene = this;
        //Inicializa os Shaders
        shader.init(gl);
        
        //ativa os Shaders
        shader.bind();
        
        camera.init(gl, shader);
        
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
        
        camera.draw(gl, root);
        
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                camera.rollup();
                break;
            case KeyEvent.VK_DOWN:
                camera.rolldown();
                break;
            case KeyEvent.VK_LEFT:
                camera.rollleft();
                break;
            case KeyEvent.VK_RIGHT:
                camera.rollright();
                break;
        }
    }
}
