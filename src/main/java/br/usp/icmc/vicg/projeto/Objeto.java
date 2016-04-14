package br.usp.icmc.vicg.projeto;

import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.model.SimpleModel;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.gl.util.ShaderFactory;
import java.util.ArrayList;
import javax.media.opengl.GL3;

public abstract class Objeto {
    private Shader shader;
    protected float tx;
    protected float ty;
    protected float tz;
    
    protected float sx;
    protected float sy;
    protected float sz;
    
    protected Quaternion rotation;
    
    private SimpleModel model;
    protected ArrayList<Objeto> filhos;

    public Objeto() {
        this.filhos = new ArrayList<>();
    }
    
    public void init(GL3 gl, Shader shader){
        for(Objeto filho : filhos){
            filho.init(gl, shader);
        }
        
        this.tx = 0;
        this.ty = 0;
        this.tz = 0;
        
        this.sx = 1;
        this.sy = 1;
        this.sz = 1;
        
        this.rotation = Quaternion.getRotation(0, 0, 0, 1);
        
        model.init(gl, shader);
    }
    protected void setModel(SimpleModel model){
        this.model = model;
    }
    public void addChild(Objeto obj){
        filhos.add(obj);
    }
    public void update(){
        for(Objeto filho : filhos){
            filho.update();
        }
    }
    public void draw(GL3 gl, Matrix4 transform){
        Matrix4 objTransform = new Matrix4(transform);
        objTransform.translate(tx, ty, tz);
        objTransform.scale(sx, sy, sz);
        objTransform.multiply(this.rotation.getMatrix());
        if(model != null){
            objTransform.bind();
            model.bind();
            model.draw();
        }
        for(Objeto filho : filhos){
            filho.draw(gl, objTransform);
        }
        
    }
    protected void rotate(float x, float y, float z, float theta){
        Quaternion r = Quaternion.getRotation(x, y, z, theta);
        this.rotation = this.rotation.multiply(r);
    }
}
