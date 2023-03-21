package PaooGame.GameWindow;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BufferedImageLoader {
    private BufferedImage image;

    public BufferedImage loadImage(String path)//se vor da si dimensinile dorite ale imaginii de incarcat
    {
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            System.out.println("Nu a putut fi incarcata o imaginie deoarece clasa este gresita:"+e);
        }catch (IllegalArgumentException e){
            System.out.println("Nu a putut fi incarcata o imaginie deoarece clasa este gresita:"+e);
        }
        return image;
    }
}
