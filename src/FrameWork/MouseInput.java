package FrameWork;

import PaooGame.GameWindow.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import static java.lang.Thread.sleep;

public class MouseInput implements MouseListener {
    Game game;
    public MouseInput(Game game)
    {
        super();
        this.game = game;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        System.out.println(e.getX()+" "+e.getY());
        if(game.state.getClass() == Main_Menu.class)
        {
            if(mx >= 384 && mx <= 616 && my >= 170 && my <= 239)
            {
                State state = new Game_Menu(game);
                game.Set_state(state);
            }
            if(mx >= 384 && mx <= 616 && my >= 250 && my <= 320)
            {
                State state = new Score(game);
                game.Set_state(state);
            }
            if(mx >= 384 && mx <= 616 && my >= 425 && my <= 484)
            {
                System.exit(0);
            }

        }


    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(game.state.getClass() == Main_Menu.class) {
            if (mx >= 384 && mx <= 616 && my >= 414 && my <= 484) {
                if (e.isConsumed()) {
                    System.exit(0);
                }
            }
        }

    }


    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        System.out.println(e.getX()+" "+e.getY());
        if(game.state.getClass() == Main_Menu.class)
        {
            if(mx >= 384 && mx <= 616 && my >= 170 && my <= 239)
            {
                if(e.isConsumed()) {
                    this.game.removeMouseListener(this);
                    //State state = new Game_Menu(game);
                    //game.Set_state(state);
                }
            }
            if(mx >= 384 && mx <= 616 && my >= 250 && my <= 320)
            {
                if(e.isConsumed()) {
                    System.out.println("Scor?");
                    this.game.removeMouseListener(this);
                    State state = new Score(game);
                    game.Set_state(state);
                }
            }
            if(mx >= 384 && mx <= 616 && my >= 170 && my <= 239)
            {
                if(e.isConsumed()) {

                }
            }
        }
        if(game.state.getClass() == Game_Menu.class){//daca ne aflam in al 2-lea meiu
            System.out.println("Stare Game_menu");
            if(mx >= 371 && mx <= 631 && my >= 141 && my <= 205)//cordinate CONTINUE GAME//ne duce la noapte
            {

                System.out.println("START PRESSED");
                game.removeMouseListener(this);
                game.Get_state().Reset();
                State state = new Room(game);
                game.Set_state(state);

            }
            if(mx >= 371 && mx <= 631 && my >= 219 && my <= 289)//cordinate RESTAT GAME
            {
                System.out.println("START PRESSED");
                game.removeMouseListener(this);
                game.Get_state().Reset();
                try {
                    game.GetLVLIntoDB(1);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                game.LVL = 1;//restart
                game.Score  = 0;//resetam scorul
                //game.insertScoreIntoDB(0);
                State state = new Room(game);
                game.Set_state(state);
            }
            if(mx >= 371 && mx <= 631 && my >= 306 && my <= 365)//cordinate MUTE GAME
            {
                System.out.println("MUTE");
                if(game.Music == 0){
                    game.sound.Stop("src/ress/muzica.wav");
                try {
                    game.GetMusicIntoDB(game.Music);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                }
            }
            if(mx >= 371 && mx <= 631 && my >= 384 && my <= 424)//cordinate BACK
            {
                    game.removeMouseListener(this);
                    //game.Get_state().Reset();
                    State state = new Main_Menu(game);
                    game.Set_state(state);
            }
            System.out.println(e.getX()+" "+e.getY());
        }
        if(game.state.getClass() == Score.class)
        {
            if(mx < 10 && my < 10)
            {
                game.removeMouseListener(this);
                game.Get_state().Reset();
                State state = new Main_Menu(game);
                game.Set_state(state);
            }
        }

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
