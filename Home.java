/*  
    6413104 Panfa Tonsomboon
    6413111 Wisara Wongrattanapipat
    6413213 Tawanwad Onnom 
    6413222 Phumed Thumtechanon
*/

package Project3_213;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class Home extends JFrame {

    private MyButton play, credit, tutorial, exit, audio;    
    
    private String path = "src/main/Java/Project3_213/images/";
    private String filePath = "src/main/Java/Project3_213/";
    private String audioFile = filePath + "audio.txt";
    private int frameWidth = 1300, frameHeight = 700;
    
    private JLabel label;
    private ImageIcon home, startButton, creditButton, tutorialButton, exitButton, speaker;
    private Music M;

    private int volume;
    
    public Home(Music m) {

        M=m;
        setTitle("CO CHEF - Home");
        setBounds(0, 0, frameWidth, frameHeight);
        Scanner scan;
        try {
            scan = new Scanner(new File(audioFile));
            volume = scan.nextInt();
            scan.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
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

        //System.out.println("Home volume: " + M.getSoundEffect().currentVolume);
        M.getSoundEffect().fc.setValue(M.getSoundEffect().currentVolume);
        
        //background
        setContentPane(label = new JLabel());
        home = new MyImageIcon(path + "Home.png").resize(1300, 700);
        label.setIcon(home);
        label.setLayout(null);
        
        //button 
        startButton = new MyImageIcon(path + "StartButton.png").resize(250, 90);
        play = new MyButton(startButton, 530, 235, 240, 60);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login(M, "");
            }
        });
        creditButton = new MyImageIcon(path + "CreditButton.png").resize(250, 90);
        credit = new MyButton(creditButton, 530, 305, 240, 60);
        credit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Credit(M);
            }
        });
        tutorialButton = new MyImageIcon(path + "TutorialButton.png").resize(250, 90);
        tutorial = new MyButton(tutorialButton, 530, 375, 240, 60);
        tutorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Tutorial(M);
            }
        });
        exitButton = new MyImageIcon(path + "ExitButton.png").resize(250, 90);
        exit = new MyButton(exitButton, 530, 445, 240, 60);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                System.exit(0);
            }
        });
        speaker = new MyImageIcon(path + "SpeakerButton.png").resize(100, 100);
        audio = new MyButton(speaker, 1170, 550, 100, 100);
        audio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                AudioSetting audioSetting = new AudioSetting(M);
            }
        });

        label.add(play);
        label.add(credit);
        label.add(tutorial);
        label.add(exit);
        label.add(audio);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLayout(null);
        setVisible(true);
        setResizable(false);
    }

};

class MyButton extends JButton {
    
    public MyButton() {}
    public MyButton(ImageIcon picture, int x, int y, int a, int b) 
    {
        setBounds(x, y, a, b); //set transparent button
        setBackground(new Color(0, 0, 0, 0));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setIcon(picture);
    }
    
};