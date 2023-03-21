package PaooGame.GameWindow;

import FrameWork.Input;
import FrameWork.MouseInput;
import FrameWork.SprieteSheet;

import java.awt.*;

public class Score implements State{

    private Game game;



    public Score(Game game){
        this.game = game;
        this.game.imageLoader = new BufferedImageLoader();
        this.game.score_screen = this.game.imageLoader.loadImage("/Back-Ground-Menu.png");//incarc fundalul
        this.game.addMouseListener(new MouseInput(this.game));
    }
    @Override
    public void Init() {

    }

    @Override
    public void Draw() {
        Font fnt0 = new Font("Times New Roman", Font.ITALIC, 50);
        Font fnt3 = new Font("Times New Roamn", Font.BOLD, 15);
        Graphics g = this.game.bs.getDrawGraphics();//get grephics context
        Graphics2D g2d = (Graphics2D) g;

        g.setFont(fnt0);
        g.setColor(Color.white);


        SprieteSheet image_redim = new SprieteSheet(game.score_screen);
        game.score_screen= image_redim.grab_image(0,0,game.score_screen.getWidth(),game.score_screen.getHeight(), 1000,600);
        g.drawImage(this.game.score_screen,0,0,null);
        g.drawString("Score of current game:", this.game.getWidth()  / 2 - 230, 100);
        int s = Game.Score;//scorul din jocul curent
        Font fnt1 = new Font("Times New Roman", Font.ITALIC, 200);
        g.setFont(fnt3);
        g.drawString("<-Back",0,15);
        g.setFont(fnt1);
        g.drawString(String.valueOf(s),this.game.getWidth()/2 - 70,400);
        g.dispose();
        g2d.dispose();;
        this.game.bs.show();
    }

    @Override
    public void Update() {

    }

    @Override
    public void Reset() {
        this.game.imageLoader = null;
        this.game.score_screen = null;
    }
}
