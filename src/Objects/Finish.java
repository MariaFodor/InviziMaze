package Objects;

import FrameWork.GameObject;
import FrameWork.ObjectId;
import FrameWork.Texture;
import PaooGame.GameWindow.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Finish extends GameObject {
    Texture tex  =  Game.getInstance();
    public Finish(float x, float y, ObjectId id) {
        super((int)x,(int)y, id,32,32);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        x += x_vel;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage((Image) tex.finish, (int) this.x, (int) this.y,null);
        //g.setColor(Color.YELLOW);
       // g.fillRect((int)x,(int)y,32,32);
       // g.drawRect((int)x,(int)y,32,32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y,16,16);
    }
}
