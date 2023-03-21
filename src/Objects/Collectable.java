package Objects;

import FrameWork.GameObject;
import FrameWork.ObjectId;
import FrameWork.Texture;
import PaooGame.GameWindow.Animation;
import PaooGame.GameWindow.Game;
import java.awt.*;
import java.util.LinkedList;

import static FrameWork.ObjectId.IceCake;

public class Collectable extends GameObject {
    Texture tex  =  Game.getInstance();
    public Collectable(float x, float y, ObjectId id, int width, int height) {
        super(x, y, id, width, height);//in funtie de id se va randa alata imagine
    }
    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        switch (this.id) {
            case IceCake: g.drawImage(tex.item[0], (int) this.x, (int) this.y, null);
            //se vor mai adauga iteme
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, this.width, this.height);
    }
}
