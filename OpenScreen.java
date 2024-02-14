/*  
    6413104 Panfa Tonsomboon
    6413111 Wisara Wongrattanapipat
    6413213 Tawanwad Onnom 
    6413222 Phumed Thumtechanon
 */
package Project3_213;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class OpenScreen extends JFrame {

    private JProgressBar bar = new JProgressBar(0, 100);
    private JLabel contentpane;
    private ImageIcon background; //can't resize
    private String path = "src/main/Java/Project3_213/images/";
    private int frameWidth = 1300, frameHeight = 700;
    private Music M;

    //add
    private int volume;
    private String filePath = "src/main/Java/Project3_213/";
    private String audioFile = filePath + "audio.txt";
    private String audio = filePath + "audio.txt";

    public OpenScreen(Music m) {

        M = m;
        try {
            PrintWriter write = new PrintWriter(new File(audioFile));
            write.println(50);
            write.close();
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //add
        try {
            Scanner scan = new Scanner(new File(audio));
            volume = scan.nextInt();
            scan.close();

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        M.getSoundEffect().currentVolume = volume;

        if (M.getSoundEffect().currentVolume == 0) {
            M.getSoundEffect().currentVolume = -80;
        } else if (M.getSoundEffect().currentVolume == 100) {
            M.getSoundEffect().currentVolume = 6;
        } else if (M.getSoundEffect().currentVolume < 100 && M.getSoundEffect().currentVolume >= 90) {
            M.getSoundEffect().currentVolume = 0;
        } else if (M.getSoundEffect().currentVolume < 90 && M.getSoundEffect().currentVolume >= 80) {
            M.getSoundEffect().currentVolume = -6;
        } else if (M.getSoundEffect().currentVolume < 80 && M.getSoundEffect().currentVolume >= 70) {
            M.getSoundEffect().currentVolume = -12;
        } else if (M.getSoundEffect().currentVolume < 70 && M.getSoundEffect().currentVolume >= 60) {
            M.getSoundEffect().currentVolume = -18;
        } else if (M.getSoundEffect().currentVolume < 60 && M.getSoundEffect().currentVolume >= 50) {
            M.getSoundEffect().currentVolume = -24;
        } else if (M.getSoundEffect().currentVolume < 50 && M.getSoundEffect().currentVolume >= 40) {
            M.getSoundEffect().currentVolume = -30;
        } else if (M.getSoundEffect().currentVolume < 40 && M.getSoundEffect().currentVolume >= 30) {
            M.getSoundEffect().currentVolume = -36;
        } else if (M.getSoundEffect().currentVolume < 30 && M.getSoundEffect().currentVolume >= 20) {
            M.getSoundEffect().currentVolume = -40;
        } else if (M.getSoundEffect().currentVolume < 20 && M.getSoundEffect().currentVolume >= 10) {
            M.getSoundEffect().currentVolume = -46;
        } else if (M.getSoundEffect().currentVolume < 10 && M.getSoundEffect().currentVolume > 0) {
            M.getSoundEffect().currentVolume = -52;
        }

        M.getSoundEffect().fc.setValue(M.getSoundEffect().currentVolume);
        //System.out.println(M.getSoundEffect().currentVolume);

        setTitle("CO CHEF Loading...");
        setBounds(0, 0, frameWidth, frameHeight);

        setContentPane(contentpane = new JLabel());
        background = new MyImageIcon(path + "Loading.png").resize(frameWidth, frameHeight);
        contentpane.setIcon(background);
        contentpane.setLayout(null);

        bar.setValue(0);
        bar.setBounds(0, 625, 1300, 40);
        bar.setStringPainted(true);
        bar.setFont(new Font("Consolas", Font.BOLD, 25));
        bar.setForeground(Color.white);
        bar.setBackground(new Color(255, 217, 222));
        bar.setBorderPainted(false);
        contentpane.add(bar);

        setSize(frameWidth, frameHeight);
        //setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        fill();

    }

    public void fill() {
        int counter = 0;

        while (counter <= 100) {
            bar.setValue(counter);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            counter++;
        }
        dispose();
        new Home(M);
    }

};
