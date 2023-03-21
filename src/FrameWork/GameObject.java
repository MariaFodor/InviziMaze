package FrameWork;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {
    protected float x, y;
    protected float x_vel = 0, y_vel = 0;
    protected ObjectId id;
    //pentru move
    protected boolean falling = true;
    protected boolean jumping = false;
    //
    //dimensiuni
    public int width;
    public int height;//dimensiunile obiectului
    //
    public GameObject(float x, float y,ObjectId id,int width,int height)
    {
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public abstract void tick(LinkedList<GameObject> object);//o folosim la coleziuni
    public abstract void render(Graphics g);//randeaza
    public abstract Rectangle getBounds();//rect care ne va indica dim si poz obj pt coleziuneu

    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public void setX(float x)
    {
        this.x = x;
    }
    public void setY(float y)
    {
        this.y = y;
    }

    public float getVelX(){
        return x_vel;
    }
    public float getVelY()
    {
        return y_vel;
    }
    public void setVelX(float x_vel){
        this.x_vel = x_vel;
    }
    public void setVelY(float y_vel){
        this.y_vel = y_vel;
    }
    public void setJumping(boolean jumping)
    {
        this.jumping = jumping;
    }
    public boolean getJumping()
    {
        return this.jumping;
    }
    public void setFalling(boolean falling)
    {
        this.jumping = falling;
    }
    public boolean getFalling()
    {
        return this.falling;
    }
    public ObjectId getId(){return  id;};


    public void update(String state) throws InterruptedException {
    }
}
