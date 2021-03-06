package br.usp.icmc.vicg.projeto.engine.core;

import br.usp.icmc.vicg.projeto.engine.graphics.Camera;
import br.usp.icmc.vicg.projeto.engine.math.Quaternion;
import br.usp.icmc.vicg.projeto.engine.math.Vector3;
import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.gl.util.Shader;
import java.util.ArrayList;
import javax.media.opengl.GL3;

public class Objeto {
    public Vector3 position;
    public Vector3 scale;
    
    public Quaternion rotation;
    
    private final ArrayList<Component> components;
    protected ArrayList<Objeto> filhos;
    
    private Objeto father;
    public float boundingSphere;

    public Objeto() {
        this.filhos = new ArrayList<>();
        this.components = new ArrayList<>();
        this.position = new Vector3(0, 0, 0);
        this.scale = new Vector3(1, 1, 1);
        this.rotation = Quaternion.getRotation(0, 0, 0, 1);
        boundingSphere = 1;
    }
    
    public void init(GL3 gl, Shader shader){
        for(Objeto filho : filhos){
            filho.init(gl, shader);
        }
        
        for(Component c : components){
            c.init(gl, shader);
        }
    }
    public final void addChild(Objeto obj){
        filhos.add(obj);
        obj.father = this;
    }
    public final void removeChild(Objeto obj){
        filhos.remove(obj);
    }
    public final ArrayList<Objeto> getChildren(){
        return filhos;
    }
    public Objeto getFather(){
        return father;
    }
    public final void addComponent(Component c){
        components.add(c);
    }
    public void update(){
        for(Component c : components){
            c.update();
        }
        //precisamos clonar o vetor de filhos antes de percorrer porque o 
        //array pode ser modificado no meio do caminho.
        ArrayList<Objeto> filhos2 = new ArrayList<>(filhos); 
        for(Objeto filho : filhos2){
            filho.update();
        }
    }
    public void draw(GL3 gl, Matrix4 transform, Camera camera){
        Matrix4 objTransform = transform(transform);
        if(camera.isVisible(transform.multiply(position), boundingSphere)){
            for(Component c : components){
                c.draw(gl, objTransform);
            }
        }
        for(Objeto filho : filhos){
            filho.draw(gl, objTransform, camera);
        }
        
    }
    public void rotate(float x, float y, float z, float theta){
        Quaternion r = Quaternion.getRotation(x, y, z, (float) Math.toRadians(theta));
        this.rotation = r.multiply(this.rotation);
    }
    public void destroy(){
        father.removeChild(this);
    }
    public Matrix4 transform(Matrix4 transform){
        Matrix4 objTransform = new Matrix4(transform);
        objTransform.translate(position.x, position.y, position.z);
        objTransform.scale(scale.x, scale.y, scale.z);
        objTransform.multiply(this.rotation.getMatrix());
        return objTransform;
    }
}
