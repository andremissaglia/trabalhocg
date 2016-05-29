package br.usp.icmc.vicg.projeto;
import com.jogamp.opengl.util.AnimatorBase;
import com.jogamp.opengl.util.FPSAnimator;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class Main {
    
    public static void main(String[] args) {
        // Get GL3 profile (to work with OpenGL 4.0)
        GLProfile profile= GLProfile.get(GLProfile.GL3);
        
        // Configurations
        GLCapabilities cap = new GLCapabilities(profile);
        cap.setDoubleBuffered(true);
        cap.setHardwareAccelerated(true);
        
        // Create canvas
        GLCanvas canvas = new GLCanvas(cap);
        
        //Add listener
        GameCore scene = new GameCore();
        canvas.addGLEventListener(scene);
        
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.addKeyListener(scene);
        frame.getContentPane().add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        AnimatorBase animator = new FPSAnimator(canvas, 30);
        animator.start();
    }
}
