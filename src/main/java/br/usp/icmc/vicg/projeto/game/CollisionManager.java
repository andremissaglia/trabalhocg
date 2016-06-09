package br.usp.icmc.vicg.projeto.game;

import br.usp.icmc.vicg.gl.matrix.Matrix4;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import br.usp.icmc.vicg.projeto.engine.core.Service;
import br.usp.icmc.vicg.projeto.engine.math.Vector3;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Gerenciador de colisão bem simples.
 */
public class CollisionManager implements Service{
    public static CollisionManager obj;
    private LinkedList<Bullet> bullets;
    //como todos os meteoros estão em um cinturão, fazer deste jeito é mais simples
    private Objeto collidables; 
    public CollisionManager() {
        obj = this;
        this.bullets = new LinkedList<>();
    }
    public void addBullet(Bullet b){
        bullets.add(b);
    }
    public void removeBullet(Bullet b){
        bullets.remove(b);
    }
    public void setCollidables(Objeto obj){
        this.collidables = obj;
    }
    private boolean checkCollisions(Objeto obj, Bullet b, Matrix4 transform){
        transform = obj.transform(transform);
        if(obj instanceof Meteoro){
            Meteoro m = (Meteoro) obj;
            float r = b.boundingSphere+m.boundingSphere;
            Vector3 position = transform.getTranslate();
            if(b.position.dist2(position)<r*r){
                m.destroy();
                b.destroy();
                return true;
            }
        }
        
        boolean collided = false;
        for(Objeto o : obj.getChildren()){
            collided = checkCollisions(o, b, transform);
            if(collided){
                return true;
            }
        }
        return false;
    }
    @Override
    public void update(){
        ListIterator<Bullet> itb = bullets.listIterator();
        while(itb.hasNext()){
            Bullet b = itb.next();
            Matrix4 mat = new Matrix4();
            mat.loadIdentity();
            if(checkCollisions(collidables, b, mat)){
                itb.remove();
            }
        }
    }
}
