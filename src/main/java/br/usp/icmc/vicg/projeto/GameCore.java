/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.icmc.vicg.projeto;

import br.usp.icmc.vicg.gl.core.Light;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.gl.util.ShaderFactory;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

/**
 *
 * @author andre
 */
public class GameCore extends KeyAdapter implements GLEventListener{
    private final Shader shader;
    private final Camera camera;
    private static GameCore scene;
    private final InputManager input;
    private final Light light;
    public static GameCore getScene(){
        return scene;
    }
    
    public GameCore() {
        shader = ShaderFactory.getInstance(ShaderFactory.ShaderType.COMPLETE_SHADER);
        this.input = new InputManager();
        this.camera = new Camera();
        this.camera.addChild(new Nave());
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                for(int k = 0; k < 1;k++){
                    camera.addChild(new Bola(i,j,k));
                }
                
            }
        }
        this.light = new Light();        
    }
    public Camera getCamera(){
        return this.camera;
    }
    public InputManager getInput(){
        return input;
    }
    @Override
    public void init(GLAutoDrawable glad) {
        try {
            GL3 gl = glad.getGL().getGL3();
            gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL3.GL_DEPTH_BUFFER_BIT);
            gl.glEnable(GL.GL_DEPTH_TEST);
            gl.glEnable(GL.GL_CULL_FACE);
            GameCore.scene = this;
            //Inicializa os Shaders
            shader.init(gl);
            
            //ativa os Shaders
            shader.bind();
            
            MeshFactory.getInstance().init(gl, shader);
            camera.init(gl, shader);
            
            
            light.setPosition(new float[]{10, 10, 50, 1.0f});
            light.setAmbientColor(new float[]{0.1f, 0.1f, 0.1f, 1.0f});
            light.setDiffuseColor(new float[]{0.75f, 0.75f, 0.75f, 1.0f});
            light.setSpecularColor(new float[]{0.7f, 0.7f, 0.7f, 1.0f});
            light.init(gl, shader);
        } catch (IOException ex) {
            Logger.getLogger(GameCore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void dispose(GLAutoDrawable glad) {
    }

    @Override
    public void display(GLAutoDrawable glad) {
        GL3 gl = glad.getGL().getGL3();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL3.GL_DEPTH_BUFFER_BIT);
        input.processEvents();
        camera.update();
        light.bind();
        camera.draw(gl);
        
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
        camera.reshape(i2, i3);
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        input.insertEvent(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        input.insertEvent(e);
    }
    
}
