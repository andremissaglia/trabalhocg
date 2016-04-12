package br.usp.icmc.vicg.projeto;

public class Quaternion {
    private float x;
    private float y;
    private float z;
    private float w;

    public Quaternion(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    public Quaternion sum(Quaternion rval){
        return new Quaternion(x+rval.x, y+rval.y, z+rval.z, w+rval.w);
    }
    public Quaternion subtract(Quaternion rval){
        return new Quaternion(x-rval.x, y-rval.y, z-rval.z, w-rval.w);
    }
    public Quaternion multiply(float a){
        return new Quaternion(a*x, a*y, a*z, a*w);
    }
    public Quaternion multiply(Quaternion r){
         float x = this.w*r.x + this.x*r.w + this.y*r.z + this.z*r.z;
         float y = this.w*r.y + this.x*r.z + this.y*r.w + this.z*r.y;
         float z = this.w*r.z + this.x*r.y + this.y*r.x + this.z*r.x;
         float w = this.w*r.w + this.x*r.x + this.y*r.y + this.z*r.w;
        return new Quaternion(x,y,z,w);
    }
    public Quaternion divide(float a){
        return new Quaternion(x/a, y/a, z/a, w/a);
    }
    public float norm(){
        return (float) Math.sqrt(x*x+y*y+z*z+w*w);
    }
    public float norm2(){
        return x*x+y*y+z*z+w*w;
    }
    public Quaternion conjugate(){
        return new Quaternion(x, -y, -z, -w);
    }
    
    
}
