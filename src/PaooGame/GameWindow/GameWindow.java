package PaooGame.GameWindow;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    public static JFrame frame;//pentru a putea adauga text box urile
    private static GameWindow instance = null;//Singleton
    private GameWindow(int width,int height,String title,Game game)//constructor
    {
        game.setPreferredSize(new Dimension(width, height));
        game.setMaximumSize(new Dimension(width, height));
        game.setMinimumSize(new Dimension(width, height));

        frame= new JFrame(title);
        /// Seteaza dimensiunile ferestrei in pixeli


        frame.add(game);
        frame.pack();

        //wndFra.setSize(wndWidth, wndHeight);
        /// Operatia de inchidere (fereastra sa poata fi inchisa atunci cand
        /// este apasat butonul x din dreapta sus al ferestrei). Totodata acest
        /// lucru garanteaza ca nu doar fereastra va fi inchisa ci intregul
        /// program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /// Avand in vedere ca dimensiunea ferestrei poate fi modificata
        /// si corespunzator continutul actualizat (aici ma refer la dalele
        /// randate) va recomand sa constrangeti deocamdata jucatorul
        /// sa se joace in fereastra stabilitata de voi. Puteti reveni asupra
        /// urmatorului apel ulterior.
        frame.setResizable(false);
        /// Recomand ca fereastra sa apara in centrul ecranului. Pentru orice
        /// alte pozitie se va apela "wndFrame.setLocation(x, y)" etc.
        frame.setLocationRelativeTo(null);
        /// Implicit o fereastra cand este creata nu este vizibila motiv pentru
        /// care trebuie setata aceasta proprietate
        frame.setVisible(true);

        game.start();
    }
    public static GameWindow GetInstance(int width,int height,String title,Game game)
    {
        if(instance == null)
        {
            instance = new GameWindow(width,height,title,game);

        }
        return instance;
    }
    public static void Reset(){
        instance = null;
    }
}
