package Objects;
import Objects.*;
import FrameWork.GameObject;
import FrameWork.ObjectId;
import FrameWork.Texture;
import PaooGame.GameWindow.Animation;
import PaooGame.GameWindow.Camera;
import PaooGame.GameWindow.Game;
import PaooGame.GameWindow.ObjectHandler;

import java.awt.*;
import java.sql.SQLException;
import java.util.IllegalFormatWidthException;
import java.util.LinkedList;
import java.util.logging.Handler;

public class Player extends GameObject {
    //viata
    private int Health = 60;//vom incrementa acest numar distribuind 300 la fiecare viata pt ca colez sa nu fie instanta

    //pentru gravitatie
    private float gravitiy = 0.5f;
    private final float MAX_vel = 10;
    private ObjectHandler handler;

    //pentru animatie
    private Animation player_walk_left;
    private  Animation player_walk_right;//pentru mers in dreapta
    private boolean last_facing = false;//false-stanga true-dreapta
    private int platforme_vel = 0;//pentru a opri animstia de run cat e pe platforma
    //camera
    private Camera camera;//initializare camera
    Texture tex  =  Game.getInstance();

    //o lista de observers
    private LinkedList<GameObject> observers = new LinkedList<>();

    public Player(float x, float y,ObjectHandler handler, ObjectId id,int w,int h,Camera cam) {
        super(x, y, id,w,h);
        this.camera = cam;
        this.handler = handler;
        player_walk_right = new Animation(2,tex.mikey[6],tex.mikey[7],tex.mikey[8]);
        player_walk_left = new Animation(2,tex.mikey[3],tex.mikey[4],tex.mikey[5]);
        //initializare observeri
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
                        if(aux.moving == true)
                        {
                            if(this.x_vel == 0 || this.x_vel == -tempObject.getVelX()) {
                                this.setVelX(tempObject.getVelX());
                            }
                            this.platforme_vel = (int)tempObject.getVelX();//pentru a vedea daca ne deplasam sau nu pe platf
                        }
                        //System.out.println("BOT COLLUSION");
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
                };
                break;
                case Finish:{
                    //dam clear la toate obiectele din nivelul curent
                    if(getBounds().intersects(tempObject.getBounds()))//coleziune cu finish line-ul
                    //handler.Clear_LVL();
                    {
                        Game.Score += Coin.Score;//adaugam toate monezile colectate pe parcurs

                        Game.LVL++;//trecere la nivelul urmator
                        Game.Next_lvl = true;
                        System.out.println(Game.LVL);
                        handler.select_Lvl();//ii pun eu un parametru sau cv in functie de progres
                    }
                }
                break;
                case Coin:{
                    {
                        if(getBounds().intersects(tempObject.getBounds())) {
                            Coin tempObject1 = (Coin) tempObject;//cope locala
                            tempObject1.Score++;//incrementam scorul
                            object.remove(tempObject);//il scoatem din lista
                            System.out.println(tempObject1.Score);
                        }
                    }
                }
                break;
                case Fire:{
                    if(getBounds().intersects(tempObject.getBounds())){//daca intersecteza focul
                        Fire temp = (Fire)tempObject;
                        Health -=  temp.Damage;//decrementam vietile? cum sa fac sa decrementez o singura viati
                    }
                    //this.Notify("ice_cake");//doar incercare
                }
                break;
                case IceCake:{
                    if(getBounds().intersects(tempObject.getBounds())) {
                        Collectable tempObject1 = (Collectable) tempObject;//cope locala
                        object.remove(tempObject);//il scoatem din lista
                        try {
                            this.Notify("ice_cake");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("s-a colectat");
                    }
                }
                break;
                case Projectile:{//se verifica coordonatele si daca sunt mai mari decat caera se sterge
                    if((tempObject.getX()> (this.getX()+ 1000 )) ||(tempObject.getX()< (this.getX()-500 ))){
                        object.remove(tempObject);//se elimina proiectilul
                        tempObject = null;
                    }
                }
                break;
                case PumpkinHead:{
                    if(getBounds().intersects(tempObject.getBounds())){//daca intersecteza focul
                        Health --;
                        Health--;
                    }
                }
                break;
            }

        }
    }
    @Override
    public void tick(LinkedList<GameObject> object) {
        this.x +=this.x_vel;
        this.y +=this.y_vel;
        if(falling || jumping)
        {
            if(y_vel < MAX_vel)
            {
              y_vel += gravitiy;//viteza pe orizontala este incrementata cu g*
            }
        }
        Collision(object);
        if(this.Health <= 0)//daca moare
        {
            handler.select_Lvl();//se reseteaza lvl-ul
        }
        //adaugata pe ultima suta de metri pt restatt in caza de fall
        if(this.y > 600)//daca a cazut de pe ecran
        {
            handler.select_Lvl();//daca cade isi da restatrt
        }
        player_walk_left.RunAnimation();
        player_walk_right.RunAnimation();
    }

    @Override
    public void render(Graphics g) {
        //aici ne ocupam de randare viata
        if(x < Game.WIDTH/4) {//inainte de deplasarea camerei
            switch (this.Health/20) {
                case 3: g.drawImage(tex.health_vect[0], 900, (int) 0, null);
                break;
                case 2: g.drawImage(tex.health_vect[0], 900, (int) 0, null);
                    break;
                case 1: g.drawImage(tex.health_vect[1], 900, (int) 0, null);
                break;
                case 0: g.drawImage(tex.health_vect[2], 900, (int) 0, null);
                break;
            }
        }
        else {
            switch (this.Health/20) {
                case 3: g.drawImage(tex.health_vect[0], (int)x-Game.WIDTH/4 + 900,(int)0, null);
                    break;
                case 2: g.drawImage(tex.health_vect[0], (int)x-Game.WIDTH/4 + 900,(int)0, null);
                    break;
                case 1:g.drawImage(tex.health_vect[1], (int)x-Game.WIDTH/4 + 900,(int)0, null);
                    break;
                case 0:g.drawImage(tex.health_vect[2], (int)x-Game.WIDTH/4 + 900,(int)0, null);
                    break;
        }}
        //
        //g.setColor(Color.RED);
        //g.fillRect((int)x, (int)y, (int)this.width,(int)this.height);
         if(x_vel == 0 || x_vel == this.platforme_vel){
            if (last_facing == false)//s-a deplasat stanga
            {
                g.drawImage(tex.mikey[3], (int) x, (int) y, null);
            } else {
                g.drawImage(tex.mikey[6], (int) x, (int) y, null);
            }
        }
         if (this.x_vel != 0) {
                    if (this.x_vel > 0 && this.x_vel > this.platforme_vel) {
                        last_facing = true;//actualizare
                        player_walk_right.DrawAnimation(g, (int) x, (int) y);
                    } else if (this.x_vel < 0 && this.x_vel < this.platforme_vel) {
                        last_facing = false;//acutualizare
                        player_walk_left.DrawAnimation(g, (int) x, (int) y);
                    }
                }
        Graphics2D g2 = ((Graphics2D) g);//pentru a tesata colez
        //g2.setColor(Color.white);
        //g2.draw(getBounds());
        //g2.draw(getTopRect());
        //g2.draw(getBottomRect());
        //g2.draw(getRightRect());
        //g2.draw(getLeftRect());
    }
    @Override
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
    //piece of code for OBSERVER method where Player is the subject
    public void Attach(GameObject observer)
    {
        observers.add(observer);
    }
    public void Detach(GameObject observer)
    {
        observers.remove(observer);
    }
    public void Notify(String state) throws InterruptedException//funtie de notificare a observerilor
    {
        //parcurgere si aplel notify cu parametrul state care descrie o stare
        for(int i=0;i<observers.size();i++)
        {
            observers.get(i).update(state);//apelez metoda
        }
    }
    public void thick2d (){
        this.x +=this.x_vel;
        this.y +=this.y_vel;
        player_walk_left.RunAnimation();
        player_walk_right.RunAnimation();
    }
}
