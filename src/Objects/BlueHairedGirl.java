package Objects;
import FrameWork.GameObject;
import FrameWork.ObjectId;
import FrameWork.Texture;
import PaooGame.GameWindow.Animation;
import PaooGame.GameWindow.Game;

import java.awt.*;
import java.awt.image.BufferedImageOp;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BlueHairedGirl extends GameObject{
    Texture tex  =  Game.getInstance();
    private Animation BlueHairedGirl_idle;//animatia de stat pe loc

    //o lista de stringuri cu replicile
    public int curent_line = 0;
    private List<String> lines = new ArrayList<String>();//o lista cu replicile incarcate
    public int MAX_LINES = 0;

    public BlueHairedGirl(float x, float y, ObjectId id, int width, int height) {
        super(x, y, id, width, height);
        BlueHairedGirl_idle = new Animation(3,tex.blue_haired_girl[0],tex.blue_haired_girl[1],tex.blue_haired_girl[2],tex.blue_haired_girl[3], tex.blue_haired_girl[4],tex.blue_haired_girl[5]);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        BlueHairedGirl_idle.RunAnimation();
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = ((Graphics2D) g);//pentru a tesata colez
        g2.setColor(Color.white);
        //g2.draw(getBounds());
        BlueHairedGirl_idle.DrawAnimation(g,(int)this.x, (int)this.y);
        //vom avea un textbox cu dialogul aferent fiecarui caracter cu care se intalneste protagonistul
        Graphics2D g_tex = ((Graphics2D) g);
        g_tex.setColor(Color.white);
        //g_tex.fillRect((int)this.x-30,(int)this.y-60,100,30);
        g_tex.drawImage(tex.dialog_box[0],(int)this.x-175,(int)this.y+155,null);
        g_tex.setColor(Color.white);
        g_tex.setFont(g.getFont().deriveFont(15f));
        g_tex.drawString(lines.get(curent_line), (int)this.x-145,(int)this.y +185);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, this.width, this.height);
    }
    public void Load_Lines(String path)//o metoda provizorie de a aduga "in graba replicile pt primul lvl"
    {
        System.out.println(path);
        try {
            FileInputStream fis = new FileInputStream(path);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine())
            {
                //System.out.println(sc.nextLine());
                MAX_LINES++;
                lines.add(sc.nextLine());
            }
            sc.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
