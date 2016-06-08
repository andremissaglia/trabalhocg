package br.usp.icmc.vicg.projeto.engine.graphics;

import br.usp.icmc.vicg.gl.jwavefront.JWavefrontObject;
import br.usp.icmc.vicg.gl.model.SimpleModel;
import br.usp.icmc.vicg.gl.model.Sphere;
import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.engine.exceptions.MeshNotLoadedException;
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
        meshes.put("./data/sun/sun.obj", new JWavefrontObject(new File("./data/sun/sun.obj")));
        meshes.put("./data/terra/terra.obj", new JWavefrontObject(new File("./data/terra/terra.obj")));
        meshes.put("./data/meteorHuge/meteorHuge.obj", new JWavefrontObject(new File("./data/meteorHuge/meteorHuge.obj")));
        for(Map.Entry<String, SimpleModel> entry : simpleModels.entrySet()){
            entry.getValue().init(gl, shader);
        }
        for(Map.Entry<String, JWavefrontObject> entry : meshes.entrySet()){
            entry.getValue().init(gl, shader);
            entry.getValue().unitize();
        }
    }
    public SimpleModel getSimpleModel(String name){
        SimpleModel obj = simpleModels.get(name);
        if(obj == null){
            throw new MeshNotLoadedException(name);
        }
        return obj;
    }
    public JWavefrontObject getMesh(String path){
        JWavefrontObject obj = meshes.get(path);
        if(obj == null){
            throw new MeshNotLoadedException(path);
        }
        return obj;
    }
}
