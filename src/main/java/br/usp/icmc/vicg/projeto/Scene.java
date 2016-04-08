/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.icmc.vicg.projeto;

import br.usp.icmc.vicg.gl.matrix.Matrix4;
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
public class Scene implements GLEventListener{
    private Objeto root;
    private Matrix4 modelMatrix;
    private Shader shader;

    @Override
    public void init(GLAutoDrawable glad) {
        GL3 gl = glad.getGL().getGL3();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        shader = ShaderFactory.getInstance(ShaderFactory.ShaderType.MODEL_MATRIX_SHADER);
        shader.init(gl);
        shader.bind();
        
        root = new Bola();
        root.init(gl, shader);
        modelMatrix = new Matrix4();
        modelMatrix.init(gl, shader.getUniformLocation("u_modelMatrix"));
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
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
    }
    
}
