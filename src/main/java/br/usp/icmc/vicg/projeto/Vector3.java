package br.usp.icmc.vicg.projeto;

public class Vector3 {
    public float x;
    public float y;
    public float z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public float norm2(){
        return x*x+y*y+z*z;
    }
    public float norm(){
        return (float) Math.sqrt(x*x+y*y+z*z);
    }
    public Vector3 multiply(float alpha){
        return new Vector3(x*alpha, y*alpha, z*alpha);
    }
    public float dot(Vector3 vec){
        return x*vec.x+y*vec.y+z*vec.z;
    }
    public void add(Vector3 vec){
        x += vec.x;
        y += vec.y;
        z += vec.z;
    }
    public void normalize(){
        float mod = norm();
        x /= mod;
        y /= mod;
        z /= mod;
    }
    public Vector3 copy(){
        return new Vector3(x, y, z);
    }
    @Override
    public String toString() {
        return String.format("[%.2f %.2f %.2f]", x,y,z);
    }
}
