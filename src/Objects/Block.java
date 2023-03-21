package Objects;


import FrameWork.*;
import PaooGame.GameWindow.Game;

import java.awt.*;
import java.util.LinkedList;

public class Block extends GameObject {
    Texture texture = Game.getInstance();
    private int type;

    private int[] bounds = new int[2];
    public Boolean moving  = false;

    public Block(float x, float y ,int type, ObjectId id,int w,int h)
    {
        super(x,y,id,w,h);
        this.type = type;
        try {
            validate(type);
        } catch (InvalidTileTypeException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Rectangle getBounds() {
        Rectangle rec = new Rectangle((int)x,(int)y,this.width,this.height);;
        return  rec;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if(moving == true)
        {
            if(this.x > this.bounds[1] || this.x < this.bounds[0])
            {
                this.x_vel = -this.x_vel;
            }
            this.x  += this.x_vel;
        }
    }
    public void set_movement(int speed, int dist)
    {
        this.bounds[0] = (int) this.x;
        this.bounds[1] = (int) (this.x + dist);
        this.x_vel = speed;
        this.moving = true;
    }
    @Override
    public void render(Graphics g) {
        //*********TEXTURILE FOLOSITE PT A VEDEA COLEXIUNILE**********//
        //g.setColor(Color.white);
        //g.drawRect((int)x,(int)y,super.width,super.height);
        //******************************
        if(type == 0)
        {
            g.drawImage(texture.list[0],(int)x,(int)y,null);
        }
        if (type == 1)
        {
            g.drawImage(texture.list[1],(int)x,(int)y,null);
        }
        if(type == 2)
        {
            g.drawImage(texture.list[2],(int)x,(int)y,null);
        }
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public float getVelX() {
        return this.x_vel;
    }

    @Override
    public float getVelY() {
        return y_vel;
    }

    @Override
    public void setVelX(float x_vel) {
        this.x_vel = x_vel;
    }

    @Override
    public void setVelY(float y_vel) {
        this.y_vel = y_vel;
    }

    @Override
    public ObjectId getId() {
        return id;
    }
    void validate(int type) throws InvalidTileTypeException {
        if (type >= texture.list.length)//aici avem cate tiouri de tile uri exista
            throw new InvalidTileTypeException("S-a dat ca argument un tile mai mare decat numarul maxim aceptat");
    }
}
//definesc o calsa de ecceptie custom care sa-mi valideze tipul
class InvalidTileTypeException extends Exception {
    InvalidTileTypeException(String s){
        super(s);
    }
}


