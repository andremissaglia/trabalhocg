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
import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;

/**
 *
 * @author andre
 */
public class Scene extends KeyAdapter implements GLEventListener{
    private final Objeto root;
    private final Shader shader;
    private Camera camera;
    private static Scene scene;
    private ArrayList<KeyEventListener> keyboardListeners;
    public static Scene getScene(){
        return scene;
    }
    public void addKeyboardListener(KeyEventListener eventListener){
        keyboardListeners.add(eventListener);
    }
    public Scene() {
        shader = ShaderFactory.getInstance(ShaderFactory.ShaderType.VIEW_MODEL_PROJECTION_MATRIX_SHADER);
        root = new Objeto();
        root.addChild(new Nave());
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                for(int k = 0; k < 10;k++){
                    root.addChild(new Bola(i,j,k));
                }
                
            }
        }
//        
        this.camera = new Camera();
        this.keyboardListeners = new ArrayList<>();
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
        for(KeyEventListener kel : keyboardListeners){
            kel.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for(KeyEventListener kel : keyboardListeners){
            kel.keyReleased(e);
        }
    }
    
}
