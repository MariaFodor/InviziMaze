package Objects;
import FrameWork.GameObject;
import FrameWork.ObjectId;
import FrameWork.Texture;
import PaooGame.GameWindow.Animation;
//import java.time.LocalTime;

import java.awt.*;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import PaooGame.GameWindow.Animation;
import PaooGame.GameWindow.Game;


public class Fire extends GameObject {
    private long start;//pentru a masura timpul de la dezactivarea focului
    //Animatia la foc
    public int Damage = 1;

    //private int last_act_time = 0;
    private  String state = "on";
    private  Animation fire_loop ;
    Texture tex  =  Game.getInstance();
    public Fire(float x, float y, ObjectId id, int width, int height) {
        super(x, y, id, width, height);
        fire_loop = new Animation(2,tex.red_fire[0],tex.red_fire[1],tex.red_fire[2],tex.red_fire[3],tex.red_fire[4],tex.red_fire[5],tex.red_fire[6],tex.red_fire[7]);
       // player_walk_left = new Animation(2,tex.mikey[3],tex.mikey[4],tex.mikey[5]);
    }
    @Override
    public void update(String state) throws InterruptedException {
        if(state == "ice_cake"){
            start = System.currentTimeMillis();
            this.state = "off";//nu mai este activ focul
            this.Damage = 0;
            System.out.println("S-a actualizat");
        }
    }
    @Override
    public void tick(LinkedList<GameObject> object) {
        fire_loop.RunAnimation();//initializam animatia

        if(this.state == "off")//astept 5-10 sec si actualizez la starea anterioara
        {

            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            if (timeElapsed >= 10000){//daca s-au scurs 10 secunde
                this.state = "on";
                this.Damage = 1;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if(this.state == "on") {
            fire_loop.DrawAnimation(g, (int) x, (int) y);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y,width,height);
    }
}
