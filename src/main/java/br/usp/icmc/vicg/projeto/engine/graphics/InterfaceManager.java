package br.usp.icmc.vicg.projeto.engine.graphics;

import br.usp.icmc.vicg.projeto.engine.core.GameCore;
import br.usp.icmc.vicg.projeto.engine.core.Scene;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class InterfaceManager{
    GLCanvas canvas;
    InterfaceScene iScene;

    public InterfaceManager() {
        iScene = new InterfaceScene();
    }
    public void setScene(Scene scene){
        iScene.setScene(scene);
    }
    public void init(){
        // Get GL3 profile (to work with OpenGL 4.0)
        GLProfile profile= GLProfile.get(GLProfile.GL3);
        
        // Configurations
        GLCapabilities cap = new GLCapabilities(profile);
        cap.setDoubleBuffered(true);
        cap.setHardwareAccelerated(true);
        
        // Create canvas
        canvas = new GLCanvas(cap);
        
        //Add listener
        canvas.addGLEventListener(iScene);
        
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.addKeyListener(GameCore.getGame().getInput());
        frame.getContentPane().add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void draw(){
        canvas.display();
    }
}
