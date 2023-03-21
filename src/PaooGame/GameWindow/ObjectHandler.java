package PaooGame.GameWindow;

import FrameWork.GameObject;
import FrameWork.ObjectId;
import FrameWork.Texture;
import Objects.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static PaooGame.GameWindow.Game.*;

public class ObjectHandler {
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    //lista de obiecte care pot fi randate si updated
    private Camera cam;
    Texture tex  =  Game.getInstance();//de refacut
    public ObjectHandler(Camera cam)
    {
        this.cam = cam;

    }

    private GameObject tempObject;//variabila aux cu care trecem prim toate obiectele

    public void tick()
    {
        if(this.object.size() == 1)
        {
            Player player = (Player) this.object.get(0);
            player.thick2d();
        }
        else {
            for (int i = 0; i < object.size(); i++) {
                tempObject = object.get(i);
                tempObject.tick(object);
            }
        }
    }
    public void render(Graphics graph)
    {
        List<GameObject> lista_playeri = new ArrayList<GameObject>();
        List<GameObject> fire_list = new ArrayList<GameObject>();
        List<GameObject> cloud_list = new ArrayList<GameObject>();
        for(int i = 0; i < object.size(); i ++)//vom face o schema sa ne puna totusi playerul desupra
        {//si focul deasupra la player
            tempObject = object.get(i);
            if (tempObject.getId() == ObjectId.Nori) {
                cloud_list.add(tempObject);
            } else {
                if (tempObject.getId() == ObjectId.Player) {
                    lista_playeri.add(tempObject);
                } else if (tempObject.getId() == ObjectId.Fire) {
                    fire_list.add(tempObject);
                } else {
                    tempObject.render(graph);
                }
            }
        }
            for (int i = 0; i < lista_playeri.size(); i++) {
                tempObject = lista_playeri.get(i);
                tempObject.render(graph);
            }
            for (int i = 0; i < fire_list.size(); i++) {
                tempObject = fire_list.get(i);
                tempObject.render(graph);
            }
            for (int i = 0; i < cloud_list.size(); i++) {
                tempObject = cloud_list.get(i);
                tempObject.render(graph);//norii se randeaza primii
            }
    }
    private void Clear_LVL(){
        object.clear();
    }
    public void addObject(GameObject obj)
    {
        this.object.add(obj);
    }
    public void removeObject(GameObject obj)
    {
        this.object.remove(obj);
    }

   /* public void creteLevel()
    {
        for(int xx = 0; xx < 20000; xx +=32)//Game.WIDTH+32
        {
            addObject(new Block(xx, Game.HEIGHT-32, ObjectId.Block));
        }
        for(int yy = 0; yy < Game.HEIGHT+32; yy +=32)
        {
            System.out.println("Blocur");
            addObject(new Block(0,yy,ObjectId.Block));
        }
        /*for(int yy = 0; yy < Game.HEIGHT+32; yy +=32)
        {
            System.out.println("Blocur");
            addObject(new Block(Game.WIDTH-32,yy,ObjectId.Block));
        }
        for(int xx = 200; xx < 600; xx +=32)
        {
            addObject(new Block(xx, 400, ObjectId.Block));
        }
    }*/
   public void Load_Level_from_Image(BufferedImage image)//ii vom trimite o imagine si ne face nivelul
   {
       int w = image.getWidth();
       int h = image.getHeight();//parametrii imaginii

       for(int X = 0; X < h; X++){//parcurgerea imaginii...este luat fiecrae pixel
           for(int Y = 0; Y < w;Y ++){
               int pixel = image.getRGB(X,Y);//extrage informatii legtae de descompunerea rgb a pixelilor
               int red = (pixel>>16) & 0xff;
               int green = (pixel>>8) & 0xff;
               int blue = (pixel) & 0xff;

               if (red == 255 && green == 255 && blue == 255)//culoare alba pur este caracterizata de asta
               {
                   addObject(new Block(X*32,Y*32,0,ObjectId.Block,32,32));
               }
               if (red == 0 && green == 255 && blue == 0)//culoare folosita pt tile uri cu iabra
               {
                   addObject(new Block(X*32,Y*32,1,ObjectId.Block,32,32));
               }
               if(red == 100 && green == 70 && blue == 70)//platforma de tip block lvl 2
               {
                    addObject(new Block(X*32,Y*32,2,ObjectId.Block,129,13));//platforma e lunga
               }
               if (red == 0 && green == 0 && blue == 255)//culoare alba pur este caracterizata de asta
               {
                   addObject(new Player((float) (X*32),(float)Y*32,this,ObjectId.Player,43,54,this.cam));
               }
               if(red == 255 && green == 30 && blue == 30)
               {
                   //System.out.println("FOC");
                   addObject(new Finish(X*32,Y*32,ObjectId.Finish));
               }
               if(red == 255 && green == 0 && blue == 0)//avem foc
               {
                   //System.out.println("FOC");
                   addObject(new Fire(X*32,Y*32 + 12,ObjectId.Fire,32,95));//pentru un pit de 2 casute
               }
               if(red == 255 && green == 255 && blue==0)//avem moneda
               {
                   addObject(new Coin(X *32,Y*32,ObjectId.Coin,20,20));
               }
               if(red == 100 && green == 100 && blue== 100 )
               {
                   addObject(new TextBox(X*32,Y*32,ObjectId.Text,160,64));
               }
               if(red == 150 && green == 255 && blue== 255)
               {
                   BlueHairedGirl instance_of_blue = new BlueHairedGirl(X*32,Y*32+5,ObjectId.BlueHairedGirl,50,92);
                   instance_of_blue.Load_Lines("C:\\Users\\Maria\\IdeaProjects\\InviziMaze\\lines1.txt");
                   addObject(instance_of_blue);
               }
               if(red == 255 && green ==106 && blue == 0)//desenez copac
               {
                    addObject(new Plante(X*32,Y*32,ObjectId.Planta,64,64,0));
                   //pentru 0 la 7 casute desupre ultimului tile
               }
               if(red == 255 && green ==66 && blue==0)//deseneaza tufa
               {
                   addObject(new Plante(X*32,Y*32,ObjectId.Planta,64,64,1));
               }
               if(red == 50 && green == 50 && blue == 50)
               {
                   addObject(new Nori_animati(X*32,Y*32 - 135,ObjectId.Nori,64,64));//dimens nu cont
               }
               if(red == 120 && green == 200 && blue == 255)
               {
                   addObject(new Collectable(X*32,Y*32,ObjectId.IceCake,68,40));
               }
               if(red == 0 && green ==200 && blue ==0)
               {
                   Block block = new Block(X*32,Y*32,1,ObjectId.Block,32,32);
                   block.set_movement(2,1000);
                   addObject(block);
               }
               if(red == 255 && green ==100 && blue ==0)//avem un dovleac
               {
                   addObject(new PumpkinHead(X*32,Y*32,this,ObjectId.PumpkinHead,43,54,120,4));
               }
               if(red == 255 && green == 178 && blue == 127)
               {
                   addObject(new Plante(X*32,Y*32,ObjectId.Planta,68,40,2));
               }
               if(red == 214 && green == 127 && blue == 255)//agatatoare
               {
                   addObject(new Plante(X*32,Y*32,ObjectId.Planta,68,40,3));
               }
               if(red == 255 && green == 0 && blue == 220)//trandafir lvl 2
               {
                   addObject(new Plante(X*32,Y*32,ObjectId.Planta,68,40,4));
               }
           }
       }//dupa ce am incarcat obiectele
       for(int i = 0;i< object.size();i++)
       {
           if(object.get(i).getId() == ObjectId.Player)//daca este player ii atasam toate celselalte
           {
               Player player = (Player) object.get(i);
               for(int j = 0;j<object.size();j++)
               {
                   player.Attach(object.get(j));
               }
           }
       }
   }
   public void select_Lvl()
   {
       Clear_LVL();
       cam.setX(0);


        switch (LVL)
        {
            case 1:
                Game.Score = 0;//
                Coin.Score = 0;//resetam staic score la 0
                Load_Level_from_Image(level);
                System.out.println("->A fost incarcat lvl 1");
                System.out.println(object.size());
                break;
            case 2:
                try {
                    Game.GetScoreIntoDB(Game.Score);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    Game.GetLVLIntoDB(2);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Load_Level_from_Image(level2);
                System.out.println("->A fost incarcat lvl 2");
                break;

        }


   }

}
