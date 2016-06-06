package br.usp.icmc.vicg.projeto;

import br.usp.icmc.vicg.gl.jwavefront.JWavefrontObject;
import br.usp.icmc.vicg.gl.model.SimpleModel;
import br.usp.icmc.vicg.gl.model.Sphere;
import br.usp.icmc.vicg.gl.util.Shader;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import javax.media.opengl.GL3;

public class MeshFactory {
    private static MeshFactory obj;
    private MeshFactory(){
        meshes = new TreeMap<>();
        simpleModels = new TreeMap<>();
    }
    public static MeshFactory getInstance(){
        if(obj == null){
            obj = new MeshFactory();
        }
        return obj;
    }
    
    private final TreeMap<String, JWavefrontObject> meshes;
    private final TreeMap<String, SimpleModel> simpleModels;
    
    public void init(GL3 gl, Shader shader) throws IOException{
        simpleModels.put("Sphere", new Sphere());
        meshes.put("./data/xwing/xwing.obj", new JWavefrontObject(new File("./data/xwing/xwing.obj")));
        meshes.put("./data/apples.obj", new JWavefrontObject(new File("./data/apples.obj")));
        for(Map.Entry<String, SimpleModel> entry : simpleModels.entrySet()){
            entry.getValue().init(gl, shader);
        }
        for(Map.Entry<String, JWavefrontObject> entry : meshes.entrySet()){
            entry.getValue().init(gl, shader);
            entry.getValue().unitize();
        }
    }
    public SimpleModel getSimpleModel(String name){
        return simpleModels.get(name);
    }
    public JWavefrontObject getMesh(String path){
        return meshes.get(path);
    }
}