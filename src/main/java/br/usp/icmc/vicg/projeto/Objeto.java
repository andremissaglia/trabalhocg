package br.usp.icmc.vicg.projeto;

import br.usp.icmc.vicg.projeto.components.Component;
import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.model.SimpleModel;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.gl.util.ShaderFactory;
import java.util.ArrayList;
import javax.media.opengl.GL3;

public class Objeto {
    protected float tx;
    protected float ty;
    protected float tz;
    
    protected float sx;
    protected float sy;
    protected float sz;
    
    protected Quaternion rotation;
    
    private ArrayList<Component> components;
    protected ArrayList<Objeto> filhos;
    
    private Objeto father;

    public Objeto() {
        this.filhos = new ArrayList<>();
        this.components = new ArrayList<>();
        
        this.tx = 0;
        this.ty = 0;
        this.tz = 0;
        
        this.sx = 1;
        this.sy = 1;
        this.sz = 1;
    }
    
    public void init(GL3 gl, Shader shader){
        for(Objeto filho : filhos){
            filho.init(gl, shader);
        }
        
        this.rotation = Quaternion.getRotation(0, 0, 0, 1);
        for(Component c : components){
            c.init(gl, shader);
        }
    }
    public void addChild(Objeto obj){
        filhos.add(obj);
        obj.father = this;
    }
    public void addComponent(Component c){
        components.add(c);
    }
    public void update(){
        for(Component c : components){
            c.update();
        }
        for(Objeto filho : filhos){
            filho.update();
        }
    }
    public void draw(GL3 gl, Matrix4 transform){
        Matrix4 objTransform = new Matrix4(transform);
        objTransform.translate(tx, ty, tz);
        objTransform.scale(sx, sy, sz);
        objTransform.multiply(this.rotation.getMatrix());
        for(Component c : components){
            c.draw(gl, objTransform);
        }
        for(Objeto filho : filhos){
            filho.draw(gl, objTransform);
        }
        
    }
    public void rotate(float x, float y, float z, float theta){
        Quaternion r = Quaternion.getRotation(x, y, z, (float) Math.toRadians(theta));
        this.rotation = r.multiply(this.rotation);
    }
//    public void 
}
