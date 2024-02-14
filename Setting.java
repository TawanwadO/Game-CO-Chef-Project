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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Setting extends JFrame { //implements ActionListener
    
    private int ModeSetting;

    private JLabel label;    
    private String path = "src/main/Java/Project3_213/images/";
    private int frameWidth = 1300, frameHeight = 700;
    
    private JComboBox cbox;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem homeItem, exitItem;
    private MyButton back, next;
    private ImageIcon mode, backButton, nextButton;
    private Music M;
    private String N;

    public Setting(Music m, String n) {

        M=m;
        N=n;
        setTitle("CO CHEF - MODE");
        setBounds(0, 0, frameWidth, frameHeight);
        
        //background
        setContentPane(label = new JLabel());
        mode = new MyImageIcon(path + "mode.png").resize(1300, 640);
        label.setIcon(mode);
        label.setLayout(null);
        
        //jcombobox
        String[] level = {"Very Easy", "Easy", "Normal", "Hard", "Very Hard"};
        cbox = new JComboBox(level);
        cbox.setBounds(720, 270, 455, 50);
        cbox.setBackground(Color.WHITE);
        cbox.setFont( new Font("Consolas", Font.PLAIN, 30) );
        
        //button
        backButton = new MyImageIcon(path + "BackButton.png").resize(187, 97);
        back = new MyButton(backButton, 20, 530, 187, 97);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login(M, N);
            }
        });
        nextButton = new MyImageIcon(path + "NextButton.png").resize(187, 97);
        next = new MyButton(nextButton, 1093, 530, 187, 97);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(cbox.getSelectedItem().toString()) {
                    case "Very Easy" : ModeSetting = 1; break;
                    case "Easy"      : ModeSetting = 2; break;
                    case "Normal"    : ModeSetting = 3; break;
                    case "Hard"      : ModeSetting = 4; break;
                    case "Very Hard" : ModeSetting = 5; break;
                }
                new Item( M, N, ModeSetting );
                dispose();
            }
        });

        //menu bar
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        homeItem = new JMenuItem("Home");
        exitItem = new JMenuItem("Exit");
        homeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (e.getSource() == homeItem) {
                    new Home(M);
                    dispose();
                }
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (e.getSource() == exitItem) {
                    System.exit(0);
                }
            }
        });
        fileMenu.add(homeItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        label.add(back);
        label.add(next);
        label.add(cbox);
        setJMenuBar(menuBar);
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLayout(null);
        setVisible(true);
    }

};
