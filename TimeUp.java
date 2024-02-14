/*  
    6413104 Panfa Tonsomboon
    6413111 Wisara Wongrattanapipat
    6413213 Tawanwad Onnom 
    6413222 Phumed Thumtechanon
 */
package Project3_213;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TimeUp extends JFrame {

    private JLabel label;
    private String path = "src/main/Java/Project3_213/images/";
    private int frameWidth = 1300, frameHeight = 700;

    private int mode, item, score;
    private String N;
    private Music M;
    private int volume;

    private ImageIcon end, p, h;
    private MyButton play, home;
    private JLabel text;

    public TimeUp(Music a, String n, int m, int i, int s) {

        M = a;
        N = n;
        mode = m;
        item = i;
        score = s;
        setTitle("CO CHEF - TIME UP");
        setBounds(0, 0, frameWidth, frameHeight);

        //background
        setContentPane(label = new JLabel());
        end = new MyImageIcon(path + "TimesUp.png").resize(1300, 700);
        label.setIcon(end);
        label.setLayout(null);

        //text
        text = new JLabel();
        text.setText(N + "'s SCORE : " + score);
        text.setBounds(380, 315, 600, 80);
        text.setFont(new Font("Italic", Font.BOLD, 50));

        //button
        p = new MyImageIcon(path + "PlayButton.png").resize(400, 90);
        play = new MyButton(p, 255, 400, 400, 90);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GamePlay2(M, N, mode, item);
            }
        });
        h = new MyImageIcon(path + "HomeButton.png").resize(400, 90);
        home = new MyButton(h, 655, 400, 400, 90);
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                M.change_home();
                M.getSoundEffect().currentVolume = volume;
                if (M.getSoundEffect().currentVolume == 0) {
                    M.getSoundEffect().currentVolume = -80;
                } else if (M.getSoundEffect().currentVolume == 100) {
                    M.getSoundEffect().currentVolume = 6;
                } else if (M.getSoundEffect().currentVolume < 100 && M.getSoundEffect().currentVolume >= 90) {
                    M.getSoundEffect().currentVolume = -2;
                } else if (M.getSoundEffect().currentVolume < 90 && M.getSoundEffect().currentVolume >= 80) {
                    M.getSoundEffect().currentVolume = -10;
                } else if (M.getSoundEffect().currentVolume < 80 && M.getSoundEffect().currentVolume >= 70) {
                    M.getSoundEffect().currentVolume = -18;
                } else if (M.getSoundEffect().currentVolume < 70 && M.getSoundEffect().currentVolume >= 60) {
                    M.getSoundEffect().currentVolume = -26;
                } else if (M.getSoundEffect().currentVolume < 60 && M.getSoundEffect().currentVolume >= 50) {
                    M.getSoundEffect().currentVolume = -34;
                } else if (M.getSoundEffect().currentVolume < 50 && M.getSoundEffect().currentVolume >= 40) {
                    M.getSoundEffect().currentVolume = -42;
                } else if (M.getSoundEffect().currentVolume < 40 && M.getSoundEffect().currentVolume >= 30) {
                    M.getSoundEffect().currentVolume = -50;
                } else if (M.getSoundEffect().currentVolume < 30 && M.getSoundEffect().currentVolume >= 20) {
                    M.getSoundEffect().currentVolume = -58;
                } else if (M.getSoundEffect().currentVolume < 20 && M.getSoundEffect().currentVolume >= 10) {
                    M.getSoundEffect().currentVolume = -66;
                } else if (M.getSoundEffect().currentVolume < 10 && M.getSoundEffect().currentVolume > 0) {
                    M.getSoundEffect().currentVolume = -74;
                }
                new Home(M);
            }
        });

        //add
        label.add(play);
        label.add(home);
        label.add(text);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLayout(null);
        setVisible(true);
    }

}
