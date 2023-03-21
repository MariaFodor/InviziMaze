package PaooGame.GameWindow;

import FrameWork.Input;
import FrameWork.ObjectId;
import FrameWork.SprieteSheet;
import FrameWork.Texture;
import Objects.Player;

import java.awt.*;

public class Room implements State{
    private Game game;
    private Player player;
    public Room(Game game){
        this.game = game;
        this.game.cam = new Camera(0,0,0);
        this.game.handler = new ObjectHandler(this.game.cam);
        this.game.addKeyListener(new Input(this.game));
        //chestii ce trebuiesc initializate pentru a adauga un amarat de player


        game.texture = new Texture(1);//se va citi primul rand//primul set de texturi
        this.player = new Player(500,500,this.game.handler,ObjectId.Player,43,54,this.game.cam);
        this.game.handler.addObject(player);
        this.game.texture =  new Texture(1);//fac un set numa cu player
        this.game.cam = new Camera(0,0,0);
        this.game.imageLoader = new BufferedImageLoader();


        this.game.room = this.game.imageLoader.loadImage("/BedRoom.png");//incarc fundalul
    }//initializare
    @Override
    public void Init() {
        //this.game.cam = new Camera(0,0,0);//hmm in caz de eroare

    }

    @Override
    public void Draw() {
        Graphics g = game.bs.getDrawGraphics();//get grephics context
        Font fnt = new Font("Times New Roman",Font.ITALIC,15);
        g.setFont(fnt);
        g.setColor(Color.black);
        g.fillRect(0,0,1000,600);

        SprieteSheet image_redim = new SprieteSheet(game.room);
        game.room = image_redim.grab_image(0,0,game.room.getWidth(), game.room.getHeight(), 600,600);
        g.drawImage(this.game.room,200,0,null);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("->Press enter near the bed",0,15);
        g.drawString("to go to sleep",0,30);
        g.drawString("->Press esc to go to GAME ",800,15);
        g.drawString("MENU and save the progress",800,30);
        this.game.handler.render(g);
        g.dispose();
        game.bs.show();
    }

    @Override
    public void Update() {
        System.out.println(this.player.getX()+" "+this.player.getY());
        game.handler.tick();
        if(this.player.getX() < 345){this.player.setX(345);}
        if(this.player.getX() > 660){this.player.setX(660);}
        if(this.player.getY() < 315){this.player.setY(315);}
        if(this.player.getY()>485){this.player.setY(485);}

    }

    @Override
    public void Reset() {

    }
}
