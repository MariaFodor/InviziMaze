package Objects;

import FrameWork.GameObject;
import FrameWork.ObjectId;
import FrameWork.Texture;
import PaooGame.GameWindow.Game;

import java.awt.*;
import java.util.LinkedList;

public class Projectile extends GameObject {
    Texture tex  =  Game.getInstance();
    public Projectile(float x, float y, ObjectId id, int width, int height, int x_vel) {
        super(x, y, id, width, height);
        this.x_vel = x_vel;
    }
    private void Collision(LinkedList<GameObject> object){
        for(int i = 0; i < object.size();i++)
        {
            GameObject temp = object.get(i);//obiectull de indic i din lista
            if(this.getBounds().intersects(temp.getBounds()) && ((temp.getId() == ObjectId.PumpkinHead) || temp.getId()== ObjectId.Block)){
                object.remove(this);
            }
        }
    }
    @Override
    public void tick(LinkedList<GameObject> object) {
        Collision(object);
        x += x_vel;
    }

    @Override
    public void render(Graphics g) {
       g.drawImage((Image) tex.proiectil, (int) this.x, (int) this.y,null);//si iau proiectilu...
       // g.setColor(Color.RED);
       // g.fillRect((int)x,(int)y,16,16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y,16,16);
    }
}
