package Objects;

import FrameWork.GameObject;
import FrameWork.ObjectId;
import FrameWork.Texture;
import PaooGame.GameWindow.Animation;
import PaooGame.GameWindow.Camera;
import PaooGame.GameWindow.Game;
import PaooGame.GameWindow.ObjectHandler;

import java.awt.*;
import java.util.LinkedList;
import java.util.logging.Handler;

public class Coin extends GameObject {
        private Animation coin_spin;
        Texture tex  =  Game.getInstance();

        public static int Score = 0;//scorul initializat cu 0

        public Coin(float x, float y, ObjectId id, int width, int height) {
                super(x, y, id, width, height);
                coin_spin = new  Animation(10,tex.coin_loop[0],tex.coin_loop[1],tex.coin_loop[2]);

        }

        @Override
        public void tick(LinkedList<GameObject> object) {

                coin_spin.RunAnimation();
        }

        @Override
        public void render(Graphics g) {

                coin_spin.DrawAnimation(g,(int)this.x,(int)this.y);

        }

        @Override
        public Rectangle getBounds() {
                return new Rectangle((int)this.x,(int)this.y,this.width,this.height);
        }
}
