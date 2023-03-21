package FrameWork;

import Objects.BlueHairedGirl;
import Objects.Player;
import Objects.Projectile;
import PaooGame.GameWindow.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import static java.awt.event.KeyEvent.*;


public class Input extends KeyAdapter {
    Game game;
    ObjectHandler objectHandler;

    public Input(Game game)//constuctor
    {
        this.game = game;
        System.out.println("->Initializat");
        this.objectHandler = this.game.handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.game.state.getClass() == InGame.class) {
            int key = e.getKeyCode();

            for (int i = 0; i < objectHandler.object.size(); i++) {
                GameObject tempObj = objectHandler.object.get(i);//obiectul curent

                if (tempObj.getId() == ObjectId.Player)//daca este player
                {
                    //System.out.println("Avem un player");
                    // if(key == VK_D || key == KeyEvent.VK_RIGHT)//daca se alege deplasare drepata
                    // {
                    //     tempObj.setVelX(5);//viteza se incrememnteaza
                    // }
                    switch (key) {
                        case (VK_D):
                            tempObj.setVelX(5);//viteza se incrememnteaza
                            break;
                        case (VK_RIGHT):
                            tempObj.setVelX(5);
                            break;
                        case VK_A:
                            tempObj.setVelX(-5);
                            break;
                        case VK_LEFT:
                            tempObj.setVelX(-5);
                            break;
                        case VK_UP:
                            if (!tempObj.jumping) {
                                tempObj.setVelY(-15);
                                tempObj.setJumping(true);//saritura
                            } else {
                                tempObj.falling = true;
                            }
                            break;
                        case VK_W:
                            if (!tempObj.jumping) {
                                tempObj.setVelY(-15);
                                tempObj.setJumping(true);//saritura
                            }
                        {
                            tempObj.falling = true;
                        }
                        break;
                        case VK_DOWN:
                            //tempObj.setVelY(5);
                            break;
                        case VK_S:
                            //empObj.setVelY(5);
                            break;
                        case VK_SPACE: {
                            Player tempObj1 = (Player) tempObj;//casting la palyer
                            int prj_vel_dir;
                            if (tempObj1.GetFacing() == false) {
                                prj_vel_dir = -1;
                            } else {
                                prj_vel_dir = 1;
                            }//set directie
                            if(prj_vel_dir == 1) {
                                objectHandler.addObject(new Projectile(tempObj.getX() + tempObj.width + 10, tempObj.getY(), ObjectId.Projectile, 16, 16, 10 * prj_vel_dir));
                            }
                            if(prj_vel_dir == -1)
                            {
                                objectHandler.addObject(new Projectile(tempObj.getX() - 30, tempObj.getY(), ObjectId.Projectile, 16, 16, 10 * prj_vel_dir));
                            }
                        }
                        break;
                        default:
                            // code block
                    }
                }
                if (tempObj.getId() == ObjectId.BlueHairedGirl) {
                    BlueHairedGirl cast_object = (BlueHairedGirl) tempObj;
                    if (key == VK_N && cast_object.MAX_LINES > 1)//n de la next
                    {
                        BlueHairedGirl tempObj_cast = (BlueHairedGirl) tempObj;
                        cast_object.MAX_LINES--;
                        tempObj_cast.curent_line++;//se incrementeaza

                    }
                }
            }

            if (key == KeyEvent.VK_ENTER) {//il vom folosi pentru dialog
                //System.out.println("->Apasat esc");
                //System.exit(1);
            }
        }
        if (this.game.Get_state().getClass() == Room.class) {
            int key = e.getKeyCode();

            for (int i = 0; i < objectHandler.object.size(); i++) {
                GameObject tempObj = objectHandler.object.get(i);//obiectul curent

                if (tempObj.getId() == ObjectId.Player)//daca este player
                {
                    switch (key) {
                        case (VK_D):
                            tempObj.setVelX(5);//viteza se incrememnteaza
                            break;
                        case (VK_RIGHT):
                            tempObj.setVelX(5);
                            break;
                        case VK_A:
                            tempObj.setVelX(-5);
                            break;
                        case VK_LEFT:
                            tempObj.setVelX(-5);
                            break;
                        case VK_UP:
                            tempObj.setVelY(-5);
                            break;
                        case VK_W:
                            tempObj.setVelY(-5);
                            break;
                        case VK_DOWN:
                            tempObj.setVelY(5);
                            break;
                        case VK_S:
                            tempObj.setVelY(5);
                            break;
                        default:
                            // code block
                    }
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.game.state.getClass() == InGame.class) {
            int key = e.getKeyCode();

            for (int i = 0; i < objectHandler.object.size(); i++) {
                GameObject tempObj = objectHandler.object.get(i);//obiectul curent

                if (tempObj.getId() == ObjectId.Player)//daca este player
                {
                    if (key == KeyEvent.VK_D || key == KeyEvent.VK_A || key == VK_LEFT || key == VK_RIGHT) {
                        tempObj.setVelX(0);
                    }
                    if (key == KeyEvent.VK_W)//daca se elibereaza cheia de up
                    {
                        tempObj.setVelY(0);//se trece in cadere
                    }
                }
            }

            if (key == VK_ESCAPE) {//daca apasa escape il ducem la game menu NU este salvat progresul pe nivel
                game.removeKeyListener(this);
                game.Get_state().Reset();
                State state = new Game_Menu(game);
                game.Set_state(state);
            }
        }
        if (this.game.Get_state().getClass() == Room.class) {
            int key = e.getKeyCode();

            for (int i = 0; i < objectHandler.object.size(); i++) {
                GameObject tempObj = objectHandler.object.get(i);//obiectul curent

                if (tempObj.getId() == ObjectId.Player)//daca este player
                {
                    System.out.println(tempObj.getX()+" "+tempObj.getY());
                    switch (key) {
                        case (VK_D):
                            tempObj.setVelX(0);//viteza se incrememnteaza
                            break;
                        case (VK_RIGHT):
                            tempObj.setVelX(0);
                            break;
                        case VK_A:
                            tempObj.setVelX(0);
                            break;
                        case VK_LEFT:
                            tempObj.setVelX(0);
                            break;
                        case VK_UP:
                            tempObj.setVelY(0);
                            break;
                        case VK_W:
                            tempObj.setVelY(0);
                            break;
                        case VK_DOWN:
                            tempObj.setVelY(0);
                            break;
                        case VK_S:
                            tempObj.setVelY(0);
                            break;
                        case VK_ENTER:
                            if(tempObj.getX() > 450 && tempObj.getX() < 540 && tempObj.getY() < 380)
                            {
                                System.out.println("START PRESSED");
                                game.removeKeyListener(this);
                                game.Get_state().Reset();
                                State state = new InGame(game);
                                game.Set_state(state);
                            }
                            break;
                        case VK_ESCAPE: {
                            game.removeKeyListener(this);
                            game.Get_state().Reset();
                            State state = new Game_Menu(game);
                            game.Set_state(state);
                        }
                            break;
                        default:
                            // code block
                    }
                }
            }

        }

    }
}

