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
    
    protected float rx;
    protected float ry;
    protected float rz;
    
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
        
        this.rx = 0;
        this.ry = 0;
        this.rz = 0;
        
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
        if(rx != 0) objTransform.rotate(rx, 1, 0, 0);
        if(ry != 0) objTransform.rotate(ry, 0, 1, 0);
        if(rz != 0) objTransform.rotate(rz, 0, 0, 1);
        if(model != null){
            objTransform.bind();
            model.bind();
            model.draw();
        }
        for(Objeto filho : filhos){
            filho.draw(gl, transform);
        }
        
    }
}
