package PaooGame.GameWindow;

import FrameWork.Input;
import FrameWork.MouseInput;
import FrameWork.SprieteSheet;
import FrameWork.Texture;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Main_Menu implements State{
    private Game game;

    public Main_Menu(Game game){
        this.game = game;
    }


    @Override
    public void Init() {
        this.game.imageLoader = new BufferedImageLoader();
        this.game.meniu1 = this.game.imageLoader.loadImage("/Back-ground-Mennu.png");//incarc fundalul
        this.game.addMouseListener(new MouseInput(this.game));
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


        SprieteSheet image_redim = new SprieteSheet(game.meniu1);
        game.meniu1 = image_redim.grab_image(0,0,game.meniu1.getWidth(), game.meniu1.getHeight(), 1000,600);
        g.drawImage(this.game.meniu1,0,0,null);
        //g.drawString("INVIZI MAZE", this.game.getWidth()  / 2 - 170, 100);
        g.dispose();
        this.game.bs.show();
    }

    @Override
    public void Update() {

    }
}
