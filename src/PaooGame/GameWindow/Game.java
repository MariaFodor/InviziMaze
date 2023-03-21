package PaooGame.GameWindow;
import FrameWork.*;
import Objects.Block;
import Objects.Finish;
import Objects.Player;


import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.Random;

public class Game extends  Canvas implements Runnable//vom face ca Game sa fie Singleton
{
    public BufferStrategy bs;//strategia de buffering
    static public int Score;
    //muzica
   // public Clip clipGAME = null;
    public int Music;//1-play; 0-nu
    public Sound sound;
    GameWindow window;
    private static Game instance = null;
    //stari pt meniu
    public State state = new Main_Menu(this);//new Room(this);//new Main_Menu(this);//starea initiala

    //tutorial ,menu
    //might del
    Random random  = new Random();//pentru obiecte random
    public static int LVL = 1;//initial nivelul este 1



    private boolean running = false;
    private  Thread thread;

    //variabile pentru coord ecranului
    public static int WIDTH,HEIGHT;
    //next lvl
    public static Boolean Next_lvl = false;//pt a afisa room cand se trece la urm lvl
    //
    //un joc are nevoide de un object handeler care are toate obiectele
    public ObjectHandler handler;
    //
    //camera
    Camera cam;
    //
    //incarcare nivel
    public BufferedImageLoader imageLoader;//cel cu care incarc nivelul
    public static BufferedImage level = null;//pentru lvl
    public static BufferedImage level2 = null;//pentru al 2-lea nivel
    public static BufferedImage Day_Sky = null;//fundaul primul lvl
    public static BufferedImage Layer_1_anim1 = null;//primii munti

    public static BufferedImage Night_Sky  = null;//fundalul pentru lvl 2
    //norii
    public static BufferedImage Nori_1=null;
    public static BufferedImage Nori_2=null;
    //meiu

    public  static  BufferedImage meniu1 = null;//MainMenu
    public static BufferedImage meniu2 = null;//GameMenu
    public static BufferedImage score_screen = null;//Fundalul pentru scor
    //room
    public static  BufferedImage room = null;//imagine cu camera
    //
    public Animation nori_animati = new Animation(2,Nori_1,Nori_2);
    //**********************TEXTURA**************
    public static Texture texture;//texturi pt joc
    private static Texture texture_button;//texturi pt butoane
    //
    GameObject object;

    private Game()  {
        this.Music = ReadMusicFrimDB();//setarile precedente
        this.sound = new Sound();
        this.Score = 0;//initializam scorul cu valoare din db

        this.window = GameWindow.GetInstance(1000,600,"InviziMaze Prorptype", this);
        this.WIDTH = getWidth();
        this.HEIGHT = getHeight();
        if(ReadScoreFromDB()!=0){
            this.Score  = ReadScoreFromDB();
            System.out.println(this.Score);
        }
        if(ReadLVLFromDB() != 0) {//
            this.LVL = ReadLVLFromDB();
            System.out.println(this.LVL+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        };
    }



    public static void Reset(){
        instance = null;
    }
    public static Game GetInstance() throws CloneNotSupportedException {
        if(instance == null)
        {
            instance = new Game();
        }
        return instance;
    }
    public synchronized void start()//syncchronized este legata de threads
    {
        if(running)
            return;
        this.running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run()
    {
        /*URL url = this.getClass().getClassLoader().getResource("muzica.wav");//pentru muzica
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            //clip.start();
            this.sound.playMsuic("muzica.wav");
            
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }//avem sound clip
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
``
*/      if(this.Music == 0) {//daca mute ul este pe 0
        this.sound.playMsuic("src/ress/muzica.wav");
        }
        init();
        this.requestFocus();//?
        this.setFocusable(true);
        /// Initializeaza obiectul game
        long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        long curentTime;                    /*!< Retine timpul curent de executie.*/

        /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
        /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond   = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame      = 1000000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/
        //System.out.print(timeFrame);
        int counter = 0;
        while (this.running == true)
        {
            /// Se obtine timpul curent
            curentTime = System.nanoTime();
            /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if((curentTime - oldTime) > timeFrame)
            {
                /// Actualizeaza pozitiile elementelor
                Update();//--tick
                /// Deseneaza elementele grafica in fereastra.
                counter = (counter+1)%1;
                if(counter==0){Draw();}//--render
                oldTime = curentTime;
                //System.out.println(i++);
            }
        }
        
    }


    private void init()  {//va fi diferita in functie de stare
        this.state.Init();
    }
    private void Draw()
    {
        bs = getBufferStrategy();
        if(bs == null)
        {
            try
            {
                createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        Graphics g = bs.getDrawGraphics();
        state.Draw();

    }
    private void Update()
    {
       state.Update();
    }
    public void Set_state(State state)
    {
        this.state =  state;
    }
    public State Get_state() {
        return this.state;
    }
    public static Texture getInstance()
    {
        return texture;
    }
    public static Texture get_button(){return texture_button;}//pentu a folosi texturil de butoane
    public static void main(String args[]) throws CloneNotSupportedException, SQLException {
        Game game = Game.GetInstance();//era 600
    }


    public void insertScoreIntoDB(int level) throws SQLException {
        Statement stmt = null;
        Connection c = DriverManager.getConnection("jdbc:sqlite:Tabel9.db");
        stmt = c.createStatement();
        //si efectuam update-ul la scor
        c.setAutoCommit(false);
        stmt = c.createStatement();
        String sql = "UPDATE InviziMaze set Levelul = "+level+";";//asta ar trebui sa seteze nou scor
        stmt.execute(sql);
        c.commit();

    }
    public static void GetScoreIntoDB(int score) throws SQLException {
        Statement stmt = null;
        Connection c = DriverManager.getConnection("jdbc:sqlite:Tabel9.db");
        stmt = c.createStatement();
       //si efectuam update-ul la scor
        c.setAutoCommit(false);
        stmt = c.createStatement();
        String sql = "UPDATE InviziMaze set SCORUL = "+score+";";//steaza noul scor
        stmt.execute(sql);
        c.commit();
    }
    public int ReadScoreFromDB(){
        int score = -1;
        Connection c= null;
        Statement stmt = null;
        try{
            c = DriverManager.getConnection("jdbc:sqlite:Tabel9.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM InviziMaze");
            while ( rs.next() ) {
                score = rs.getInt("SCORUL");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }
    public static void GetMusicIntoDB(int mute) throws SQLException {
    Statement stmt = null;
    Connection c = DriverManager.getConnection("jdbc:sqlite:Tabel9.db");
    stmt = c.createStatement();
    //si efectuam update-ul la muzica
    c.setAutoCommit(false);
    stmt = c.createStatement();
    String sql = "UPDATE InviziMaze set MUTE = "+mute+";";//sets score
    stmt.execute(sql);
    c.commit();
}
    public static void GetLVLIntoDB(int score) throws SQLException {
        Statement stmt = null;
        Connection c = DriverManager.getConnection("jdbc:sqlite:Tabel9.db");
        stmt = c.createStatement();
        //si efectuam update-ul la scor
        c.setAutoCommit(false);
        stmt = c.createStatement();
        String sql = "UPDATE InviziMaze set Levelul = "+score+";";//asta ar trebui sa seteze nou scor
        stmt.execute(sql);
        c.commit();
    }

    public int ReadLVLFromDB(){
        int lvl = 1;
        Connection c= null;
        Statement stmt = null;
        try{
            c = DriverManager.getConnection("jdbc:sqlite:Tabel9.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM InviziMaze");
            while ( rs.next() ) {
                lvl = rs.getInt("Levelul");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lvl;
    }
    private int ReadMusicFrimDB() {
        int Muz = 1;
        Connection c= null;
        Statement stmt = null;
        try{
            c = DriverManager.getConnection("jdbc:sqlite:Tabel9.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM InviziMaze");
            while ( rs.next() ) {
                Muz = rs.getInt("MUTE");//activ pe 0
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Muz;
    }



}
