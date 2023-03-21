package PaooGame.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {
    private int speed;//cat de repede
    private int frames;//cate imagini

    private int index = 0;
    private int count = 0;

    private BufferedImage[] images;//toate imaginile de animat
    private BufferedImage currentImg;//imaginea curenta

    public Animation (int speed,BufferedImage... ip)//ip = vector cu ipostazele carcaterului
    {
        this.speed = speed;
        images = new BufferedImage[ip.length];
        for (int i = 0; i < ip.length;i++)
        {
            this.images[i] = ip[i];
        }
        frames = ip.length;
    }

    public void RunAnimation(){
        index++;
        if(index > speed)//cu cat speed va fi mai mare cu atat animatia va fi mai lenta...
            //se trece la urmatorul frame mai lent
        {
            index = 0;
            nextFrame();
        }
    }
    private void nextFrame()
    {
        for(int i = 0; i < frames; i++)
        {
            if(count == i)
            {
                currentImg = images[i];//ceea ce face codul acesta este defapt sa selecteze imaginea la care suntem
            }
        }
        count ++;
        if(count > frames){count = 0;}
    }

    public void DrawAnimation(Graphics gr,int x, int y)
    {
        gr.drawImage(currentImg,x,y,null);//scale la min 7
    }
}

