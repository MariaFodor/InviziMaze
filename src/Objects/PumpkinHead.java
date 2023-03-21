package Objects;

import FrameWork.GameObject;
import FrameWork.ObjectId;
import FrameWork.Texture;
import PaooGame.GameWindow.Animation;
import PaooGame.GameWindow.Game;
import PaooGame.GameWindow.ObjectHandler;

import java.awt.*;
import java.util.LinkedList;

public class PumpkinHead extends GameObject {
    //viata
    private int Health = 20;//vom decrementa acest numar la fiecare coleziune cu un obiect de tip proiectil

    //pentru gravitatie
    private float gravitiy = 0.5f;
    private final float MAX_vel = 10;
    private ObjectHandler handler;

    //distanta pe care merge
    private int distance = 0;
    private int start = 0;
    //pentru animatie
    private Animation enemy_walk_left;
    private  Animation enemy_walk_right;//pentru mers in dreapta
    private boolean last_facing = false;//false-stanga true-dreapta
    Texture tex  =  Game.getInstance();
    public PumpkinHead(float x, float y, ObjectHandler handler, ObjectId id, int w, int h,int distance,int velocoty) {
        super(x, y, id,w,h);
        this.x_vel = velocoty;
        this.start = (int)this.getX();
        this.distance = distance;
        this.handler = handler;
        enemy_walk_right = new Animation(2,tex.pumpkin_head[0],tex.pumpkin_head[1],tex.pumpkin_head[2]);//modificata
        //enemy_walk_left = new Animation(2,tex.mikey[6],tex.mikey[7],tex.mikey[8]);//a se schimba
        enemy_walk_left = new Animation(2, tex.pumpkin_head[3],tex.pumpkin_head[4],tex.pumpkin_head[5]);
        //enemy_walk_right = new Animation(2,tex.mikey[3],tex.mikey[4],tex.mikey[5]);
        //initializare observeri
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if(this.Health <= 0)
        {
            this.handler.removeObject(this);
        }
        this.x +=this.x_vel;
        if(this.getX() > (this.start + this.distance) || this.getX() < (this.start) )
        {
            this.setVelX(-this.x_vel);//se schimba directia de mers
        }
        Collision(object);
        enemy_walk_left.RunAnimation();
        enemy_walk_right.RunAnimation();
    }
    private void Collision(LinkedList<GameObject> object)//primeste o lista cu restul obiceteleor
    {
        for(int i = 0; i < handler.object.size();i++)
        {
            GameObject tempObject =  handler.object.get(i);//obiectul curent
            switch(tempObject.getId())
            {
                case Block: {
                    if(this.getBottomRect().intersects(tempObject.getBounds()))//daca am coleziune joc coleziune
                    {
                        this.setY(tempObject.getY() - this.height);//rezolvare bug loop reaction...il lipim de bloc
                        this.setVelY(0);
                        falling =(false);
                        jumping =(false);
                        Block aux = (Block) tempObject;
                    }
                    else
                    {
                        this.falling = true;
                    }
                    if(this.getTopRect().intersects(tempObject.getBounds()))//coleziune sus
                    {
                        setVelY(0);//viteza  = 0
                        falling = (true);//obiectul cade
                        jumping = (true);//pentru a nu se putea "atasa de tavan"
                        setY(tempObject.getY() + tempObject.getBounds().height);//cred ca este ok
                        //System.out.println("TOP COLLUSION");
                    }
                    if(this.getRightRect().intersects(tempObject.getBounds()))//coleziune sus
                    {
                        setVelX(0);//viteza  = 0
                        setX(tempObject.getX() -  this.width);//cred ca este ok
                        //System.out.println("Right COLLUSION");
                    }
                    if(this.getLeftRect().intersects(tempObject.getBounds()))//coleziune sus
                    {
                        setVelX(0);//viteza  = 0
                        setX(tempObject.getX() + tempObject.getBounds().width);//cred ca este ok
                        //System.out.println("Right COLLUSION");
                    }
                }
                break;
                case Projectile:{
                    if(this.getBounds().intersects(tempObject.getBounds())) {
                        this.Health--;//decrementam viata
                        System.out.println(this.Health);
                    }
                }break;
            }

        }
    }
    @Override
    public void render(Graphics g) {
        if(x_vel == 0){
            if (last_facing == false)//s-a deplasat stanga
            {
                g.drawImage(tex.mikey[3], (int) x, (int) y, null);
            } else {
                g.drawImage(tex.mikey[6], (int) x, (int) y, null);
            }
        }
        if (this.x_vel != 0) {
            if (this.x_vel > 0 ) {
                last_facing = true;//actualizare
                enemy_walk_left.DrawAnimation(g, (int) x, (int) y);
            } else if (this.x_vel < 0) {
                last_facing = false;//acutualizare
                enemy_walk_right.DrawAnimation(g, (int) x, (int) y);
            }
        }
    }
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)this.width,(int)this.height);
    }
    public Rectangle getTopRect() {
        return new Rectangle((int) ((int)x + (width/4)), (int)y, (int) ((int)this.width-this.width/2),(int)this.height/2);
    }
    public Rectangle getBottomRect() {
        return new Rectangle((int) ((int)x + width/4), (int) ((int)y + height/2), (int) ((int)this.width-this.width/2),(int)this.height/2);
    }
    public Rectangle getLeftRect() {
        return new Rectangle((int)x , (int)y + 5,5,(int)this.height-10);
    }
    public Rectangle getRightRect() {
        return new Rectangle((int) ((int)x + width - 5), (int)y + 5, 5,(int)this.height-10);
    }
    public boolean GetFacing(){return this.last_facing;}//va returna false pt stanga si true pt dreapta


}
