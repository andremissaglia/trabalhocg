package br.usp.icmc.vicg.projeto.engine.graphics;

import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.gl.util.ShaderFactory;
import br.usp.icmc.vicg.projeto.engine.core.GameCore;
import br.usp.icmc.vicg.projeto.engine.core.Scene;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class InterfaceScene implements GLEventListener{
    private final Shader shader;
    private Scene scene;
    public InterfaceScene() {
        shader = ShaderFactory.getInstance(ShaderFactory.ShaderType.MODEL_PROJECTION_MATRIX_SHADER);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    
    @Override
    public void init(GLAutoDrawable glad) {
        try {
            GL3 gl = glad.getGL().getGL3();
            gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL3.GL_DEPTH_BUFFER_BIT);
            gl.glEnable(GL.GL_DEPTH_TEST);
            gl.glEnable(GL.GL_CULL_FACE);
            //Inicializa os Shaders
            shader.init(gl);
            
            //ativa os Shaders
            shader.bind();
            
            MeshFactory.getInstance().init(gl, shader);
            scene.getCamera().init(gl, shader);
//            scene.getLight().init(gl, shader);
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
//        scene.getLight().bind();
        scene.getCamera().draw(gl);
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
        scene.getCamera().reshape(i2, i3);
    }
}
