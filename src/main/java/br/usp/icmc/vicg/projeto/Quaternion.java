package br.usp.icmc.vicg.projeto;

import br.usp.icmc.vicg.gl.matrix.Matrix4;

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
    public Quaternion(double x, double y, double z, double w) {
        this.x = (float) x;
        this.y = (float) y;
        this.z = (float) z;
        this.w = (float) w;
    }
    public Quaternion(Vector3 vector){
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
        this.w = 0;
    }
    public static Quaternion getRotation(float vx, float vy, float vz, float theta){
        Quaternion q = new Quaternion(vx*Math.sin(theta/2), vy*Math.sin(theta/2), vz*Math.sin(theta/2), Math.cos(theta/2));
        q.normalize();
        return q;
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
         float w = this.w*r.w - this.x*r.x - this.y*r.y - this.z*r.z;
         float x = this.w*r.x + this.x*r.w + this.y*r.z - this.z*r.y;
         float y = this.w*r.y - this.x*r.z + this.y*r.w + this.z*r.x;
         float z = this.w*r.z + this.x*r.y - this.y*r.x + this.z*r.w;
        return new Quaternion(x,y,z,w);
    }
    public void divide(float a){
        x /= a;
        y /= a;
        z /= a;
        w /= a;
    }
    public float norm(){
        return (float) Math.sqrt(x*x+y*y+z*z+w*w);
    }
    public float norm2(){
        return x*x+y*y+z*z+w*w;
    }
    public Quaternion conjugate(){
        return new Quaternion(-x, -y, -z, w);
    }
    public Matrix4 getMatrix(){
        float mat[] = new float[16];
        mat[0]=1-2*y*y-2*z*z;
        mat[1]=2*x*y+2*z*w;
        mat[2]=2*x*z-2*y*w;
        mat[3]=0;
        mat[4]=2*x*y-2*z*w;
        mat[5]=1-2*x*x-2*z*z;
        mat[6]=2*y*z+2*x*w;
        mat[7]=0;
        mat[8]=2*x*z+2*y*w;
        mat[9]=2*y*z-2*x*w;
        mat[10]=1-2*x*x-2*y*y;
        mat[11]=0;
        mat[12]=0;
        mat[13]=0;
        mat[14]=0;
        mat[15]=1;
        return new Matrix4(mat);
    }
    public void normalize(){
        this.divide(this.norm());
    }
    public Vector3 asVec(){
        return new Vector3(x, y, z);
    }
    public static Vector3 rotate(Vector3 point, Quaternion rotation){
        Quaternion p = (new Quaternion(point));
        Quaternion res = rotation.multiply(p).multiply(rotation.conjugate());
        return res.asVec();
    }
}
