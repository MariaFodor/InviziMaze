package Objects;

import FrameWork.GameObject;
import FrameWork.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class TextBox extends GameObject {

    public TextBox(float x, float y, ObjectId id, int width, int height) {
        super(x, y, id, width, height);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = ((Graphics2D) g);//pentru a tesata colez
        g2.setColor(Color.white);
        g2.fillRect((int)this.x,(int)this.y,this.width,this.height);
        g2.setColor(Color.RED);
        g2.setFont(g.getFont().deriveFont(25f));
        g.drawString("A",(int)this.x,(int)this.y + 50);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
