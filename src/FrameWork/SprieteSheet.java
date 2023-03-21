package FrameWork;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SprieteSheet {
    private BufferedImage image;
   // static int Last_x = 0;
   // static int Last_y= 0;//seteaza coltutile pt imaginea urmatoare
  //  static int row = 0;
   //static int column = 0;//vom retine la ce elem am ajuns
   // private static int Last_height;
    public SprieteSheet(BufferedImage image)
    {
        if(image == null)//daca imaginea nu este preluat bine
        {
            throw new NullPointerException("Nu a fost oferita o imagine existenta cu o cale existenta, deci avem nullpointer");
        }
        this.image = image;
    }

    public BufferedImage grab_image (int x,int y,int width, int height,int wanted_width, int waned_height) throws NullPointerException//metoda care ne extrage un tile din tile sheet
    {
        //BufferedImage img_tile = image.getSubimage(((column - 1) * width),((row - 1) * height),width,height);

        BufferedImage img_tile = image.getSubimage(x,y,width,height);
        BufferedImage resized_img = new BufferedImage(wanted_width,waned_height,img_tile.getType());//creare noua imagine de dat cu resize

        Graphics2D g2d = resized_img.createGraphics();
        g2d.drawImage(img_tile,0,0,wanted_width,waned_height,null);//scalare
        g2d.dispose();
        //Last_height = height;
        //modificam colturile pt imaginea urmatore
        //Last_x += width;//x trebuie actualizat la fiecare y trebuie resetat la rand nou si y inc
        //Last_y += height
       // column ++;//inc coloana
        return resized_img;
    }
    //public void Inc_row(){row++;column = 0;Last_y = Last_y+ Last_height;Last_x= 0;};//trecere la rand nou
    //public void Inc_colum(){column++;}//trecere la coloana noua
}
