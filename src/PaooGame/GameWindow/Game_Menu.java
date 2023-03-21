package PaooGame.GameWindow;

import FrameWork.MouseInput;
import FrameWork.SprieteSheet;

import java.awt.*;

public class Game_Menu implements State{

    private Game game;

    public Game_Menu(Game game){
        this.game = game;
        this.game.imageLoader = new BufferedImageLoader();
        this.game.meniu2 = this.game.imageLoader.loadImage("/Game-Menu.jpg");//incarc fundalul
        this.game.addMouseListener(new MouseInput(this.game));
    }


    @Override
    public void Init() {
    }

    public void Reset() {
        this.game.imageLoader = null;
    }

    @Override
    public void Draw() {
        Font fnt0 = new Font("Times New Roman", Font.ITALIC, 50);
        Graphics g = this.game.bs.getDrawGraphics();//get grephics context
        Graphics2D g2d = (Graphics2D) g;

        g.setFont(fnt0);
        g.setColor(Color.white);


        SprieteSheet image_redim = new SprieteSheet(game.meniu2);
        game.meniu2 = image_redim.grab_image(0,0,game.meniu2.getWidth(), game.meniu2.getHeight(), 1000,600);
        g.drawImage(this.game.meniu2,0,0,null);
        //g.drawString("INVIZI MAZE", this.game.getWidth()  / 2 - 170, 100);
        g.dispose();
        g2d.dispose();;
        this.game.bs.show();
    }

    @Override
    public void Update() {

    }
}
