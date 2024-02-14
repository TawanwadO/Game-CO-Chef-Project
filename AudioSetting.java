/*  
    6413104 Panfa Tonsomboon
    6413111 Wisara Wongrattanapipat
    6413213 Tawanwad Onnom 
    6413222 Phumed Thumtechanon
 */
package Project3_213;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.*;

public class AudioSetting extends JFrame implements ChangeListener {

    private JPanel panel;
    private JLabel contentpane, label;
    private JSlider music;
    private JButton back;
    private ImageIcon backButton;

    private String path = "src/main/Java/Project3_213/images/";
    private String filePath = "src/main/Java/Project3_213/";
    private String musicPath = "src/main/Java/Project3_213/music/";
    private String audio = filePath + "audio.txt";
    private Music M;
    //add
    private int volume;

    public AudioSetting(Music m) {
        //add
        try {
            Scanner scan = new Scanner(new File(audio));
            volume = scan.nextInt();
            scan.close();

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        M = m;
        setTitle("Audio Setting");
        setBounds(20, 20, 260, 340);

        panel = (JPanel) getContentPane();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        music = new JSlider(0, 100, volume);
        music.setBounds(55, 20, 130, 200);
        music.setBackground(Color.WHITE);
        music.setFont(new Font("Italic", Font.PLAIN, 15));
        music.setPreferredSize(new Dimension(400, 200));
        music.setPaintTicks(true);
        //music.setMinorTickSpacing(10);
        music.setPaintTrack(true);
        music.setMajorTickSpacing(25);
        music.setPaintLabels(true);
        music.setOrientation(SwingConstants.VERTICAL);
        music.addChangeListener(this);
        music.setBackground(new Color(255, 217, 222));

        back = new JButton();
        backButton = new MyImageIcon(path + "CloseButton.png").resize(90, 50);
        back.setBounds(85, 270, 90, 50);
        back.setBackground(new Color(0, 0, 0, 0));
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setIcon(backButton);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PrintWriter write = new PrintWriter(new File(audio));
                    write.println(music.getValue());
                    write.close();
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                dispose();
            }
        });

        label = new JLabel();
        label.setBounds(55, 240, 150, 25);
        label.setFont(new Font("Italic", Font.BOLD, 25));
        label.setText("Music : " + music.getValue());

        panel.add(music);
        panel.add(label);
        panel.add(back);
        panel.setBackground(new Color(255, 217, 222));

        setUndecorated(true);
        setBackground(new Color(255, 217, 222));
        setResizable(false);
        setSize(260, 350);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    //add
    public int getVolume() {
        return music.getValue();
    }
    //

    @Override
    public void stateChanged(ChangeEvent e) {
        // TODO Auto-generated method stub
        label.setText("Music : " + music.getValue());

        //add
        M.getSoundEffect().currentVolume = music.getValue();

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

        //System.out.println("volume: " + M.getSoundEffect().currentVolume);
        M.getSoundEffect().fc.setValue(M.getSoundEffect().currentVolume);
    }
};
