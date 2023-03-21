package Objects;

import FrameWork.GameObject;
import FrameWork.ObjectId;
import PaooGame.GameWindow.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import PaooGame.GameWindow.BufferedImageLoader;

public class Nori_animati extends GameObject {
    private Animation nori_animati ;
    //Texture tex  =  Game.getInstance();
    public Nori_animati(float x, float y, ObjectId id, int width, int height) {
        super(x, y, id, width, height);
        BufferedImageLoader n = new BufferedImageLoader();
        BufferedImage Nori_1 = n.loadImage("/Layer 3 clound anim1.png");
        BufferedImage Nori_2 =n.loadImage("/Layer 3 clound anim2.png");
        nori_animati = new Animation(6,Nori_1,Nori_2);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        nori_animati.RunAnimation();
    }

    @Override
    public void render(Graphics g) {
        nori_animati.DrawAnimation(g,(int)x,(int)y);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.x, (int) this.y,this.width,this.height);
    }
}
