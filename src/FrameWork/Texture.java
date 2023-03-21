package FrameWork;

import PaooGame.GameWindow.BufferedImageLoader;

import java.awt.image.BufferedImage;

public class Texture {//texture loader
    SprieteSheet spite;
    //private BufferedImage earth_sheet = null;
    //private BufferedImage player_sheet = null;
    public BufferedImage full_sheet = null;

    public BufferedImage[] list = new BufferedImage[3];//vom pune imaginile cate 6 pe rand
    public BufferedImage[] mikey = new BufferedImage[9];//vom avea imaginile pentru animatia lui mikey
    public BufferedImage[] red_fire = new BufferedImage[8];//avem 8 imagini pentru animatia focului
    public BufferedImage[] coin_loop = new BufferedImage[3];//imaginile cu moneda care se roteste
    public BufferedImage[] health_vect = new BufferedImage[3];//imaginile cu cele 3 vieti
    public BufferedImage[] blue_haired_girl = new BufferedImage[6];//imaginile cu fata cu par albastru
    public BufferedImage[] dialog_box = new BufferedImage[2];//vom avea doar 2 casute de dialog
    public BufferedImage[] nori_jos = new BufferedImage[2];//norii din layer 4
    public BufferedImage[] planta = new BufferedImage[5];//in primul == copac
                                                       //in al 2-lea == tufa
                                                        //in al 3-lea la lvl 2 == copac mic
                                                        //in 4 la lvl2 == planta agatatoare
    public BufferedImage[] item  =  new BufferedImage[100];//punem 100 in caz ca mai adaugam iteme
    //texturile din setul2 pt butoane
    public BufferedImage[] main_menu =  new BufferedImage[4];
    public BufferedImage[] game_menu = new BufferedImage[4];
    //specifice lvl 2
    public BufferedImage[] pumpkin_head = new BufferedImage[6];//6 imagini pt mers stanga-dreapta la inamic
    //finisg-line si proiectile
    public BufferedImage finish;
    public BufferedImage proiectil;

    private int set_index;
    public Texture(int set) {
        this.set_index = set;
        BufferedImageLoader imageLoader = new BufferedImageLoader();
        if (set == 1) {
            try {
                full_sheet = imageLoader.loadImage("/Tile.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
            spite = new SprieteSheet(full_sheet);

            getTextures();
        }
        if(set == 2){
            try {
                full_sheet = imageLoader.loadImage("/Tile2.png");//aici punem alt sprite dar umplem acelessi imagini
            } catch (Exception e) {
                e.printStackTrace();
            }
            spite = new SprieteSheet(full_sheet);

            getTextures_lvl2();//va selecta texturile pt lvl 2
        }
        if(set == 3)//luam texturi pentru butoanele meniurilor
        {
            try {
                full_sheet = imageLoader.loadImage("/Tile.png");//altceva aici pr meniuri
            } catch (Exception e) {
                e.printStackTrace();
            }
            spite = new SprieteSheet(full_sheet);
            get_menu_textures();
        }
    }

    private void getTextures()
    {
        list[0] = spite.grab_image(0,0,32, 32,32,32);
        list[1] = spite.grab_image(32,0,32,32,32,32);


        mikey[0] = spite.grab_image(0,32,43,54,43,54);
        mikey[1] = spite.grab_image(63,32,43,54,43,54);
        mikey[2] = spite.grab_image(127,32,43,54,43,54);

        mikey[3] = spite.grab_image(0,95,43,54,43,54);
        mikey[4] = spite.grab_image(63,95,43,54,43,54);
        mikey[5] = spite.grab_image(127,95,43,54,43,54);

        mikey[6] = spite.grab_image(0,160,43,54,43,54);
        mikey[7] = spite.grab_image(63,157,43,57,43,54);
        mikey[8] = spite.grab_image(127,160,43,54,43,54);
        //de aici se va modifica la dim caract
        //mikey[1] = spite.grab_image(64,64);//frame  for player

        //fire loop
        red_fire[0] = spite.grab_image(179,58,15,30,35,60);//vom lasa scala asa si vom modela din poz
        red_fire[1] = spite.grab_image(201,58,15,30,35,60);
        red_fire[2] = spite.grab_image(226,58,15,30,35,60);
        red_fire[3] = spite.grab_image(247,58,15,30,35,60);
        red_fire[4] = spite.grab_image(275,58,15,30,35,60);
        red_fire[5] = spite.grab_image(297,58,15,30,35,60);
        red_fire[6] = spite.grab_image(320,58,15,30,35,60);
        red_fire[7] = spite.grab_image(346,58,15,30,35,60);

        //coin loop
        coin_loop[0] = spite.grab_image(196,106,11,19,11,19);
        coin_loop[1] = spite.grab_image(193,132,21,22,18,19);//21//22
        coin_loop[2] = spite.grab_image(194,165,15,15,19,19);//15

        //vieti
        health_vect[0] = spite.grab_image(192,192,63,22,63,22);//punem o monedsa ca lene
        health_vect[1] = spite.grab_image(192,223,63,22,63,22);
        health_vect[2] = spite.grab_image(192,250,63,22,63,22);

        //Blue Haired Girl
        blue_haired_girl[0] = spite.grab_image(286,104,28,56,43,92);
        blue_haired_girl[1] = spite.grab_image(286,174,28,56,43,92);
        blue_haired_girl[2] = spite.grab_image(286,243,28,56,43,92);
        blue_haired_girl[3] = spite.grab_image(286,309,28,56,43,92);
        blue_haired_girl[4] = spite.grab_image(286,372,28,60,43,92);
        blue_haired_girl[5] = spite.grab_image(286,441,28,56,40,92);

        //texturi pentru casutele de dialog
        dialog_box[0] = spite.grab_image(170,22,157,31,272,128);
        dialog_box[1] = spite.grab_image(310,22,157,31,272,128);

        //texturi_plante
        planta[0] = spite.grab_image(490,13,49,122,100,244);//nu stiu de la c s-a def spite ul
        planta[1] = spite.grab_image(542,91,74,34,148,68);
        //texturi pentru nori.

        //texturi pentru iteme
        //ice_cake
        item[0] = spite.grab_image(200,282,68,40,68,40);//ice_cake
        item[1] = spite.grab_image(196,106,11,19,11,19);
        item[2] = spite.grab_image(196,106,11,19,11,19);//...

        //texturei pentru fisish si proiectil
        proiectil = spite.grab_image(379,65,18,15,25,25);
        finish = spite.grab_image(321,108,30,37,52,74);
    }
    private void getTextures_lvl2()
    {
        list[0] = spite.grab_image(0,0,32, 32,32,32);
        list[1] = spite.grab_image(32,0,32,32,32,32);//imaginile cu texturi gen pamant
        list[2] = spite.grab_image(452,160,129,25,129,25);//platforma lvl 2
        //plante
        planta[0] = spite.grab_image(458,371,163,212,492,636);//copac mare
        planta[1] = spite.grab_image(443,255,102,30,148,68);//tufa mica
        planta[2] = spite.grab_image(333,187,83,96,249,288);//copac mic
        planta[3] = spite.grab_image(326,110,23,46,46,64);//planta agatatoare
        planta[4] =spite.grab_image(326,157,51,29,102,58);//trandafir

        //
        mikey[0] = spite.grab_image(0,32,43,54,43,54);
        mikey[1] = spite.grab_image(63,32,43,54,43,54);
        mikey[2] = spite.grab_image(127,32,43,54,43,54);

        mikey[3] = spite.grab_image(0,95,43,54,43,54);
        mikey[4] = spite.grab_image(63,95,43,54,43,54);
        mikey[5] = spite.grab_image(127,95,43,54,43,54);

        mikey[6] = spite.grab_image(0,160,43,54,43,54);
        mikey[7] = spite.grab_image(63,157,43,57,43,54);
        mikey[8] = spite.grab_image(127,160,43,54,43,54);
        //de aici se va modifica la dim caract
        //mikey[1] = spite.grab_image(64,64);//frame  for player

        //fire loop
        red_fire[0] = spite.grab_image(179,58,15,30,35,60);//vom lasa scala asa si vom modela din poz
        red_fire[1] = spite.grab_image(201,58,15,30,35,60);
        red_fire[2] = spite.grab_image(226,58,15,30,35,60);
        red_fire[3] = spite.grab_image(247,58,15,30,35,60);
        red_fire[4] = spite.grab_image(275,58,15,30,35,60);
        red_fire[5] = spite.grab_image(297,58,15,30,35,60);
        red_fire[6] = spite.grab_image(320,58,15,30,35,60);
        red_fire[7] = spite.grab_image(346,58,15,30,35,60);

        //coin loop
        coin_loop[0] = spite.grab_image(196,106,11,19,11,19);
        coin_loop[1] = spite.grab_image(193,132,21,22,18,19);//21//22
        coin_loop[2] = spite.grab_image(194,165,15,15,19,19);//15

        //vieti
        health_vect[0] = spite.grab_image(192,192,63,22,63,22);//punem o monedsa ca lene
        health_vect[1] = spite.grab_image(192,223,63,22,63,22);
        health_vect[2] = spite.grab_image(192,250,63,22,63,22);

        //Blue Haired Girl
        blue_haired_girl[0] = spite.grab_image(286,104,28,56,43,92);
        blue_haired_girl[1] = spite.grab_image(286,174,28,56,43,92);
        blue_haired_girl[2] = spite.grab_image(286,243,28,56,43,92);
        blue_haired_girl[3] = spite.grab_image(286,309,28,56,43,92);
        blue_haired_girl[4] = spite.grab_image(286,372,28,60,43,92);
        blue_haired_girl[5] = spite.grab_image(286,441,28,56,40,92);

        //texturi pentru casutele de dialog
        dialog_box[0] = spite.grab_image(170,22,157,31,272,128);
        dialog_box[1] = spite.grab_image(310,22,157,31,272,128);



        //texturi pentru iteme
        //ice_cake
        item[0] = spite.grab_image(198,282,51,42,40,40);//ice_cake
        item[1] = spite.grab_image(196,106,11,19,11,19);
        item[2] = spite.grab_image(196,106,11,19,11,19);//...

        //texturei pentru inamici
        pumpkin_head[0] = spite.grab_image(0,289,48,56,43,54);
        pumpkin_head[1] = spite.grab_image(59,287,48,60,43,54);
        pumpkin_head[2] = spite.grab_image(117,288,49,57,43,54);
        pumpkin_head[3] = spite.grab_image(4,358,46,56,43,54);
        pumpkin_head[4] = spite.grab_image(60,356,46,57,43,54);
        pumpkin_head[5] = spite.grab_image(115,359,47,55,43,54);

        //mai am
        proiectil = spite.grab_image(378,65,19,15,25,25);
        finish = spite.grab_image(413,62,19,37,19,37);

    }
    private void get_menu_textures()
    {
        //astea vor fi modificate
        main_menu[0] = spite.grab_image(179,58,15,30,35,60);
        main_menu[1] = spite.grab_image(179,58,15,30,35,60);
        main_menu[2] = spite.grab_image(179,58,15,30,35,60);
        main_menu[3] = spite.grab_image(179,58,15,30,35,60);

        game_menu[0] = spite.grab_image(179,58,15,30,35,60);
        game_menu[1] = spite.grab_image(179,58,15,30,35,60);
        game_menu[2] = spite.grab_image(179,58,15,30,35,60);
        game_menu[3] = spite.grab_image(179,58,15,30,35,60);

    }

}
