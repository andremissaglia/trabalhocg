package br.usp.icmc.vicg.projeto.exceptions;

public class MeshNotLoadedException extends RuntimeException{
    String path;
    public MeshNotLoadedException(String path) {
        this.path = path;
    }

    @Override
    public String getMessage() {
        return "Mesh not loaded: "+ path;
    }
    
}
