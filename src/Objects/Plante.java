package Objects;

import FrameWork.GameObject;
import FrameWork.ObjectId;
import FrameWork.Texture;
import PaooGame.GameWindow.Animation;
import PaooGame.GameWindow.Game;

import java.awt.*;
import java.util.LinkedList;

public class Plante extends GameObject {
    Texture tex  =  Game.getInstance();
    private int Indice_textura;
    public Plante(float x, float y, ObjectId id, int width, int height,int indice_textura) {
        super(x, y, id, width, height);
        this.Indice_textura = indice_textura;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tex.planta[this.Indice_textura],(int) this.x, (int) this.y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) super.x, (int) super.y, this.width, this.height);
    }
}
