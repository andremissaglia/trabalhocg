package br.usp.icmc.vicg.projeto.game.components;

import br.usp.icmc.vicg.gl.util.Shader;
import br.usp.icmc.vicg.projeto.engine.core.Component;
import br.usp.icmc.vicg.projeto.engine.core.GameCore;
import br.usp.icmc.vicg.projeto.engine.core.Objeto;
import javax.media.opengl.GL3;

public class FollowObject extends Component {
    private Objeto follow;
    public FollowObject(Objeto objeto, Objeto follow) {
        super(objeto);
        this.follow = follow;
    }

    @Override
    public void init(GL3 gl, Shader shader) {
        super.init(gl, shader); 
        GameCore.getGame().getEventManager().addOnEvent("moved", new Runnable() {
            @Override
            public void run() {
                objeto.position = follow.position;
            }
        });
    }
    

}
