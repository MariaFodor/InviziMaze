package PaooGame.GameWindow;

import FrameWork.Input;
import FrameWork.ObjectId;
import FrameWork.SprieteSheet;
import FrameWork.Texture;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.sql.SQLException;

public class InGame implements State{
    private Game game;//obiectul a carei stari o va si modifica
    public InGame(Game game){
        System.out.println(this.game.LVL);
        this.game = game;

        if(game.LVL == 1) {
            game.texture = new Texture(1);//se va citi primul rand//primul set de texturi
        }
        if (game.LVL == 2){
            game.texture = new Texture(2);
        }
        game.imageLoader = new BufferedImageLoader();

        game.level = game.imageLoader.loadImage("/TesteLevel.png");
        game.level2 = game.imageLoader.loadImage("/TestLevel2.png");
        game.Day_Sky = game.imageLoader.loadImage("/Background_1.png");//incarc fundalul
        game.Night_Sky = game.imageLoader.loadImage("/Night_sky.png");//incarca funcalul ptlvl 2
        game.Layer_1_anim1 = game.imageLoader.loadImage("/Layer 1 anim1.png");//primul layer
        //redimesionare pe mapa a inaginilor
        SprieteSheet image_redim = new SprieteSheet(game.Layer_1_anim1);
        game.Layer_1_anim1 = image_redim.grab_image(0,0,game.Layer_1_anim1.getWidth(), game.Layer_1_anim1.getHeight(), 2*game.Layer_1_anim1.getWidth(),2*game.Layer_1_anim1.getHeight());
        //imagini meniuri

        game.cam = new Camera(0,0,0);//de unde este initalizata camera
        game.handler = new ObjectHandler(game.cam);


        // handler.Load_Level_from_Image(level);
        // ca sa citeasca inputul
        this.game.addKeyListener(new Input(this.game));//!!!pentru citit input

        //****************************************************************
        //incarcare de nivel
        this.game.handler.select_Lvl();
        //this.game.handler.Load_Level_from_Image(this.game.level);
        //
        //incarcare de mwniuri
    }
    public void Init(){
        System.out.println("ceva");
    }
    @Override
    public void Draw() {
        /*BufferStrategy bs = game.getBufferStrategy();
        if(bs == null)
        {
            /// Se executa doar la primul apel al metodei Draw()
            try
            {
                /// Se construieste tripul buffer
                game.createBufferStrategy(3);//tiple buffering
                return;
            }
            catch (Exception e)
            {
                /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }*/
        Graphics g = game.bs.getDrawGraphics();//get grephics context
        Graphics g_1 = game.bs.getDrawGraphics();//a 2-a strategie
        //g_1.drawRect(20,20,20,20);
        Graphics2D g2 = (Graphics2D) g;//cast pentru a folosi camera

        //vreificare a metodei translate
        //Graphics g_test = bs.getDrawGraphics();
        //g_test.setColor(Color.white);
        //
        //////////////////////////////////////Draw here//////////////////
        //g.setColor(Color.BLACK);
        //g.fillRect(0,0,getWidth(),getHeight());
        //g.drawImage(imageLoader.loadImage("/Background_1.png"),0,0,null);
        //g_test.fillRect(200,200,32,32);
        //g.drawImage(Layer_1_anim1,bx,350,null);g.drawImage(Layer_1_anim1,bx2, 350,null);//2 imag in cont
        //bx --;bx2--;
        if(this.game.LVL == 1) {
            for (int xx = 0; xx < 2224; xx += game.Day_Sky.getWidth()) {
                //g.drawImage(Layer_1_anim1,0,0,null);
                g.drawImage(game.Day_Sky, 0, 0, null);//desen background
                g.drawImage(game.Layer_1_anim1, 0, 88, null);
                //g2.drawImage(Nori_1,0,290,null);
            }
        }
        else {
            for (int xx = 0; xx < 2224; xx += game.Night_Sky.getWidth()) {
                //g.drawImage(Layer_1_anim1,0,0,null);
                g.drawImage(game.Night_Sky, 0, 0, null);//desen background
                g.drawImage(game.Night_Sky, 0, 88, null);
                //g2.drawImage(Nori_1,0,290,null);
            }
        }
        //desenam copaci
        //g.drawImage(texture.planta[1],120,120,null);
        //
        g2.translate(game.cam.getX(), game.cam.getY());//begin of cam

        //partea de glisare
        /*
            g.drawImage(Layer_1_anim1,bx,350,null);g.drawImage(Layer_1_anim1,bx2, 350,this);//2 imag in cont
        /*    bx --;bx2--;
            if(bx<-1220){bx = 1220;};
            if(bx2<=-1220){bx2 = 1220;};*/
        game.handler.render(g);

        //

        g2.translate(-game.cam.getX(), -game.cam.getY());//end of cam


        ///////////////////////////////////////////
        g_1.drawImage(game.Nori_1,50,500,game);
        //nori_animati.DrawAnimation(g_1,150,500);
        g2.dispose();
        g.dispose();
        g_1.dispose();
        game.bs.show();
    }

    @Override
    public void Reset() {

    }

    @Override
    public void Update() {
        game.handler.tick();
        if(this.game.Next_lvl == true)
        {
            State state = new Room(this.game);
            this.game.Next_lvl = false;
            this.game.Set_state(state);
        }
        game.nori_animati.RunAnimation();
        for(int i = 0; i< game.handler.object.size();i++) {
            if(game.handler.object.get(i).getId() == ObjectId.Player)
                game.cam.tick((game.handler.object.get(i)));//asta face update ul la coordonatele din camera
        }
    }
}
