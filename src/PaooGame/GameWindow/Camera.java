package PaooGame.GameWindow;
import FrameWork.GameObject;

public class Camera {
    private float x,y;//coorconatele de unde incepe camera
    private float x_init;
    public Camera(float x, float y, float x_init)
    {
        this.x = x;
        this.y = y;
        this.x_init = x_init;
    }
    public void tick(GameObject player) {
        float aux = -player.getX() + Game.WIDTH / 4;
        if(aux < 0) {
            this.x = aux;//valoarea trebuie sa fie negativa pentru deplasare la stanga
            // game.Width/2 este ceea de ne focudeaza pe mijlocul ecranului player-ul
        }
        else
        {
            this.x = 0;
        }
        //this.y = -player.getY() + Game.HEIGHT/2;//...ar merge daca ar redimentsiona tot sau doar fundalul
        //System.out.println(player.getX());
        //System.out.println("");
    }
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }
}
